package com.example.tiktokvideoplayer;

import com.example.tiktokvideoplayer.utils.MediaPlayerUtils;
import com.example.tiktokvideoplayer.utils.TimeUtils;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SixTeamsController implements ControllerInterface {
  private static final Logger LOGGER= LoggerFactory.getLogger(SixTeamsController.class);
  @FXML
  private Label label_comment_race4;
  @FXML
  private Label label_like_race4;
  @FXML
  private HBox hbox_team1;
  @FXML
  private Label point_team1;
  @FXML
  private MediaView video_team1;
  @FXML
  private HBox hbox_team2;
  @FXML
  private Label point_team2;
  @FXML
  private MediaView video_team2;
  @FXML
  private HBox hbox_team3;
  @FXML
  private Label point_team3;
  @FXML
  private MediaView video_team3;
  @FXML
  private HBox hbox_team4;
  @FXML
  private Label point_team4;
  @FXML
  private MediaView video_team4;
  @FXML
  private HBox hbox_team5;
  @FXML
  private Label point_team5;
  @FXML
  private MediaView video_team5;
  @FXML
  private HBox hbox_team6;
  @FXML
  private Label point_team6;
  @FXML
  private MediaView video_team6;
  @FXML
  private MediaView sharing_music_race4;
  @FXML
  private MediaView sharing_commentary_race4;
  @FXML
  private Label timer_label;
  private String NEUTRAL_MUSIC_URL;
  private String COMMENTARY_URL_TEAM1;
  private String COMMENTARY_URL_TEAM2;
  private String COMMENTARY_URL_TEAM3;
  private String COMMENTARY_URL_TEAM4;
  private String COMMENTARY_URL_TEAM5;
  private String COMMENTARY_URL_TEAM6;
  private String VIDEO_URL_TEAM1;
  private String VIDEO_URL_TEAM2;
  private String VIDEO_URL_TEAM3;
  private String VIDEO_URL_TEAM4;
  private String VIDEO_URL_TEAM5;
  private String VIDEO_URL_TEAM6;
  private String MARS_URL_TEAM1;
  private String MARS_URL_TEAM2;
  private String MARS_URL_TEAM3;
  private String MARS_URL_TEAM4;
  private String MARS_URL_TEAM5;
  private String MARS_URL_TEAM6;
  private int COUNT_IMAGE_PER_TEAM;
  private List<MediaPlayer> team1MusicMediaPlayers;
  private List<MediaPlayer> team2MusicMediaPlayers;
  private List<MediaPlayer> team3MusicMediaPlayers;
  private List<MediaPlayer> team4MusicMediaPlayers;
  private List<MediaPlayer> team5MusicMediaPlayers;
  private List<MediaPlayer> team6MusicMediaPlayers;
  private Timeline team1Timeline;
  private Timeline team2Timeline;
  private Timeline team3Timeline;
  private Timeline team4Timeline;
  private Timeline team5Timeline;
  private Timeline team6Timeline;
  private int currentTeamIndex = 0;
  private int commentaryCurrentIndex = 0;
  private List<Gift> team1Gifts;
  private List<Gift> team2Gifts;
  private List<Gift> team3Gifts;
  private List<Gift> team4Gifts;
  private List<Gift> team5Gifts;
  private List<Gift> team6Gifts;
  private boolean threadFlag=true;
  private int time;
  @FXML
  private MediaView winning_team_video;
  @FXML
  private GridPane grid_pane1;
  private String WINNING_VIDEO_TEAM1;
  private String WINNING_VIDEO_TEAM2;
  private String WINNING_VIDEO_TEAM3;
  private String WINNING_VIDEO_TEAM4;
  private String WINNING_VIDEO_TEAM5;
  private String WINNING_VIDEO_TEAM6;
  @FXML
  private StackPane stackpane1;
  private int extendedTime;


  public void display(String team1Folder,String team2Folder,String team3Folder,String team4Folder,String team5Folder,
                      String team6Folder, String neutralMusicPath,int countGiftImage,List<String> team1GiftNames,
                      List<String> team2GiftNames,List<String> team3GiftNames,List<String> team4GiftNames,
                      List<String> team5GiftNames,List<String> team6GiftNames,int time,int extendedTime
  ) {
    LOGGER.debug("6 takımlı yarış display");
    this.NEUTRAL_MUSIC_URL=neutralMusicPath;
    this.COUNT_IMAGE_PER_TEAM=countGiftImage;
    editFolders(team1Folder,team2Folder,team3Folder,team4Folder, team5Folder,team6Folder );
    MediaPlayerUtils mediaPlayerUtils = new MediaPlayerUtils(this.COUNT_IMAGE_PER_TEAM);
    team1Gifts=mediaPlayerUtils.teamGifts(team1GiftNames);
    team2Gifts=mediaPlayerUtils.teamGifts(team2GiftNames);
    team3Gifts=mediaPlayerUtils.teamGifts(team3GiftNames);
    team4Gifts=mediaPlayerUtils.teamGifts(team4GiftNames);
    team5Gifts=mediaPlayerUtils.teamGifts(team5GiftNames);
    team6Gifts=mediaPlayerUtils.teamGifts(team6GiftNames);
    editHBoxes(mediaPlayerUtils);
    editVideos(mediaPlayerUtils);
    createMusicPlayers(mediaPlayerUtils);
    editMusicPlayers();
    startNeutralMusic();
    createTimelineTeams(mediaPlayerUtils);
    startConsumer();
    this.extendedTime=extendedTime;
    timer_label.toFront();
    if (time>0){
      this.time=time;
      createTimer();
    }else{
      this.time=Integer.MAX_VALUE;
    }
  }

  private void createTimelineTeams(MediaPlayerUtils mediaPlayerUtils) {
    LOGGER.debug("createTimelineTeams");
    team1Timeline=createTimelineTeams(mediaPlayerUtils.createVolumeFiles(this.COMMENTARY_URL_TEAM1));
    team2Timeline=createTimelineTeams(mediaPlayerUtils.createVolumeFiles(this.COMMENTARY_URL_TEAM2));
    team3Timeline=createTimelineTeams(mediaPlayerUtils.createVolumeFiles(this.COMMENTARY_URL_TEAM3));
    team4Timeline=createTimelineTeams(mediaPlayerUtils.createVolumeFiles(this.COMMENTARY_URL_TEAM4));
    team5Timeline=createTimelineTeams(mediaPlayerUtils.createVolumeFiles(this.COMMENTARY_URL_TEAM5));
    team6Timeline=createTimelineTeams(mediaPlayerUtils.createVolumeFiles(this.COMMENTARY_URL_TEAM6));
  }

  private void createMusicPlayers(MediaPlayerUtils mediaPlayerUtils) {
    LOGGER.debug("createMusicPlayerss");
    team1MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(this.MARS_URL_TEAM1);
    team2MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(this.MARS_URL_TEAM2);
    team3MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(this.MARS_URL_TEAM3);
    team4MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(this.MARS_URL_TEAM4);
    team5MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(this.MARS_URL_TEAM5);
    team6MusicMediaPlayers = mediaPlayerUtils.createVolumeFiles(this.MARS_URL_TEAM6);
  }

  private void editMusicPlayers() {
    LOGGER.debug("editMusicPlayers");
    editMusicPlayers(team1MusicMediaPlayers);
    editMusicPlayers(team2MusicMediaPlayers);
    editMusicPlayers(team3MusicMediaPlayers);
    editMusicPlayers(team4MusicMediaPlayers);
    editMusicPlayers(team5MusicMediaPlayers);
    editMusicPlayers(team6MusicMediaPlayers);
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
        LOGGER.debug("Oynatılacak indexler : " + currentTeamIndex);
        MediaPlayer mediaPlayer = musicPlayers.get(currentTeamIndex);
        mediaPlayer.seek(Duration.ZERO);
        sharing_music_race4.setMediaPlayer(mediaPlayer);
        sharing_music_race4.getMediaPlayer().play();
      });
    }
  }
  private void startNeutralMusic(){
    LOGGER.debug("startNeutralMusic");
    Media neutralMusic = new Media(Paths.get(NEUTRAL_MUSIC_URL).toUri().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(neutralMusic);
    mediaPlayer1.setStartTime(Duration.seconds(10));
    mediaPlayer1.setVolume(80);
    mediaPlayer1.setCycleCount(100);
    sharing_music_race4.setMediaPlayer(mediaPlayer1);
    mediaPlayer1.setAutoPlay(true);
  }
  private void editVideos(MediaPlayerUtils mediaPlayerUtils){
    LOGGER.debug("editVideos");
    video_team1.setMediaPlayer(mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM1));
    video_team2.setMediaPlayer(mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM2));
    video_team3.setMediaPlayer(mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM3));
    video_team4.setMediaPlayer(mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM4));
    video_team5.setMediaPlayer(mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM5));
    video_team6.setMediaPlayer(mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM6));
    video_team1.getMediaPlayer().setOnError(()->setOnErrorVideo1(mediaPlayerUtils));
    video_team2.getMediaPlayer().setOnError(()->setOnErrorVideo2(mediaPlayerUtils));
    video_team3.getMediaPlayer().setOnError(()->setOnErrorVideo3(mediaPlayerUtils));
    video_team4.getMediaPlayer().setOnError(()->setOnErrorVideo4(mediaPlayerUtils));
    video_team5.getMediaPlayer().setOnError(()->setOnErrorVideo5(mediaPlayerUtils));
    video_team6.getMediaPlayer().setOnError(()->setOnErrorVideo6(mediaPlayerUtils));
    video_team1.getMediaPlayer().play();
    video_team2.getMediaPlayer().play();
    video_team3.getMediaPlayer().play();
    video_team4.getMediaPlayer().play();
    video_team5.getMediaPlayer().play();
    video_team6.getMediaPlayer().play();
  }
  private void editHBoxes(MediaPlayerUtils mediaPlayerUtils) {
    LOGGER.debug("editHBoxes");
    hbox_team1.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team1Gifts));
    hbox_team2.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team2Gifts));
    hbox_team3.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team3Gifts));
    hbox_team4.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team4Gifts));
    hbox_team5.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team5Gifts));
    hbox_team6.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team6Gifts));
  }

  private void editFolders(String team1Folder, String team2Folder, String team3Folder, String team4Folder,
                           String team5Folder,String team6Folder) {
    LOGGER.debug("editFolders");
    this.COMMENTARY_URL_TEAM1 = team1Folder + "/belgesel";
    this.COMMENTARY_URL_TEAM2 = team2Folder + "/belgesel";
    this.COMMENTARY_URL_TEAM3 = team3Folder + "/belgesel";
    this.COMMENTARY_URL_TEAM4 = team4Folder + "/belgesel";
    this.COMMENTARY_URL_TEAM5 = team5Folder + "/belgesel";
    this.COMMENTARY_URL_TEAM6 = team6Folder + "/belgesel";
    this.MARS_URL_TEAM1 = team1Folder + "/mars";
    this.MARS_URL_TEAM2 = team2Folder + "/mars";
    this.MARS_URL_TEAM3 = team3Folder + "/mars";
    this.MARS_URL_TEAM4 = team4Folder + "/mars";
    this.MARS_URL_TEAM5 = team5Folder + "/mars";
    this.MARS_URL_TEAM6 = team6Folder + "/mars";
    this.VIDEO_URL_TEAM1 = team1Folder + "/video";
    this.VIDEO_URL_TEAM2 = team2Folder + "/video";
    this.VIDEO_URL_TEAM3 = team3Folder + "/video";
    this.VIDEO_URL_TEAM4 = team4Folder + "/video";
    this.VIDEO_URL_TEAM5 = team5Folder + "/video";
    this.VIDEO_URL_TEAM6 = team6Folder + "/video";
    this.WINNING_VIDEO_TEAM1 = team1Folder + "/winning_video";
    this.WINNING_VIDEO_TEAM2 = team2Folder + "/winning_video";
    this.WINNING_VIDEO_TEAM3 = team3Folder + "/winning_video";
    this.WINNING_VIDEO_TEAM4 = team4Folder + "/winning_video";
    this.WINNING_VIDEO_TEAM5 = team5Folder + "/winning_video";
    this.WINNING_VIDEO_TEAM6 = team6Folder + "/winning_video";
  }
  private void setOnErrorVideo1(MediaPlayerUtils mediaPlayerUtils){
    LOGGER.error("1Videoda hata");
    video_team1.getMediaPlayer().stop();
    video_team1.getMediaPlayer().dispose();
    MediaPlayer video = mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM1);
    video.setOnError(()-> {
      setOnErrorVideo1(mediaPlayerUtils);
    });
    video_team1.setMediaPlayer(video);
  }
  private void setOnErrorVideo2(MediaPlayerUtils mediaPlayerUtils){
    LOGGER.error("2Videoda hata");
    video_team2.getMediaPlayer().stop();
    video_team2.getMediaPlayer().dispose();
    MediaPlayer video = mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM2);
    video.setOnError(()-> {
      setOnErrorVideo2(mediaPlayerUtils);
    });
    video_team2.setMediaPlayer(video);
  }
  private void setOnErrorVideo3(MediaPlayerUtils mediaPlayerUtils){
    LOGGER.error("3Videoda hata");
    video_team3.getMediaPlayer().stop();
    video_team3.getMediaPlayer().dispose();
    MediaPlayer video = mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM3);
    video.setOnError(()-> {
      setOnErrorVideo3(mediaPlayerUtils);
    });
    video_team3.setMediaPlayer(video);
  }
  private void setOnErrorVideo4(MediaPlayerUtils mediaPlayerUtils){
    LOGGER.error("4Videoda hata");
    video_team4.getMediaPlayer().stop();
    video_team4.getMediaPlayer().dispose();
    MediaPlayer video = mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM4);
    video.setOnError(()-> {
      setOnErrorVideo4(mediaPlayerUtils);
    });
    video_team4.setMediaPlayer(video);
  }
  private void setOnErrorVideo5(MediaPlayerUtils mediaPlayerUtils){
    LOGGER.error("5Videoda hata");
    video_team5.getMediaPlayer().stop();
    video_team5.getMediaPlayer().dispose();
    MediaPlayer video = mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM5);
    video.setOnError(()-> {
      setOnErrorVideo5(mediaPlayerUtils);
    });
    video_team5.setMediaPlayer(video);
  }
  private void setOnErrorVideo6(MediaPlayerUtils mediaPlayerUtils){
    LOGGER.error("6Videoda hata");
    video_team6.getMediaPlayer().stop();
    video_team6.getMediaPlayer().dispose();
    MediaPlayer video = mediaPlayerUtils.getVideo(this.VIDEO_URL_TEAM6);
    video.setOnError(()-> {
      setOnErrorVideo6(mediaPlayerUtils);
    });
    video_team6.setMediaPlayer(video);
  }
  private Timeline createTimelineTeams(List<MediaPlayer> teamCommentaries) {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
      LOGGER.debug("Takım Belgeseli başlıyor");
      if (sharing_commentary_race4.getMediaPlayer() != null) {
        sharing_commentary_race4.getMediaPlayer().stop();
      }
      sharing_commentary_race4.setMediaPlayer(teamCommentaries.get(commentaryCurrentIndex));
      sharing_music_race4.getMediaPlayer().setVolume(20);
      sharing_commentary_race4.getMediaPlayer().play();
      if (commentaryCurrentIndex >= teamCommentaries.size() - 1) {
        commentaryCurrentIndex = 0;
      } else {
        commentaryCurrentIndex += 1;
      }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    return timeline;
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
                  Platform.runLater(() -> label_like_race4.setText("Son Beğenen : " + ((String) finalValue.get(1))));
                  break;
                }
                case "Comment": {
                  ArrayList finalValue = value;
                  Platform.runLater(() -> label_comment_race4.setText("Son Yorum Yapan : " + ((String) finalValue.get(1))));
                  break;
                }
                case "Gift", "GiftCombo": {
                  ArrayList finalValue = value;
                  String name = ((String) finalValue.get(4));
                  int hediye_miktari = ((int) finalValue.get(2)) * ((int) finalValue.get(3));
                  LOGGER.debug("Gift : " + name + " Gift Toplam Miktari : " + hediye_miktari);
                  Optional<Gift> gift1 = team1Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  Optional<Gift> gift2 = team2Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  Optional<Gift> gift3 = team3Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  Optional<Gift> gift4 = team4Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  Optional<Gift> gift5 = team5Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  Optional<Gift> gift6 = team6Gifts.stream().filter(gift -> gift.getName().equals(name)).findAny();
                  String labelPoint1 = point_team1.getText();
                  String labelPoint2 = point_team2.getText();
                  String labelPoint3 = point_team3.getText();
                  String labelPoint4 = point_team4.getText();
                  String labelPoint5 = point_team5.getText();
                  String labelPoint6 = point_team6.getText();
                  int point1 = Integer.parseInt(labelPoint1);
                  int point2 = Integer.parseInt(labelPoint2);
                  int point3 = Integer.parseInt(labelPoint3);
                  int point4 = Integer.parseInt(labelPoint4);
                  int point5 = Integer.parseInt(labelPoint5);
                  int point6 = Integer.parseInt(labelPoint6);
                  if (gift1.isPresent()) {
                    if ((point1 <= point2 || point1 <= point3 || point1<=point4 || point1<=point5 || point1<=point6) &&
                            (point1 + hediye_miktari > point4 && point1 + hediye_miktari > point2
                                    && point1 + hediye_miktari > point3 && point1 +hediye_miktari>point5 &&
                                    point1+hediye_miktari>point6)) {
                      LOGGER.debug("Takım Değişti Team1");
                      increaseTime();
                      currentTeamIndex = 0;
                      sharing_music_race4.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team1MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_race4.setMediaPlayer(mediaPlayer);
                      sharing_music_race4.getMediaPlayer().play();
                      if (sharing_commentary_race4.getMediaPlayer() != null) {
                        sharing_commentary_race4.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team2Timeline.stop();
                      team3Timeline.stop();
                      team4Timeline.stop();
                      team5Timeline.stop();
                      team6Timeline.stop();
                      team1Timeline.play();
                      Platform.runLater(() -> {
                        point_team1.setText(String.valueOf(point1 + hediye_miktari));
                        point_team1.setTextFill(Color.DARKGREEN);
                        point_team1.setBackground(whiteBackground());
                        point_team4.setTextFill(Color.WHITE);
                        point_team4.setBackground(grayBackground());
                        point_team2.setTextFill(Color.WHITE);
                        point_team2.setBackground(grayBackground());
                        point_team3.setTextFill(Color.WHITE);
                        point_team3.setBackground(grayBackground());
                        point_team6.setTextFill(Color.WHITE);
                        point_team6.setBackground(grayBackground());
                        point_team5.setTextFill(Color.WHITE);
                        point_team5.setBackground(grayBackground());
                      });
                    } else {
                      Platform.runLater(() -> {
                        point_team1.setText(String.valueOf(point1 + hediye_miktari));
                      });
                    }
                  } else if (gift2.isPresent()) {
                    if ((point2 <= point1 || point2 <= point3 || point2<=point4 || point2<=point5 || point2<=point6) &&
                            (point2 + hediye_miktari > point1 && point2 + hediye_miktari > point3
                                    && point2 + hediye_miktari > point4 && point2+hediye_miktari>point5 &&
                                    point2+hediye_miktari>point6)) {
                      LOGGER.debug("Takım Değişti Team2");
                      increaseTime();
                      currentTeamIndex = 0;
                      sharing_music_race4.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team2MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_race4.setMediaPlayer(mediaPlayer);
                      sharing_music_race4.getMediaPlayer().play();
                      if (sharing_commentary_race4.getMediaPlayer() != null) {
                        sharing_commentary_race4.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team1Timeline.stop();
                      team3Timeline.stop();
                      team4Timeline.stop();
                      team6Timeline.stop();
                      team5Timeline.stop();
                      team2Timeline.play();
                      Platform.runLater(() -> {
                        point_team2.setText(String.valueOf(point2 + hediye_miktari));
                        point_team2.setTextFill(Color.DARKGREEN);
                        point_team2.setBackground(whiteBackground());
                        point_team4.setTextFill(Color.WHITE);
                        point_team4.setBackground(grayBackground());
                        point_team1.setTextFill(Color.WHITE);
                        point_team1.setBackground(grayBackground());
                        point_team3.setTextFill(Color.WHITE);
                        point_team3.setBackground(grayBackground());
                        point_team6.setTextFill(Color.WHITE);
                        point_team6.setBackground(grayBackground());
                        point_team5.setTextFill(Color.WHITE);
                        point_team5.setBackground(grayBackground());
                      });
                    } else {
                      Platform.runLater(() -> {
                        point_team2.setText(String.valueOf(point2 + hediye_miktari));
                      });
                    }
                  }else if (gift3.isPresent()) {
                    if ((point3 <= point1 || point3 <= point2 || point3<=point4 || point3<= point5 || point3<=point6) &&
                            (point3 + hediye_miktari > point1 && point3 + hediye_miktari > point2
                                    && point3 + hediye_miktari > point4 && point3+hediye_miktari>point5 &&
                                    point3+hediye_miktari>point6)) {
                      LOGGER.debug("Takım Değişti Team3");
                      increaseTime();
                      currentTeamIndex = 0;
                      sharing_music_race4.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team3MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_race4.setMediaPlayer(mediaPlayer);
                      sharing_music_race4.getMediaPlayer().play();
                      if (sharing_commentary_race4.getMediaPlayer() != null) {
                        sharing_commentary_race4.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team2Timeline.stop();
                      team1Timeline.stop();
                      team4Timeline.stop();
                      team5Timeline.stop();
                      team6Timeline.stop();
                      team3Timeline.play();
                      Platform.runLater(() -> {
                        point_team3.setText(String.valueOf(point3 + hediye_miktari));
                        point_team3.setTextFill(Color.DARKGREEN);
                        point_team3.setBackground(whiteBackground());
                        point_team4.setTextFill(Color.WHITE);
                        point_team4.setBackground(grayBackground());
                        point_team1.setTextFill(Color.WHITE);
                        point_team1.setBackground(grayBackground());
                        point_team2.setTextFill(Color.WHITE);
                        point_team2.setBackground(grayBackground());
                        point_team5.setTextFill(Color.WHITE);
                        point_team5.setBackground(grayBackground());
                        point_team6.setTextFill(Color.WHITE);
                        point_team6.setBackground(grayBackground());
                      });
                    }else {
                      Platform.runLater(() -> {
                        point_team3.setText(String.valueOf(point3 + hediye_miktari));
                      });
                    }
                  }else if (gift4.isPresent()) {
                    if ((point4 <= point1 || point4 <= point3 || point4<=point2 || point4<=point5 || point4<= point6) &&
                            (point4 + hediye_miktari > point1 && point4 + hediye_miktari > point3
                                    && point4 + hediye_miktari > point2 && point4 + hediye_miktari>point5 &&
                                    point4+hediye_miktari>point6)) {
                      LOGGER.debug("Takım Değişti Team4");
                      increaseTime();
                      currentTeamIndex = 0;
                      sharing_music_race4.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team4MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_race4.setMediaPlayer(mediaPlayer);
                      sharing_music_race4.getMediaPlayer().play();
                      if (sharing_commentary_race4.getMediaPlayer() != null) {
                        sharing_commentary_race4.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team2Timeline.stop();
                      team3Timeline.stop();
                      team1Timeline.stop();
                      team5Timeline.stop();
                      team6Timeline.stop();
                      team4Timeline.play();
                      Platform.runLater(() -> {
                        point_team4.setText(String.valueOf(point4 + hediye_miktari));
                        point_team4.setTextFill(Color.DARKGREEN);
                        point_team4.setBackground(whiteBackground());
                        point_team1.setTextFill(Color.WHITE);
                        point_team1.setBackground(grayBackground());
                        point_team2.setTextFill(Color.WHITE);
                        point_team2.setBackground(grayBackground());
                        point_team3.setTextFill(Color.WHITE);
                        point_team3.setBackground(grayBackground());
                        point_team5.setTextFill(Color.WHITE);
                        point_team5.setBackground(grayBackground());
                        point_team6.setTextFill(Color.WHITE);
                        point_team6.setBackground(grayBackground());
                      });
                    } else {
                      Platform.runLater(() -> {
                        point_team4.setText(String.valueOf(point4 + hediye_miktari));
                      });
                    }
                  }else if (gift5.isPresent()) {
                    if ((point5 <= point1 || point5 <= point3 || point5<=point2 || point5<=point4 || point5<= point6) &&
                            (point5 + hediye_miktari > point1 && point5 + hediye_miktari > point2
                                    && point5 + hediye_miktari > point3 && point5 + hediye_miktari>point4 &&
                                    point5+hediye_miktari>point6)) {
                      LOGGER.debug("Takım Değişti Team5");
                      increaseTime();
                      currentTeamIndex = 0;
                      sharing_music_race4.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team4MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_race4.setMediaPlayer(mediaPlayer);
                      sharing_music_race4.getMediaPlayer().play();
                      if (sharing_commentary_race4.getMediaPlayer() != null) {
                        sharing_commentary_race4.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team2Timeline.stop();
                      team3Timeline.stop();
                      team1Timeline.stop();
                      team5Timeline.play();
                      team6Timeline.stop();
                      team4Timeline.stop();
                      Platform.runLater(() -> {
                        point_team5.setText(String.valueOf(point5 + hediye_miktari));
                        point_team4.setTextFill(Color.WHITE);
                        point_team4.setBackground(grayBackground());
                        point_team1.setTextFill(Color.WHITE);
                        point_team1.setBackground(grayBackground());
                        point_team2.setTextFill(Color.WHITE);
                        point_team2.setBackground(grayBackground());
                        point_team3.setTextFill(Color.WHITE);
                        point_team3.setBackground(grayBackground());
                        point_team5.setTextFill(Color.DARKGREEN);
                        point_team5.setBackground(whiteBackground());
                        point_team6.setTextFill(Color.WHITE);
                        point_team6.setBackground(grayBackground());
                      });
                    } else {
                      Platform.runLater(() -> {
                        point_team5.setText(String.valueOf(point5 + hediye_miktari));
                      });
                    }
                  }
                  else if (gift6.isPresent()) {
                    if ((point6 <= point1 || point6 <= point3 || point6<=point2 || point6<=point5 || point6<= point4) &&
                            (point6 + hediye_miktari > point1 && point6 + hediye_miktari > point3
                                    && point6 + hediye_miktari > point2 && point6 + hediye_miktari>point5 &&
                                    point6+hediye_miktari>point4)) {
                      LOGGER.debug("Takım Değişti Team6");
                      increaseTime();
                      currentTeamIndex = 0;
                      sharing_music_race4.getMediaPlayer().stop();
                      MediaPlayer mediaPlayer = team4MusicMediaPlayers.get(currentTeamIndex);
                      mediaPlayer.seek(Duration.ZERO);
                      sharing_music_race4.setMediaPlayer(mediaPlayer);
                      sharing_music_race4.getMediaPlayer().play();
                      if (sharing_commentary_race4.getMediaPlayer() != null) {
                        sharing_commentary_race4.getMediaPlayer().stop();
                      }
                      commentaryCurrentIndex = 0;
                      team2Timeline.stop();
                      team3Timeline.stop();
                      team1Timeline.stop();
                      team5Timeline.stop();
                      team6Timeline.play();
                      team4Timeline.stop();
                      Platform.runLater(() -> {
                        point_team6.setText(String.valueOf(point6 + hediye_miktari));
                        point_team4.setTextFill(Color.WHITE);
                        point_team4.setBackground(grayBackground());
                        point_team1.setTextFill(Color.WHITE);
                        point_team1.setBackground(grayBackground());
                        point_team2.setTextFill(Color.WHITE);
                        point_team2.setBackground(grayBackground());
                        point_team3.setTextFill(Color.WHITE);
                        point_team3.setBackground(grayBackground());
                        point_team5.setTextFill(Color.WHITE);
                        point_team5.setBackground(grayBackground());
                        point_team6.setTextFill(Color.DARKGREEN);
                        point_team6.setBackground(whiteBackground());
                      });
                    } else {
                      Platform.runLater(() -> {
                        point_team6.setText(String.valueOf(point6 + hediye_miktari));
                      });
                    }
                  }
                  else {
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
  private Background whiteBackground(){
    BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY);
    return new Background(backgroundFill);
  }
  private Background grayBackground(){
    BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#A9A9A9"), CornerRadii.EMPTY, Insets.EMPTY);
    return new Background(backgroundFill);
  }
  public void close() {
    LOGGER.debug("Kapatma isteği");
    this.time=-1;
    threadFlag=false;
    team1Timeline.stop();
    team2Timeline.stop();
    team3Timeline.stop();
    team4Timeline.stop();
    team5Timeline.stop();
    team6Timeline.stop();
    if (sharing_commentary_race4.getMediaPlayer() != null) {
      sharing_commentary_race4.getMediaPlayer().stop();
    }
    if (sharing_music_race4.getMediaPlayer() != null) {
      sharing_music_race4.getMediaPlayer().stop();
    }
    if (video_team1.getMediaPlayer() != null) {
      video_team1.getMediaPlayer().stop();
    }
    if (video_team2.getMediaPlayer() != null) {
      video_team2.getMediaPlayer().stop();
    }
    if (video_team3.getMediaPlayer() != null) {
      video_team3.getMediaPlayer().stop();
    }
    if (video_team4.getMediaPlayer() != null) {
      video_team4.getMediaPlayer().stop();
    }
    if (video_team5.getMediaPlayer() != null) {
      video_team5.getMediaPlayer().stop();
    }
    if (video_team6.getMediaPlayer() != null) {
      video_team6.getMediaPlayer().stop();
    }
    if (winning_team_video.getMediaPlayer()!=null){
     winning_team_video.getMediaPlayer().stop();
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
  @Override
  public void increaseTime(int time) {
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
    ObservableList<Node> children = stackpane1.getChildren();
    double width = stackpane1.getWidth();
    double height = stackpane1.getHeight();
    children.removeAll(children);
    timer_label.setVisible(false);
    team1Timeline.stop();
    team2Timeline.stop();
    team3Timeline.stop();
    team4Timeline.stop();
    team5Timeline.stop();
    team6Timeline.stop();
    if (sharing_commentary_race4.getMediaPlayer()!=null) {
      sharing_commentary_race4.getMediaPlayer().stop();
    }
    if (sharing_music_race4.getMediaPlayer()!=null) {
      sharing_music_race4.getMediaPlayer().stop();
    }
    winning_team_video.setPreserveRatio(false);
    winning_team_video.setFitWidth(width);
    winning_team_video.setFitHeight(height);
    int team1point = Integer.parseInt(point_team1.getText());
    int team2point = Integer.parseInt(point_team2.getText());
    int team3point = Integer.parseInt(point_team3.getText());
    int team4point = Integer.parseInt(point_team4.getText());
    int team5point = Integer.parseInt(point_team5.getText());
    int team6point = Integer.parseInt(point_team6.getText());
    MediaPlayerUtils mediaPlayerUtils = new MediaPlayerUtils();
    MediaPlayer video;
    if (team1point > team2point && team1point > team3point && team1point>team4point
            && team1point>team5point && team1point>team6point) {
      video = mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM1);
    } else if (team2point > team1point && team2point > team3point && team2point>team4point
            && team2point > team5point && team2point > team6point) {
      video = mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM2);
    } else if (team3point>team4point && team3point>team2point && team3point > team1point &&
      team3point > team5point && team3point > team6point){
      video = mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM3);
    }else if (team4point>team3point && team4point>team2point && team4point > team1point &&
            team4point > team5point && team4point > team6point) {
      video=mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM4);
    }else if (team5point>team3point && team5point>team2point && team5point > team1point &&
            team5point > team4point && team5point > team6point) {
      video=mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM5);
    }else {
      video=mediaPlayerUtils.getVideo(this.WINNING_VIDEO_TEAM6);
    }
    video.setVolume(100);
    winning_team_video.setMediaPlayer(video);
    grid_pane1.add(winning_team_video,0,2,4,7);
    winning_team_video.setVisible(true);
    winning_team_video.getMediaPlayer().play();
  }
}
