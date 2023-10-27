package com.example.tiktokvideoplayer.utils;

public class TimeUtils {
    public static String convertSecondsToClock(int seconds){
        int minutes = seconds / 60;
        int remainSeconds = seconds % 60;
        return minutes + ":" + remainSeconds;
    }
}
