package project.gui.top.instructionEditor;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.gui.top.instructionEditor.InstructionEditorFooter.InstructionEditorSubmitButton;
import project.gui.top.instructionEditor.body.InstructionEditorBody;

public class InstructionEditor {


    public void setEditorWindow() {

        VBox scrollContainer = new VBox();
        Scene secondScene = new Scene(scrollContainer, 640, 720);
        VBox secondaryLayout = new VBox();
        secondaryLayout.setStyle("-fx-border-color: black");

        //add a scroll pane
        ScrollPane scrollPane = new ScrollPane(secondaryLayout);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);
        //force the scrollbar to scroll to the end
        secondaryLayout.heightProperty().addListener(e -> scrollPane.setVvalue(1d));

        //container for all the elements that need to be scrolled through
        secondaryLayout.getChildren().addAll(
                InstructionEditorHeader.setHeader(),
                InstructionEditorBody.createBody(),
                InstructionEditorSubmitButton.createSubmitButton(),
                new Text(" "));

        //final container that contains the scrollbar and all the vertical elements
        scrollContainer.getChildren().addAll(secondaryLayout, scrollPane);

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
