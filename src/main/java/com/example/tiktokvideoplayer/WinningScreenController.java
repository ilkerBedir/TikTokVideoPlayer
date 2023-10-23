package com.example.tiktokvideoplayer;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class WinningScreenController implements Initializable {
    @FXML
    MediaView media_view;

    @FXML
    Label title_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = new Media(Paths.get("Video1.mp4").toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(999999);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0);
        media_view.setMediaPlayer(mediaPlayer);
    }
}