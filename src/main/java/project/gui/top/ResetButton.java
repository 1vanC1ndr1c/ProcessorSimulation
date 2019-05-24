package project.gui.top;

import javafx.scene.control.Button;
import project.Processor;
import project.gui.leftSide.LeftSide;
import project.gui.leftSide.middleLeftSide.MiddleLeftSide;
import project.gui.rightSide.RightSide;
import project.gui.rightSide.UpperRightSide;
import project.logic.CycleHandler;
import project.model.memory.MemoryLoader;
import project.model.processor.*;

public class ResetButton {

    public static Button set() {
        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: green");
        resetButton.setOnAction(e -> {
            while (CycleHandler.getInstance().getCurrentCycle() > 0) {
                MiddleLeftSide.buttonPrevOperation(Processor.border);
            }
            MemoryLoader.loadData();
            MiddleLeftSide.buttonPrevOperation(Processor.border);
            MiddleLeftSide.buttonNext.setDisable(true);
            MiddleLeftSide.prevButton.setDisable(true);
            MiddleLeftSide.fetchAndExecuteButton.setDisable(true);
            MiddleLeftSide.middleLeftSideBox.getChildren().clear();
            LeftSide.set(Processor.border);
            Accumulator.getInstance().setValue("00000000");
            ALU.getInstance().setValue("00000000");
            InstructionRegister.getInstance().setValue("0");
            InternalBus.getInstance().setValue("00000000");
            MemoryAddressRegister.getInstance().setValue("000000");
            MemoryDataRegister.getInstance().setValue("00000000");
            ProgramCounter.getInstance().setValue("000000");
            TemporaryRegister.getInstance().setValue("00000000");
            UpperRightSide.componentsGridPane.getChildren().clear();
            RightSide.set(Processor.border);

        });

        return resetButton;
    }
}
