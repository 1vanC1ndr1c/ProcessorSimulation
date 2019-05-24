package project.gui.top;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import project.gui.top.instructionEditor.InstructionEditor;

public class Top {

    private static HBox topBox = new HBox();

    public static void set(BorderPane borderPane) {

        topBox.setStyle("-fx-border-color: black");
        topBox.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.01));

        Button buttonInstructionEditor = new Button("Instruction Editor");
        buttonInstructionEditor.setOnAction(e -> new InstructionEditor().setEditorWindow());

        Button resetButton = ResetButton.set();

        topBox.getChildren().addAll(buttonInstructionEditor, new Text(" "),
                InstructionOpCodes.setInstructionList(),
                InstructionEditor.createSpacer(),
                resetButton,
                InstructionEditor.createSpacer());

        borderPane.setTop(topBox);
    }
}