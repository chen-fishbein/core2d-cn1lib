package com.nlcode.cn1.core.audio;

import com.codename1.media.Media;

/**
 *
 * @author User
 */
public class SoundEffect {
    
    private final String path;
    private final Media media;
    
    public SoundEffect (String path, Media media){
        this.path = path;
        this.media = media;
    }
    
    public void play(){
        media.play();
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the media
     */
    public Media getMedia() {
        return media;
    }
}