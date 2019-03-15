package project.instructions;


public interface BaseInstruction {
    Integer noOfCycles = 0;
    String opCode = "0";

    void execute();
}
