package project.output;

import org.apache.commons.lang3.StringUtils;
import project.model.memory.Memory;
import project.model.processor.*;

import java.util.Map;

public class OutputHandler {

    //commented out so there is no printouts in the console
    public static void processorOut(String phase, Integer indentationLevel) {
//        String padding = "";
//        if (indentationLevel > 0) {
//            indentationLevel = indentationLevel + 10;
//            String padd = "%-" + indentationLevel.toString() + "s";
//            padding = String.format(padd, "");
//        }
//        if(indentationLevel > 0)System.out.println(padding + phase + StringUtils.repeat("=", 40 - phase.length()));
//        else System.out.println(phase + StringUtils.repeat("=", 52 - phase.length()));
//        System.out.println(padding + "Acc:" + Accumulator.getInstance().getValue());
//        System.out.println(padding + "ALU:" + ALU.getInstance().getValue());
//        System.out.println(padding + "IR:" + InstructionRegister.getInstance().getValue());
//        System.out.println(padding + "IntBus:" + InternalBus.getInstance().getValue());
//        System.out.println(padding + "MDR: " + MemoryDataRegister.getInstance().getValue());
//        System.out.println(padding + "MAR: " + MemoryAddressRegister.getInstance().getValue());
//        System.out.println(padding + "PC: " + ProgramCounter.getInstance().getValue());
//        System.out.println(padding + "TR: " + TemporaryRegister.getInstance().getValue());
//        if (indentationLevel > 0) System.out.println(padding + StringUtils.repeat("=", 40));
//        else System.out.println(StringUtils.repeat("=", 52));
    }

    public static void memoryOut() {
        System.out.println("||  Location  ||  Content     ||");
        System.out.println("================================");
        for (Map.Entry<String, String> entry : Memory.getInstance().getLocationAndContent().entrySet()) {

            String mapOut = "||   " + entry.getKey() + "   ||  " + entry.getValue();
            String end = StringUtils.repeat(" ", 30 - mapOut.length()) + "||";

            System.out.println(mapOut + end);
        }
        System.out.println("================================");
    }
}
