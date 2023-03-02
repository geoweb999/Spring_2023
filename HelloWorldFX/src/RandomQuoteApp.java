import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Random;

public class RandomQuoteApp extends Application {

    private String[] quotes = {
        "The best way to predict the future is to invent it. - Alan Kay",
        "It does not matter how slowly you go as long as you do not stop. - Confucius",
        "Success is not final, failure is not fatal: it is the courage to continue that counts. - Winston Churchill",
        "Believe you can and you're halfway there. - Theodore Roosevelt",
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Be the change you wish to see in the world. - Mahatma Gandhi",
        "In three words I can sum up everything I've learned about life: it goes on. - Robert Frost",
        "Not everything that is faced can be changed, but nothing can be changed until it is faced. - James Baldwin"
    };
    
    private String[] fonts = {
    		"Arial","Comic Sans MS","Courier New", "Helvetica","Impact","Skinny","Times" 
    		};
    
    private Color[] colors = {
    		Color.RED, Color.BLUE, Color.GREEN, Color.BROWN
    };
    
    @Override
    public void start(Stage primaryStage) {
        // Create a layout to hold the button
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Button quoteButton = new Button("Generate Quote!");
        quoteButton.setOnAction(this::handleButton);  
        root.setCenter(quoteButton);
        Scene scene = new Scene(root, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Random Quote Generator");
        primaryStage.show();
    }

    private void handleButton(ActionEvent event) {
        String randomQuote = getRandomQuote();
        
        // Create a new stage to display the quote
        Stage quoteStage = new Stage();
        Label quoteLabel = new Label(randomQuote);
        quoteLabel.setFont(new Font(getRandomFont(), 20));
        quoteLabel.setTextFill(getRandomColor());

        BorderPane qRoot = new BorderPane();
        qRoot.setPadding(new Insets(10));
        qRoot.setCenter(quoteLabel);
        Scene scene = new Scene(qRoot, 800, 100);
        quoteStage.setScene(scene);
        quoteStage.setTitle("Random Quote");
        quoteStage.show();
    }
    
    private String getRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        return quotes[index];
    }

    private String getRandomFont() {
        Random random = new Random();
        int index = random.nextInt(fonts.length);
        return fonts[index];
    }
    
    private Color getRandomColor() {
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index];
    }

    public static void main(String[] args) {
        launch(args);
    }
}
