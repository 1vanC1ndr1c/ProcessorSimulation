package project.gui.leftSide.upperLeftSide;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.rightSide.LowerRightSide.LowerRightSide;
import project.gui.rightSide.UpperRightSide;
import project.gui.validation.Validator;
import project.logic.InstructionPicker;
import project.model.memory.Memory;
import project.model.processor.ControlUnit;
import project.model.processor.ProgramCounter;

public class InstructionHandler {

    public static void createInstructionRow(String instructionName, int column, int row, boolean isMemoryInstruction, GridPane gridPane) {

        //default memory location to save the instruction
        TextField location = new TextField("000000");

        //if it is a memory instruction, put a field for entering a memory location
        if (isMemoryInstruction == true) {
            location.setPrefWidth(80);
            gridPane.add(location, column + 1, row);
        }

        //instruction start button    ==================================================================================
        Button instructionButton = new Button(instructionName);
        instructionButton.setPrefWidth(70);
        instructionButton.setOnAction(e -> {
            //check if the memory location is 6 digits long
            Validator.validateInstructionLocation(gridPane);
            if (isMemoryInstruction == true) doTheInstruction(instructionButton.getText(), location.getText());
            else doTheInstruction(instructionButton.getText(), "is not memory instruction");
        });
        //==============================================================================================================

        //add the instruction into the grid pane
        gridPane.add(instructionButton, column, row);
    }


    public static void doTheInstruction(String instructionName, String memoryLocation) {

        //make the instruction code that will be written into memory ===================================================
        String opCode = InstructionPicker.retrunOpCode(instructionName.toLowerCase());
        String irrelevantBits = "0";
        if (memoryLocation.equals("is not memory instruction")) {
            memoryLocation = "000000";
        }
        String memoryValueOfInstruction = opCode + irrelevantBits + memoryLocation;
        //==============================================================================================================

        //check the validity of the input data
        Validator.validateAndSetProcessorComponentsData(UpperRightSide.componentsGridPane);

        //save the instruction
        String memoryLocationOfInstruction = ProgramCounter.getInstance().getValue();
        Memory.getInstance().getLocationAndContent().put(memoryLocationOfInstruction, memoryValueOfInstruction);

        //change the gui memory
        Validator.validateAndSetMemorySubmitData(Memory.getInstance().getLocationAndContent(), LowerRightSide.memoryGridPane);

        //fetch and execute the instruction
        ControlUnit.getInstance().start();

        //change the gui components values
        UpperRightSide.loadComponents(UpperRightSide.componentsGridPane);

        //instruction started, enable "next" and "prev" buttons
        LowerLeftSide.buttonNext.setDisable(false);
        LowerLeftSide.prevButton.setDisable(false);

        //reset the current cycle button
        CycleHandler.getInstance().setCurrentCycle(0);
    }

}
