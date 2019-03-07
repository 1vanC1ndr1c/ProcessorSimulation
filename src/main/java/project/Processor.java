package project;

import project.data.DataLoader;

import project.logic.InstructionPicker;
import project.model.processor.ControlUnit;
import project.output.OutputHandler;

public class Processor {
    public static void main(String[] args) {

        DataLoader.loadData();

        //instruction list: adda, anda, lda

//        InstructionPicker.pick("adda");
//        ControlUnit.getInstance().start();
//
//        InstructionPicker.pick("anda");
//        ControlUnit.getInstance().start();
//
//        InstructionPicker.pick("lda");
//        ControlUnit.getInstance().start();

//
//        System.out.println("Memory Before:");
//        OutputHandler.memoryOut();
//        InstructionPicker.pick("sta");
//        ControlUnit.getInstance().start();
//        System.out.println("Memory After:");
//        OutputHandler.memoryOut();

    }
}
