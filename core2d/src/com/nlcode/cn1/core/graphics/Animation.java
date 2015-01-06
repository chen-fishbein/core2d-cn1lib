package com.nlcode.cn1.core.graphics;

import com.codename1.ui.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AntonioJazo
 */
public class Animation {

    private List<AnimationFrame> frames;
    private int currFrameIndex = 0;
    private int animTime = 0;
    private int totalDuration = 0;

    public Animation() {
        frames = new ArrayList<AnimationFrame>();
    }

    public Animation(Image image) {
        this();
        this.addFrame(image, 100);
    }

    public void restart() {
        animTime = 0;
        currFrameIndex = 0;
    }

    public final void addFrame(Image image, int duration) {
        totalDuration += duration;
        frames.add(new AnimationFrame(image, duration));
    }
    //private int originalAnim = 0;
    //private int originalCurrFrameIndex = 0;

    public void update(long time) {

        if (frames.size() > 1) {
            animTime += time;

            while (animTime >= totalDuration) {
                animTime -= totalDuration;
                currFrameIndex = 0;
            }

            int duration = 0;
            for (int i = 0; i < frames.size(); i++) {
                duration += frames.get(i).getDuration();
                if (i == currFrameIndex) {
                    break;
                }
            }
            //int duracion = frames.get(currFrameIndex).getDuration();
            while (animTime > duration) {
                currFrameIndex++;
                duration += frames.get(currFrameIndex).getDuration();
            }
        }

        /*if (frames.size() > 1) {
         animTime += time;
         originalAnim = animTime;
         originalCurrFrameIndex = currFrameIndex;
         while (animTime >= totalDuration) {
         animTime -= totalDuration;
         currFrameIndex = 0;
         }

         AnimationFrame frame;
         do {
         frame = frames.get(currFrameIndex);
         //animTime -= frame.getDuration();
         currFrameIndex++;
         /*if (currFrameIndex >= frames.size()) {
         currFrameIndex = 0;
         }*/
        /*  } while (animTime > frame.getDuration() && currFrameIndex < frames.size());
         if (currFrameIndex >= frames.size()) {
         currFrameIndex = 0;
         }
         }*/
    }

    public Image getImage() {
        Image image;
        if (this.frames.isEmpty() || this.frames.size() <= this.currFrameIndex) {
            image = null;
        } else {
            image = this.frames.get(this.currFrameIndex).getImage();
        }
        return image;
    }

    public Image getImage(int index) {
        Image image;
        if (this.frames.isEmpty() || this.frames.size() <= index) {
            image = null;
        } else {
            image = frames.get(index).getImage();
        }
        return image;
    }

    private int height = -1;

    public int getHeight() {
        if (height == -1) {
            height = getImage() != null ? getImage().getHeight() : -1;
        }
        return height;
    }

    private int width = -1;

    public int getWidth() {
        if (width == -1) {
            width = getImage() != null ? getImage().getWidth() : -1;
        }
        return width;
    }
}
