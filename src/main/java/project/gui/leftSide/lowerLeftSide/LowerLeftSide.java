package project.gui.leftSide.lowerLeftSide;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class LowerLeftSide {
    public static VBox set(BorderPane borderPane) {

        VBox lowerLeftSideBox = new VBox();
        lowerLeftSideBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        lowerLeftSideBox.setStyle("-fx-border-color: black");
        lowerLeftSideBox.setPadding(new Insets(0.0, 10.0, 10.0, 10.0));

        //title
        Text text = new Text("Cycles");
        lowerLeftSideBox.getChildren().add(text);





        return lowerLeftSideBox;
    }
}
