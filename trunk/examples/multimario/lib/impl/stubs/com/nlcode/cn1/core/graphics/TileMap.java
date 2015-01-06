package com.nlcode.cn1.core.graphics;


/**
 * 
 *  @author AntonioJazo
 */
public class TileMap {

	public TileMap(com.nlcode.cn1.core.Game game, String mapName, SpriteFactory spriteFactory, com.codename1.ui.Image parallaxBackground, Sprite scrollingSprite, int ssPosTileX, int ssPosTileY) {
	}

	/**
	 *  @return the horizontalScrolling
	 */
	public TileMap.Scrolling getHorizontalScrolling() {
	}

	/**
	 *  @param horizontalScrolling the horizontalScrolling to set
	 */
	public void setHorizontalScrolling(TileMap.Scrolling horizontalScrolling) {
	}

	/**
	 *  @return the verticalScrolling
	 */
	public TileMap.Scrolling getVerticalScrolling() {
	}

	/**
	 *  @param verticalScrolling the verticalScrolling to set
	 */
	public void setVerticalScrolling(TileMap.Scrolling verticalScrolling) {
	}

	/**
	 *  @return the horizontalParallaxBackgroundScrolling
	 */
	public TileMap.ParallaxBackgroundScrolling getHorizontalParallaxBackgroundScrolling() {
	}

	/**
	 *  @param horizontalParallaxBackgroundScrolling the
	 *  horizontalParallaxBackgroundScrolling to set
	 */
	public void setHorizontalParallaxBackgroundScrolling(TileMap.ParallaxBackgroundScrolling horizontalParallaxBackgroundScrolling) {
	}

	/**
	 *  @return the verticalParallaxBackgroundScrolling
	 */
	public TileMap.ParallaxBackgroundScrolling getVerticalParallaxBackgroundScrolling() {
	}

	/**
	 *  @param verticalParallaxBackgroundScrolling the
	 *  verticalParallaxBackgroundScrolling to set
	 */
	public void setVerticalParallaxBackgroundScrolling(TileMap.ParallaxBackgroundScrolling verticalParallaxBackgroundScrolling) {
	}

	public static int pixelsToTiles(float pixels) {
	}

	public static int tilesToPixels(int tiles) {
	}

	public void update(long time) {
	}

	public void draw(com.codename1.ui.Graphics g, int screenWidth, int screenHeight) {
	}

	public final void buildMap() {
	}

	public void rebuildMap() {
	}

	public com.codename1.ui.geom.Point getCollisionTile(Sprite sprite, float newX, float newY) {
	}

	public Sprite getCollisionSprite(Sprite sprite, float x, float y) {
	}

	public void updateCollisions(Sprite sprite, long time) {
	}

	/**
	 *  @return the sprites
	 */
	public java.util.List getSprites() {
	}

	/**
	 *  @return the width
	 */
	public int getWidth() {
	}

	/**
	 *  @param width the width to set
	 */
	public void setWidth(int width) {
	}

	/**
	 *  @return the height
	 */
	public int getHeight() {
	}

	/**
	 *  @param height the height to set
	 */
	public void setHeight(int height) {
	}

	/**
	 *  @return the ready
	 */
	public boolean isReady() {
	}

	public static final class Scrolling {


		public static final TileMap.Scrolling SPRITE;

		public static final TileMap.Scrolling MANUAL;

		public static final TileMap.Scrolling START;

		public static final TileMap.Scrolling END;

		public static TileMap.Scrolling[] values() {
		}

		public static TileMap.Scrolling valueOf(String name) {
		}
	}

	public static final class ParallaxBackgroundScrolling {


		public static final TileMap.ParallaxBackgroundScrolling NORMAL;

		public static final TileMap.ParallaxBackgroundScrolling START;

		public static final TileMap.ParallaxBackgroundScrolling END;

		public static TileMap.ParallaxBackgroundScrolling[] values() {
		}

		public static TileMap.ParallaxBackgroundScrolling valueOf(String name) {
		}
	}
}
