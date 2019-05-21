package project.gui.top.instructionEditor;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;


public class InstructionEditor {

    public static VBox secondaryLayout = new VBox();
    public static VBox editorInput = new VBox();

    public static void setEditorWindow() {

        Scene secondScene = new Scene(secondaryLayout, 640, 720);

        Button editorSubmitButton = new Button("Submit");
        editorSubmitButton.setOnAction(e -> {
            InstructionEditorBody.handleEditorLineInput();
        });

        InstructionEditorBody.createEditorLine("00000000");
        secondaryLayout.getChildren().addAll(InstructionEditorHeader.setHeader(), editorInput, editorSubmitButton);
        secondaryLayout.setStyle("-fx-border-color: black");

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Instruction Editor");
        newWindow.setScene(secondScene);
        newWindow.show();
    }

    public static Region createSpacer() {
        Region spacer = new Region();
        // Make it always grow or shrink according to the available space
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
}
