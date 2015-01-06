package com.nlcode.cd1.mario.sprites;

import com.codename1.ui.Image;
import com.nlcode.cn1.core.graphics.Animation;
import com.nlcode.cn1.core.ResourceManager;

/**
 *
 * @author NLCodePc01
 */
public class MarioNormal extends Mario {

    public MarioNormal() {
        //Cargamos imagenes de Mario mirando a la izquierda
        Image mario01i = ResourceManager.loadImage("/c_mario_l_01.png");
        Image mario02i = ResourceManager.loadImage("/c_mario_l_02.png");
        Image mario03i = ResourceManager.loadImage("/c_mario_l_03.png");
        Image mario04i = ResourceManager.loadImage("/c_mario_l_04.png");
        //Cargamos imagenes de Mario mirando a la derecha
        Image mario01d = ResourceManager.loadImage("/c_mario_r_01.png");
        Image mario02d = ResourceManager.loadImage("/c_mario_r_02.png");
        Image mario03d = ResourceManager.loadImage("/c_mario_r_03.png");
        Image mario04d = ResourceManager.loadImage("/c_mario_r_04.png");
        //Animacion Mario caminando a la izquierda

        setAnimCaminandoIzquierda(new Animation());
        getAnimCaminandoIzquierda().addFrame(mario01i, 150);
        getAnimCaminandoIzquierda().addFrame(mario02i, 150);
        //Animacion Mario caminando a la derecha
        setAnimCaminandoDerecha(new Animation());
        getAnimCaminandoDerecha().addFrame(mario01d, 150);
        getAnimCaminandoDerecha().addFrame(mario02d, 150);
        //Animacion Mario Parado a la izquierda
        setAnimParadoIzquierda(new Animation());
        getAnimParadoIzquierda().addFrame(mario04i, 100);
        //Animacion Mario Parado a la derecha
        setAnimParadoDerecha(new Animation());
        getAnimParadoDerecha().addFrame(mario04d, 100);
        //Animacion Mario Saltando a la izquierda
        setAnimSaltandoIzquierda(new Animation());
        getAnimSaltandoIzquierda().addFrame(mario03i, 100);
        //Animacion Mario Saltando a la derecha
        setAnimSaltandoDerecha(new Animation());
        getAnimSaltandoDerecha().addFrame(mario03d, 100);
        //Animacion por Defecto
        setAnimation(this.getAnimParadoDerecha());
        //Velocidad
        setVelocidad(0.3f);
        setActive(true);
    }
}