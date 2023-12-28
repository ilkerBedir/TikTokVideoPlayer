package com.example.tiktokvideoplayer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.example.tiktokvideoplayer.TikTokRace.port;

public class RunWSController {
  private static final Logger LOGGER = LoggerFactory.getLogger(RunWSController.class);
  private final String streamName;
  private long pid;

  public RunWSController(String streamName) {
    this.streamName = streamName;
  }

  public void start() {
    LOGGER.debug("Tiktok ws socket başlatılıyor : yayın ismi {}, port {}", streamName, port);
    List<String> command = List.of("TikTokLiveWs/TikTokLiveDeneme.exe", streamName, port);
    ProcessBuilder processBuilder = new ProcessBuilder(command);
    Process process = null;
    try {
      process = processBuilder.start();
      pid = process.pid();
      LOGGER.debug("Başlatılan İşlem PID: {}", pid);
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  public void stop() {
    LOGGER.debug("TiktokWS Kapatılıyor : {}", pid);
    try {
      String killCommand = "taskkill /F /PID " + pid;
      Process process = Runtime.getRuntime().exec(killCommand);
      int exitCode = process.waitFor();
      if (exitCode == 0) {
        LOGGER.debug("İşlem başarıyla sonlandırıldı.");
      } else {
        LOGGER.error("İşlem sonlandırılamadı. Çıkış Kodu: {}", exitCode);
      }
      forceStopClient();
    } catch (IOException | InterruptedException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  public void forceStopClient() {
    try {
      String uygulamaAdi = "TikTokLiveDeneme.exe";
      Process process = Runtime.getRuntime().exec("taskkill /F /IM " + uygulamaAdi);
      int exitCode = process.waitFor();
      if (exitCode == 0) {
        LOGGER.debug(uygulamaAdi + " tüm örnekleri başarıyla kapatıldı.");
      } else {
        LOGGER.error(uygulamaAdi + " tüm örnekleri kapatılamadı.");
      }
    } catch (Exception e) {

    }
  }
}
