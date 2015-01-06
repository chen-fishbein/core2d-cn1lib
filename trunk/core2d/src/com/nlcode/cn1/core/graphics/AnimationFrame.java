package com.nlcode.cn1.core.graphics;

import com.codename1.ui.Image;

/**
 *
 * @author AntonioJazo
 */
public class AnimationFrame {
    private Image image;
    private int duration;
    
    public AnimationFrame(Image image, int duration){
        this.image = image;
        this.duration = duration;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}