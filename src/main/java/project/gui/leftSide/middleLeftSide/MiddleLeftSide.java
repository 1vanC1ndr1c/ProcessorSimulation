package project.gui.leftSide.middleLeftSide;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.gui.bottom.Bottom;
import project.logic.ComponentValuesContainer;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.middle.Middle;
import project.gui.rightSide.UpperRightSide;
import project.logic.CycleHandler;
import project.model.processor.behavior.Execute;
import project.model.processor.behavior.Fetch;

/**
 * Class that is used to draw the 'next' and 'prev' buttons into gui.
 */

public class MiddleLeftSide {

    //cycle switching buttons, need to be public because they are disabled until an instruction is picked
    public static Button buttonNext = new Button("Next");
    public static Button prevButton = new Button("Prev.");
    public static Button fetchAndExecuteButton = new Button("Fetch and Execute");


    public static VBox set(BorderPane borderPane) {

        VBox middleLeftSideBox = new VBox();
        middleLeftSideBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        middleLeftSideBox.setStyle("-fx-border-color: black");
        middleLeftSideBox.setPadding(new Insets(0.0, 10.0, 10.0, 10.0));

        //title
        Text text = new Text("Cycles");

        //'next' button ================================================================================================
        buttonNext = new Button("Next");
        buttonNext.setOnAction(e -> {
            buttonNextOperation(borderPane);
        });
        //==============================================================================================================
        //prev button ==================================================================================================
        prevButton = new Button("Prev.");
        prevButton.setOnAction(e -> {
            buttonPrevOperation(borderPane);
        });
        //==============================================================================================================
        //fetch and execute button =====================================================================================
        fetchAndExecuteButton = new Button("Fetch and Execute");
        fetchAndExecuteButton.setOnAction(e -> {
            for (int i = 0; i < 7; i++) {
                buttonNextOperation(borderPane);
            }
            for (int i = 0; i < Fetch.getInstance().getDecodedInstruction().getNoOfCycles() - 7; i++) {
                buttonNextOperation(borderPane);
            }
        });
        //==============================================================================================================

        //disable the buttons until an instruction is picked
        buttonNext.setDisable(true);
        prevButton.setDisable(true);
        fetchAndExecuteButton.setDisable(true);

        //box that holds the buttons
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(prevButton, new Text(" "), buttonNext, new Text(" "), fetchAndExecuteButton);

        middleLeftSideBox.getChildren().add(text);
        middleLeftSideBox.getChildren().add(buttonBox);

        return middleLeftSideBox;
    }

    private static void buttonNextOperation(BorderPane borderPane) {

        //save current values
        ComponentValuesContainer.getInstance().saveCurrentComponentValues();
        //get the current cycle number and save the incremented value
        Integer currCycle = CycleHandler.getInstance().getCurrentCycle() + 1;
        CycleHandler.getInstance().setCurrentCycle(currCycle);
        //do the proper instructions for a cycle
        if (currCycle < 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            Fetch.getInstance().fetch();
        }
        if (currCycle >= 8 + CycleHandler.getInstance().getInstructionStartCycle()) Execute.getInstance().execute();
        //draw the results
        Bottom.set(borderPane);
        //update component values
        UpperRightSide.loadComponents(UpperRightSide.componentsGridPane);
        LowerLeftSide.setActiveOperations();

        //if the instruction is done, proceed to the next one
        if (currCycle >= 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            if (currCycle == CycleHandler.getInstance().getInstructionStartCycle() + Fetch.getInstance().getDecodedInstruction().getNoOfCycles()) {
                //next instruction starts at the next cycle
                CycleHandler.getInstance().setInstructionStartCycle(currCycle);
            }
        }
    }

    private static void buttonPrevOperation(BorderPane borderPane) {
        //remove current values
        if (CycleHandler.getInstance().getCurrentCycle() > 1) {
            ComponentValuesContainer.getInstance().removeCurrentComponentValues();
        }
        if (CycleHandler.getInstance().getCurrentCycle() > 1) {
            ComponentValuesContainer.getInstance().setNewComponentValues();
        }
        //get the current cycle number and save the decremented value
        Integer currCycle = CycleHandler.getInstance().getCurrentCycle() - 1;
        CycleHandler.getInstance().setCurrentCycle(currCycle);
        //do the proper instructions for a cycle
        if (currCycle < 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            Fetch.getInstance().fetch();
        }
        if (currCycle >= 8 + CycleHandler.getInstance().getInstructionStartCycle()) Execute.getInstance().execute();
        if (currCycle <= 0) {
            ComponentValuesContainer.getInstance().removeCurrentComponentValues();
            CycleHandler.getInstance().setCurrentCycle(0);
            Middle.fillTheGrid(Middle.middleGroup);
        }
        //draw the results
        Bottom.set(borderPane);
        //update component values
        UpperRightSide.loadComponents(UpperRightSide.componentsGridPane);
        LowerLeftSide.setActiveOperations();
    }
}
