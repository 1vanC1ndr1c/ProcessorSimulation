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


import java.util.Map;

import static project.gui.leftSide.lowerLeftSide.LowerLeftSide.operationsMap;

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
        int currCycle = CycleHandler.getInstance().getCurrentCycle() + 1;
        CycleHandler.getInstance().setCurrentCycle(currCycle);

        //do the proper instructions for a cycle (fetch or execute phase)

        //fetch phase
        if (currCycle < 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            Fetch.getInstance().fetch();
        }

        //execute phase
        if (currCycle >= 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //if it is decoded correctly, do the execute phase
            if (Fetch.decodedCorrectly) {
                Execute.execute();
            } else //else, go to the next memory location
                CycleHandler.getInstance().setInstructionStartCycle(currCycle);
        }

        Bottom.set(borderPane);//draw the results
        UpperRightSide.loadComponents(UpperRightSide.componentsGridPane);  //update component values
        LowerLeftSide.setActiveOperations();

        CycleHandler.startOfTheInstructions.put(currCycle, CycleHandler.getInstance().getInstructionStartCycle());

        //if it is the end of the current operation(that was successfully executed), set the starting cycle
        //of the next instruction to the next cycle
        if (currCycle >= 8 + CycleHandler.getInstance().getInstructionStartCycle() && Fetch.decodedCorrectly) {
            if (currCycle == CycleHandler.getInstance().getInstructionStartCycle()
                    + Fetch.getInstance().getDecodedInstruction().getNoOfCycles()) {
                CycleHandler.getInstance().setInstructionStartCycle(currCycle);
            }
        }
    }

    private static void buttonPrevOperation(BorderPane borderPane) {

        for (Map.Entry<Integer, String> entry : LowerLeftSide.operationsMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        //remove current values
        if (CycleHandler.getInstance().getCurrentCycle() > 1) {
            ComponentValuesContainer.getInstance().removeCurrentComponentValues();
            ComponentValuesContainer.getInstance().setNewComponentValues();

            //check if current is part of previous instruction rather than current one
            CycleHandler.getInstance().setInstructionStartCycle(
                    CycleHandler.startOfTheInstructions.get(CycleHandler.getInstance().getCurrentCycle()));
        }


        //get the current cycle number and save the decremented value
        int currCycle = CycleHandler.getInstance().getCurrentCycle() - 1;
        CycleHandler.getInstance().setCurrentCycle(currCycle);

        //do the proper instructions for a cycle
        if (currCycle < 8 + CycleHandler.getInstance().getInstructionStartCycle())
            Fetch.getInstance().fetch();

        if (currCycle >= 8 + CycleHandler.getInstance().getInstructionStartCycle()) Execute.execute();

        //make sure cycles don't rollback past zero
        if (currCycle <= 0) {
            ComponentValuesContainer.getInstance().removeCurrentComponentValues();
            CycleHandler.getInstance().setCurrentCycle(0);
            Middle.fillTheGrid(Middle.middleGroup);
        }

        Bottom.set(borderPane);    //draw the results
        UpperRightSide.loadComponents(UpperRightSide.componentsGridPane);  //update component values
        LowerLeftSide.setActiveOperations();
    }
}
