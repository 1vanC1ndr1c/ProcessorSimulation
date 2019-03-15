package project.gui.leftSide;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.gui.Validation.Validator;
import project.gui.rightSide.LowerRightSide.LowerRightSide;
import project.gui.rightSide.UpperRightSide;
import project.logic.InstructionPicker;
import project.model.memory.Memory;
import project.model.processor.ControlUnit;
import project.model.processor.ProgramCounter;

public class LeftSide {

    public static void set(BorderPane borderPane) {

        VBox leftBox = new VBox();
        leftBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        leftBox.setStyle("-fx-border-color: black");
        leftBox.setPadding(new Insets(0.0, 10.0, 0.0, 10.0));

        //title
        Text leftText = new Text("Instructions:      Location:");
        leftBox.getChildren().add(leftText);


        GridPane instructionsGridPane = new GridPane();
        instructionsGridPane.setHgap(40);

        createInstructionRow("ADDA", 0, 0, true, instructionsGridPane);
        createInstructionRow("ANDA", 0, 1, true, instructionsGridPane);
        createInstructionRow("LDA", 0, 2, true, instructionsGridPane);
        createInstructionRow("STA", 0, 3, true, instructionsGridPane);
        createInstructionRow("JMPZ", 0, 4, true, instructionsGridPane);
        createInstructionRow("JMP", 0, 5, true, instructionsGridPane);
        createInstructionRow("SHRA", 0, 6, false, instructionsGridPane);
        createInstructionRow("COMA", 0, 7, false, instructionsGridPane);

        leftBox.getChildren().add(instructionsGridPane);

        borderPane.setLeft(leftBox);
    }

    public static void createInstructionRow(String instructionName, int column, int row, boolean isMemoryInstruction, GridPane gridPane) {

        TextField location = new TextField("000000");

        if (isMemoryInstruction == true) {
            location.setPrefWidth(80);
            gridPane.add(location, column + 1, row);
        }

        Button instructionButton = new Button(instructionName);
        instructionButton.setPrefWidth(70);

        instructionButton.setOnAction(e -> {
            Validator.validateInstructionLocation(gridPane);
            if (isMemoryInstruction == true) doTheInstruction(instructionButton.getText(), location.getText());
            else doTheInstruction(instructionButton.getText(), "is not memory instruction");
        });
        gridPane.add(instructionButton, column, row);
    }

    public static void doTheInstruction(String instructionName, String memoryLocation) {

        //make the instruction code that will be written into memory
        String opCode = InstructionPicker.retrunOpCode(instructionName.toLowerCase());
        String irrelevantBits = "0";
        if (memoryLocation.equals("is not memory instruction")) {
            memoryLocation = "000000";
        }
        String memoryValueOfInstruction = opCode + irrelevantBits + memoryLocation;

        Validator.validateAndSetProcessorComponentsData(UpperRightSide.componentsGridPane);
        String memoryLocationOfInstruction = ProgramCounter.getInstance().getValue();

        //save the instruction
        Memory.getInstance().getLocationAndContent().put(memoryLocationOfInstruction, memoryValueOfInstruction);

        //change the gui memory
        Validator.validateAndSetMemorySubmitData(Memory.getInstance().getLocationAndContent(), LowerRightSide.memoryGridPane);

        //execute the instruction
        ControlUnit.getInstance().start();
    }
}
