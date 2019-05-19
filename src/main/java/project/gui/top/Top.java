package project.gui.top;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Top {

    private static HBox topBox = new HBox();

    public static void set(BorderPane borderPane) {

        topBox.setStyle("-fx-border-color: black");
        topBox.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.01));

        //when resizing, adjust the graphical elements
//        topBox.widthProperty().addListener(observable -> resize(topBox, borderPane));
//        topBox.heightProperty().addListener(observable -> resize(topBox, borderPane));


        topBox.getChildren().add(new Text("testfefdf"));


        borderPane.setTop(topBox);
    }
}