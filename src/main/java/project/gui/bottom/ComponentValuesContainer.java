package project.gui.bottom;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.model.processor.*;

import java.util.LinkedList;
import java.util.List;

@Data
public class ComponentValuesContainer {
    private static ComponentValuesContainer ourInstance = new ComponentValuesContainer();

    List<String> accValues = new LinkedList<>();
    List<String> aluValues = new LinkedList<>();
    List<String> irValues = new LinkedList<>();
    List<String> intBusValues = new LinkedList<>();
    List<String> marValues = new LinkedList<>();
    List<String> mdrValues = new LinkedList<>();
    List<String> pcValues = new LinkedList<>();
    List<String> trValues = new LinkedList<>();


    public static ComponentValuesContainer getInstance() {
        return ourInstance;
    }

    public void saveCurrentComponentValues() {
        accValues.add(CycleHandler.getInstance().getCurrentCycle(), Accumulator.getInstance().getValue());
        aluValues.add(CycleHandler.getInstance().getCurrentCycle(), ALU.getInstance().getValue());
        irValues.add(CycleHandler.getInstance().getCurrentCycle(), InstructionRegister.getInstance().getValue());
        intBusValues.add(CycleHandler.getInstance().getCurrentCycle(), InternalBus.getInstance().getValue());
        marValues.add(CycleHandler.getInstance().getCurrentCycle(), MemoryAddressRegister.getInstance().getValue());
        mdrValues.add(CycleHandler.getInstance().getCurrentCycle(), MemoryDataRegister.getInstance().getValue());
        pcValues.add(CycleHandler.getInstance().getCurrentCycle(), ProgramCounter.getInstance().getValue());
        trValues.add(CycleHandler.getInstance().getCurrentCycle(), TemporaryRegister.getInstance().getValue());
    }

    public void removeCurrentComponentValues() {
        accValues.remove(CycleHandler.getInstance().getCurrentCycle());
        aluValues.remove(CycleHandler.getInstance().getCurrentCycle());
        irValues.remove(CycleHandler.getInstance().getCurrentCycle());
        intBusValues.remove(CycleHandler.getInstance().getCurrentCycle());
        marValues.remove(CycleHandler.getInstance().getCurrentCycle());
        mdrValues.remove(CycleHandler.getInstance().getCurrentCycle());
        pcValues.remove(CycleHandler.getInstance().getCurrentCycle());
        trValues.remove(CycleHandler.getInstance().getCurrentCycle());
    }

}
