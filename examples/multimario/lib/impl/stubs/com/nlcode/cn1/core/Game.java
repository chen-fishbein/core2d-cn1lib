package com.nlcode.cn1.core;


/**
 * 
 *  @author AntonioJazo
 */
public abstract class Game {

	public Game(int width, int height, boolean physicalInputEnabled, boolean touchscreenInputEnabled) {
	}

	public abstract void init() {
	}

	public abstract void update(long time) {
	}

	public abstract void draw(com.codename1.ui.Graphics g, int width, int height) {
	}

	public abstract void destroy() {
	}

	public void run(com.codename1.ui.Form form) {
	}

	/**
	 *  @return the canvas
	 */
	public Canvas getCanvas() {
	}

	/**
	 *  @return the running
	 */
	public boolean isRunning() {
	}

	/**
	 *  sets running to false
	 */
	public void stop() {
	}

	/**
	 *  @return the currentKeyboardState
	 */
	public input.KeyboardState getCurrentKeyboardState() {
	}

	/**
	 *  @return the previousKeyboardState
	 */
	public input.KeyboardState getPreviousKeyboardState() {
	}

	/**
	 *  @return true if physical Input is Enabled
	 */
	public boolean isPhysicalInputEnabled() {
	}

	/**
	 *  @param physicalInputEnabled the physicalInputEnabled to set
	 */
	public void setPhysicalInputEnabled(boolean physicalInputEnabled) {
	}

	/**
	 *  @return true if touchscreen Input is Enabled
	 */
	public boolean isTouchscreenInputEnabled() {
	}

	/**
	 *  @param touchscreenInputEnabled the touchscreenInput to set
	 */
	public void setTouchscreenInputEnabled(boolean touchscreenInputEnabled) {
	}

	/**
	 *  @return the previousTouchPanelState
	 */
	public input.TouchPanelState getPreviousTouchPanelState() {
	}

	/**
	 *  @return the currentTouchPanelState
	 */
	public input.TouchPanelState getCurrentTouchPanelState() {
	}

	/**
	 *  @return the previousScreenpadState
	 */
	public input.ScreenpadState getPreviousScreenpadState() {
	}

	/**
	 *  @return the currentScreenpadState
	 */
	public input.ScreenpadState getCurrentScreenpadState() {
	}

	/**
	 *  @return the screenpadSpriteButtons
	 */
	public java.util.List getScreenpadSpriteButtons() {
	}
}
