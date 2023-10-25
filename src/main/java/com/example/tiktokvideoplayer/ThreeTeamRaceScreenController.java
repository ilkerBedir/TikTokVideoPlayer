package com.example.tiktokvideoplayer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreeTeamRaceScreenController implements Initializable {

    @FXML
    MediaView team1_video;
    @FXML
    MediaView team2_video;
    @FXML
    MediaView team3_video;
    @FXML
    MediaView neutral_music;
    @FXML
    MediaView team1_music;
    @FXML
    MediaView team1_commentary;
    @FXML
    MediaView team2_music;
    @FXML
    MediaView team2_commentary;
    @FXML
    MediaView team3_music;
    @FXML
    MediaView team3_commentary;
    @FXML
    Label team1_puan;
    @FXML
    Label team2_puan;
    @FXML
    Label team3_puan;
    @FXML
    Label last_like;
    @FXML
    Label last_comment;
    @FXML
    HBox team1_gifts;
    @FXML
    HBox team2_gifts;
    @FXML
    HBox team3_gifts;
    Timeline team1_timeline;
    Timeline team2_timeline;
    Timeline team3_timeline;

    private static final String NEUTRAL_MUSIC_URL = "tarafsiz/1.mp3";
    private static final String TEAM1_VIDEO_URL = "takimlar/fb/video/1.mp4";
    private static final String TEAM1_MARS_URL = "takimlar/fb/mars/";
    private static final String TEAM1_COMMENTARY_URL = "takimlar/fb/belgesel/";
    private static final String TEAM2_VIDEO_URL = "takimlar/bjk/video/1.mp4";
    private static final String TEAM2_MARS_URL = "takimlar/bjk/mars/";
    private static final String TEAM2_COMMENTARY_URL = "takimlar/bjk/belgesel/";
    private static final String TEAM3_VIDEO_URL = "takimlar/gs/video/1.mp4";
    private static final String TEAM3_MARS_URL = "takimlar/gs/mars/";
    private static final String TEAM3_COMMENTARY_URL = "takimlar/gs/belgesel/";
    private static final int COUNT_IMAGE_PER_TEAM = 4;
    private static AtomicInteger currentTeam=new AtomicInteger(0);
    List<Gift> gifts = Gift.giftList();

    private List<Gift> team1_giftList() {
        return List.of(gifts.get(108), gifts.get(45), gifts.get(387), gifts.get(357));
    }

    ;

    private List<Gift> team2_giftList() {
        return List.of(gifts.get(519), gifts.get(495), gifts.get(413), gifts.get(500));
    }

    ;

    private List<Gift> team3_giftList() {
        return List.of(gifts.get(212), gifts.get(340), gifts.get(451), gifts.get(106));
    }

    ;

    private List<MediaPlayer> team1_musicPlayers;
    private List<MediaPlayer> team1_commentaryPlayers;
    private int team1_commentaryCurrentIndex = 0;
    private int team1_musicCurrentIndex = 0;
    private List<MediaPlayer> team2_musicPlayers;
    private List<MediaPlayer> team2_commentaryPlayers;
    private int team2_commentaryCurrentIndex = 0;
    private int team2_musicCurrentIndex = 0;
    private List<MediaPlayer> team3_musicPlayers;
    private List<MediaPlayer> team3_commentaryPlayers;
    private int team3_commentaryCurrentIndex = 0;
    private int team3_musicCurrentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startNeutralMusic();
        team1_musicPlayers = listMediaFiles(TEAM1_MARS_URL);
        team2_musicPlayers = listMediaFiles(TEAM2_MARS_URL);
        team3_musicPlayers = listMediaFiles(TEAM3_MARS_URL);
        editMediaPlayersTeam1();
        editMediaPlayersTeam2();
        editMediaPlayersTeam3();
        team1_commentaryPlayers = listMediaFiles(TEAM1_COMMENTARY_URL);
        team2_commentaryPlayers = listMediaFiles(TEAM2_COMMENTARY_URL);
        team3_commentaryPlayers = listMediaFiles(TEAM3_COMMENTARY_URL);
        team1_video.setMediaPlayer(startTeamVideo(TEAM1_VIDEO_URL));
        team2_video.setMediaPlayer(startTeamVideo(TEAM2_VIDEO_URL));
        team3_video.setMediaPlayer(startTeamVideo(TEAM3_VIDEO_URL));
        createTimelineTeam1();
        createTimelineTeam2();
        createTimelineTeam3();
        team1_gifts.getChildren().addAll(getImageViewsFromGifts(team1_giftList()));
        team2_gifts.getChildren().addAll(getImageViewsFromGifts(team2_giftList()));
        team3_gifts.getChildren().addAll(getImageViewsFromGifts(team3_giftList()));
        startConsumer();
    }

    private List<ImageView> getImageViewsFromGifts(List<Gift> giftList) {
        ArrayList<ImageView> objects = new ArrayList<>();
        for (Gift gift : giftList) {
            objects.add(createImageView(gift.image()));
        }
        return objects;
    }

    private ImageView createImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(200 / (COUNT_IMAGE_PER_TEAM + 1));
        imageView.setFitWidth(200 / (COUNT_IMAGE_PER_TEAM + 1));
        return imageView;
    }

    private void startNeutralMusic() {
        Media neutralMusic = new Media(Paths.get(NEUTRAL_MUSIC_URL).toUri().toString());
        MediaPlayer mediaPlayer1 = new MediaPlayer(neutralMusic);
        mediaPlayer1.setStartTime(Duration.seconds(10));
        mediaPlayer1.setVolume(80);
        mediaPlayer1.setCycleCount(100);
        neutral_music.setMediaPlayer(mediaPlayer1);
        mediaPlayer1.setAutoPlay(true);
    }

    private MediaPlayer startTeamVideo(String url) {
        Media media = new Media(Paths.get(url).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(999999);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0);
        return mediaPlayer;
    }

    private List<MediaPlayer> listMediaFiles(String url) {
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
                    mediaPlayers.add(mediaPlayer);
                }
            }
        }
        return mediaPlayers;
    }

    private void editMediaPlayersTeam1() {
        for (MediaPlayer team1MusicPlayer : team1_musicPlayers) {
            team1MusicPlayer.setOnEndOfMedia(() -> {
                if (team1_musicCurrentIndex >= team1_musicPlayers.size() - 1) {
                    team1_musicCurrentIndex = 0;
                } else {
                    team1_musicCurrentIndex += 1;
                }
                System.out.println("Oynatılacak indexler : " + team1_musicCurrentIndex);
                MediaPlayer mediaPlayer = team1_musicPlayers.get(team1_musicCurrentIndex);
                mediaPlayer.seek(Duration.ZERO);
                team1_music.setMediaPlayer(mediaPlayer);
                team1_music.getMediaPlayer().play();
            });
        }
    }

    private void createTimelineTeam1() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Timeline");
                if (team1_commentary.getMediaPlayer() != null) {
                    team1_commentary.getMediaPlayer().stop();
                }
                team1_commentary.setMediaPlayer(team1_commentaryPlayers.get(team1_commentaryCurrentIndex));
                team1_music.getMediaPlayer().setVolume(20);
                team1_commentary.getMediaPlayer().play();
                if (team1_commentaryCurrentIndex >= team1_commentaryPlayers.size() - 1) {
                    team1_commentaryCurrentIndex = 0;
                } else {
                    team1_commentaryCurrentIndex += 1;
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        team1_timeline = timeline;
    }

    private void createTimelineTeam2() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Timeline");
                if (team2_commentary.getMediaPlayer() != null) {
                    team2_commentary.getMediaPlayer().stop();
                }
                team2_commentary.setMediaPlayer(team2_commentaryPlayers.get(team2_commentaryCurrentIndex));
                team2_music.getMediaPlayer().setVolume(20);
                team2_commentary.getMediaPlayer().play();
                if (team2_commentaryCurrentIndex >= team2_commentaryPlayers.size() - 1) {
                    team2_commentaryCurrentIndex = 0;
                } else {
                    team2_commentaryCurrentIndex += 1;
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        team2_timeline = timeline;
    }

    private void createTimelineTeam3() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Timeline");
                if (team3_commentary.getMediaPlayer() != null) {
                    team3_commentary.getMediaPlayer().stop();
                }
                team3_commentary.setMediaPlayer(team3_commentaryPlayers.get(team3_commentaryCurrentIndex));
                if (team3_music.getMediaPlayer() != null) {
                    team3_music.getMediaPlayer().setVolume(20);
                }
                team3_commentary.getMediaPlayer().play();
                if (team3_commentaryCurrentIndex >= team3_commentaryPlayers.size() - 1) {
                    team3_commentaryCurrentIndex = 0;
                } else {
                    team3_commentaryCurrentIndex += 1;
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        team3_timeline = timeline;
    }

    private void editMediaPlayersTeam2() {
        for (MediaPlayer team2MusicPlayer : team2_musicPlayers) {
            team2MusicPlayer.setOnEndOfMedia(() -> {
                if (team2_musicCurrentIndex >= team2_musicPlayers.size() - 1) {
                    team2_musicCurrentIndex = 0;
                } else {
                    team2_musicCurrentIndex += 1;
                }
                System.out.println("Oynatılacak indexler : " + team2_musicCurrentIndex);
                MediaPlayer mediaPlayer = team2_musicPlayers.get(team2_musicCurrentIndex);
                mediaPlayer.seek(Duration.ZERO);
                team2_music.setMediaPlayer(mediaPlayer);
                team2_music.getMediaPlayer().play();
            });
        }
    }

    private void editMediaPlayersTeam3() {
        for (MediaPlayer team3MusicPlayer : team3_musicPlayers) {
            team3MusicPlayer.setOnEndOfMedia(() -> {
                if (team3_musicCurrentIndex >= team3_musicPlayers.size() - 1) {
                    team3_musicCurrentIndex = 0;
                } else {
                    team3_musicCurrentIndex += 1;
                }
                System.out.println("Oynatılacak indexler : " + team3_musicCurrentIndex);
                MediaPlayer mediaPlayer = team3_musicPlayers.get(team3_musicCurrentIndex);
                mediaPlayer.seek(Duration.ZERO);
                team3_music.setMediaPlayer(mediaPlayer);
                team3_music.getMediaPlayer().play();
            });
        }
    }

    private void startConsumer() {
        currentTeam.set(0);
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
                            System.out.println("MessageType : "+messageType);
                            switch (messageType) {
                                case "Like": {
                                    ArrayList finalValue = value;
                                    Platform.runLater(() -> last_like.setText("Son Beğenen : " + ((String) finalValue.get(1))));
                                    break;
                                }
                                case "Comment": {
                                    ArrayList finalValue = value;
                                    Platform.runLater(() -> last_comment.setText("Son Yorum Yapan : " + ((String) finalValue.get(1))));
                                    break;
                                }
                                case "Gift", "GiftCombo": {
                                    ArrayList finalValue = value;
                                    String name = ((String) finalValue.get(4));
                                    int hediye_miktari = ((int) finalValue.get(2)) * ((int) finalValue.get(3));
                                    System.out.println("Gift : "+name +" Gift Toplam Miktari : "+hediye_miktari);
                                    Optional<Gift> gift1 = team1_giftList().stream().filter(gift -> gift.getName().equals(name)).findAny();
                                    Optional<Gift> gift2 = team2_giftList().stream().filter(gift -> gift.getName().equals(name)).findAny();
                                    Optional<Gift> gift3 = team3_giftList().stream().filter(gift -> gift.getName().equals(name)).findAny();
                                    if (gift1.isPresent()) {
                                        int oldValue = Integer.parseInt(team1_puan.getText());
                                        if (isMaxPoint(oldValue, team2_puan, team3_puan) && currentTeam.get()!=1) {
                                            currentTeam.set(1);
                                            System.out.println("Yeni Takım FB");
                                            Platform.runLater(() -> {
                                                team1_puan.setText(String.valueOf(oldValue + hediye_miktari));
                                                team1_puan.setTextFill(Color.YELLOW);
                                                team3_puan.setTextFill(Color.WHITE);
                                                team2_puan.setTextFill(Color.WHITE);
                                                Font font = team1_puan.getFont();
                                                team3_puan.setFont(font);
                                                team2_puan.setFont(font);
                                                team1_puan.setFont(new Font(font.getName(), 50));
                                            });
                                            MediaPlayer tmp_mediaPlayer = team1_musicPlayers.get(team1_musicCurrentIndex);
                                            team1_music.setMediaPlayer(tmp_mediaPlayer);
                                            if (neutral_music.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                                                neutral_music.getMediaPlayer().stop();
                                            }
                                            if(team2_music.getMediaPlayer()!=null){
                                                team2_music.getMediaPlayer().stop();
                                            }
                                            if (team3_music.getMediaPlayer()!=null){
                                                team3_music.getMediaPlayer().stop();
                                            }
                                            team1_music.getMediaPlayer().play();
                                            team2_timeline.stop();
                                            team3_timeline.stop();
                                            if (team2_commentary.getMediaPlayer()!=null && team2_commentary.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                                                team2_commentary.getMediaPlayer().stop();
                                            }
                                            if (team3_commentary.getMediaPlayer()!=null && team3_commentary.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                                                team3_commentary.getMediaPlayer().stop();
                                            }
                                            team1_timeline.play();
                                        } else {
                                            Platform.runLater(() -> {
                                                team1_puan.setText(String.valueOf(oldValue + hediye_miktari));
                                            });
                                        }
                                    } else if (gift2.isPresent()) {
                                        int oldValue = Integer.parseInt(team2_puan.getText());
                                        if (isMaxPoint(oldValue, team1_puan, team3_puan) && currentTeam.get()!=2) {
                                            currentTeam.set(2);
                                            System.out.println("Yeni Takım BJK");
                                            Platform.runLater(() -> {
                                                team2_puan.setText(String.valueOf(oldValue + hediye_miktari));
                                                team2_puan.setTextFill(Color.YELLOW);
                                                team1_puan.setTextFill(Color.WHITE);
                                                team3_puan.setTextFill(Color.WHITE);
                                                Font font = team2_puan.getFont();
                                                team3_puan.setFont(font);
                                                team1_puan.setFont(font);
                                                team2_puan.setFont(new Font(font.getName(), 50));
                                            });
                                            MediaPlayer tmp_mediaPlayer = team2_musicPlayers.get(team2_musicCurrentIndex);
                                            team2_music.setMediaPlayer(tmp_mediaPlayer);
                                            neutral_music.getMediaPlayer().stop();
                                            if (team1_music.getMediaPlayer()!=null){
                                                team1_music.getMediaPlayer().stop();
                                            }
                                            if (team3_music.getMediaPlayer()!=null){
                                                team3_music.getMediaPlayer().stop();
                                            }
                                            team2_music.getMediaPlayer().play();

                                            team1_timeline.stop();
                                            team3_timeline.stop();
                                            if (team1_commentary.getMediaPlayer()!=null && team1_commentary.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                                                team1_commentary.getMediaPlayer().stop();
                                            }
                                            if (team3_commentary.getMediaPlayer()!=null && team3_commentary.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                                                team3_commentary.getMediaPlayer().stop();
                                            }
                                            team2_timeline.play();
                                        } else {
                                            Platform.runLater(() -> {
                                                team2_puan.setText(String.valueOf(oldValue + hediye_miktari));
                                            });
                                        }
                                    } else if (gift3.isPresent()) {
                                        int oldValue = Integer.parseInt(team3_puan.getText());
                                        if (isMaxPoint(oldValue, team2_puan, team1_puan) && currentTeam.get()!=3) {
                                            currentTeam.set(3);
                                            System.out.println("Yeni Takım GS");
                                            Platform.runLater(() -> {
                                                team3_puan.setText(String.valueOf(oldValue + hediye_miktari));
                                                team3_puan.setTextFill(Color.YELLOW);
                                                team1_puan.setTextFill(Color.WHITE);
                                                team2_puan.setTextFill(Color.WHITE);
                                                Font font = team3_puan.getFont();
                                                team1_puan.setFont(font);
                                                team2_puan.setFont(font);
                                                team3_puan.setFont(new Font(font.getName(), 50));
                                            });
                                            if (team1_music.getMediaPlayer() != null) {
                                                team1_music.getMediaPlayer().stop();
                                            }
                                            if (team2_music.getMediaPlayer() != null) {
                                                team2_music.getMediaPlayer().stop();
                                            }
                                            if (neutral_music.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)) {
                                                System.out.println("Tarafsız Müzik Kapatılıyor");
                                                neutral_music.getMediaPlayer().stop();
                                            }
                                            MediaPlayer tmp_mediaPlayer = team3_musicPlayers.get(team3_musicCurrentIndex);
                                            team3_music.setMediaPlayer(tmp_mediaPlayer);
                                            team3_music.getMediaPlayer().play();
                                            team2_timeline.stop();
                                            team1_timeline.stop();
                                            if (team1_commentary.getMediaPlayer()!=null && team1_commentary.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                                                team1_commentary.getMediaPlayer().stop();
                                            }
                                            if (team2_commentary.getMediaPlayer()!=null && team2_commentary.getMediaPlayer().getStatus().equals(MediaPlayer.Status.PLAYING)){
                                                team2_commentary.getMediaPlayer().stop();
                                            }
                                            team3_timeline.play();
                                        } else {
                                            Platform.runLater(() -> {
                                                team3_puan.setText(String.valueOf(oldValue + hediye_miktari));
                                            });
                                        }
                                    } else {
                                        System.out.println("Farklı gift");
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


    private boolean isMaxPoint(int value, Label label, Label label2) {
        boolean b = value > Integer.parseInt(label.getText());
        boolean b1 = b && value > Integer.parseInt(label2.getText());
        if (b && b1){
            System.out.println("En büyük Değer");
            return true;
        }
        return false;
    }
}


