package project.gui.bottom;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import project.gui.leftSide.middleLeftSide.CycleHandler;

/**
 * This class is used to draw the bottom portion of the gui.
 * In the bottom portion we see component values as they change through time
 */

public class Bottom {

    private static GridPane bottomGrid = new GridPane();

    public static void set(BorderPane borderPane) {

        bottomGrid.setStyle("-fx-border-color: black");
        bottomGrid.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.75));
        bottomGrid.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.10));
        bottomGrid.setMaxHeight(200);
        bottomGrid.setPadding(new Insets(0.0, 0.0, 0.0, 10.0));

        //when resizing, adjust the graphical elements
        bottomGrid.widthProperty().addListener(observable -> resize(bottomGrid, borderPane));
        bottomGrid.heightProperty().addListener(observable -> resize(bottomGrid, borderPane));

        //method that draws all the components
        fillTheGrid(bottomGrid);

        borderPane.setBottom(bottomGrid);
    }


    //method that deals with grid resizing
    private static void resize(GridPane gridPane, BorderPane borderPane) {
        gridPane.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.70));
        gridPane.prefHeightProperty().bind(borderPane.widthProperty().multiply(0.70));
        gridPane.getChildren().clear();
        //every resizing requires drawing the elements again
        fillTheGrid(bottomGrid);
    }


    private static void fillTheGrid(GridPane gridPane) {
        //draw the initial grid
        fillAnEmptyGrid(bottomGrid);

        //component names in the first row
        gridPane.add(new Text(" ACC"), 0, 1);
        gridPane.add(new Text(" ALU"), 0, 2);
        gridPane.add(new Text(" IR"), 0, 3);
        gridPane.add(new Text(" IntBus"), 0, 4);
        gridPane.add(new Text(" MAR"), 0, 5);
        gridPane.add(new Text(" MDR"), 0, 6);
        gridPane.add(new Text(" PC"), 0, 7);
        gridPane.add(new Text(" TR"), 0, 8);

        //cycle numbers in the first row
        for (Integer i = 1; i <= CycleHandler.getInstance().getCurrentCycle(); i++) {
            gridPane.add(new Text("|#" + i.toString()), i, 0);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getAccValues().get(i - 1)), i, 1);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getAluValues().get(i - 1)), i, 2);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getIrValues().get(i - 1)), i, 3);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getIntBusValues().get(i - 1)), i, 4);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getMarValues().get(i - 1)), i, 5);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getMdrValues().get(i - 1)), i, 6);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getPcValues().get(i - 1)), i, 7);
            gridPane.add(new Text(ComponentValuesContainer.getInstance().getTrValues().get(i - 1)), i, 8);
        }
    }


    public static void fillAnEmptyGrid(GridPane gridPane) {
        //fill the grid with rectangles
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= 16; j++) {
                if (j == 0) {
                    //first column and first row will be gray
                    gridPane.add(
                            new Rectangle(
                                    gridPane.getWidth() / 17 - 1,
                                    gridPane.getHeight() / 9 - 1,
                                    Color.GRAY),
                            j, i);
                } else {
                    //the grid is interchanging white and light gray columns
                    gridPane.add(
                            new Rectangle(
                                    gridPane.getWidth() / 17 - 1,
                                    gridPane.getHeight() / 9 - 1,
                                    j % 2 == 0 ? Color.LIGHTGRAY : Color.WHITE),
                            j, i);
                }
                //first column and first row will be gray
                gridPane.add(
                        new Rectangle(
                                gridPane.getWidth() / 17 - 1,
                                gridPane.getHeight() / 9 - 1,
                                Color.GRAY),
                        j, 0);
            }
        }
        //this is used to put placeholder text into grid
        //otherwise the grid changes size in the first cycle
        for (Integer i = 1; i <= CycleHandler.getInstance().getCurrentCycle(); i++) {
            gridPane.add(new Text(""), i, 0);
            gridPane.add(new Text(""), i, 1);
            gridPane.add(new Text(""), i, 2);
            gridPane.add(new Text(""), i, 3);
            gridPane.add(new Text(""), i, 4);
            gridPane.add(new Text(""), i, 5);
            gridPane.add(new Text(""), i, 6);
            gridPane.add(new Text(""), i, 7);
            gridPane.add(new Text(""), i, 8);
        }
    }
}
