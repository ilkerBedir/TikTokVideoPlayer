package com.example.tiktokvideoplayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {
    private static final Logger LOGGER= LoggerFactory.getLogger(Launcher.class);
    public static void main(String[] args) {
        LOGGER.debug("Uygulama açılıyor");
        TikTokRace.main(args);
    }
}
