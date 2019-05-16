package project.gui.middle;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that is used to draw the middle of the gui,
 * where components and their changes through time are being shown
 */
public class Middle {

    //must be public, because other parts of the gui need access to the middle to change currently active components
    public static GridPane middleGroup;

    public static void set(BorderPane borderPane) {

        middleGroup = new GridPane();

        middleGroup.setStyle("-fx-border-color: black");
        middleGroup.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.68));
        middleGroup.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.67));
        middleGroup.setMinSize(400.0, 400.0);

        //method that draws all the components
        fillTheGrid(middleGroup);

        VBox middleBox = new VBox();
        middleBox.setStyle("-fx-border-color: black");
        middleBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.70));
        middleBox.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.68));
        middleBox.setMinSize(400.0, 400.0);
        middleBox.getChildren().addAll(middleGroup);

        //when resizing, adjust the graphical elements
        middleGroup.widthProperty().addListener(observable -> resize(middleBox, borderPane, middleGroup));
        middleGroup.heightProperty().addListener(observable -> resize(middleBox, borderPane, middleGroup));

        borderPane.setCenter(middleBox);
    }

    //method that deals with grid resizing
    private static void resize(VBox box, BorderPane borderPane, GridPane gridPane) {
        box.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.70));
        box.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.68));
        gridPane.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.68));
        gridPane.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.67));
        gridPane.getChildren().clear();
        //every resizing requires drawing the elements again
        fillTheGrid(gridPane);
    }


    //method that draws the elements onto the screen
    public static void fillTheGrid(GridPane gridPane, String... activeElements) {

        //fill the grid with rectangles
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gridPane.add(
                        new Rectangle(
                                gridPane.getWidth() / 12 - 1,
                                gridPane.getHeight() / 12 - 1,
                                Color.LIGHTGRAY),
                        j, i);
            }
        }

        //get rectangles dimension for adjusting element's dimensions
        Double cellDimension = 0.0;
        if (gridPane.getChildren().get(0).getClass().isInstance(new Rectangle())) {
            Rectangle rectangle = (Rectangle) gridPane.getChildren().get(0);
            cellDimension = rectangle.getHeight();
        }

        //default colors, when components are not active
        Color defaultComponentColor = Color.GRAY;
        Color defaultConnectionColor = Color.BLUE;
        Color defaultMemoryColor = Color.PINK;

        //colors when components are active
        Color activeComponentColor = Color.GREEN;
        Color activeConnectionColor = Color.LIGHTGREEN;
        Color activeMemoryColor = Color.ORANGE;

        List<String> activeElementsList = new ArrayList<>();
        activeElementsList.addAll(Arrays.asList(activeElements));

        //draw the elements ============================================================================================
        //MDR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "MDR",
                0, 4,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("mdr") ? activeComponentColor : defaultComponentColor,
                (activeElementsList.contains("intbus") && activeElementsList.contains("mdr"))
                        ? activeConnectionColor : defaultConnectionColor,
                "else");
        //COND
        ComponentBuilder.buildComponentAndIntBusConnection(
                "COND",
                2, 3,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("cond") ? activeComponentColor : defaultComponentColor,
                activeElementsList.contains("cond") ? activeConnectionColor : defaultConnectionColor,
                "else");
        //ACC
        ComponentBuilder.buildComponentAndIntBusConnection(
                "ACC",
                2, 5,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("acc") ? activeComponentColor : defaultComponentColor,
                (activeElementsList.contains("acc")
                        && !activeElementsList.contains("noIntBus"))
                        ? activeConnectionColor : defaultConnectionColor,
                "else");
        //TR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "TR",
                4, 4,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("tr") ? activeComponentColor : defaultComponentColor,
                (activeElementsList.contains("tr")
                        && activeElementsList.contains("trToIntBus"))
                        ? activeConnectionColor : defaultConnectionColor,
                "else");
        //PC
        ComponentBuilder.buildComponentAndIntBusConnection(
                "PC",
                7, 4,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("pc") ? activeComponentColor : defaultComponentColor,
                (activeElementsList.contains("pc")
                        && !activeElementsList.contains("inc")) ? activeConnectionColor : defaultConnectionColor,
                "else");
        //IR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "IR",
                9, 4,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("ir") ? activeComponentColor : defaultComponentColor,
                activeElementsList.contains("ir") ? activeConnectionColor : defaultConnectionColor,
                "else");
        //MAR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "MAR",
                11, 4,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("mar") ? activeComponentColor : defaultComponentColor,
                (activeElementsList.contains("intbus") && activeElementsList.contains("mar"))
                        ? activeConnectionColor : defaultConnectionColor,
                "else");
        //ALU
        ComponentBuilder.buildComponentAndIntBusConnection(
                "ALU",
                5, 5,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("alu") ? activeComponentColor : defaultComponentColor,
                activeElementsList.contains("alu") ? activeConnectionColor : defaultConnectionColor,
                "alu");
        //IntBus
        ComponentBuilder.buildComponentAndIntBusConnection(
                "intbus",
                5, 5,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("intbus") ? activeComponentColor : defaultComponentColor,
                activeElementsList.contains("intbus") ? activeConnectionColor : defaultConnectionColor,
                "intbus");
        //Memory
        ComponentBuilder.buildComponentAndIntBusConnection(
                "Memory",
                5, 2,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("memory") ? activeMemoryColor : defaultMemoryColor,
                Color.WHITE,
                "memory");
        //ALU to TR connection
        ComponentBuilder.buildComponentAndIntBusConnection(
                "aluTrConnection",
                5, 2,
                gridPane, cellDimension,
                //check if the component is active
                activeElementsList.contains("aluTrConnection") ? activeConnectionColor : defaultConnectionColor,
                Color.WHITE,
                "aluTrConnection");
    }
}
