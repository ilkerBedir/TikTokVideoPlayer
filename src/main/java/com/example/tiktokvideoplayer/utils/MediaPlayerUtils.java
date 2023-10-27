package com.example.tiktokvideoplayer.utils;

import com.example.tiktokvideoplayer.Gift;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MediaPlayerUtils {
    private int countImagePerTeam;
    private List<Gift> gifts;

    public MediaPlayerUtils() {

    }

    public MediaPlayerUtils(int countImagePerTeam) {
        this.countImagePerTeam = countImagePerTeam;
        gifts = Gift.giftList();
    }

    public List<MediaPlayer> createVolumeFiles(String url) {
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

    public MediaPlayer getVideo(String url) {
        List<MediaPlayer> mediaPlayers = new ArrayList<>();
        File folder = new File(url);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    Media media = new Media(file.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setCycleCount(999999);
                    mediaPlayer.setAutoPlay(true);
                    mediaPlayer.setVolume(0);
                    mediaPlayers.add(mediaPlayer);
                    return mediaPlayer;
                }
            }
        }
        System.out.println("Video da hata : " + url);
        return null;
    }

    public List<Gift> teamGifts(List<String> giftNames) {
      List<Gift> giftList = gifts.stream()
              .filter(gift -> giftNames.contains(gift.getName()))
              .collect(Collectors.toList());
      List<Gift> tmp = new ArrayList<>();
      Set<String> giftSet=new HashSet<>();
      for (Gift gift : giftList) {
        if (giftSet.add(gift.getName())) {
          tmp.add(gift);
        }
      }
      return tmp;
    }

    public List<ImageView> createGiftsHBOX(List<Gift> tmpGifts) {
        return tmpGifts.stream()
                .map(gift -> createImageView(gift.image()))
                .collect(Collectors.toList());
    }

    private ImageView createImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(200 / (this.countImagePerTeam + 1));
        imageView.setFitWidth(200 / (this.countImagePerTeam + 1));
        return imageView;
    }
}
