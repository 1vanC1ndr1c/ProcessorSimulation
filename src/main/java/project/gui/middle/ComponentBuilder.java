package project.gui.middle;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ComponentBuilder {

    public static void buildComponentAndIntBusConnection(
            String componentName,
            Integer xCoordinate,
            Integer yCoordinate,
            GridPane gridPane,
            Double cellDimension,
            Color componentColor,
            Color connectionColor,
            String componentChoice) {

        //draw connections to the intbus ===============================================================================
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
        //==============================================================================================================
        //draw the component ===========================================================================================
        if (componentChoice.equals("else") || componentChoice.equals("ctrl")) {
            Rectangle component = new Rectangle(cellDimension, cellDimension);
            component.setFill(componentColor);
            component.setStroke(Color.BLACK);
            Text componentText = new Text(componentName);
            StackPane componentStackPane = new StackPane();
            componentStackPane.getChildren().addAll(component, componentText);
            gridPane.add(componentStackPane, xCoordinate, yCoordinate);
        }
        //==============================================================================================================
        //draw alu =====================================================================================================
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
        //==============================================================================================================
        //draw intbus ==================================================================================================
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
