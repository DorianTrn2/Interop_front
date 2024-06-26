package hr.algebra.dtarin.interop_frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


public class frontend extends Application {

    private static final String URL = "http://localhost:8090/api";

    @Override
    public void start(Stage primaryStage) {
        Label resultLabel = new Label("Result:");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Button getButton = new Button( "GET");
        Button postButton = new Button("POST");
        Button putButton = new Button("PUT");
        Button deleteButton = new Button("DELETE");


        getButton.setOnAction(e -> {
            String response = sendGetRequest(new HttpGet(URL + "/get"));
            resultArea.setText(response);
        });

        postButton.setOnAction(e -> {
            String response = sendPostRequest(new HttpPost(URL + "/post"), "POST body");
            resultArea.setText(response);
        });

        putButton.setOnAction(e -> {
            String response = sendPutRequest(new HttpPut(URL + "/put"), "PUT body");
            resultArea.setText(response);
        });

        deleteButton.setOnAction(e -> {
            String response = sendDeleteRequest(new HttpDelete(URL + "/delete"));
            resultArea.setText(response);
        });

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.addRow(0, getButton, postButton);
        buttonGrid.addRow(1, putButton, deleteButton);

        VBox vbox = new VBox(10, buttonGrid, resultLabel, resultArea);
        vbox.setPadding(new Insets(10));        Scene scene = new Scene(vbox, 500, 500);

        primaryStage.setTitle("Interoperability Project Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private String sendGetRequest(HttpGet request) {
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            return new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String sendPostRequest(HttpPost request, String body) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            request.setEntity(new StringEntity(body));
            try (CloseableHttpResponse response = client.execute(request)) {
                return new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
                        .lines()
                        .collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String sendPutRequest(HttpPut request, String body) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            request.setEntity(new StringEntity(body));
            try (CloseableHttpResponse response = client.execute(request)) {
                return new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
                        .lines()
                        .collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String sendDeleteRequest(HttpDelete request) {
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            return new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}