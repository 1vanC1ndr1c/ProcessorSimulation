package project.gui.leftSide;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.leftSide.upperLeftSide.UpperLeftSide;

/**
 * This class is used to draw the left side of the gui.
 *
 * On the left side, there are instructions that can be chosen to run,
 * and 'next' and 'prev' button to see the effect of those instructions
 * on processor components as the instruction is being fetched and executed
 */
public class LeftSide {

    public static void set(BorderPane borderPane) {

        VBox leftBox = new VBox();
        leftBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.18));
        leftBox.setStyle("-fx-border-color: black");

        VBox upperLeftSideBox = UpperLeftSide.set(borderPane);
        VBox lowerLeftSideBox = LowerLeftSide.set(borderPane);

        leftBox.getChildren().addAll(upperLeftSideBox, lowerLeftSideBox);

        borderPane.setLeft(leftBox);
    }
}
