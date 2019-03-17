package project.gui.leftSide.lowerLeftSide;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.model.processor.behavior.Execute;
import project.model.processor.behavior.Fetch;

public class LowerLeftSide {

    //cycle switching buttons, need to be public because they are disabled until an instruction is picked
    public static Button buttonNext = new Button("Next");
    public static Button prevButton = new Button("Prev.");

    public static VBox set(BorderPane borderPane) {

        VBox lowerLeftSideBox = new VBox();
        lowerLeftSideBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        lowerLeftSideBox.setStyle("-fx-border-color: black");
        lowerLeftSideBox.setPadding(new Insets(0.0, 10.0, 10.0, 10.0));

        //title
        Text text = new Text("Cycles");

        //next button
        buttonNext = new Button("Next");
        buttonNext.setOnAction(e -> {
            //get the current cycle number
            Integer currCycle = CycleHandler.getInstance().getCurrentCycle() + 1;
            CycleHandler.getInstance().setCurrentCycle(currCycle);
            //do the proper instructions for a cycle
            if (currCycle < 8) Fetch.getInstance().fetch();
            if (currCycle >= 8) Execute.getInstance().execute();
        });


        //prev button
        prevButton = new Button("Prev.");
        prevButton.setOnAction(e -> {
            Integer currCycle = CycleHandler.getInstance().getCurrentCycle() - 1;
            CycleHandler.getInstance().setCurrentCycle(currCycle);
            if (currCycle < 8) Fetch.getInstance().fetch();
            if (currCycle >= 8) Execute.getInstance().execute();
            if (currCycle == 0) CycleHandler.getInstance().setCurrentCycle(1);
        });


        //disable the buttons until an instruction is picked
        buttonNext.setDisable(true);
        prevButton.setDisable(true);

        //box that holds the buttons
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(prevButton, new Text(" "), buttonNext);


        lowerLeftSideBox.getChildren().add(text);
        lowerLeftSideBox.getChildren().add(buttonBox);

        return lowerLeftSideBox;
    }
}