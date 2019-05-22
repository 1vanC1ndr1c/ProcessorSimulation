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

        Button button = new Button("Instruction Editor");
        button.setOnAction(e -> new InstructionEditor().setEditorWindow());

        topBox.getChildren().addAll(button, new Text(" "), InstructionOpCodes.setInstructionList());

        borderPane.setTop(topBox);
    }
}