package com.example.tiktokvideoplayer;

import com.example.tiktokvideoplayer.utils.MediaPlayerUtils;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.util.List;

public class OneTeamController implements ControllerInterface {
  private String COMMENTARY_URL;
  private String VIDEO_URL;
  private String MARS_URL;
  @FXML
  Label label_like_1;
  @FXML
  Label label_comment_1;
  @FXML
  MediaView video_media_view_1;
  @FXML
  MediaView ses_media_view_1;
  @FXML
  MediaView belgesel_media_view_1;
  private List<MediaPlayer> commentaryMedias;
  private List<MediaPlayer> marsMedias;
  private int currentMusicIndex = 0;
  private int currentCommentaryIndex = 0;

  public void display(String folderName) {
    this.COMMENTARY_URL = folderName + "\\belgesel";
    this.MARS_URL = folderName + "\\mars";
    this.VIDEO_URL = folderName + "\\video";
    MediaPlayerUtils fileUtils = new MediaPlayerUtils();
    commentaryMedias = fileUtils.createVolumeFiles(COMMENTARY_URL);
    marsMedias = fileUtils.createVolumeFiles(MARS_URL);
    MediaPlayer video = fileUtils.getVideo(this.VIDEO_URL);
    video_media_view_1.setMediaPlayer(video);
    video_media_view_1.setFitWidth(400);
    video_media_view_1.setPreserveRatio(false);
    musicMediaPlayer();
    commentaryTimeline();
    startConsumer();
  }

  private void musicMediaPlayer() {
    for (MediaPlayer team1MusicPlayer : marsMedias) {
      team1MusicPlayer.setOnEndOfMedia(() -> {
        if (currentMusicIndex >= marsMedias.size() - 1) {
          currentMusicIndex = 0;
        } else {
          currentMusicIndex += 1;
        }
        System.out.println("Oynatılacak indexler : " + currentMusicIndex);
        MediaPlayer mediaPlayer = marsMedias.get(currentMusicIndex);
        mediaPlayer.seek(Duration.ZERO);
        ses_media_view_1.setMediaPlayer(mediaPlayer);
        ses_media_view_1.getMediaPlayer().play();
      });
    }
    ses_media_view_1.setMediaPlayer(marsMedias.get(0));
    ses_media_view_1.getMediaPlayer().play();
  }

  private void commentaryTimeline() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
      System.out.println("Timeline");
      if (belgesel_media_view_1.getMediaPlayer() != null) {
        belgesel_media_view_1.getMediaPlayer().stop();
      }
      belgesel_media_view_1.setMediaPlayer(commentaryMedias.get(currentCommentaryIndex));
      ses_media_view_1.getMediaPlayer().setVolume(20);
      belgesel_media_view_1.getMediaPlayer().play();
      if (currentCommentaryIndex >= commentaryMedias.size() - 1) {
        currentCommentaryIndex = 0;
      } else {
        currentCommentaryIndex += 1;
      }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  private void startConsumer() {
    Service<Void> service = new Service<>() {
      @Override
      protected Task<Void> createTask() {
        Task task = new Task<Void>() {
          @Override
          public Void call() {
            while (true) {
              String[] value = null;
              try {
                value = GiftServer.arrayLists.take();
              } catch (InterruptedException e) {
                throw new RuntimeException();
              }
              String messageType = (String) value[0];
              System.out.println("MessageType : " + messageType);
              switch (messageType) {
                case "Like": {
                  String[] finalValue = value;
                  Platform.runLater(() -> label_like_1.setText("Son Beğenen : " + finalValue[1]));
                  break;
                }
                case "Comment": {
                  String[] finalValue = value;
                  Platform.runLater(() -> label_comment_1.setText("Son Yorum Yapan : " + finalValue[1]));
                  break;
                }
                default:
                  break;
              }
            }
          }
        };
        return task;
      }
    };
    service.start();
  }

  @Override
  public void close() {

  }

  @Override
  public void increaseTime(int time) {

  }

  @Override
  public void finishRace() {

  }
}
