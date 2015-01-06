package com.nlcode.cd1.mario.sprites;

import com.nlcode.cn1.core.ResourceManager;
import com.nlcode.cn1.core.graphics.Animation;
import com.nlcode.cn1.core.graphics.Sprite;
import com.nlcode.cn1.core.graphics.TileMap;

/**
 *
 * @author NLCodePc01
 */
public class Mario extends Sprite {

    private float gravity = 0.002f;
    private float speed = 0.00f;
    private Animation animCaminandoIzquierda = null;
    private Animation animCaminandoDerecha = null;
    private Animation animParadoIzquierda = null;
    private Animation animParadoDerecha = null;
    private Animation animSaltandoIzquierda = null;
    private Animation animSaltandoDerecha = null;
    private Animation animMuerte = null;

    /**
     * @return the gravity
     */
    public float getGravity() {
        return gravity;
    }

    /**
     * @param gravity the gravity to set
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    /**
     * @return the speed
     */
    public float getVelocidad() {
        return speed;
    }

    /**
     * @param velocidad the speed to set
     */
    public void setVelocidad(float velocidad) {
        this.speed = velocidad;
    }

    /**
     * @return the animCaminandoIzquierda
     */
    public Animation getAnimCaminandoIzquierda() {
        return animCaminandoIzquierda;
    }

    /**
     * @param animCaminandoIzquierda the animCaminandoIzquierda to set
     */
    public void setAnimCaminandoIzquierda(Animation animCaminandoIzquierda) {
        this.animCaminandoIzquierda = animCaminandoIzquierda;
    }

    /**
     * @return the animCaminandoDerecha
     */
    public Animation getAnimCaminandoDerecha() {
        return animCaminandoDerecha;
    }

    /**
     * @param animCaminandoDerecha the animCaminandoDerecha to set
     */
    public void setAnimCaminandoDerecha(Animation animCaminandoDerecha) {
        this.animCaminandoDerecha = animCaminandoDerecha;
    }

    /**
     * @return the animParadoIzquierda
     */
    public Animation getAnimParadoIzquierda() {
        return animParadoIzquierda;
    }

    /**
     * @param animParadoIzquierda the animParadoIzquierda to set
     */
    public void setAnimParadoIzquierda(Animation animParadoIzquierda) {
        this.animParadoIzquierda = animParadoIzquierda;
    }

    /**
     * @return the animParadoDerecha
     */
    public Animation getAnimParadoDerecha() {
        return animParadoDerecha;
    }

    /**
     * @param animParadoDerecha the animParadoDerecha to set
     */
    public void setAnimParadoDerecha(Animation animParadoDerecha) {
        this.animParadoDerecha = animParadoDerecha;
    }

    /**
     * @return the animSaltandoIzquierda
     */
    public Animation getAnimSaltandoIzquierda() {
        return animSaltandoIzquierda;
    }

    /**
     * @param animSaltandoIzquierda the animSaltandoIzquierda to set
     */
    public void setAnimSaltandoIzquierda(Animation animSaltandoIzquierda) {
        this.animSaltandoIzquierda = animSaltandoIzquierda;
    }

    /**
     * @return the animSaltandoDerecha
     */
    public Animation getAnimSaltandoDerecha() {
        return animSaltandoDerecha;
    }

    /**
     * @param animSaltandoDerecha the animSaltandoDerecha to set
     */
    public void setAnimSaltandoDerecha(Animation animSaltandoDerecha) {
        this.animSaltandoDerecha = animSaltandoDerecha;
    }

    /**
     * @return the animMuerte
     */
    public Animation getAnimMuerte() {
        return animMuerte;
    }

    /**
     * @param animMuerte the animMuerte to set
     */
    public void setAnimMuerte(Animation animMuerte) {
        this.animMuerte = animMuerte;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDireccion(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the estado to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * @param coins the coins to set
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * @param lives the lives to set
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * @return the ppints
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the puntos to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    public enum Direction {

        LEFT,
        RIGHT
    };
    private Direction direction = Mario.Direction.RIGHT;

    public enum State {

        WALKING,
        JUMPING,
        STANDING
    };
    private State state = State.STANDING;
    private int coins = 0;
    private int lives = 0;
    private int points = 0;

    //Methods
    @Override
    public void update(long time) {

        //Animacion correcta
        switch (this.getDirection()) {
            case LEFT:
                switch (this.getState()) {
                    case WALKING:
                        setAnimation(getAnimCaminandoIzquierda());
                        break;
                    case STANDING:
                        setAnimation(getAnimParadoIzquierda());
                        break;
                    case JUMPING:
                        setAnimation(getAnimSaltandoIzquierda());
                        break;
                }
                break;
            case RIGHT:
                switch (this.getState()) {
                    case WALKING:
                        setAnimation(getAnimCaminandoDerecha());
                        break;
                    case STANDING:
                        setAnimation(getAnimParadoDerecha());
                        break;
                    case JUMPING:
                        setAnimation(getAnimSaltandoDerecha());
                        break;
                }
                break;
        }
        //Gravedad
        this.setSpeedY(this.getSpeedY() + (this.getGravity() * time));//* time/2));
        super.update(time);
    }

    public void saltar() {
        if (!state.equals(State.JUMPING)) {
            //this.sonidoSalto.replay();
            //Salta!
            this.setSpeedY(-0.7f);
            this.setState(Mario.State.JUMPING);
            //this.sonidoSalto.multiPlay();
            ResourceManager.loadSound("/c_mario_jump.mp3").play();
            //Display.getInstance().getCurrent().revalidate();
        }
    }

    public void morir() {
        if (this.getLives() != 0) {
            this.setLives(this.getLives() - 1);
            this.getStage().rebuildMap();
        } else {
            //Salir del juego
            //this.game.Exit();
            //Reiniciar juego
            this.setLives(3);
            this.setCoins(0);
            this.setPoints(0);
            this.getStage().rebuildMap();
        }
    }

    @Override
    public void event_tileVerticalCollision() {
        if (this.getSpeedY() >= 0 && this.getState() == Mario.State.JUMPING) {
            this.setState(Mario.State.STANDING);
        }
        if (this.getY() + this.getAnimation().getImage().getHeight() >= TileMap.tilesToPixels(this.getStage().getHeight())) {
            this.morir();
        }
        super.event_tileVerticalCollision();
    }
}
