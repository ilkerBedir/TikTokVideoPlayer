package com.example.tiktokvideoplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RaceSettingsController implements Initializable {

    private Stage yeniEkranStage;
    private Scene yeniEkranScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void startRaceAction(ActionEvent actionEvent) {
        System.out.println("Yarışma Başladı");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("three_teams.fxml"));
        AnchorPane yeniEkranRoot = null;
        try {
            yeniEkranRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        yeniEkranScene = new Scene(yeniEkranRoot);
        yeniEkranStage = new Stage();
        yeniEkranStage.setScene(yeniEkranScene);
        yeniEkranStage.show();
    }

    public void finishRaceAction(ActionEvent actionEvent) {
        System.out.println("Yarışma Kapatılıyor");
        yeniEkranStage.close();
        Parent root = yeniEkranScene.getRoot();
        for (Node node : root.getChildrenUnmodifiable()) {
            System.out.println(node.getClass());
            if (node instanceof MediaView) {
                MediaPlayer mediaPlayer = ((MediaView) node).getMediaPlayer();
                if (mediaPlayer!=null){
                    mediaPlayer.stop();
                }
            }
        }
    }
}
