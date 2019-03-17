package project.gui.rightSide.LowerRightSide;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.model.memory.Memory;

import java.util.Map;

public class LowerRightSide {

    public final static GridPane memoryGridPane = new GridPane();

    public static VBox set(BorderPane borderPane) {

        //lower right side box and it's scaling and border color set
        VBox lowerRightSideVBox = new VBox();
        lowerRightSideVBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        lowerRightSideVBox.setStyle("-fx-border-color: black");

        //grid which will hold the memory location and memory values
        memoryGridPane.setHgap(30);
        memoryGridPane.setPrefWidth(150);
        memoryGridPane.setPadding(new Insets(0, 0, 0, 5));

        //titles of the lower box
        Text memoryHeader = new Text(" Memory");
        Text table = new Text(" Location        Content");

        //get all entries from the Memory map and put them into the grid to be displayed
        int i = 0;
        for (Map.Entry<String, String> entry : Memory.getInstance().getLocationAndContent().entrySet()) {
            memoryGridPane.add(new Text(entry.getKey()), 0, i);
            memoryGridPane.add(new TextField(entry.getValue()), 1, i);
            i++;
        }

        //add a scroll pane to the memory grid
        ScrollPane scrollPane = new ScrollPane(memoryGridPane);
        scrollPane.setFitToHeight(true);

        //submit button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            //when the button is used, go and save changes in memory back to the memory java object
            MemorySubmitButtonHandler.handle(memoryGridPane);
        });

        //these 3 commands are used to center the submit button into the middle of a line
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(submitButton);

        //add the header, memory grid and the button into the lower right side box
        lowerRightSideVBox.getChildren().add(memoryHeader);
        lowerRightSideVBox.getChildren().add(table);
        lowerRightSideVBox.getChildren().add(scrollPane);
        lowerRightSideVBox.getChildren().add(new Text(" "));
        lowerRightSideVBox.getChildren().add(buttonBox);
        lowerRightSideVBox.getChildren().add(new Text(" "));

        return lowerRightSideVBox;
    }
}
