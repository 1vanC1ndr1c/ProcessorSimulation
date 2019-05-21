package project.gui.leftSide;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.leftSide.middleLeftSide.MiddleLeftSide;
import project.gui.leftSide.upperLeftSide.UpperLeftSide;

/**
 * This class is used to draw the left side of the gui.
 * <p>
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
        VBox middleLeftSideBox = MiddleLeftSide.set(borderPane);
        VBox lowerLeftSideBox = LowerLeftSide.set(borderPane);

        //add a scroll pane to the cycles
        ScrollPane scrollPane = new ScrollPane(lowerLeftSideBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);
        
        leftBox.getChildren().addAll(upperLeftSideBox, middleLeftSideBox, lowerLeftSideBox, scrollPane);

        borderPane.setLeft(leftBox);
    }
}
