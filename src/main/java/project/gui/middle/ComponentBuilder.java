package project.gui.middle;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import project.logic.CycleHandler;

/**
 * Class that is used to build a component and show it in the
 * middle grid of the gui.
 */
public class ComponentBuilder {

    //method takes in the name, position and color of the component and it's respective connection the the
    //internal bus or some other component
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
        if (componentChoice.equals("else") || componentChoice.equals("memory")) {
            Rectangle component = new Rectangle(cellDimension*1.3, cellDimension);
            component.setFill(componentColor);
            component.setStroke(Color.BLACK);
            Text componentText = new Text(componentName);
            StackPane componentStackPane = new StackPane();
            componentStackPane.getChildren().addAll(component, componentText);
            GridPane.setHalignment(component, HPos.CENTER);
            GridPane.setHalignment(componentText, HPos.CENTER);

            if (componentName.equals("TR")) {
                GridPane.setHalignment(component, HPos.RIGHT);
            }
            gridPane.add(component, xCoordinate, yCoordinate);
            gridPane.add(componentText, xCoordinate, yCoordinate);
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
        //draw ALU connection to tr(ir)
        if (componentChoice.equals("aluTrConnection")) {
            //connection to tr
            Rectangle cornerUp = new Rectangle(cellDimension, cellDimension / 2, 0.2 * cellDimension, cellDimension * 0.605);
            cornerUp.setFill(componentColor);
            cornerUp.setStroke(Color.BLACK);
            Rectangle cornerLeft = new Rectangle(0, cellDimension / 2, cellDimension * 0.76, cellDimension * 0.2);
            cornerLeft.setFill(componentColor);
            cornerLeft.setStroke(Color.BLACK);

            GridPane.setHalignment(cornerUp, HPos.CENTER);
            GridPane.setValignment(cornerUp, VPos.BOTTOM);

            gridPane.add(cornerLeft, 5, 4);
            gridPane.add(cornerUp, 5, 4);
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
        //==============================================================================================================
    }

    //depending on the current cycle, different elements are being active in the grid
    //since all the instructions have the same fetch phase, it is being executed here, while the execute
    //phases are being executed in the individual classes of the instructions
    public static void redrawActiveElementsFetchPhase() {
        //1. MAR <- PC
        if (CycleHandler.getInstance().getCurrentCycle() == 1) {
            //epc
            Middle.fillTheGrid(Middle.middleGroup, "pc", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 2) {
            //lmar
            Middle.fillTheGrid(Middle.middleGroup, "pc", "mar", "intbus");
        }
        //2. MDR <- M[MAR], read
        if (CycleHandler.getInstance().getCurrentCycle() == 3) {
            //read
            Middle.fillTheGrid(Middle.middleGroup, "memory");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 4) {
            //lmdr
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "memory");
        }
        //3. PC++, IR <- MDR{31:28}
        if (CycleHandler.getInstance().getCurrentCycle() == 5) {
            //inc, emdr
            Middle.fillTheGrid(Middle.middleGroup, "pc", "mdr", "intbus", "inc");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 6) {
            //lir
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "ir");
        }
        //4. decode
        if (CycleHandler.getInstance().getCurrentCycle() == 7) {
            //nothing is active during the decoding
            Middle.fillTheGrid(Middle.middleGroup);
        }
    }
}
