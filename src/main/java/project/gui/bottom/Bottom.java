package project.gui.bottom;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Bottom {

    //TODO

    public static void set(BorderPane borderPane) {
        //bottom

        HBox bottomBox = new HBox();


        Text bottom = new Text("bottom");
        bottomBox.getChildren().add(bottom);
        bottomBox.setStyle("-fx-border-color: black");
        bottomBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.75));


        borderPane.setBottom(bottomBox);
    }
}
