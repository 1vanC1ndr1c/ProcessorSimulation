package project.gui.leftSide.upperLeftSide;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class that draws the upper left side of gui, where instructions are being picked and ran.
 */
public class UpperLeftSide {

    public static VBox set(BorderPane borderPane) {
        VBox upperLeftSideBox = new VBox();
        upperLeftSideBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        upperLeftSideBox.setStyle("-fx-border-color: black");
        upperLeftSideBox.setPadding(new Insets(0.0, 10.0, 10.0, 10.0));

        //title
        Text leftText = new Text("Instructions               Location ");
        upperLeftSideBox.getChildren().add(leftText);

        GridPane instructionsGridPane = new GridPane();
        instructionsGridPane.setHgap(40);

        InstructionHandler.createInstructionRow("ADDA", 0, 0, true, instructionsGridPane);
        InstructionHandler.createInstructionRow("ANDA", 0, 1, true, instructionsGridPane);
        InstructionHandler.createInstructionRow("LDA", 0, 2, true, instructionsGridPane);
        InstructionHandler.createInstructionRow("STA", 0, 3, true, instructionsGridPane);
        InstructionHandler.createInstructionRow("JMPZ", 0, 4, true, instructionsGridPane);
        InstructionHandler.createInstructionRow("JMP", 0, 5, true, instructionsGridPane);
        InstructionHandler.createInstructionRow("SHRA", 0, 6, false, instructionsGridPane);
        InstructionHandler.createInstructionRow("COMA", 0, 7, false, instructionsGridPane);

        upperLeftSideBox.getChildren().add(instructionsGridPane);

        return upperLeftSideBox;
    }
}
