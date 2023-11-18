package com.example.tiktokvideoplayer.utils;

import com.example.tiktokvideoplayer.Gift;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MediaPlayerUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(MediaPlayerUtils.class);
  private int countImagePerTeam;
  private List<Gift> gifts;

  public MediaPlayerUtils() {

  }

  public MediaPlayerUtils(int countImagePerTeam) {
    this.countImagePerTeam = countImagePerTeam;
    gifts = Gift.giftList();
  }

  public List<MediaPlayer> createVolumeFiles(String url) {
    LOGGER.debug("create volume files");
    List<MediaPlayer> mediaPlayers = new ArrayList<>();
    File folder = new File(url);
    File[] files = folder.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isFile()) {
          Media media = new Media(file.toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(media);
          mediaPlayer.setStartTime(Duration.seconds(2));
          mediaPlayer.setVolume(100);
          mediaPlayer.setOnError(() -> {
            LOGGER.debug("Ses Videosunda hata");
            mediaPlayer.stop();
            mediaPlayer.dispose();
            MediaPlayer mediaPlayer1 = createVolumeFiles(url).get(0);
            mediaPlayer1.setStartTime(Duration.seconds(2));
            mediaPlayer1.setVolume(100);
            mediaPlayer1.play();
          });
          mediaPlayers.add(mediaPlayer);
        }
      }
    }
    return mediaPlayers;
  }

  public MediaPlayer getVideo(String url) {
    LOGGER.debug("getVideo");
    List<MediaPlayer> mediaPlayers = new ArrayList<>();
    File folder = new File(url);
    File[] files = folder.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isFile()) {
          Media media = new Media(file.toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(media);
          mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
          mediaPlayer.setAutoPlay(true);
          mediaPlayer.setVolume(0);
          mediaPlayers.add(mediaPlayer);
          return mediaPlayer;
        }
      }
    }
    LOGGER.error("Video da hata : {}", url);
    return null;
  }

  public List<Gift> teamGifts(List<String> giftNames) {
    LOGGER.debug("teamGifts");
    List<Gift> giftList = gifts.stream()
        .filter(gift -> giftNames.contains(gift.getName()))
        .sorted(Comparator.comparing(gift -> giftNames.indexOf(gift.getName())))
        .collect(Collectors.toList());

    List<Gift> tmp = new ArrayList<>();
    Set<String> giftSet = new HashSet<>();
    for (Gift gift : giftList) {
      if (giftSet.add(gift.getName())) {
        tmp.add(gift);
      }
    }
    return tmp;
  }

  public List<VBox> createGiftsHBOX(List<Gift> tmpGifts) {
    LOGGER.debug("CreateGiftsHBOX");
    return tmpGifts.stream()
        .map(gift -> createImageBox(gift))
        .collect(Collectors.toList());
  }

  private ImageView createImageView(Image image) {
    LOGGER.debug("createImageView");
    ImageView imageView = new ImageView();
    imageView.setImage(image);
    imageView.setFitHeight((double) 100 / (4));
    imageView.setFitWidth((double) 92 / (4));
    return imageView;
  }

  private VBox createImageBox(Gift gift) {
    VBox vBox = new VBox();
    ImageView imageView = createImageView(gift.image());
    Label label = new Label();
    label.setText(String.valueOf(gift.getDiamondCost()));
    label.setFont(Font.font(8));
    label.setStyle("-fx-font-weight: bold;");
    vBox.setAlignment(Pos.CENTER);
    label.setVisible(true);
    vBox.getChildren().addAll(imageView, label);
    return vBox;
  }
}
