package project.gui.top.instructionEditor;

import javafx.scene.layout.*;
import javafx.scene.text.Text;
import project.gui.top.Spacer;

class InstructionEditorHeader {

    static HBox setHeader() {
        Text memoryLocation = new Text("Memory Location");
        Text instruction = new Text("Instruction");
        Text value = new Text("Value");

        memoryLocation.setStyle("-fx-border-color: black");
        instruction.setStyle("-fx-border-color: black");
        value.setStyle("-fx-border-color: black");

        HBox header = new HBox();
        header.getChildren().addAll(memoryLocation, Spacer.createSpacer(),
                instruction, Spacer.createSpacer(),
                value, Spacer.createSpacer());
        header.setStyle("-fx-border-color: black");

        return header;
    }
}

