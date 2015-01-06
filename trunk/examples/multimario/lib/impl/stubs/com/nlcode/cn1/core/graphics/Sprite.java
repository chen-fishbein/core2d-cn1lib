package com.nlcode.cn1.core.graphics;


/**
 * 
 *  @author AntonioJazo
 */
public abstract class Sprite {

	public Sprite() {
	}

	public void update(long time) {
	}

	public void draw(com.codename1.ui.Graphics g, int screenWidth, int screenHeight, int offsetX, int offsetY) {
	}

	public void event_tileHorizontalCollision() {
	}

	public void event_tileVerticalCollision() {
	}

	public void event_spriteHorizontalCollision(Sprite sprite) {
	}

	public void event_spriteVerticalCollision(Sprite sprite) {
	}

	/**
	 *  @return the x
	 */
	public float getX() {
	}

	/**
	 *  @param x the x to set
	 */
	public void setX(float x) {
	}

	/**
	 *  @return the y
	 */
	public float getY() {
	}

	/**
	 *  @param y the y to set
	 */
	public void setY(float y) {
	}

	/**
	 *  @return the speedX
	 */
	public float getSpeedX() {
	}

	/**
	 *  @param speedX the speedX to set
	 */
	public void setSpeedX(float speedX) {
	}

	/**
	 *  @return the speedY
	 */
	public float getSpeedY() {
	}

	/**
	 *  @param speedY the speedY to set
	 */
	public void setSpeedY(float speedY) {
	}

	/**
	 *  @return the active
	 */
	public boolean isActive() {
	}

	/**
	 *  @param active the active to set
	 */
	public void setActive(boolean active) {
	}

	/**
	 *  @return the mobile
	 */
	public boolean isMobile() {
	}

	/**
	 *  @param mobile the mobile to set
	 */
	public void setMobile(boolean mobile) {
	}

	/**
	 *  @return the visible
	 */
	public boolean isVisible() {
	}

	/**
	 *  @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
	}

	/**
	 *  @return the tileSolid
	 */
	public boolean isTileSolid() {
	}

	/**
	 *  @param tileSolid the tileSolid to set
	 */
	public void setTileSolid(boolean tileSolid) {
	}

	/**
	 *  @return the spriteSolid
	 */
	public boolean isSpriteSolid() {
	}

	/**
	 *  @param spriteSolid the spriteSolid to set
	 */
	public void setSpriteSolid(boolean spriteSolid) {
	}

	/**
	 *  @return the animation
	 */
	public Animation getAnimation() {
	}

	/**
	 *  @param animation the animation to set
	 */
	public void setAnimation(Animation animation) {
	}

	/**
	 *  @return the stage
	 */
	public TileMap getStage() {
	}

	/**
	 *  @param stage the stage to set
	 */
	public void setStage(TileMap stage) {
	}

	/**
	 *  @return the alpha
	 */
	public float getAlpha() {
	}

	/**
	 *  @param alpha the alpha to set
	 */
	public void setAlpha(float alpha) {
	}

	public int getHeight() {
	}

	public int getWidth() {
	}

	public com.codename1.ui.Image getImage() {
	}
}
