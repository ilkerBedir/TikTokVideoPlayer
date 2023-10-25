package com.example.tiktokvideoplayer;

import com.example.tiktokvideoplayer.utils.MediaPlayerUtils;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.List;

public class FourTeamsController {
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
  private MediaView sharing_music_race4;
  @FXML
  private MediaView sharing_commentart_race4;
  private String NEUTRAL_MUSIC_URL;
  private String COMMENTARY_URL_TEAM1;
  private String COMMENTARY_URL_TEAM2;
  private String COMMENTARY_URL_TEAM3;
  private String COMMENTARY_URL_TEAM4;
  private String VIDEO_URL_TEAM1;
  private String VIDEO_URL_TEAM2;
  private String VIDEO_URL_TEAM3;
  private String VIDEO_URL_TEAM4;
  private String MARS_URL_TEAM1;
  private String MARS_URL_TEAM2;
  private String MARS_URL_TEAM3;
  private String MARS_URL_TEAM4;
  private int COUNT_IMAGE_PER_TEAM;
  private MediaPlayer team1VideoMediaPlayer;
  private MediaPlayer team2VideoMediaPlayer;
  private MediaPlayer team3VideoMediaPlayer;
  private MediaPlayer team4VideoMediaPlayer;
  private List<MediaPlayer> team1MusicMediaPlayers;
  private List<MediaPlayer> team2MusicMediaPlayers;
  private List<MediaPlayer> team3MusicMediaPlayers;
  private List<MediaPlayer> team4MusicMediaPlayers;
  private List<MediaPlayer> team1CommentaryMediaPlayers;
  private List<MediaPlayer> team2CommentaryMediaPlayers;
  private List<MediaPlayer> team3CommentaryMediaPlayers;
  private List<MediaPlayer> team4CommentaryMediaPlayers;
  private Timeline team1Timeline;
  private Timeline team2Timeline;
  private Timeline team3Timeline;
  private Timeline team4Timeline;
  private int currentTeamIndex = 0;
  private int commentaryCurrentIndex = 0;
  private List<Gift> gifts = Gift.giftList();
  private List<Gift> team1Gifts;
  private List<Gift> team2Gifts;
  private List<Gift> team3Gifts;
  private List<Gift> team4Gifts;
  public void display(String team1Folder,String team2Folder,String team3Folder,String team4Folder,String neutralMusicPath
                      ,int countGiftImage,List<String> team1GiftNames,List<String> team2GiftNames,List<String> team3GiftNames
                      ,List<String> team4GiftNames
  ) {
    this.NEUTRAL_MUSIC_URL=neutralMusicPath;
    this.COUNT_IMAGE_PER_TEAM=countGiftImage;
    editFolders(team1Folder,team2Folder,team3Folder,team4Folder);
    MediaPlayerUtils mediaPlayerUtils = new MediaPlayerUtils(this.COUNT_IMAGE_PER_TEAM);
    team1Gifts=mediaPlayerUtils.teamGifts(team1GiftNames);
    team2Gifts=mediaPlayerUtils.teamGifts(team2GiftNames);
    team3Gifts=mediaPlayerUtils.teamGifts(team3GiftNames);
    team4Gifts=mediaPlayerUtils.teamGifts(team4GiftNames);
    editHBoxes(mediaPlayerUtils);
  }

  private void editHBoxes(MediaPlayerUtils mediaPlayerUtils) {
    hbox_team1.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team1Gifts));
    hbox_team2.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team2Gifts));
    hbox_team3.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team3Gifts));
    hbox_team4.getChildren().addAll(mediaPlayerUtils.createGiftsHBOX(team4Gifts));
  }

  private void editFolders(String team1Folder, String team2Folder, String team3Folder, String team4Folder) {
    this.COMMENTARY_URL_TEAM1 = team1Folder + "\\belgesel";
    this.COMMENTARY_URL_TEAM2 = team2Folder + "\\belgesel";
    this.COMMENTARY_URL_TEAM3 = team3Folder + "\\belgesel";
    this.COMMENTARY_URL_TEAM4 = team4Folder + "\\belgesel";
    this.MARS_URL_TEAM1 = team1Folder + "\\mars";
    this.MARS_URL_TEAM2 = team2Folder + "\\mars";
    this.MARS_URL_TEAM3 = team3Folder + "\\mars";
    this.MARS_URL_TEAM4 = team4Folder + "\\mars";
    this.VIDEO_URL_TEAM1 = team1Folder + "\\video";
    this.VIDEO_URL_TEAM2 = team2Folder + "\\video";
    this.VIDEO_URL_TEAM3 = team3Folder + "\\video";
    this.VIDEO_URL_TEAM4 = team4Folder + "\\video";
  }
}
