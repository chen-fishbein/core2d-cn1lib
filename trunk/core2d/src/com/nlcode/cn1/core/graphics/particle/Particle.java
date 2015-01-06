package com.nlcode.cn1.core.graphics.particle;

import com.codename1.ui.Graphics;
import com.nlcode.cn1.core.graphics.Sprite;

/**
 *
 * @author NLCodeCoreI7
 */
public class Particle extends Sprite {

    private int color;

    private long life;
    private float size;

    private long originalLife;
    private double angleInRadians;
    
    public Particle(Parameters params){
        setParameters(params);
    }
    
    public void setParameters(Parameters params){
        this.setX(params.getX());
        this.setY(params.getY());
        setSize(params.getSize());
        setLife(params.getLifeInMilliseconds());
        this.angleInRadians = params.getAngle() * Math.PI / 180;
        this.setSpeedX(params.getSpeed() * (float) Math.cos(angleInRadians));
        this.setSpeedX(-params.getSpeed() * (float) Math.sin(angleInRadians));
        setColor(params.getColor());
        setAlpha(params.getAlpha());
    }

    /**
     * @return the life
     */
    public long getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(long life) {
        this.originalLife = this.life = life;
    }

    /**
     * @return the angleInRadians
     */
    public double getAngleInRadians() {
        return angleInRadians;
    }

    @Override
    public void update(long time) {
        this.life -= time;

        if (this.life > 0) {
            float ageRatio = this.life / this.getOriginalLife();
            //this.size = this.originalSize * ageRatio;
            setAlpha(ageRatio);
            super.update(time);
        }
    }

    @Override
    public void draw(Graphics g, int screenWidth, int screenHeight, int offsetX, int offsetY) {
        if (this.getAnimation() == null) {
            g.setColor(this.getColor());
            g.setAlpha((int)getAlpha()*255);
            g.fillArc((int) this.getX(), (int) this.getY(), (int) this.size, (int) this.size, 0, 360);
        } else {
            super.draw(g, screenWidth, screenHeight, offsetX, offsetY);
        }
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public final void setColor(int color) {
        this.color = color;
    }

    /**
     * @return the originalLife
     */
    long getOriginalLife() {
        return originalLife;
    }

    /**
     * @return the size
     */
    float getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    void setSize(float size) {
        this.size = size;
    }
    
    public static class Parameters{
        
        private float x;
        private float y;
        private long lifeInMilliseconds;
        private float angle;
        private float speed;
        private float size;
        private int color;
        private int alpha;
        
        public Parameters(float x, float y, long lifeInMilliseconds, float angle, float speed, float size, int color, int alpha){
            this.x = x;
            this.y = y;
            this.lifeInMilliseconds = lifeInMilliseconds;
            this.angle = angle;
            this.speed = speed;
            this.size = size;
            this.color = color;
            this.alpha = alpha;
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
         * @return the lifeInMilliseconds
         */
        public long getLifeInMilliseconds() {
            return lifeInMilliseconds;
        }

        /**
         * @param lifeInMilliseconds the lifeInMilliseconds to set
         */
        public void setLifeInMilliseconds(long lifeInMilliseconds) {
            this.lifeInMilliseconds = lifeInMilliseconds;
        }

        /**
         * @return the angle
         */
        public float getAngle() {
            return angle;
        }

        /**
         * @param angle the angle to set
         */
        public void setAngle(float angle) {
            this.angle = angle;
        }

        /**
         * @return the speed
         */
        public float getSpeed() {
            return speed;
        }

        /**
         * @param speed the speed to set
         */
        public void setSpeed(float speed) {
            this.speed = speed;
        }

        /**
         * @return the size
         */
        public float getSize() {
            return size;
        }

        /**
         * @param size the size to set
         */
        public void setSize(float size) {
            this.size = size;
        }

        /**
         * @return the color
         */
        public int getColor() {
            return color;
        }

        /**
         * @param color the color to set
         */
        public void setColor(int color) {
            this.color = color;
        }

        /**
         * @return the alpha
         */
        public int getAlpha() {
            return alpha;
        }

        /**
         * @param alpha the alpha to set
         */
        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }
    }
}