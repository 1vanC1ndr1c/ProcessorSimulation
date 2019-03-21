package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import project.data.MemoryLoader;
import project.gui.bottom.Bottom;
import project.gui.leftSide.LeftSide;
import project.gui.middle.Middle;
import project.gui.rightSide.RightSide;
import project.gui.top.Top;

public class Processor extends Application {
    public static void main(String[] args) {

        MemoryLoader.loadData();
        pickInstruction();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MemoryLoader.loadData();

        primaryStage.setTitle("Processor Simulation Application");

        BorderPane border = new BorderPane();

        LeftSide.set(border);
        RightSide.set(border);
        Middle.set(border);
        Top.set(border);
        Bottom.set(border);

        Scene scene = new Scene(border, 1600, 1000);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    public static void pickInstruction() {

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
//        ControlUnit.getInstance().start();
//
//        System.out.println("Memory Before:");         //sta
//        OutputHandler.memoryOut();                    //sta
//        InstructionPicker.pick("sta");      //sta
//        ControlUnit.getInstance().start();            //sta
//        System.out.println("Memory After:");          //sta
//        OutputHandler.memoryOut();                    //sta

    }
}
