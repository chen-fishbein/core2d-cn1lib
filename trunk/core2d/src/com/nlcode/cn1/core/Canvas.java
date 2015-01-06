package com.nlcode.cn1.core;

import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.nlcode.cn1.core.input.InputEvent;
import com.nlcode.cn1.core.input.Keyboard;
import com.nlcode.cn1.core.input.KeyboardState;
import com.nlcode.cn1.core.input.Screenpad;
import com.nlcode.cn1.core.input.ScreenpadSpriteButton;
import com.nlcode.cn1.core.input.ScreenpadState;
import com.nlcode.cn1.core.input.TouchPanel;
import com.nlcode.cn1.core.input.TouchPanelState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Canvas extends Label {

    private KeyboardState previousKeyboardState;
    private KeyboardState currentKeyboardState;
    private TouchPanelState previousTouchPanelState;
    private TouchPanelState currentTouchPanelState;
    private ScreenpadState previousScreenpadState;
    private ScreenpadState currentScreenpadState;

    private final List<ScreenpadSpriteButton> screenpadSpriteButtons;
    private boolean physicalInputEnabled;
    private boolean touchscreenInputEnabled;

    boolean running;
    private final long initTime;
    private long currentTime;
    private long elapsedTime;
    private final Game game;

    public Canvas(Game game, int width, int height, boolean physicalInputEnabled, boolean touchscreenInputEnabled) {
        this.game = game;
        initTime = System.currentTimeMillis();
        currentTime = initTime;
        this.screenpadSpriteButtons = new ArrayList<ScreenpadSpriteButton>();
        this.previousKeyboardState = Keyboard.getState();
        this.currentKeyboardState = Keyboard.getState();
        this.previousTouchPanelState = TouchPanel.getState();
        this.currentTouchPanelState = TouchPanel.getState();
        this.previousScreenpadState = Screenpad.getState();
        this.currentScreenpadState = Screenpad.getState();
        setWidth(width);
        setHeight(height);
        this.physicalInputEnabled = physicalInputEnabled;
        this.touchscreenInputEnabled = touchscreenInputEnabled;

    }

    @Override
    public void keyPressed(int keyCode) {
        System.out.println("Key pressed: " + keyCode);
        InputEvent.registerKeyPress(keyCode);
    }

    @Override
    public void keyReleased(int keyCode) {
        InputEvent.registerKeyRelease(keyCode);
    }

    @Override
    public void pointerDragged(int[] x, int[] y) {
        InputEvent.registerTouchMovement(x, y);
    }

    @Override
    public void pointerPressed(int[] x, int[] y) {
        InputEvent.registerTouchPress(x, y);
    }

    @Override
    public void pointerReleased(int[] x, int[] y) {
        InputEvent.registerTouchRelease(x, y);
    }

    private boolean destroyed = false;

    @Override
    public boolean animate() {
        return running;
    }

    @Override
    public void paint(Graphics g) {
        if (isRunning()) {
            if (isTouchscreenInputEnabled()) {
                getPreviousTouchPanelState().inactivate();
                setPreviousTouchPanelState(getCurrentTouchPanelState());
                setCurrentTouchPanelState(TouchPanel.getState());
                if (!screenpadSpriteButtons.isEmpty()) {
                    //Update button status...
                    for (Screenpad.Button button : Screenpad.Button.values()) {
                        InputEvent.registerScreenPadButtonRelease(button);
                    }
                    for (ScreenpadSpriteButton screenButton : screenpadSpriteButtons) {
                        boolean pressed = false;
                        float sbx = screenButton.getX();
                        float sby = screenButton.getY();
                        for (TouchPanel.TouchLocation touchLocation : currentTouchPanelState.getTouchLocations()) {
                            if (touchLocation.getState() != TouchPanel.TouchLocationState.Invalid && touchLocation.getState() != TouchPanel.TouchLocationState.Released) {
                                int x = touchLocation.getPosition().getX();
                                int y = touchLocation.getPosition().getY();
                                if (x >= sbx && x < sbx + screenButton.getWidth() && y >= sby && y < sby + screenButton.getHeight()) {
                                    pressed = true;
                                }
                            }
                        }
                        if (pressed) {
                            for (Screenpad.Button button : screenButton.getButtons()) {
                                InputEvent.registerScreenPadButtonPress(button);
                            }
                        }
                    }
                    getPreviousScreenpadState().inactivate();
                    setPreviousScreenpadState(getCurrentScreenpadState());
                    setCurrentScreenpadState(Screenpad.getState());
                }
            }
            if (isPhysicalInputEnabled()) {
                getPreviousKeyboardState().inactivate();
                setPreviousKeyboardState(getCurrentKeyboardState());
                setCurrentKeyboardState(Keyboard.getState());
            }
            elapsedTime = System.currentTimeMillis() - currentTime;
            currentTime += elapsedTime;
            //skip too long frames for smooth gameplay
            if (elapsedTime < 1000) {
                game.update(elapsedTime);
                super.paint(g);
                game.draw(g, getWidth(), getWidth());
                g.setAlpha(255);
                for (ScreenpadSpriteButton button : screenpadSpriteButtons) {
                    button.update(elapsedTime);
                    button.draw(g, getWidth(), getHeight(), 0, 0);
                }
            }
        } else {
            if (!isDestroyed()) {
                game.destroy();
                setDestroyed(true);
            }
            super.paint(g);
        }
    }

    /**
     * @return the previousKeyboardState
     */
    public KeyboardState getPreviousKeyboardState() {
        return previousKeyboardState;
    }

    /**
     * @param previousKeyboardState the previousKeyboardState to set
     */
    public void setPreviousKeyboardState(KeyboardState previousKeyboardState) {
        this.previousKeyboardState = previousKeyboardState;
    }

    /**
     * @return the currentKeyboardState
     */
    public KeyboardState getCurrentKeyboardState() {
        return currentKeyboardState;
    }

    /**
     * @param currentKeyboardState the currentKeyboardState to set
     */
    public void setCurrentKeyboardState(KeyboardState currentKeyboardState) {
        this.currentKeyboardState = currentKeyboardState;
    }

    /**
     * @return the previousTouchPanelState
     */
    public TouchPanelState getPreviousTouchPanelState() {
        return previousTouchPanelState;
    }

    /**
     * @param previousTouchPanelState the previousTouchPanelState to set
     */
    public void setPreviousTouchPanelState(TouchPanelState previousTouchPanelState) {
        this.previousTouchPanelState = previousTouchPanelState;
    }

    /**
     * @return the currentTouchPanelState
     */
    public TouchPanelState getCurrentTouchPanelState() {
        return currentTouchPanelState;
    }

    /**
     * @param currentTouchPanelState the currentTouchPanelState to set
     */
    public void setCurrentTouchPanelState(TouchPanelState currentTouchPanelState) {
        this.currentTouchPanelState = currentTouchPanelState;
    }

    /**
     * @return the previousScreenpadState
     */
    public ScreenpadState getPreviousScreenpadState() {
        return previousScreenpadState;
    }

    /**
     * @param previousScreenpadState the previousScreenpadState to set
     */
    public void setPreviousScreenpadState(ScreenpadState previousScreenpadState) {
        this.previousScreenpadState = previousScreenpadState;
    }

    /**
     * @return the currentScreenpadState
     */
    public ScreenpadState getCurrentScreenpadState() {
        return currentScreenpadState;
    }

    /**
     * @param currentScreenpadState the currentScreenpadState to set
     */
    public void setCurrentScreenpadState(ScreenpadState currentScreenpadState) {
        this.currentScreenpadState = currentScreenpadState;
    }

    /**
     * @return the screenpadSpriteButtons
     */
    public List<ScreenpadSpriteButton> getScreenpadSpriteButtons() {
        return screenpadSpriteButtons;
    }

    /**
     * @return the physicalInputEnabled
     */
    public boolean isPhysicalInputEnabled() {
        return physicalInputEnabled;
    }

    /**
     * @param physicalInputEnabled the physicalInputEnabled to set
     */
    public void setPhysicalInputEnabled(boolean physicalInputEnabled) {
        this.physicalInputEnabled = physicalInputEnabled;
    }

    /**
     * @return the touchscreenInputEnabled
     */
    public boolean isTouchscreenInputEnabled() {
        return touchscreenInputEnabled;
    }

    /**
     * @param touchscreenInputEnabled the touchscreenInputEnabled to set
     */
    public void setTouchscreenInputEnabled(boolean touchscreenInputEnabled) {
        this.touchscreenInputEnabled = touchscreenInputEnabled;
    }

    /**
     * @return the destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * @param destroyed the destroyed to set
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @return the initTime
     */
    public long getInitTime() {
        return initTime;
    }

    /**
     * @return the currentTime
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * @return the elapsedTime
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    public void stop() {
        this.running = false;
    }
}
