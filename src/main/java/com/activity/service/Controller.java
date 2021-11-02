package com.activity.service;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {
    @FXML
    private Button getActivity;
    @FXML
    private Label activityAdviserLabel;
    @FXML
    private Pane windowAdviser;
    @FXML
    private Sphere adviserIcon;
    @FXML
    private Text activity;

    @FXML
    void initialize() {
        getActivity.setOnAction(event -> {
            String output = getUrlContent("http://www.boredapi.com/api/activity");
            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                activity.setText("Do it: " + obj.get("activity/service"));
            }
        });
    }

    private static String getUrlContent(String urlAddress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Sorry, the service is not available :(");
        }
        return content.toString();
    }

}

