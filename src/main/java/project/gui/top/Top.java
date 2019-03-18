package project.gui.top;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Top {

    public static void set(BorderPane borderPane) {

        //top
        HBox topBox = new HBox();
        topBox.setStyle("-fx-border-color: black");
        borderPane.setTop(topBox);
    }
}
