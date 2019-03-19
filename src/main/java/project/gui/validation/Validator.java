package project.gui.validation;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import project.gui.rightSide.UpperRightSide;
import project.model.memory.Memory;
import project.model.processor.*;

import java.util.Map;

/**
 * Class that validates various input data from gui before
 * committing the changed values into java objects.
 */
public class Validator {

    public static void validateAndSetMemorySubmitData(Map<String, String> tmpMap, GridPane gridPane) {

        for (Map.Entry<String, String> entry : tmpMap.entrySet()) {
            //validate the new data and, if valid, save it into memory
            if (entry.getKey().length() == 6 && entry.getValue().length() == 8) {
                Memory.getInstance().getLocationAndContent().put(entry.getKey(), entry.getValue());
            }
        }
        //clear the old data
        gridPane.getChildren().clear();

        //set the new valid data into gui
        int i = 0;
        for (Map.Entry<String, String> entry : Memory.getInstance().getLocationAndContent().entrySet()) {
            gridPane.add(new Text(entry.getKey()), 0, i);
            gridPane.add(new TextField(entry.getValue()), 1, i);
            i++;
        }
    }


    public static void validateAndSetProcessorComponentsData(GridPane gridPane) {

        //search the gridpane for textfields
        for (Node node : gridPane.getChildren()) {
            if (node.getClass().isInstance(new TextField())) {
                TextField textfield = (TextField) node;
                String value = textfield.getText();
                int rowIndex = GridPane.getRowIndex(node);
                //validate the data
                switch (rowIndex) {
                    case 0:
                        if (value.length() == 8 || value.length() == 6)
                            Accumulator.getInstance().setValue(value);
                        break;
                    case 1:
                        if (value.length() == 8 || value.length() == 6)
                            ALU.getInstance().setValue(value);
                        break;
                    case 2:
                        if (value.length() == 1)
                            InstructionRegister.getInstance().setValue(value);
                        break;
                    case 3:
                        if (value.length() == 8 || value.length() == 6 || value.length() == 1)
                            InternalBus.getInstance().setValue(value);
                        break;
                    case 4:
                        if (value.length() == 6)
                            MemoryAddressRegister.getInstance().setValue(value);
                        break;
                    case 5:
                        if (value.length() == 8 || value.length() == 6 || value.length() == 1)
                            MemoryDataRegister.getInstance().setValue(value);
                        break;
                    case 6:
                        if (value.length() == 6)
                            ProgramCounter.getInstance().setValue(value);
                        break;
                    case 7:
                        if (value.length() == 8 || value.length() == 6)
                            TemporaryRegister.getInstance().setValue(value);
                        break;
                }
            }
        }
        //redraw the component values
        gridPane.getChildren().clear();
        UpperRightSide.loadComponents(gridPane);
    }

    public static boolean validateInstructionLocation(GridPane gridPane) {
        //check text fields for memory location values
        //location needs to be a six digit string
        //if it isn't, reset it to "000000"
        //if there is no such memory location, reset to default value
        for (Node node : gridPane.getChildren()) {
            if (node.getClass().isInstance(new TextField())) {
                TextField textField = (TextField) node;
                if (textField.getLength() != 6) {
                    gridPane.add(new TextField("000000"), GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    return false;
                }
                if (!Memory.getInstance().getLocationAndContent().containsKey(textField.getText())) {
                    gridPane.add(new TextField("000000"), GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    return false;
                }
            }
        }
        return true;
    }
}
