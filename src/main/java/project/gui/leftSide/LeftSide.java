package project.gui.leftSide;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.leftSide.upperLeftSide.UpperLeftSide;

public class LeftSide {

    public static void set(BorderPane borderPane) {

        VBox leftBox = new VBox();
        leftBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        leftBox.setStyle("-fx-border-color: black");

        VBox upperLeftSideBox = UpperLeftSide.set(borderPane);
        VBox lowerLeftSideBox = LowerLeftSide.set(borderPane);

        leftBox.getChildren().addAll(upperLeftSideBox, lowerLeftSideBox);

        borderPane.setLeft(leftBox);
    }
}
