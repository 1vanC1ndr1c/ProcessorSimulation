package project;

import project.data.MemoryLoader;

import project.logic.InstructionPicker;
import project.model.processor.Accumulator;
import project.model.processor.ControlUnit;
import project.output.OutputHandler;

public class Processor {
    public static void main(String[] args) {
        MemoryLoader.loadData();

//        InstructionPicker.pick("adda");
//       InstructionPicker.pick("anda");
//        InstructionPicker.pick("lda");
//        InstructionPicker.pick("shra");
//        InstructionPicker.pick("jmp");
//        InstructionPicker.pick("jmpz");                     //successful one
//        Accumulator.getInstance().setValue("00000001");     //unsuccessful one
//        InstructionPicker.pick("jmpz");                     //unsuccessful one
//        InstructionPicker.pick("coma");
//
        ControlUnit.getInstance().start();
//
//        System.out.println("Memory Before:");         //sta
//        OutputHandler.memoryOut();                    //sta
//        InstructionPicker.pick("sta");      //sta
//        ControlUnit.getInstance().start();            //sta
//        System.out.println("Memory After:");          //sta
//        OutputHandler.memoryOut();                    //sta

    }
}
