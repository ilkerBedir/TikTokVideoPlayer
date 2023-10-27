package com.example.tiktokvideoplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TikTokRace extends Application {
    private static final Logger LOGGER= LoggerFactory.getLogger(TikTokRace.class);
    public static void main(String[] args) {
        if (args.length<1){
            LOGGER.debug("Port Numarası Giriniz");
            throw new RuntimeException("Port Numarasını giriniz");
        }
        LOGGER.debug("Gift Server Başlatılıyor Port {}",args[0]);
        GiftServer giftServer=new GiftServer(Integer.parseInt(args[0]));
        giftServer.start();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        LOGGER.debug("Options Ekranı yükleniyor");
        FXMLLoader fxmlLoader = new FXMLLoader(TikTokRace.class.getResource("settings.fxml"));
        Parent loaded = null;
        try {
            loaded = fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.error("Opsiyon ekranı yüklenemedi");
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(loaded);
        stage.setOnCloseRequest((event)-> {
            LOGGER.debug("Uygulamaya kapatma isteği geldi : {}",event.getEventType().getName());
            stage.close();
            Platform.exit();
        });
        stage.setTitle("Options!");
        stage.setScene(scene);
        stage.show();
    }
}
