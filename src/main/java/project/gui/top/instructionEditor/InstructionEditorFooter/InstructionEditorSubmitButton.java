package project.gui.top.instructionEditor.InstructionEditorFooter;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import project.gui.leftSide.middleLeftSide.MiddleLeftSide;
import project.gui.rightSide.LowerRightSide.LowerRightSide;
import project.gui.top.Spacer;
import project.gui.top.instructionEditor.InstructionValidation;
import project.gui.top.instructionEditor.body.InstructionEditorBody;
import project.model.memory.Memory;

import java.util.List;

public class InstructionEditorSubmitButton {
    public static HBox createSubmitButton() {
        //submit button and it's container=============================================================================
        //container is used to make the button centered
        Button editorSubmitButton = new Button("Submit");
        editorSubmitButton.setOnAction(e -> {
            //check if the instructions and the rest of the input variables are valid
            InstructionValidation.validateEditorBody();
            //ignore invalid ones (reset them to 0), and save the valid ones into the application
            submitValuesAndStartTheApp();
            Stage stage = (Stage) editorSubmitButton.getScene().getWindow();

            //reset the main window to correct values
            LowerRightSide.set();
            //application can now run
            MiddleLeftSide.buttonNext.setDisable(false);
            MiddleLeftSide.prevButton.setDisable(false);
            MiddleLeftSide.fetchAndExecuteButton.setDisable(false);
            stage.close();
        });

        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(
                Spacer.createSpacer(),
                editorSubmitButton,
                Spacer.createSpacer());
        //=============================================================================================================

        return buttonContainer;
    }

    private static void submitValuesAndStartTheApp() {

        List<Node> bodyLines = InstructionEditorBody.bodyLines.getChildren();
        TextField memoryAddress = new TextField();
        TextField value = new TextField();
        String selectedInstruction = "";


        //get the whole editor line
        for (Node bodyLine : bodyLines) {
            //check that it isn't the final row - which is new line button
            if (bodyLine.getClass().isInstance(new HBox())) {
                HBox line = (HBox) bodyLine;
                List<Node> lineNodes = line.getChildren();
                //get specific elements of the line

                int i = 0;//counter that counts textfields. every second textfield is the value
                for (Node lineNode : lineNodes) {
                    if (i % 2 == 0) {//the first textfield found is always the memory location
                        //the first occurrence of a textfield is the memory address ====================================
                        if (lineNode.getClass().isInstance(new TextField())
                                && !lineNode.getClass().isInstance(new Region())) {
                            memoryAddress = (TextField) lineNode;
                        }
                        //==============================================================================================
                    } else {//the second textfield found is always the instruction value
                        //second textfield is the value ================================================================
                        if (lineNode.getClass().isInstance(new TextField())
                                && !lineNode.getClass().isInstance(new Region())) {
                            value = (TextField) lineNode;
                        }
                    }
                    i++;

                    //get the selected instruction
                    if (lineNode.getClass().isInstance(new ChoiceBox<>())
                            && !lineNode.getClass().isInstance(new Region())) {
                        ChoiceBox<String> instructions = (ChoiceBox) lineNode;
                        selectedInstruction = instructions.getSelectionModel().getSelectedItem();
                    }

                }
                if (selectedInstruction != "Select An Instruction...")
                    saveTheValuesIntoTheApplication(memoryAddress.getText(), value.getText(), selectedInstruction);
            }
        }
    }

    private static void saveTheValuesIntoTheApplication(String memoryAddress, String value, String instruction) {

        String opCode = "-1";

        switch (instruction.toLowerCase()) {
            case "adda": //adda.opcode = HEX(1)
                opCode = "10";
                break;
            case "lda": //lda.opcode = HEX(2)
                opCode = "20";
                break;
            case "anda": //anda.opcode = HEX(3)
                opCode = "30";
                break;
            case "sta": //sta.opcode = HEX(4)
                opCode = "40";
                break;
            case "shra": //shra.opcode = HEX(5)
                opCode = "50";
                break;
            case "jmpz": //jmpz.opcode = HEX(6)
                opCode = "60";
                break;
            case "jmp": //jmp.opcode = HEX(7)
                opCode = "70";
                break;
            case "coma": //com.opcode = HEX(8)
                opCode = "80";
                break;
            default:
                break;
        }

        //if everything is correct, write the data  into the memory
        if (opCode != "-1" && !value.contains("val")) {
            Memory.getInstance().getLocationAndContent().put(memoryAddress, opCode + value);
        }
        if (opCode == "50" || opCode == "80") {
            Memory.getInstance().getLocationAndContent().put(memoryAddress, opCode + "000000");
        }
    }
}

