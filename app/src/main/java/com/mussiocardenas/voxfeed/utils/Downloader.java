package com.mussiocardenas.voxfeed.utils;

public abstract class Downloader {

    public String download(){
        throw new RuntimeException("Must Be Implemented In Concrete Class");
    }
}
