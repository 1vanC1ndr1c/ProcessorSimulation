package project.gui.top.instructionEditor;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InstructionEditorBody {

    public static void handleEditorLineInput() {
    }

    public static void createEditorLine(String memoryLocationString) {
        HBox editorLine = new HBox();
        TextField memoryLocation = new TextField();
        memoryLocation.setText(memoryLocationString);

        TextField values = new TextField("values...");

        ChoiceBox<String> instructions = new ChoiceBox<>();

        instructions.getItems().addAll("AddA", "LdA", "AndA", "StA", "ShrA", "JmpZ",
                "Jmp", "ComA", "Select An Instruction...");
        instructions.setValue("Select An Instruction...");
        instructions.setOnAction(e -> {
            System.out.println("wooot");
        });

        editorLine.getChildren().addAll(memoryLocation,
                InstructionEditor.createSpacer(), instructions,
                InstructionEditor.createSpacer(), values,
                InstructionEditor.createSpacer());

        InstructionEditor.editorInput.getChildren().add(editorLine);
    }
}
