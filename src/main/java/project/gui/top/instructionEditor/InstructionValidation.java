package project.gui.top.instructionEditor;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import project.gui.top.instructionEditor.body.InstructionEditorBody;
import project.logic.HexHandler;

import java.util.List;

public class InstructionValidation {

    public static void validateEditorBody() {
        List<Node> bodyLines = InstructionEditorBody.bodyLines.getChildren();

        boolean validationFail = false;

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
                            TextField memoryAddress = (TextField) lineNode;
                            //validate memory
                            if (memoryAddress.getText().length() != 6) validationFail = true;
                            if (HexHandler.checkIfHex(memoryAddress.getText()) == false) validationFail = true;
                        }
                        //==============================================================================================

                    } else {//the second textfield found is always the instruction value
                        //second textfield is the value ================================================================
                        if (lineNode.getClass().isInstance(new TextField())
                                && !lineNode.getClass().isInstance(new Region())) {
                            TextField value = (TextField) lineNode;
                            if (value.getText().length() > 6) validationFail = true;

                            else {//check if the value is in the correct format (6 digit long hex number)
                                if (HexHandler.checkIfHex(value.getText()) == false) {
                                    //reset to default it not valid
                                    validationFail = true;
                                    value.setText("value...");
                                }
                                if (validationFail == false) {
                                    // if the number is correct, but does not have 6 digits, expand it to 6 digits
                                    while (value.getText().length() < 6)
                                        value.setText("0" + value.getText());
                                }
                            }
                        }
                    }
                    i++;
                }
            }
            validationFail = false;
        }
    }
}
