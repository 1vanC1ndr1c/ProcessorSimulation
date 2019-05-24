package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import project.model.memory.MemoryLoader;
import project.gui.bottom.Bottom;
import project.gui.leftSide.LeftSide;
import project.gui.middle.Middle;
import project.gui.rightSide.RightSide;
import project.gui.top.Top;

import static project.logic.CycleHandler.startOfTheInstructions;

public class Processor extends Application {

    public static BorderPane border = new BorderPane();

    public static void main(String[] args) {
        MemoryLoader.loadData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MemoryLoader.loadData();
        startOfTheInstructions.put(0, 0);

        primaryStage.setTitle("Processor Simulation Application");

        LeftSide.set(border);
        RightSide.set(border);
        Middle.set(border);
        Top.set(border);
        Bottom.set(border);

        Scene scene = new Scene(border, 1280, 720);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
