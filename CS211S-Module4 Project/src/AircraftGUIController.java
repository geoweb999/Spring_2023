import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AircraftGUIController extends Application {

	private Fleet fleet;
	private AircraftGUIView aircraftInputView;

	public AircraftGUIController() {
		fleet = new Fleet(); // the model
		aircraftInputView = new AircraftGUIView(); // the view
		
		aircraftInputView.setProcessTakeOffAction(this::takeOffAction);
		aircraftInputView.setProcessLandAction(this::landAction);
		aircraftInputView.setDisplayAircraftAction(this::displayAircraft);
		aircraftInputView.setAddAircraftAction(this::addAircraft);
	}
	

	@Override
	public void start(Stage primaryStage) {
		AircraftGUIController controller = new AircraftGUIController();

		Scene scene = new Scene(controller.aircraftInputView.getParent(), 600, 650, Color.TRANSPARENT);
		primaryStage.setTitle("Aircraft Fleet Management System");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void addAircraft(ActionEvent event) {
		
		String errorMessage = "";
		try {
			String regID = aircraftInputView.getRegIDField(); // getting info from view
			if (regID.isEmpty()) {
				errorMessage = "Registration ID must not be empty.";
				throw new IllegalArgumentException();
			}
			
			int numEngines = 1;
			try {
				numEngines = Integer.parseInt(aircraftInputView.getEnginesField());
			} catch (NumberFormatException e) {
				errorMessage = "Number of Engines must be numeric.";
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Error");
				error.setHeaderText(null);
				error.setContentText(errorMessage);
				error.showAndWait();
			} finally {
				int numSouls = aircraftInputView.getSoulsField();	
				AircraftType type = aircraftInputView.getTypeField();
				boolean hasFuel = aircraftInputView.getFuelField();
				Aircraft aircraft = new Aircraft(regID, numSouls, numEngines, type, hasFuel); 
				fleet.addAircraft(aircraft);
				aircraftInputView.clearInputs(); 
				aircraftInputView.hideResultText();
			}
		} catch (IllegalArgumentException ex) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText(null);
			error.setContentText(errorMessage);
			error.showAndWait();

		} finally {
			aircraftInputView.clearInputs(); 
			aircraftInputView.hideResultText();
		}
	}
			

	private void takeOffAction(ActionEvent event) {
		ArrayList<Aircraft> aircraftList = fleet.getAircraftList();
		String output = "";
		for (Aircraft a : aircraftList) {
			output += a.takeOff() + "\n";
		}
		aircraftInputView.displayResultText("Processing Take Off", output); 
	}
	
	private void landAction(ActionEvent event) {
		ArrayList<Aircraft> aircraftList = fleet.getAircraftList();
		String output = "";
		for (Aircraft a : aircraftList) {
			output += a.land() + "\n";
		}
		aircraftInputView.displayResultText("Processing Take Off", output); 
	}

	private void displayAircraft(ActionEvent event) {
		ArrayList<Aircraft> aircraftList = fleet.getAircraftList(); 
		String output = "";
		for (Aircraft a : aircraftList) {
			output += a.toString() + "\n";
		}
		aircraftInputView.displayResultText("Fleet:", output); 

	}
	
}