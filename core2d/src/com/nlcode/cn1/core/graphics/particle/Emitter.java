package com.nlcode.cn1.core.graphics.particle;

import com.codename1.ui.Graphics;
import com.nlcode.cn1.core.graphics.Sprite;
import java.util.List;

/**
 *
 * @author NLCodeCoreI7
 */
public abstract class Emitter extends Sprite {

    private List<Particle> activeParticles;
    private List<Particle> inactiveParticles;

    private ParticleFactory factory;
    private int frequency;
    private int originalFrequency;
    
    public Emitter(ParticleFactory factory, int frequency) {
        this.factory = factory;
        this.originalFrequency = this.frequency = frequency;
        this.setMobile(false);
        this.setTileSolid(false);
        this.setSpriteSolid(false);
        this.setActive(true);
    }

    private Emitter() {
    }

    @Override
    public void update(long time) {
        // Crear nuevas!
        frequency -= time;
        if (frequency <= 0){
            frequency = originalFrequency;
            if (inactiveParticles.size() > 0){
                Particle p = inactiveParticles.get(0);
                p.setParameters(factory.getParameters());
                p.setX(this.getX()+p.getX());
                p.setY(this.getY()+p.getY());
                activeParticles.add(p);
                inactiveParticles.remove(0);
            }else{
                activeParticles.add(factory.createParticle());
            }
        }
        //Update all particles
        for (int i = 0; i < this.activeParticles.size(); i++) {
            Particle p = this.activeParticles.get(i);
            if (p.getLife() > 0) {
                this.activeParticles.get(i).update(time);
            }else{
                this.inactiveParticles.add(p);
                this.activeParticles.remove(i);
                i--;
            }
        }
        super.update(time);
    }

    @Override
    public void draw(Graphics g, int screenWidth, int screenHeight, int offsetX, int offsetY) {
        for (Particle activeParticle : this.activeParticles) {
            activeParticle.draw(g, screenWidth, screenHeight, offsetX, offsetY);
        }
        //this.draw(g, screenWidth, screenHeight, offsetX, offsetY);
    }

    /**
     * @return the frequency
     */
    int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    void setFrequency(int frequency) {
        this.originalFrequency = this.frequency = frequency;
    }
}