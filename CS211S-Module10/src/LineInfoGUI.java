import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LineInfoGUI extends Application {

    private Pane pane;
    private BorderPane borderPane;
    private Circle startPoint, endPoint;
    private Line line;
    private Button distanceButton, midpointButton, vertHorzButton, slopeButton;
    private Text distanceText, midpointText, vertHorzText, timeText, slopeText; // USE THESE!
    private LineInfoDisplayer lineInfoDisplayer; // USE THIS!

    private static final int CIRCLE_RADIUS = 5;

    public void start(Stage primaryStage) {
        borderPane = new BorderPane();

        pane = new Pane();
        pane.setOnMouseClicked(this::handleMouseClicks);
        borderPane.setCenter(pane);

        distanceText = new Text("");
        distanceButton = new Button("Calculate Distance");
        distanceButton.setOnAction((event) -> {
        	lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.DISTANCE);
        	distanceText.setText(lineInfoDisplayer.getInfo(line));
        });
        

        midpointText = new Text("");
        midpointButton = new Button("Calculate Midpoint");
        midpointButton.setOnAction((event) -> {
        	lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.MIDPOINT);
        	midpointText.setText(lineInfoDisplayer.getInfo(line));
        });
       
        vertHorzText = new Text("");
        vertHorzButton = new Button("Determine Vertical/Horizontal");
        vertHorzButton.setOnAction((event) -> {
        	lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.VERT_HORZ);
        	vertHorzText.setText(lineInfoDisplayer.getInfo(line));
        });
       
        slopeText = new Text("");
        slopeButton = new Button("Calculate slope");
        slopeButton.setOnAction((event) -> {
        	lineInfoDisplayer = LineInfoDisplayer.createLineInfoDisplayer(LineInfoDisplayer.InfoType.SLOPE);
        	slopeText.setText(lineInfoDisplayer.getInfo(line));
        });

        timeText = new Text("");
             
        TilePane distancePane = new TilePane(distanceButton, distanceText);
        distancePane.setAlignment(Pos.CENTER);
        TilePane midpointPane = new TilePane(midpointButton, midpointText);
        midpointPane.setAlignment(Pos.CENTER);
        TilePane vertHorzPane = new TilePane(vertHorzButton, vertHorzText);
        vertHorzPane.setAlignment(Pos.CENTER);
        TilePane slopePane = new TilePane(slopeButton, slopeText);
        slopePane.setAlignment(Pos.CENTER);
        TilePane timePane = new TilePane(timeText);
        timePane.setAlignment(Pos.CENTER);

        VBox controlBox = new VBox(distancePane,midpointPane,vertHorzPane, slopePane, timePane);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setSpacing(15);

        borderPane.setBottom(controlBox);

        Scene scene = new Scene(borderPane, 500, 500, Color.WHITE);
        primaryStage.setTitle("Line Information");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleMouseClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        
        if(startPoint==null ) { // no start point yet, set the start point
            startPoint = new Circle(x,y,CIRCLE_RADIUS);
            pane.getChildren().add(startPoint);
            createCoordinates(x,y);
            line = null;
        } else if(endPoint==null) { // start point, but not end point yet
        							// set the end point
            endPoint = new Circle(x,y,CIRCLE_RADIUS);
            pane.getChildren().add(endPoint);
            createCoordinates(x,y);
            
            line = new Line(startPoint.getCenterX(), startPoint.getCenterY(), endPoint.getCenterX(), endPoint.getCenterY());
            pane.getChildren().add(line);
            
            // update time when line is drawn
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
            timeText.setText("Line created: " + now.format(format));
            
                        
        } else { // startPoint != null && endPoint !=null
        	// both start and end are there, so this is starting a new line; 
        	// set a new start point
            pane.getChildren().clear();
            endPoint = null;
            line = null;
            distanceText.setText("");
            midpointText.setText(""); 
            vertHorzText.setText("");
            slopeText.setText("");
            timeText.setText("");
            
            startPoint = new Circle(x,y,CIRCLE_RADIUS);
            pane.getChildren().add(startPoint);        
            createCoordinates(x,y);
        }
    }
    
    private void createCoordinates(double x, double y) {
        String coordinateString = "(" + x + ", " + y + ")";
        Text coordinates = new Text(x-CIRCLE_RADIUS, y-CIRCLE_RADIUS-2, coordinateString);
        pane.getChildren().add(coordinates);
    }
  

    public static void main(String[] args) {
        launch(args);
    }

}
