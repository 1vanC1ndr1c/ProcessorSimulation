package project.gui.top;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import project.gui.validation.Validator;
import project.gui.rightSide.UpperRightSide;
import project.model.memory.Memory;

public class Top {

    public static void set(BorderPane borderPane) {

        //top
        HBox topBox = new HBox();
        topBox.setStyle("-fx-border-color: black");
        Text top = new Text("top");
        topBox.getChildren().add(top);


        //submit button
        //saves all the component changes into their respective java objects
        Button submitButton = new Button("GO");
        submitButton.setOnAction(e -> {
            Validator.validateAndSetProcessorComponentsData(UpperRightSide.componentsGridPane);
            Validator.validateAndSetMemorySubmitData(Memory.getInstance().getLocationAndContent(), UpperRightSide.componentsGridPane);
        });

        topBox.getChildren().add(submitButton);
        borderPane.setTop(topBox);
    }
}
