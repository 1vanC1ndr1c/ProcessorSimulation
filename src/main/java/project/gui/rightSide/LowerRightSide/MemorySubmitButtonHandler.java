package project.gui.rightSide.LowerRightSide;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import project.gui.validation.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class used to get the new memory input data and save it into the memory java object
 * when the "submit" button is pressed.
 * <p>
 * Since (in the time of writing this code) there is no method to directly access certain elements of the
 * grid pane, where the memory submit form is displayed, - a search of all the elements needs to be done.
 * <p>
 * Firstly, the keys (or memory locations) of each row are found, and then, using those keys, another loop is used
 * to find the corresponding value - which is then saved into the Memory java object.
 */
public class MemorySubmitButtonHandler {


    public static void handle(GridPane gridPane) {

        Map<String, String> tmpMap = new LinkedHashMap<>();

        for (Node node : gridPane.getChildren()) {                              //get the key from the grid pane
            if (node.getClass().isInstance(new Text())) {                       //keys are saved as javafx...Text
                Text text = (Text) node;                                        //so we check if the entry node is Text
                String location = text.getText();                               //key = location in memory
                int rowIndex = GridPane.getRowIndex(node);                      //get the grid row index

                for (Node node2 : gridPane.getChildren()) {                     //get the value that matches the key
                    if (node2.getClass().isInstance(new TextField())) {         //values are saved in javafx...TextField
                        if (GridPane.getRowIndex(node2) == rowIndex) {          //we check for the right type and if...
                            TextField textfield = (TextField) node2;            //the element is the same row as the key
                            String value = textfield.getText();
                            tmpMap.put(location, value);                        //store the (location, value) pair
                            break;
                        }
                    }
                }
            }
        }
        Validator.validateAndSetMemorySubmitData(tmpMap, gridPane);                       //validate and set new data
    }
}
