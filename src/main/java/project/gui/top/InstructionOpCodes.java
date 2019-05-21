package project.gui.top;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class InstructionOpCodes {

    public static ComboBox setInstructionList() {
        //when resizing, adjust the graphical elements
        ObservableList<String> instructions =
                FXCollections.observableArrayList(
                        "'1' = AddA",
                        "'2' = LdA",
                        "'3' = AndA",
                        "'4' = StA",
                        "'5' = ShrA",
                        "'6' = JmpZ",
                        "'7' = Jmp",
                        "'8' = ComA",
                        "'0' = Undefined"
                );
        ComboBox comboBox = new ComboBox(instructions);
        comboBox.setPromptText("OpCodes");

        return comboBox;
    }
}
