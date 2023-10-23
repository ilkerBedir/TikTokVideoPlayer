package com.example.tiktokvideoplayer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RaceScreenController implements Initializable {
    @FXML
    GridPane grid_pane;

    private int countBox=4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<VBox> vBoxes=new ArrayList<>();
        for (int i = 0; i < countBox; i++) {
            VBox vBox = new VBox();
            vBox.setPrefSize(200,600);
            vBox.setSpacing(10);
            MediaView mediaView = new MediaView();
            mediaView.setFitHeight(400);
            mediaView.setFitWidth(200);
            Media media = new Media(Paths.get("Video1.mp4").toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(999999);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setVolume(0);
            mediaView.setMediaPlayer(mediaPlayer);
            Label label=new Label();
            label.setFont(Font.font("System",26));
            label.setAlignment(Pos.CENTER);
            label.setText("75");
            Image image = new Image(Paths.get("1.jpg").toUri().toString());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            vBox.getChildren().addAll(mediaView,label,imageView);
            vBoxes.add(vBox);
        }
        grid_pane.add(vBoxes.get(0),0,1);
        grid_pane.add(vBoxes.get(1),1,1);
        grid_pane.add(vBoxes.get(2),2,1);
        grid_pane.add(vBoxes.get(3),3,1);
    }
}