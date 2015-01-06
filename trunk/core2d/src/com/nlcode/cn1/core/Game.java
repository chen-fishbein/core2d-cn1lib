package com.nlcode.cn1.core;

import com.nlcode.cn1.core.input.KeyboardState;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.nlcode.cn1.core.input.ScreenpadSpriteButton;
import com.nlcode.cn1.core.input.ScreenpadState;
import com.nlcode.cn1.core.input.TouchPanelState;
import java.util.List;

/**
 *
 * @author AntonioJazo
 */
public abstract class Game {

    private final Canvas canvas;

    public Game(int width, int height, boolean physicalInputEnabled, boolean touchscreenInputEnabled) {
        this.canvas = new Canvas(this, width, height, physicalInputEnabled, touchscreenInputEnabled);
        //Thanks to Codename one support!
        Display.getInstance().setMultiKeyMode(true);
    }

    public abstract void init();

    public abstract void update(long time);

    public abstract void draw(Graphics g, int width, int height);

    public abstract void destroy();

    public void run(Form form) {
        init();
        canvas.running = true;
        
        //All frames your cellphone can handle!
        Display.getInstance().setFramerate(1000);
        form.registerAnimated(getCanvas());
        getCanvas().requestFocus();
        getCanvas().setHandlesInput(true);
        getCanvas().setEnabled(true);
    }

    /**
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return getCanvas().isRunning();
    }

    /**
     * sets running to false
     */
    public void stop() {
        this.getCanvas().stop();
    }

    /**
     * @return the currentKeyboardState
     */
    public KeyboardState getCurrentKeyboardState() {
        return getCanvas().getCurrentKeyboardState();
    }

    /**
     * @return the previousKeyboardState
     */
    public KeyboardState getPreviousKeyboardState() {
        return getCanvas().getPreviousKeyboardState();
    }

    /**
     * @return true if physical Input is Enabled
     */
    public boolean isPhysicalInputEnabled() {
        return getCanvas().isPhysicalInputEnabled();
    }

    /**
     * @param physicalInputEnabled the physicalInputEnabled to set
     */
    public void setPhysicalInputEnabled(boolean physicalInputEnabled) {
        getCanvas().setPhysicalInputEnabled(physicalInputEnabled);
    }

    /**
     * @return true if touchscreen Input is Enabled
     */
    public boolean isTouchscreenInputEnabled() {
        return getCanvas().isTouchscreenInputEnabled();
    }

    /**
     * @param touchscreenInputEnabled the touchscreenInput to set
     */
    public void setTouchscreenInputEnabled(boolean touchscreenInputEnabled) {
        getCanvas().setTouchscreenInputEnabled(touchscreenInputEnabled);
    }

    /**
     * @return the previousTouchPanelState
     */
    public TouchPanelState getPreviousTouchPanelState() {
        return getCanvas().getPreviousTouchPanelState();
    }

    /**
     * @return the currentTouchPanelState
     */
    public TouchPanelState getCurrentTouchPanelState() {
        return getCanvas().getCurrentTouchPanelState();
    }

    /**
     * @return the previousScreenpadState
     */
    public ScreenpadState getPreviousScreenpadState() {
        return getCanvas().getPreviousScreenpadState();
    }

    /**
     * @return the currentScreenpadState
     */
    public ScreenpadState getCurrentScreenpadState() {
        return getCanvas().getCurrentScreenpadState();
    }

    /**
     * @return the screenpadSpriteButtons
     */
    public List<ScreenpadSpriteButton> getScreenpadSpriteButtons() {
        return getCanvas().getScreenpadSpriteButtons();
    }
}