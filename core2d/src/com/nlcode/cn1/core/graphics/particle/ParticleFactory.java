package com.nlcode.cn1.core.graphics.particle;

/**
 *
 * @author NLCodeCoreI7
 */
public abstract class ParticleFactory {
    
    public Particle createParticle(){
        return new Particle(getParameters());
    }
    
    public Particle.Parameters getParameters(){
        return new Particle.Parameters(0, 0, 1000, 0, 0, 1, 0xFFFFFFFF, 1);
    }
}