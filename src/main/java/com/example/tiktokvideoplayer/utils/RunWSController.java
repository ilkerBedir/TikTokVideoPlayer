package com.example.tiktokvideoplayer.utils;

import java.io.IOException;
import java.util.List;

public class RunWSController {
    private final String streamName;
    private long pid;

    public RunWSController(String streamName) {
        this.streamName = streamName;
    }

    public void start() {
        List<String> command = List.of("java", "-jar", "TikTokLiveWs/TikTokLiveWs-1.0-SNAPSHOT.jar", streamName);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = null;
        try {
            process = processBuilder.start();
            pid = process.pid();
            System.out.println("Başlatılan İşlem PID: " + pid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop(){
        try {
            String killCommand = "taskkill /F /PID " + pid;
            Process process = Runtime.getRuntime().exec(killCommand);
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("İşlem başarıyla sonlandırıldı.");
            } else {
                System.out.println("İşlem sonlandırılamadı. Çıkış Kodu: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
