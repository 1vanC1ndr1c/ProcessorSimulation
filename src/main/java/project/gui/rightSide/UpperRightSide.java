package project.gui.rightSide;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.gui.Validation.Validator;
import project.model.processor.*;

public class UpperRightSide {

    public final static GridPane componentsGridPane = new GridPane();

    public static VBox set(BorderPane borderPane) {

        //upper right side box and it's scaling and border color set
        VBox upperRightSideBOx = new VBox();
        upperRightSideBOx.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        upperRightSideBOx.setStyle("-fx-border-color: black");

        //name of the box
        Text componentHeader = new Text("Components");

        //load components
        loadComponents(componentsGridPane);

        //submit button
        //saves all the component changes into their respective java objects
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            Validator.validateAndSetProcessorComponentsData(componentsGridPane);
        });

        //these 3 commands are used to center the submit button into the middle of a line
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(submitButton);

        //add the header, components and the button into the upper right side box
        upperRightSideBOx.getChildren().add(componentHeader);
        upperRightSideBOx.getChildren().add(componentsGridPane);
        upperRightSideBOx.getChildren().add(new Text(" "));
        upperRightSideBOx.getChildren().add(buttonBox);
        upperRightSideBOx.getChildren().add(new Text(" "));

        return upperRightSideBOx;
    }

    public static void loadComponents(GridPane componentsGridPane) {

        //list of components =========================================================================================

        //acc
        Text accumulatorText = new Text(" ACC: ");                                          //component name
        TextField accValue = new TextField(Accumulator.getInstance().getValue());           //component value
        accValue.setPrefWidth(80);                                                          //value box width
        componentsGridPane.add(accumulatorText, 0, 0);                 //add value and name into...
        componentsGridPane.add(accValue, 1, 0);                        //the grid

        //alu
        Text ALUText = new Text(" ALU: ");
        TextField ALUValue = new TextField(ALU.getInstance().getValue());
        ALUValue.setPrefWidth(80);
        componentsGridPane.add(ALUText, 0, 1);
        componentsGridPane.add(ALUValue, 1, 1);

        //ir
        Text instructionRegisterText = new Text(" InstReg: ");
        TextField instructionRegisterValue = new TextField(InstructionRegister.getInstance().getValue());
        instructionRegisterValue.setPrefWidth(80);
        componentsGridPane.add(instructionRegisterText, 0, 2);
        componentsGridPane.add(instructionRegisterValue, 1, 2);

        //intbus
        Text intBusText = new Text(" IntBUs: ");
        TextField intBusValue = new TextField(InternalBus.getInstance().getValue());
        intBusValue.setPrefWidth(80);
        componentsGridPane.add(intBusText, 0, 3);
        componentsGridPane.add(intBusValue, 1, 3);

        //mar
        Text MARText = new Text(" MAR: ");
        TextField MARValue = new TextField(MemoryAddressRegister.getInstance().getValue());
        MARValue.setPrefWidth(80);
        componentsGridPane.add(MARText, 0, 4);
        componentsGridPane.add(MARValue, 1, 4);

        //mdr
        Text MDRText = new Text(" MDR: ");
        TextField MDRValue = new TextField(MemoryDataRegister.getInstance().getValue());
        MDRValue.setPrefWidth(80);
        componentsGridPane.add(MDRText, 0, 5);
        componentsGridPane.add(MDRValue, 1, 5);

        //pc
        Text PCText = new Text(" PC: ");
        TextField PCValue = new TextField(ProgramCounter.getInstance().getValue());
        PCValue.setPrefWidth(80);
        componentsGridPane.add(PCText, 0, 6);
        componentsGridPane.add(PCValue, 1, 6);

        //tr
        Text TRText = new Text(" TR: ");
        TextField TRValue = new TextField(TemporaryRegister.getInstance().getValue());
        TRValue.setPrefWidth(80);
        componentsGridPane.add(TRText, 0, 7);
        componentsGridPane.add(TRValue, 1, 7);
        //==============================================================================================================
    }

}
