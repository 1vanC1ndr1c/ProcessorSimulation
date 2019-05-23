package project.model.processor.behavior.signals;

import lombok.Data;
import project.output.OutputHandler;

@Data
public abstract class BaseSignal {

    public  abstract void signal();

    public void printData(){
        OutputHandler.processorOut(getClass().getSimpleName(), 2);
    }
}
