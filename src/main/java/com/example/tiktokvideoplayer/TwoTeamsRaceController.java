package com.example.tiktokvideoplayer;

import com.example.tiktokvideoplayer.utils.MediaPlayerUtils;
import com.example.tiktokvideoplayer.utils.TimeUtils;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TwoTeamsRaceController implements ControllerInterface{
  private static final Logger LOGGER= LoggerFactory.getLogger(TwoTeamsRaceController.class);
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
  @FXML
  private Label timer_label;

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
  private List<Gift> team1Gifts;
  private List<Gift> team2Gifts;
  private boolean threadFlag=true;
  private int time;

  @FXML
  private VBox vbox1;
  @FXML
  private VBox vbox2;
  @FXML
  private MediaView winning_video_view;
  private String WINNING_VIDEO_TEAM1;
  private String WINNING_VIDEO_TEAM2;
  @FXML
  private HBox hbox1;
  private int extendedTime;

  public void display(String team1FolderName, String team2FolderName, String neutralMusicPath, List<String> team1GiftNames,
                      List<String> team2GiftNames, int countImage, int time, int extendedTime) {
    LOGGER.debug("2 takımlı ekran display");
    this.COMMENTARY_URL_TEAM1 = team1FolderName + "/belgesel";
    this.MARS_URL_TEAM1 = team1FolderName + "/mars";
    this.VIDEO_URL_TEAM1 = team1FolderName + "/video";
    this.COMMENTARY_URL_TEAM2 = team2FolderName + "/belgesel";
    this.MARS_URL_TEAM2 = team2FolderName + "/mars";
    this.VIDEO_URL_TEAM2 = team2FolderName + "/video";
    this.WINNING_VIDEO_TEAM1 = team1FolderName + "/winning_video";
    this.WINNING_VIDEO_TEAM2 = team2FolderName + "/winning_video";
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
    this.extendedTime=extendedTime;
    if (time>0){
      this.time=time;
      timer_label.toFront();
      createTimer();
    }else {
      this.time=Integer.MAX_VALUE;
    }

  }

  private void editMusicPlayers() {
    LOGGER.debug("editMusicPlayers");
    editMusicPlayers(team1MusicMediaPlayers);
    editMusicPlayers(team2MusicMediaPlayers);
  }

  private Timeline createTimelineTeams(List<MediaPlayer> teamCommentaries) {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
      LOGGER.debug("takım belgeseli başlatılıyor");
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
    LOGGER.debug("startNeutralMusic");
    Media neutralMusic = new Media(Paths.get(NEUTRAL_MUSIC_URL).toUri().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(neutralMusic);
    mediaPlayer1.setStartTime(Duration.seconds(10));
    mediaPlayer1.setVolume(80);
    mediaPlayer1.setCycleCount(100);
    sharing_music_media_view_race_2.setMediaPlayer(mediaPlayer1);
    mediaPlayer1.setAutoPlay(true);
  }


  private void editMusicPlayers(List<MediaPlayer> musicPlayers) {
    LOGGER.debug("editMusicPlayers");
    for (MediaPlayer musicPlayer : musicPlayers) {
      musicPlayer.setOnEndOfMedia(() -> {
        if (currentTeamIndex >= musicPlayers.size() - 1) {
          currentTeamIndex = 0;
        } else {
          currentTeamIndex += 1;
        }
        LOGGER.debug("Oynatılacak indexler : {}" , currentTeamIndex);
        MediaPlayer mediaPlayer = musicPlayers.get(currentTeamIndex);
        mediaPlayer.seek(Duration.ZERO);
        sharing_music_media_view_race_2.setMediaPlayer(mediaPlayer);
        sharing_music_media_view_race_2.getMediaPlayer().play();
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
            while (threadFlag) {
              ArrayList value = null;
              try {
                value = GiftServer.arrayLists.take();
              } catch (InterruptedException e) {
                throw new RuntimeException();
              }
              String messageType = (String) value.get(0);
              LOGGER.debug("MessageType : " + messageType);
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
                  LOGGER.debug("Gift : " + name + " Gift Toplam Miktari : " + hediye_miktari);
                  Optional<Gift> gift1 = team1Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  Optional<Gift> gift2 = team2Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  String labelPoint1 = label_team_point1_race2.getText();
                  String labelPoint2 = label_team_point2_race2.getText();
                  int point1 = Integer.parseInt(labelPoint1);
                  int point2 = Integer.parseInt(labelPoint2);
                  if (gift1.isPresent()) {
                    if (point1 <= point2 && point1 + hediye_miktari > point2) {
                      LOGGER.debug("Takım Değişti Team1");
                      increaseTime();
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
                      if (team2Timeline!=null && team2Timeline.getStatus().equals(Animation.Status.RUNNING)){
                        team2Timeline.stop();
                      }
                      team1Timeline.play();
                      Platform.runLater(() -> {
                        label_team_point1_race2.setText(String.valueOf(point1 + hediye_miktari));
                        label_team_point1_race2.setTextFill(Color.YELLOW);
                        label_team_point2_race2.setTextFill(Color.WHITE);
                      });
                    } else {
                      Platform.runLater(() -> {
                        label_team_point1_race2.setText(String.valueOf(point1 + hediye_miktari));
                      });
                    }
                  } else if (gift2.isPresent()) {
                    if (point2 <= point1 && point2 + hediye_miktari > point1) {
                      LOGGER.debug("Takım Değişti Team2");
                      increaseTime();
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
                      if (team1Timeline!=null && team1Timeline.getStatus().equals(Animation.Status.RUNNING)){
                        team1Timeline.stop();
                      }
                      team2Timeline.play();
                      Platform.runLater(() -> {
                        label_team_point2_race2.setText(String.valueOf(point2 + hediye_miktari));
                        label_team_point2_race2.setTextFill(Color.YELLOW);
                        label_team_point1_race2.setTextFill(Color.WHITE);
                      });
                    } else {
                      Platform.runLater(() -> {
                        label_team_point2_race2.setText(String.valueOf(point2 + hediye_miktari));
                      });
                    }
                  } else {
                    LOGGER.debug("Farklı Gift");
                  }
                  break;
                }
                default:
                  break;
              }
            }
            return null;
          }
        };
        return task;
      }
    };
    service.start();
  }

  public void close() {
    LOGGER.debug("Kapatma isteği");
    this.time=-1;
    threadFlag=false;
    team1Timeline.stop();
    team2Timeline.stop();
    if (sharing_commentary_media_view_race_2.getMediaPlayer() != null) {
      sharing_commentary_media_view_race_2.getMediaPlayer().stop();
    }
    if (sharing_music_media_view_race_2.getMediaPlayer() != null) {
      sharing_music_media_view_race_2.getMediaPlayer().stop();
    }
    if (video_media_player_race2_1.getMediaPlayer() != null) {
      video_media_player_race2_1.getMediaPlayer().stop();
    }
    if (video_media_player_race2_2.getMediaPlayer() != null) {
      video_media_player_race2_2.getMediaPlayer().stop();
    }
    if (winning_video_view.getMediaPlayer()!=null){
      winning_video_view.getMediaPlayer().stop();
    }
  }

  private void createTimer(){
    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
      time--;
      timer_label.setText(TimeUtils.convertSecondsToClock(time));
      if (time == 0) {
        timeline.stop();
        finishRace();
      }
    });

    timeline.getKeyFrames().add(keyFrame);
    timeline.play();
  }
  public void increaseTime(int time){
    LOGGER.debug("Süre uzatıldı");
    this.time=time+this.time;
  }
  public void increaseTime(){
    if (time>0 && time<60){
      LOGGER.debug("Süre uzatıldı");
      this.time=extendedTime+this.time;
    }
  }

  @Override
  public void finishRace() {
    LOGGER.debug("finishRace");
    if (this.time<0){
      return;
    }
    this.time=-1;
    timer_label.setVisible(false);
    List<Node> removedList = new ArrayList<>(hbox1.getChildren());
    hbox1.getChildren().removeAll(removedList);
    hbox1.getChildren().add(winning_video_view);
    team1Timeline.stop();
    team2Timeline.stop();
    vbox1.setVisible(false);
    vbox2.setVisible(false);
    winning_video_view.setPreserveRatio(false);
    winning_video_view.setFitWidth(2*vbox1.getWidth());
    winning_video_view.setFitHeight(vbox1.getHeight());
    int team1point = Integer.parseInt(label_team_point1_race2.getText());
    int team2point = Integer.parseInt(label_team_point2_race2.getText());
    MediaPlayerUtils mediaPlayerUtils = new MediaPlayerUtils();
    MediaPlayer video;
    if (team1point>team2point){
       video = mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM1);
    }else {
       video = mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM2);
    }
    if (sharing_music_media_view_race_2.getMediaPlayer()!=null){
      sharing_music_media_view_race_2.getMediaPlayer().stop();
    }
    if (sharing_commentary_media_view_race_2.getMediaPlayer()!=null){
      sharing_commentary_media_view_race_2.getMediaPlayer().stop();
    }
    video.setVolume(100);
    winning_video_view.setMediaPlayer(video);
    winning_video_view.setVisible(true);
    winning_video_view.getMediaPlayer().play();
  }
}
