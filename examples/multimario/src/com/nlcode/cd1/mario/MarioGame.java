package com.nlcode.cd1.mario;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.nlcode.cd1.mario.sprites.Mario;
import com.nlcode.cd1.mario.sprites.MarioNormal;
import com.nlcode.cn1.core.Game;
import com.nlcode.cn1.core.ResourceManager;
import com.nlcode.cn1.core.graphics.Animation;
import com.nlcode.cn1.core.graphics.TileMap;
import com.nlcode.cn1.core.input.Keyboard;
import com.nlcode.cn1.core.input.Screenpad;
import com.nlcode.cn1.core.input.ScreenpadSpriteButton;

/**
 *
 * @author NLCodePc01
 */
public class MarioGame extends Game {

    Mario mario;
    TileMap stage;

    public MarioGame(int width, int height) {
        super(width, height, true, true);
    }

    @Override
    public void init() {
        Image background = null;
        background = ResourceManager.loadImage("/bg_01.png");
        mario = new MarioNormal();
        stage = new TileMap(
                this, "/mapa_1-1.csv",
                new MarioSpriteFactory(),
                background,
                mario, 10, 10);
        //stage.setVerticalScrolling(TileMap.Scrolling.END);
        ResourceManager.loadSound("/bg_01.mp3").play();

        //On screen pad
        createDPad(100, 165);
        //On screen buttons
        createButtons(400, 165);
    }

    @Override
    public void update(long time) {
        //A keyCode or Xperia Play's X or Screenbutton 1
        boolean correr = false;
        if (this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.A)
                || this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.XPERIA_PLAY_X)
                || this.getCurrentScreenpadState().isButtonDown(Screenpad.Button.B1)) {
            correr = true;
        }
        this.mario.setSpeedX(0);
        //Left key/button
        if (this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.LEFT)
                || this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.XPERIA_PLAY_LEFT)
                || this.getCurrentScreenpadState().isButtonDown(Screenpad.Button.LEFT)) {
            this.mario.setDireccion(Mario.Direction.LEFT);
            this.mario.setSpeedX(mario.getSpeedX() - this.mario.getVelocidad());
            if (correr) {
                this.mario.setSpeedX(mario.getSpeedX() * 2);
            }
        }
        //Right key/button
        if (this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.RIGHT)
                || this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.XPERIA_PLAY_RIGHT)
                || this.getCurrentScreenpadState().isButtonDown(Screenpad.Button.RIGHT)) {
            this.mario.setDireccion(Mario.Direction.RIGHT);
            this.mario.setSpeedX(this.mario.getVelocidad());
            if (correr) {
                this.mario.setSpeedX(mario.getSpeedX() * 2);
            }
        }
        //X keyCode or Xperia Play's Circle or ScreenButton 2
        if (this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.X)
                || this.getCurrentKeyboardState().isKeyDown(Keyboard.Key.XPERIA_PLAY_CIRCLE)
                || this.getCurrentScreenpadState().isButtonDown(Screenpad.Button.B2)) {
            this.mario.saltar();
        }
        //Update Mario states
        if (!this.mario.getState().equals(Mario.State.JUMPING)) {
            if (this.mario.getSpeedX() != 0) {
                this.mario.setState(Mario.State.WALKING);
            } else {
                this.mario.setState(Mario.State.STANDING);
            }
        }
        stage.update(time);
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        stage.draw(g, width, height);

        // On screen debug values
        /*g.drawString("Mario x:" + mario.getX(), 10, 10);
         g.drawString("Mario y:" + mario.getY(), 10, 30);
         g.drawString("Mario vx:" + mario.getSpeedX(), 10, 50);
         g.drawString("Mario vy:" + mario.getSpeedY(), 10, 70);
         g.drawString("Sprites:" + stage.getSprites().size(), 10, 100);
         g.drawString("Touch x:" + getCurrentTouchPanelState().getTouchLocations()[0].getPosition().getX(), 10, 130);
         g.drawString("Touch y:" + getCurrentTouchPanelState().getTouchLocations()[0].getPosition().getY(), 10, 160);
         g.drawString("Touch state:" + getCurrentTouchPanelState().getTouchLocations()[0].getState().name(), 10, 190);*/
    }

    @Override
    public void destroy() {
    }

    public void createDPad(int x, int y) {
        ScreenpadSpriteButton upButton = new ScreenpadSpriteButton(
                this,
                x,
                y,
                new Animation(ResourceManager.loadImage("/vk_d_up.png")),
                new Animation(ResourceManager.loadImage("/vk_up.png")),
                Screenpad.Button.UP);
        ScreenpadSpriteButton leftButton = new ScreenpadSpriteButton(
                this,
                (int) upButton.getX() - upButton.getHeight() + 16,
                (int) upButton.getY() + upButton.getHeight() - 15,
                new Animation(ResourceManager.loadImage("/vk_d_left.png")),
                new Animation(ResourceManager.loadImage("/vk_left.png")),
                Screenpad.Button.LEFT);
        ScreenpadSpriteButton rightButton = new ScreenpadSpriteButton(
                this,
                (int) upButton.getX() + upButton.getWidth() - 15,
                (int) upButton.getY() + upButton.getHeight() - 14,
                new Animation(ResourceManager.loadImage("/vk_d_right.png")),
                new Animation(ResourceManager.loadImage("/vk_right.png")),
                Screenpad.Button.RIGHT);
        ScreenpadSpriteButton downButton = new ScreenpadSpriteButton(
                this,
                (int) upButton.getX() - 1,
                (int) leftButton.getY() + leftButton.getHeight() - 15,
                new Animation(ResourceManager.loadImage("/vk_d_down.png")),
                new Animation(ResourceManager.loadImage("/vk_down.png")),
                Screenpad.Button.DOWN);
        this.getCanvas().getScreenpadSpriteButtons().add(upButton);
        this.getCanvas().getScreenpadSpriteButtons().add(leftButton);
        this.getCanvas().getScreenpadSpriteButtons().add(rightButton);
        this.getCanvas().getScreenpadSpriteButtons().add(downButton);
    }
    public void createButtons(int x, int y) {
        ScreenpadSpriteButton triangleButton = new ScreenpadSpriteButton(
                this,
                x,
                y,
                new Animation(ResourceManager.loadImage("/vk_d_triangle.png")),
                new Animation(ResourceManager.loadImage("/vk_triangle.png")),
                Screenpad.Button.B1);
        ScreenpadSpriteButton circleButton = new ScreenpadSpriteButton(
                this,
                (int) triangleButton.getX() + triangleButton.getHeight() + 16,
                (int) triangleButton.getY() + triangleButton.getHeight() - 15,
                new Animation(ResourceManager.loadImage("/vk_d_circle.png")),
                new Animation(ResourceManager.loadImage("/vk_circle.png")),
                Screenpad.Button.B2);
        this.getCanvas().getScreenpadSpriteButtons().add(triangleButton);
        this.getCanvas().getScreenpadSpriteButtons().add(circleButton);
    }
}