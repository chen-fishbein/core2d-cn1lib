package com.nlcode.cn1.core.graphics;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

/**
 *
 * @author AntonioJazo
 */
public abstract class Sprite {

    private float alpha = 1;
    private float x;
    private float y;
    private float speedX;
    private float speedY;
    private boolean active;
    private boolean mobile = true;
    private boolean visible = true;
    private boolean tileSolid = true;
    private boolean spriteSolid = true;
    private Animation animation;
    private TileMap stage;
    private boolean markX = false;
    private boolean markY = false;

    public void update(long time) {
        if (this.isActive()) {
            if (this.isMobile()) {
                if (this.getStage() != null) {
                    this.getStage().updateCollisions(this, time);
                }
                float pos = !markX ? x + speedX * time : x;
                this.setX(pos);
                markX = false;
                pos = !markY ? y + speedY * time : y;
                this.setY(pos);
                markY = false;
            }
            this.getAnimation().update(time);
        }
    }

    public void draw(Graphics g, int screenWidth, int screenHeight, int offsetX, int offsetY) {
        g.setAlpha((int) alpha * 255);
        if (this.getAnimation() != null && this.getAnimation().getImage() != null) {
            int ix = Math.round(this.getX()) + offsetX;
            if (ix + this.getAnimation().getImage().getWidth() >= 0 && ix < screenWidth) {
                int iy = Math.round(this.getY()) + offsetY;
                g.drawImage(this.getAnimation().getImage(), ix, iy);
            }
        }
    }

    public void event_tileHorizontalCollision() {
        this.setSpeedX(0);
    }

    public void event_tileVerticalCollision() {
        this.setSpeedY(0);
    }

    public void event_spriteHorizontalCollision(Sprite sprite) {
    }

    public void event_spriteVerticalCollision(Sprite sprite) {
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the speedX
     */
    public float getSpeedX() {
        return speedX;
    }

    /**
     * @param speedX the speedX to set
     */
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    /**
     * @return the speedY
     */
    public float getSpeedY() {
        return speedY;
    }

    /**
     * @param speedY the speedY to set
     */
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the mobile
     */
    public boolean isMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the tileSolid
     */
    public boolean isTileSolid() {
        return tileSolid;
    }

    /**
     * @param tileSolid the tileSolid to set
     */
    public void setTileSolid(boolean tileSolid) {
        this.tileSolid = tileSolid;
    }

    /**
     * @return the spriteSolid
     */
    public boolean isSpriteSolid() {
        return spriteSolid;
    }

    /**
     * @param spriteSolid the spriteSolid to set
     */
    public void setSpriteSolid(boolean spriteSolid) {
        this.spriteSolid = spriteSolid;
    }

    /**
     * @return the animation
     */
    public Animation getAnimation() {
        return animation;
    }

    /**
     * @param animation the animation to set
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    /**
     * @return the stage
     */
    public TileMap getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(TileMap stage) {
        this.stage = stage;
    }

    /**
     * @return the markX
     */
    boolean isMarkX() {
        return markX;
    }

    /**
     * @param markX the markX to set
     */
    void setMarkX(boolean markX) {
        this.markX = markX;
    }

    /**
     * @return the markY
     */
    boolean isMarkY() {
        return markY;
    }

    /**
     * @param markY the markY to set
     */
    void setMarkY(boolean markY) {
        this.markY = markY;
    }

    /**
     * @return the alpha
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * @param alpha the alpha to set
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getHeight() {
        return getAnimation() != null ? getAnimation().getHeight() : -1;
    }

    public int getWidth() {
        return getAnimation() != null ? getAnimation().getWidth() : -1;
    }

    public Image getImage() {
        return animation != null ? animation.getImage() : null;
    }
}
