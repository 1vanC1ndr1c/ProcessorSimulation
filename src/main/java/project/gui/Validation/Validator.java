package project.gui.Validation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import project.gui.rightSide.UpperRightSide;
import project.model.memory.Memory;
import project.model.processor.*;

import java.util.Map;

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

        String accValue = "";            //acc = row 0
        String aluValue = "";            //alu = row 1
        String irValue = "";             //ir = row 2
        String intBusValue = "";         //intBus = row 3
        String marValue = "";            //mar = row 4
        String mdrValue = "";            //mdr = row 5
        String pcValue = "";             //pc = row 6
        String trValue = "";             //tr = row 7

        //search the gridpane for textfields
        for (Node node : gridPane.getChildren()) {
            if (node.getClass().isInstance(new TextField())) {
                TextField textfield = (TextField) node;
                String value = textfield.getText();
                int rowIndex = GridPane.getRowIndex(node);
                switch (rowIndex) {
                    case 0:
                        accValue = value;
                        break;
                    case 1:
                        aluValue = value;
                        break;
                    case 2:
                        irValue = value;
                        break;
                    case 3:
                        intBusValue = value;
                        break;
                    case 4:
                        marValue = value;
                        break;
                    case 5:
                        mdrValue = value;
                        break;
                    case 6:
                        pcValue = value;
                        break;
                    case 7:
                        trValue = value;
                        break;
                }
            }
        }
        if (accValue.length() == 8 || accValue.length() == 6) {
            Accumulator.getInstance().setValue(accValue);
        }
        if (aluValue.length() == 8 || accValue.length() == 6) {
            ALU.getInstance().setValue(aluValue);
        }
        if (intBusValue.length() == 8 || intBusValue.length() == 6 || intBusValue.length() == 1) {
            InternalBus.getInstance().setValue(intBusValue);
        }
        if (irValue.length() == 1) {
            InstructionRegister.getInstance().setValue(irValue);
        }
        if (marValue.length() == 6) {
            MemoryAddressRegister.getInstance().setValue(marValue);
        }
        if (mdrValue.length() == 8 || mdrValue.length() == 6 || mdrValue.length() == 1) {
            MemoryDataRegister.getInstance().setValue(mdrValue);
        }
        if (pcValue.length() == 6) {
            ProgramCounter.getInstance().setValue(pcValue);
        }
        if (trValue.length() == 8 || trValue.length() == 6) {
            TemporaryRegister.getInstance().setValue(trValue);
        }
        gridPane.getChildren().clear();
        UpperRightSide.loadComponents(gridPane);
    }


    public static boolean validateInstructionLocation(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node.getClass().isInstance(new TextField())) {
                TextField textField = (TextField) node;
                if (textField.getLength() != 6) {
                    gridPane.add(new TextField("000000"), GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    return false;
                }
            }
        }
        return true;
    }
}
