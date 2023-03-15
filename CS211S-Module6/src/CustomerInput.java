import java.io.*;
import java.util.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class CustomerInput extends Application {

    private Stage primaryStage;
    private Text statusText, resultText;
    private Button uploadButton;
    private VBox primaryBox, resultsBox, uploadBox;

    private final static Font RESULT_FONT = Font.font("Helvetica", 16);
    private final static Font INPUT_FONT = Font.font("Helvetica", 20);
    private final static Font ERROR_FONT = Font.font("Helvetica", 16);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryBox = new VBox();
        
        primaryBox.setAlignment(Pos.CENTER);
        primaryBox.setSpacing(20);
        primaryBox.setStyle("-fx-background-color: white");

        uploadBox = new VBox();
        uploadBox.setAlignment(Pos.CENTER);
        uploadBox.setSpacing(20);
        Text uploadLabel = new Text("Upload a comma-separated file with customer data.");
        uploadLabel.setFont(INPUT_FONT);
        uploadButton = new Button("Upload data");
        uploadButton.setOnAction(this::processDataUpload);

        uploadBox.getChildren().add(uploadLabel);
        uploadBox.getChildren().add(uploadButton);
        primaryBox.getChildren().add(uploadBox);

        resultsBox = new VBox();
        resultsBox.setAlignment(Pos.CENTER);
        resultsBox.setSpacing(20);
        statusText = new Text("status");
        statusText.setVisible(false);
        statusText.setFont(RESULT_FONT);
        statusText.setFill(Color.RED);
        resultText = new Text("result");
        resultText.setVisible(false);
        resultText.setFont(RESULT_FONT);
        resultsBox.getChildren().add(statusText);
        resultsBox.getChildren().add(resultText);
        primaryBox.getChildren().add(resultsBox);

        Scene scene = new Scene(primaryBox, 675, 400, Color.TRANSPARENT);
        primaryStage.setTitle("Customer Data Upload");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void processDataUpload(ActionEvent event) {
        statusText.setVisible(false);
        resultText.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        parseFile(file);

    }

    private void parseFile(File file) {
    		List<Customer> custList = new ArrayList<Customer>();  // stores successfully parsed customer data
    		List<String> errorList = new ArrayList<String>();	  // stores list of errors encountered
    		try {
				Scanner fileScan = new Scanner(new FileInputStream(file));
				while (fileScan.hasNext()) {
					String line = fileScan.nextLine();
					Scanner lineScan = new Scanner(line);
					lineScan.useDelimiter(",");
					while (lineScan.hasNext()) {
						String s = lineScan.next();
						String stringNum = lineScan.next();
						try { // s must be alphanumeric!
					        if (!isAlphaNumeric(s)) {
					            	throw new NonAlphaNumericException();
					        } 
						} catch (NonAlphaNumericException ex) {
							String message = "Record skipped.\nInvalid Cust ID (non-alphanumeric character(s): " + s;
							popupWindow(message);
							errorList.add(message);
							continue;
						}

						try { // stringNum must be numeric
							int num = Integer.parseInt(stringNum);
							custList.add(new Customer(s, num));
						} catch (NumberFormatException ex) {
							String message = "Record skipped.\nNon-integer number orders: " + stringNum + " customer=" + s;
							popupWindow(message);
							errorList.add(message);
						}
					}
				}
				
		        String statusMsg = "";
		        if (custList.size() == 0) {
		        	statusMsg = "Fail.  0 records uploaded.";
		        } else if (errorList.size() == 0) {
		        	statusMsg = "Success. Uploaded " + custList.size() + " customers.";
		        	uploadButton.setDisable(true);
		        } else {
		        	statusMsg = "Success w/errors. Uploaded " + custList.size() + " customers. Errors: " + errorList.size();
		        }
				statusText = new Text(statusMsg);
		        statusText.setFont(RESULT_FONT);
		        statusText.setFill(Color.BLUE);
				statusText.setVisible(true);
				String results = "";
				int sumOfOrders = 0;
				for (Customer cust : custList) {
					sumOfOrders += cust.getNumberOfOrders();
				}
				results = "\nTotal Number of Orders: " + sumOfOrders;
				
				if (errorList.size() != 0) {
					results += "\n Errors caught:\n";
					for (String err : errorList) {
						results += err + "\n";
					}
				}
				resultText = new Text(results);
				statusText.setVisible(true);
		        resultText.setFont(RESULT_FONT);

		        resultsBox.getChildren().clear();
		        resultsBox.getChildren().add(statusText);
		        resultsBox.getChildren().add(resultText);
		        primaryStage.setResizable(true);
    

			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
				
			} catch (NullPointerException ex) {
				popupWindow("No file selected!");

			}
    	
    }
    
    private void popupWindow(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private boolean isAlphaNumeric(String s) {
    	for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') &&
                    !(c >= 'a' && c <= 'z') &&
                    !(c >= '0' && c <= '9') &&
                    !(c == ' ')) {
            	return false;
            }
    	}
    	return true;
    }

    public static void main(String[] args) {
        launch(args);
    }

}