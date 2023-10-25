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
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TwoTeamsRaceController {
  @FXML
  private Label label_like_team_2;

  @FXML
  private Label label_comment_team_2;

  @FXML
  private Label label_team_point1_race2;

  @FXML
  private Label label_team_point2_race2;

  @FXML
  private MediaView video_media_player_race2_1;

  @FXML
  private MediaView video_media_player_race2_2;
  @FXML
  private MediaView sharing_commentary_media_view_race_2;
  @FXML
  private MediaView sharing_music_media_view_race_2;

  @FXML
  private HBox hbox_gifts_team_race2;

  @FXML
  private HBox hbox_gifts_team_race2_2;
  private String NEUTRAL_MUSIC_URL;
  private String COMMENTARY_URL_TEAM1;
  private String VIDEO_URL_TEAM1;
  private String MARS_URL_TEAM1;
  private String COMMENTARY_URL_TEAM2;
  private String VIDEO_URL_TEAM2;
  private String MARS_URL_TEAM2;
  private int COUNT_IMAGE_PER_TEAM;
  private MediaPlayer team1VideoMediaPlayer;
  private MediaPlayer team2VideoMediaPlayer;
  private List<MediaPlayer> team1MusicMediaPlayers;
  private List<MediaPlayer> team2MusicMediaPlayers;
  private List<MediaPlayer> team1CommentaryMediaPlayers;
  private List<MediaPlayer> team2CommentaryMediaPlayers;
  private Timeline team1Timeline;
  private Timeline team2Timeline;
  private int currentTeamIndex = 0;
  private int commentaryCurrentIndex = 0;
  private List<Gift> gifts = Gift.giftList();
  private List<Gift> team1Gifts;
  private List<Gift> team2Gifts;

  public void display(String team1FolderName, String team2FolderName, String neutralMusicPath, List<String> team1GiftNames,
                      List<String> team2GiftNames, int countImage) {
    this.COMMENTARY_URL_TEAM1 = team1FolderName + "\\belgesel";
    this.MARS_URL_TEAM1 = team1FolderName + "\\mars";
    this.VIDEO_URL_TEAM1 = team1FolderName + "\\video";
    this.COMMENTARY_URL_TEAM2 = team2FolderName + "\\belgesel";
    this.MARS_URL_TEAM2 = team2FolderName + "\\mars";
    this.VIDEO_URL_TEAM2 = team2FolderName + "\\video";
    this.NEUTRAL_MUSIC_URL = neutralMusicPath;
    this.COUNT_IMAGE_PER_TEAM = countImage;
    MediaPlayerUtils mediaPlayerUtils = new MediaPlayerUtils(countImage);
    this.team1Gifts = mediaPlayerUtils.teamGifts(team1GiftNames);
    this.team2Gifts = mediaPlayerUtils.teamGifts(team2GiftNames);
    hbox_gifts_team_race2.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team1Gifts));
    hbox_gifts_team_race2_2.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team2Gifts));
    team1MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(MARS_URL_TEAM1);
    team2MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(MARS_URL_TEAM2);
    team1CommentaryMediaPlayers = mediaPlayerUtils.createVolumeFiles(COMMENTARY_URL_TEAM1);
    team2CommentaryMediaPlayers = mediaPlayerUtils.createVolumeFiles(COMMENTARY_URL_TEAM2);
    team1Timeline = createTimelineTeams(team1CommentaryMediaPlayers);
    team2Timeline = createTimelineTeams(team2CommentaryMediaPlayers);
    editMusicPlayers();
    team1VideoMediaPlayer = mediaPlayerUtils.getVideo(VIDEO_URL_TEAM1);
    team2VideoMediaPlayer = mediaPlayerUtils.getVideo(VIDEO_URL_TEAM2);
    video_media_player_race2_1.setMediaPlayer(team1VideoMediaPlayer);
    video_media_player_race2_2.setMediaPlayer(team2VideoMediaPlayer);
    startNeutralMusic();
    startConsumer();
  }

  private void editMusicPlayers() {
    editMusicPlayers(team1MusicMediaPlayers);
    editMusicPlayers(team2MusicMediaPlayers);
  }

  private Timeline createTimelineTeams(List<MediaPlayer> teamCommentaries) {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
      System.out.println("Timeline");
      if (sharing_commentary_media_view_race_2.getMediaPlayer() != null) {
        sharing_commentary_media_view_race_2.getMediaPlayer().stop();
      }
      sharing_commentary_media_view_race_2.setMediaPlayer(teamCommentaries.get(commentaryCurrentIndex));
      sharing_music_media_view_race_2.getMediaPlayer().setVolume(20);
      sharing_commentary_media_view_race_2.getMediaPlayer().play();
      if (commentaryCurrentIndex >= teamCommentaries.size() - 1) {
        commentaryCurrentIndex = 0;
      } else {
        commentaryCurrentIndex += 1;
      }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    return timeline;
  }

  private void startNeutralMusic() {
    Media neutralMusic = new Media(Paths.get(NEUTRAL_MUSIC_URL).toUri().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(neutralMusic);
    mediaPlayer1.setStartTime(Duration.seconds(10));
    mediaPlayer1.setVolume(80);
    mediaPlayer1.setCycleCount(100);
    sharing_music_media_view_race_2.setMediaPlayer(mediaPlayer1);
    mediaPlayer1.setAutoPlay(true);
  }


  private void editMusicPlayers(List<MediaPlayer> musicPlayers) {
    for (MediaPlayer musicPlayer : musicPlayers) {
      musicPlayer.setOnEndOfMedia(() -> {
        if (currentTeamIndex >= musicPlayers.size() - 1) {
          currentTeamIndex = 0;
        } else {
          currentTeamIndex += 1;
        }
        System.out.println("Oynatılacak indexler : " + currentTeamIndex);
        MediaPlayer mediaPlayer = musicPlayers.get(currentTeamIndex);
        mediaPlayer.seek(Duration.ZERO);
      });
    }
  }


  private void startConsumer() {
    Service<Void> service = new Service<>() {
      @Override
      protected Task<Void> createTask() {
        Task task = new Task<Void>() {
          @Override
          public Void call() {
            while (true) {
              ArrayList value = null;
              try {
                value = GiftServer.arrayLists.take();
              } catch (InterruptedException e) {
                throw new RuntimeException();
              }
              String messageType = (String) value.get(0);
              System.out.println("MessageType : " + messageType);
              switch (messageType) {
                case "Like": {
                  ArrayList finalValue = value;
                  Platform.runLater(() -> label_like_team_2.setText("Son Beğenen : " + ((String) finalValue.get(1))));
                  break;
                }
                case "Comment": {
                  ArrayList finalValue = value;
                  Platform.runLater(() -> label_comment_team_2.setText("Son Yorum Yapan : " + ((String) finalValue.get(1))));
                  break;
                }
                case "Gift", "GiftCombo": {
                  ArrayList finalValue = value;
                  String name = ((String) finalValue.get(4));
                  int hediye_miktari = ((int) finalValue.get(2)) * ((int) finalValue.get(3));
                  System.out.println("Gift : " + name + " Gift Toplam Miktari : " + hediye_miktari);
                  Optional<Gift> gift1 = team1Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  Optional<Gift> gift2 = team2Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  String labelPoint1 = label_team_point1_race2.getText();
                  String labelPoint2 = label_team_point2_race2.getText();
                  int point1 = Integer.parseInt(labelPoint1);
                  int point2 = Integer.parseInt(labelPoint2);
                  if (gift1.isPresent()) {
                    if (point1 <= point2 && point1 + hediye_miktari > point2) {
                      System.out.println("Takım Değişti Team1");
                      currentTeamIndex = 0;
                      sharing_music_media_view_race_2.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team1MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_media_view_race_2.setMediaPlayer(mediaPlayer);
                      sharing_music_media_view_race_2.getMediaPlayer().play();
                      if (sharing_commentary_media_view_race_2.getMediaPlayer() != null) {
                        sharing_commentary_media_view_race_2.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team1Timeline.play();
                    } else {
                      Platform.runLater(() -> {
                        label_team_point1_race2.setText(String.valueOf(point1 + hediye_miktari));
                      });
                    }
                  } else if (gift2.isPresent()) {
                    if (point2 <= point1 && point2 + hediye_miktari > point1) {
                      System.out.println("Takım Değişti Team2");
                      currentTeamIndex = 0;
                      sharing_music_media_view_race_2.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team2MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_media_view_race_2.setMediaPlayer(mediaPlayer);
                      sharing_music_media_view_race_2.getMediaPlayer().play();
                      if (sharing_commentary_media_view_race_2.getMediaPlayer() != null) {
                        sharing_commentary_media_view_race_2.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team2Timeline.play();
                    } else {
                      Platform.runLater(() -> {
                        label_team_point2_race2.setText(String.valueOf(point2 + hediye_miktari));
                      });
                    }
                  } else {
                    System.out.println("Farklı Gift");
                  }
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

}
