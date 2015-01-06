package com.nlcode.cn1.core.input;

import com.nlcode.cn1.core.Game;
import com.nlcode.cn1.core.graphics.Animation;
import com.nlcode.cn1.core.graphics.Sprite;

/**
 *
 * @author User
 */
public class ScreenpadSpriteButton extends Sprite {

    private final Animation pressedAnimation;
    private final Animation unpressedAnimation;
    private final Screenpad.Button[] buttons;
    private Game game;

    public ScreenpadSpriteButton(Game game, int x, int y, Animation unpressedAnimation, Screenpad.Button... buttons) {
        this(game, x, y, unpressedAnimation, unpressedAnimation, buttons);
    }

    public ScreenpadSpriteButton(Game game, int x, int y, Animation unpressedAnimation, Animation pressedAnimation, Screenpad.Button... buttons) {
        this.unpressedAnimation = unpressedAnimation;
        this.pressedAnimation = pressedAnimation;
        this.setAnimation(pressedAnimation);
        this.buttons = buttons;
        setX(x);
        setY(y);
        this.game = game;
    }

    /**
     * @return the pressedAnimation
     */
    public Animation getPressedAnimation() {
        return pressedAnimation;
    }

    /**
     * @return the unpressedAnimation
     */
    public Animation getUnpressedAnimation() {
        return unpressedAnimation;
    }

    /**
     * @return the button
     */
    public Screenpad.Button[] getButtons() {
        return buttons;
    }

    @Override
    public void update(long time) {
        if (game != null) {
            boolean pressed = true;
            for (Screenpad.Button button : buttons) {
                if (!game.getCurrentScreenpadState().isButtonDown(button)) {
                    pressed = false;
                    break;
                }
            }
            if (pressed) {
                this.setAnimation(pressedAnimation);
            } else {
                this.setAnimation(unpressedAnimation);
            }
        }
        super.update(time);
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
}