package com.example.tiktokvideoplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ThreeTeamRace extends Application {

    public static void main(String[] args) {
        GiftServer giftServer=new GiftServer(8887);
        giftServer.start();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(ThreeTeamRace.class.getResource("three_teams.fxml"));*/
        FXMLLoader fxmlLoader = new FXMLLoader(ThreeTeamRace.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setOnCloseRequest((event)-> {
            System.out.println(event.getEventType().getName());
            stage.close();
            Platform.exit();
        });
        stage.setTitle("Race!");
        stage.setScene(scene);
        stage.show();
    }
}