package project.gui.top.instructionEditor.body;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.gui.top.Spacer;
import project.logic.HexHandler;

public class InstructionEditorBody {

    public static VBox bodyLines = new VBox();
    private static String currentMemoryLocation = "000000";
    private static Integer currentLine = 0;

    public static VBox createBody() {

        currentLine = 0;
        currentMemoryLocation = "000000";
        bodyLines.getChildren().clear();

        //initially, set first 10 lines
        if (currentLine == 0) {
            bodyLines.getChildren().add(createEditorLine(currentMemoryLocation));
            for (int i = 0; i < 10; i++) {
                currentMemoryLocation = HexHandler.addNumbers(currentMemoryLocation, "1");
                currentMemoryLocation = currentMemoryLocation.substring(2);
                bodyLines.getChildren().add(createEditorLine(currentMemoryLocation));
            }
        }

        //new line button ==============================================================================================
        Button editorSubmitButton = new Button("New Line");
        editorSubmitButton.setOnAction(e -> {
            currentMemoryLocation = HexHandler.addNumbers(currentMemoryLocation, "1");
            currentMemoryLocation = currentMemoryLocation.substring(2);
            bodyLines.getChildren().remove(editorSubmitButton);
            bodyLines.getChildren().add(createEditorLine(currentMemoryLocation));
            bodyLines.getChildren().add(editorSubmitButton);
        });
        //==============================================================================================================

        bodyLines.getChildren().add(editorSubmitButton);
        return bodyLines;
    }

    private static HBox createEditorLine(String memoryLocationString) {

        //elements of the line: memoryLocation | selected instr. | '$' value
        HBox editorLine = new HBox();
        TextField memoryLocation = new TextField(memoryLocationString);

        ChoiceBox<String> instructions = new ChoiceBox<>();
        instructions.getItems().addAll("AddA", "LdA", "AndA", "StA", "ShrA", "JmpZ",
                "Jmp", "ComA", "Select An Instruction...");
        instructions.setValue("Select An Instruction...");

        HBox dollarSignContainer = new HBox();
        Text dollarSign = new Text("$");
        dollarSignContainer.getChildren().add(dollarSign);
        dollarSignContainer.setStyle("-fx-padding: 5 0 0 0;");

        TextField value = new TextField("value...");

        editorLine.getChildren().addAll(
                memoryLocation,
                Spacer.createSpacer(), instructions,
                Spacer.createSpacer(), dollarSignContainer, value,
                Spacer.createSpacer());

        return editorLine;
    }
}
