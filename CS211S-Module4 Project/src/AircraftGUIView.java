import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AircraftGUIView {
	
	private TextField regIDField, soulsField, enginesField;
	private ComboBox<AircraftType> typeComboBox;
	private RadioButton radioHasFuel, radioHasNoFuel;
	private ToggleGroup fuelToggle;
	private Text introText, regIDLabel, soulsLabel, enginesLabel, fuelLabel, typeLabel, resultLabel;
	private TextArea result;
	private Button addButton, displayButton, takeOffButton, landButton;
	private VBox primaryBox;
	
	private final static AircraftType DEFAULT_TYPE = AircraftType.FIXED_WING;
	private final static Font titleFont = Font.font("Ariel", 20);
	private final static Font textFont = Font.font("Ariel", 14);
	
	public AircraftGUIView() {
		System.setProperty("glass.accessible.force", "false");
		
		primaryBox = new VBox();
		primaryBox.setAlignment(Pos.CENTER);
	    primaryBox.setPadding(new Insets(10, 10, 10, 10));

		primaryBox.setSpacing(20);
		primaryBox.setStyle("-fx-background-color: white");
	
		introText = new Text("Aircraft Management System!\nProvide the aircraft data below.");
		introText.setFont(titleFont);
		VBox introBox = new VBox(introText);
		introBox.setAlignment(Pos.CENTER);
		introBox.setSpacing(10);
		primaryBox.getChildren().add(introBox);
		
		regIDLabel = new Text("Registration ID:");
		regIDLabel.setFont(textFont);
		regIDField = new TextField();
		regIDField.setPrefWidth(70);
		regIDField.setMaxWidth(70);
		HBox regIDBox = new HBox(regIDLabel, regIDField);
		regIDBox.setAlignment(Pos.CENTER_LEFT);
		regIDBox.setSpacing(10);
		primaryBox.getChildren().add(regIDBox);
		
		enginesLabel = new Text("Number of Engines:");
		enginesLabel.setFont(textFont);
		enginesField = new TextField();
		enginesField.setPrefWidth(25);
		enginesField.setMaxWidth(25);
		HBox enginesBox = new HBox(enginesLabel, enginesField);
		enginesBox.setAlignment(Pos.CENTER_LEFT);
		enginesBox.setSpacing(10);
		primaryBox.getChildren().add(enginesBox);
		
		soulsLabel = new Text("Souls on Board: ");
		soulsLabel.setFont(textFont);
		soulsField = new TextField();
		soulsField.setPrefWidth(40);
		soulsField.setMaxWidth(40);
		HBox soulsBox = new HBox(soulsLabel, soulsField);
		enginesBox.setAlignment(Pos.CENTER_LEFT);
		enginesBox.setSpacing(10);
		primaryBox.getChildren().add(soulsBox);
		
		fuelLabel = new Text("Has fuel:");
		fuelLabel.setFont(textFont);
		radioHasFuel = new RadioButton("true");
		radioHasNoFuel = new RadioButton("false");
		fuelToggle = new ToggleGroup();
		radioHasFuel.setToggleGroup(fuelToggle);
		radioHasNoFuel.setToggleGroup(fuelToggle);
		
		HBox fuelBox = new HBox(fuelLabel, radioHasFuel, radioHasNoFuel);
		fuelBox.setAlignment(Pos.CENTER_LEFT);
		fuelBox.setSpacing(10);
		primaryBox.getChildren().add(fuelBox);
		
		typeLabel = new Text("Select the type:");
		typeLabel.setFont(textFont);
		ObservableList<AircraftType> typeOptions = FXCollections.observableArrayList((Arrays.asList(AircraftType.values())));
		// three objects {AircraftType.FIXED_WING, AircraftType.SEA_PLANE, AircraftType.ROTARY}
		// Arrays.asList converts the array to a List
		// FXCollections.observableArrayList converts to an ObservableList, which can be pased to the ComboBox constructor
		typeComboBox = new ComboBox<AircraftType>(typeOptions);
		typeComboBox.setValue(AircraftType.FIXED_WING);
		VBox typeBox = new VBox(typeLabel, typeComboBox);
		typeBox.setAlignment(Pos.CENTER_LEFT);
		typeBox.setSpacing(10);
		primaryBox.getChildren().add(typeBox);
		
		addButton = new Button("Add Aircraft");
		displayButton = new Button("Show Aircraft");
		takeOffButton = new Button("Execute takeoff");
		landButton = new Button("Execute landing");
		
		HBox buttonBox = new HBox(addButton, displayButton, takeOffButton, landButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		primaryBox.getChildren().add(buttonBox);
	
		resultLabel = new Text("");
		resultLabel.setFont(titleFont);
		resultLabel.setFill(Color.RED);
		resultLabel.setVisible(false);
		result = new TextArea("");
		result.setVisible(false);
		result.setEditable(false);
		VBox resultBox = new VBox(resultLabel, result);
		resultBox.setAlignment(Pos.CENTER);
		resultBox.setSpacing(10);
		primaryBox.getChildren().add(resultBox);
	}

	
	public Parent getParent() {
		return primaryBox;
	}

	public void setProcessLandAction(EventHandler<ActionEvent> handler) {
		landButton.setOnAction(handler);
	}
	
	public void setProcessTakeOffAction(EventHandler<ActionEvent> handler) {
		takeOffButton.setOnAction(handler);
	}
	public void setDisplayAircraftAction(EventHandler<ActionEvent> handler) {
		displayButton.setOnAction(handler);
	}
	public void setAddAircraftAction(EventHandler<ActionEvent> handler) {
		addButton.setOnAction(handler);
	}

	public void displayResultText(String label, String output) {
		resultLabel.setVisible(true);
		resultLabel.setText(label);
		
		result.clear();
		result.setVisible(true);
		result.setText(output);
	}
	
	public void hideResultText() {
		result.clear();
		result.setVisible(false);
		resultLabel.setVisible(false);
	}
	public void clearInputs() {
		regIDField.clear();
		soulsField.clear();
		enginesField.clear();
		radioHasFuel.setSelected(false);
		radioHasNoFuel.setSelected(false);
		typeComboBox.setValue(DEFAULT_TYPE);
	}
	
	public String getRegIDField() {
		return regIDField.getText();
	}
	
	public int getSoulsField() {
		String text = soulsField.getText();
		return Integer.parseInt(text);
	}
	
	public String getEnginesField() {
		return enginesField.getText();
	}
	
	public AircraftType getTypeField() {
		return typeComboBox.getValue();
	}
	
	public void setRegIDField(String text) {
		regIDField.setText(text);
	}
	
	public void setEnginesField(int engines) {
		enginesField.setText(Integer.toString(engines));
	}

	public void setSoulsField(int souls) {
		soulsField.setText(Integer.toString(souls));
	}
	
	public void setTypeField(AircraftType type) {
		typeComboBox.setValue(type);
	}

	public boolean getFuelField() {
		RadioButton selectedRadioButton = (RadioButton) fuelToggle.getSelectedToggle();
		return (selectedRadioButton.getText() == "true");
	}
}
