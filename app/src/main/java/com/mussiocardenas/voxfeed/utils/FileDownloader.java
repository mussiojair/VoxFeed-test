package com.mussiocardenas.voxfeed.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader extends Downloader {

    private String url;

    public FileDownloader(String url){
        this.url = url;
    }

    @Override
    public String download(){

        BufferedReader br = null;
        try {
            URL feed = new URL(this.url);
            br = new BufferedReader(new InputStreamReader(feed.openStream()));
            StringBuffer sb = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while( (read = br.read(chars)) != -1 )
                sb.append(chars, 0, read);

            return sb.toString();

        }catch(MalformedURLException murle){
            murle.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally {
            try {
                if (br != null)
                    br.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        return null;
    }
}
