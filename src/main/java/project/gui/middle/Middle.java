package project.gui.middle;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
        buildComponentAndIntBusConnection("MDR", 0, 4, gridPane, cellDimension, Color.GRAY, Color.BLUE, "else");
        //COND
        buildComponentAndIntBusConnection("COND", 2, 3, gridPane, cellDimension, Color.GRAY, Color.BLUE, "else");
        //ACC
        buildComponentAndIntBusConnection("ACC", 2, 5, gridPane, cellDimension, Color.GRAY, Color.BLUE, "else");
        //TR
        buildComponentAndIntBusConnection("TR", 4, 4, gridPane, cellDimension, Color.GRAY, Color.BLUE, "else");
        //PC
        buildComponentAndIntBusConnection("PC", 7, 4, gridPane, cellDimension, Color.GRAY, Color.BLUE, "else");
        //IR
        buildComponentAndIntBusConnection("IR", 9, 4, gridPane, cellDimension, Color.GRAY, Color.BLUE, "else");
        //MAR
        buildComponentAndIntBusConnection("MAR", 11, 4, gridPane, cellDimension, Color.GRAY, Color.BLUE, "else");
        //CONTROL
        buildComponentAndIntBusConnection("CONTROL \n UNIT", 5, 2, gridPane, cellDimension, Color.GREEN, Color.BLUE, "ctrl");
        //ALU
        buildComponentAndIntBusConnection("ALU", 5, 5, gridPane, cellDimension, Color.GRAY, Color.BLUE, "alu");
        //IntBus
        buildComponentAndIntBusConnection("intbus", 5, 5, gridPane, cellDimension, Color.GRAY, Color.BLUE, "intbus");
    }

    public static void buildComponentAndIntBusConnection(
            String componentName,
            Integer xCoordinate,
            Integer yCoordinate,
            GridPane gridPane,
            Double cellDimension,
            Color componentColor,
            Color connectionColor,
            String componentChoice) {

        //draw connections to the intbus
        if (componentChoice.equals("else") || componentChoice.equals("alu")) {
            Rectangle connectionToIntBus1 = new Rectangle(cellDimension / 2, 0, 0.2 * cellDimension, cellDimension);
            connectionToIntBus1.setFill(connectionColor);
            connectionToIntBus1.setStroke(Color.BLACK);
            StackPane connectionToIntBusStackPane1 = new StackPane();
            connectionToIntBusStackPane1.getChildren().addAll(connectionToIntBus1);
            gridPane.add(connectionToIntBusStackPane1, xCoordinate, yCoordinate + 1);

            Rectangle connectionToIntBus2 = new Rectangle(cellDimension / 2, 0, 0.2 * cellDimension, cellDimension);
            connectionToIntBus2.setFill(connectionColor);
            connectionToIntBus2.setStroke(Color.BLACK);
            StackPane connectionToIntBusStackPane2 = new StackPane();
            connectionToIntBusStackPane2.getChildren().addAll(connectionToIntBus2);
            gridPane.add(connectionToIntBusStackPane2, xCoordinate, yCoordinate + 2);
        }
        //draw the control unit
        if (componentChoice.equals("else") || componentChoice.equals("ctrl")) {
            Rectangle component = new Rectangle(cellDimension, cellDimension);
            component.setFill(componentColor);
            component.setStroke(Color.BLACK);
            Text componentText = new Text(componentName);
            StackPane componentStackPane = new StackPane();
            componentStackPane.getChildren().addAll(component, componentText);
            gridPane.add(componentStackPane, xCoordinate, yCoordinate);
        }
        //draw alu
        if (componentChoice.equals("alu")) {
            Polygon aluRectangle = new Polygon();
            //addAll(ULX, ULY, URX, URY, DRX, DRY, DLX, DLY)
            aluRectangle.getPoints().addAll(
                    0.0, 0.0,
                    cellDimension, 0.0,
                    0.8 * cellDimension, cellDimension,
                    0.2 * cellDimension, cellDimension
            );
            aluRectangle.setFill(componentColor);
            aluRectangle.setStroke(Color.BLACK);
            Text aluText = new Text("ALU");
            StackPane aluStack = new StackPane();
            aluStack.getChildren().addAll(aluRectangle, aluText);
            gridPane.add(aluStack, xCoordinate, yCoordinate);
        }
        //draw intbus
        if (componentChoice.equals("intbus")) {
            for (int i = 0; i <= 11; i++) {
                gridPane.add(
                        new Rectangle(
                                gridPane.getWidth() / 12 - 1,
                                gridPane.getHeight() / 12 - 1,
                                connectionColor),
                        i, 7);
            }
        }
    }
}
