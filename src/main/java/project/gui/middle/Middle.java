package project.gui.middle;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Middle {

    public static void set(BorderPane borderPane) {

        GridPane middleGroup = new GridPane();
        middleGroup.setStyle("-fx-border-color: black");
        middleGroup.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.70));
        middleGroup.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.70));
        middleGroup.setMinSize(400.0, 400.0);

        //when resizing, adjust the graphical elements
        middleGroup.widthProperty().addListener(observable -> resize(middleGroup, borderPane));
        middleGroup.heightProperty().addListener(observable -> resize(middleGroup, borderPane));

        //method that draws all the components
        fillTheGrid(middleGroup);

        borderPane.setCenter(middleGroup);
    }

    //method that deals with grid resizing
    public static void resize(GridPane gridPane, BorderPane borderPane) {
        gridPane.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.70));
        gridPane.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.70));
        gridPane.getChildren().clear();
        //every resizing requires drawing the elements again
        fillTheGrid(gridPane);
    }

    //method that draws the elements onto the screen
    private static void fillTheGrid(GridPane gridPane) {

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
        //MDR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "MDR",
                0, 4,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "else");

        //COND
        ComponentBuilder.buildComponentAndIntBusConnection(
                "COND",
                2, 3,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "else");
        //ACC
        ComponentBuilder.buildComponentAndIntBusConnection(
                "ACC",
                2, 5,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "else");
        //TR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "TR",
                4, 4,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "else");
        //PC
        ComponentBuilder.buildComponentAndIntBusConnection(
                "PC",
                7, 4,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "else");
        //IR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "IR",
                9, 4,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "else");
        //MAR
        ComponentBuilder.buildComponentAndIntBusConnection(
                "MAR",
                11, 4,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "else");
        //CONTROL
        ComponentBuilder.buildComponentAndIntBusConnection(
                "CONTROL \n UNIT",
                5, 2,
                gridPane, cellDimension,
                Color.GREEN, Color.BLUE,
                "ctrl");
        //ALU
        ComponentBuilder.buildComponentAndIntBusConnection(
                "ALU",
                5, 5,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "alu");
        //IntBus
        ComponentBuilder.buildComponentAndIntBusConnection(
                "intbus",
                5, 5,
                gridPane, cellDimension,
                Color.GRAY, Color.BLUE,
                "intbus");
    }
}
