package project.gui.rightSide;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import project.gui.rightSide.LowerRightSide.LowerRightSide;

/**
 * On the right side of the gui there are memory and component values being displayed,
 * as well as the option to change those values
 */

public class RightSide {

    public static void set(BorderPane borderPane) {
        VBox rightSideBox = new VBox();                                 //box for the whole right side
        VBox components = UpperRightSide.set(borderPane);               //box for the upper side
        VBox memory = LowerRightSide.set();                   //box for the lower side

        //set scaling
        rightSideBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.12));
        rightSideBox.setStyle("-fx-border-color: black");               //set border color

        rightSideBox.getChildren().add(components);                     //add upper side into the right side box
        rightSideBox.getChildren().add(memory);                         //add lower side into the right side box

        borderPane.setRight(rightSideBox);                              //add it into the "window"
    }
}
