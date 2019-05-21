package project.gui.leftSide;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    public static ScrollPane scrollPane;

    public static void set(BorderPane borderPane) {

        VBox leftBox = new VBox();
        leftBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.18));
        leftBox.setStyle("-fx-border-color: black");

        VBox upperLeftSideBox = UpperLeftSide.set(borderPane);
        VBox middleLeftSideBox = MiddleLeftSide.set(borderPane);
        VBox lowerLeftSideBox = LowerLeftSide.set(borderPane);

        //add a scroll pane to the cycles
        scrollPane = new ScrollPane(lowerLeftSideBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);
        //force the scrollbar to scroll to the end
        lowerLeftSideBox.layout();
        lowerLeftSideBox.heightProperty().addListener(
                (ChangeListener) (observable, oldvalue, newValue) -> scrollPane.setVvalue(1d));

        leftBox.getChildren().addAll(upperLeftSideBox, middleLeftSideBox, lowerLeftSideBox, scrollPane);

        borderPane.setLeft(leftBox);
    }
}
