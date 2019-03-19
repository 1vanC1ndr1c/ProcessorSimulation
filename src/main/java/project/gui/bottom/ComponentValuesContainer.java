package project.gui.bottom;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.model.processor.*;

import java.util.*;

@Data
public class ComponentValuesContainer {
    private static ComponentValuesContainer ourInstance = new ComponentValuesContainer();

    Map<Integer, String> accValues = new LinkedHashMap<>();
    Map<Integer, String> aluValues = new LinkedHashMap<>();
    Map<Integer, String> irValues = new LinkedHashMap<>();
    Map<Integer, String> intBusValues = new LinkedHashMap<>();
    Map<Integer, String> marValues = new LinkedHashMap<>();
    Map<Integer, String> mdrValues = new LinkedHashMap<>();
    Map<Integer, String> pcValues = new LinkedHashMap<>();
    Map<Integer, String> trValues = new LinkedHashMap<>();


    public static ComponentValuesContainer getInstance() {
        return ourInstance;
    }

    public void saveCurrentComponentValues() {
        accValues.put(CycleHandler.getInstance().getCurrentCycle(), Accumulator.getInstance().getValue());
        aluValues.put(CycleHandler.getInstance().getCurrentCycle(), ALU.getInstance().getValue());
        irValues.put(CycleHandler.getInstance().getCurrentCycle(), InstructionRegister.getInstance().getValue());
        intBusValues.put(CycleHandler.getInstance().getCurrentCycle(), InternalBus.getInstance().getValue());
        marValues.put(CycleHandler.getInstance().getCurrentCycle(), MemoryAddressRegister.getInstance().getValue());
        mdrValues.put(CycleHandler.getInstance().getCurrentCycle(), MemoryDataRegister.getInstance().getValue());
        pcValues.put(CycleHandler.getInstance().getCurrentCycle(), ProgramCounter.getInstance().getValue());
        trValues.put(CycleHandler.getInstance().getCurrentCycle(), TemporaryRegister.getInstance().getValue());
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

    public void setNewComponentValues() {
        Accumulator.getInstance().setValue(accValues.get(accValues.size() - 1));
        ALU.getInstance().setValue(aluValues.get(aluValues.size() - 1));
        InstructionRegister.getInstance().setValue(irValues.get(irValues.size() - 1));
        InternalBus.getInstance().setValue(intBusValues.get(intBusValues.size() - 1));
        MemoryAddressRegister.getInstance().setValue(marValues.get(marValues.size() - 1));
        MemoryDataRegister.getInstance().setValue(mdrValues.get(mdrValues.size() - 1));
        ProgramCounter.getInstance().setValue(pcValues.get(pcValues.size() - 1));
        TemporaryRegister.getInstance().setValue(trValues.get(trValues.size() - 1));
    }
}
