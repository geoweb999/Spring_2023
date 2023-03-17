import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataSFToMySQL {
    public static void main(String[] args) {
        String url = "https://data.sfgov.org/resource/wg3w-h783.json";
        String dbUrl = "jdbc:mysql://localhost:3306/mydatabase";
        String dbUser = "myuser";
        String dbPassword = "mypassword";

        try {
            // Connect to the API endpoint
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            // Connect to the MySQL database
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // Insert data into the database
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String query = "INSERT INTO incidents (incident_number, category, description) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, jsonObject.getString("incident_number"));
                preparedStatement.setString(2, jsonObject.getString("category"));
                preparedStatement.setString(3, jsonObject.getString("descript"));
                preparedStatement.execute();
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}