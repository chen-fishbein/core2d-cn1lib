package com.nlcode.cn1.core.graphics;

import com.codename1.io.Util;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.nlcode.cn1.core.Game;
import com.nlcode.cn1.core.ResourceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author AntonioJazo
 */
public class TileMap {

    // Tamaño del tilemap
    // Formula:
    //    2^TILEMAP_TILE_SIZE_BITS = TILEMAP_TILE_SIZE
    //
    private static final int TILEMAP_TILE_SIZE = 32;
    private static final int TILEMAP_TILE_SIZE_BITS = 5;
    //Cuantos tiles distintos hay
    private static final int TILEMAP_MAX_TILE = 92;
    //Formato de los archivos de tilemap
    private static final int TILEMAP_TILE_MIN_DIGITS = 2;
    //Directorio para buscar los tilemaps (en cn1 no se puede en otro)
    private static final String TILEMAP_BASE_DIR = "/";
    //Colision con los "límites" del mapa
    private static final boolean BOUNDARY_COLLISION = false;

    //TO DO
    private static final boolean COLLISION_VISIBLE = true;

    private Point pointCache;
    private Image[][] tilesSolid;
    private Image[][] tilesTecho;
    private Image[][] tilesFondo;
    private Image[][] tilesSuper;
    private final List<Sprite> sprites;
    private final List<Sprite> spritesBack;
    private final List<Sprite> spritesFront;
    private int width;
    private int height;
    private Game game;
    private String mapName;
    private SpriteFactory spriteFactory;
    private Image parallaxBackground;
    private Sprite scrollingSprite;
    private int posScrollingSpriteTileX;
    private int posScrollingSpriteTileY;
    private List<List<String>> CSVMap;
    private boolean ready = false;
    private Scrolling horizontalScrolling = Scrolling.START;
    private Scrolling verticalScrolling = Scrolling.START;
    private ParallaxBackgroundScrolling horizontalParallaxBackgroundScrolling = ParallaxBackgroundScrolling.START;
    private ParallaxBackgroundScrolling verticalParallaxBackgroundScrolling = ParallaxBackgroundScrolling.START;

    public TileMap(Game game, String mapName, SpriteFactory spriteFactory, Image parallaxBackground, Sprite scrollingSprite, int ssPosTileX, int ssPosTileY) {
        this.game = game;
        this.mapName = mapName;
        this.spriteFactory = spriteFactory;
        this.parallaxBackground = parallaxBackground;
        this.scrollingSprite = scrollingSprite;
        if (scrollingSprite != null) {
            horizontalScrolling = Scrolling.SPRITE;
            verticalScrolling = Scrolling.SPRITE;
            horizontalParallaxBackgroundScrolling = ParallaxBackgroundScrolling.NORMAL;
            verticalParallaxBackgroundScrolling = ParallaxBackgroundScrolling.NORMAL;
        }
        this.posScrollingSpriteTileX = ssPosTileX;
        this.posScrollingSpriteTileY = ssPosTileY;
        this.pointCache = new Point(0, 0);
        this.sprites = new ArrayList<Sprite>() {
            @Override
            public boolean add(Sprite e) {
                e.setStage(TileMap.this);
                return super.add(e);
            }
        };
        this.spritesBack = new ArrayList<Sprite>() {
            @Override
            public boolean add(Sprite e) {
                e.setStage(TileMap.this);
                return super.add(e);
            }
        };
        this.spritesFront = new ArrayList<Sprite>() {
            @Override
            public boolean add(Sprite e) {
                e.setStage(TileMap.this);
                return super.add(e);
            }
        };
        buildMap();
    }

    /**
     * @return the horizontalScrolling
     */
    public Scrolling getHorizontalScrolling() {
        return horizontalScrolling;
    }

    /**
     * @param horizontalScrolling the horizontalScrolling to set
     */
    public void setHorizontalScrolling(Scrolling horizontalScrolling) {
        this.horizontalScrolling = horizontalScrolling;
    }

    /**
     * @return the verticalScrolling
     */
    public Scrolling getVerticalScrolling() {
        return verticalScrolling;
    }

    /**
     * @param verticalScrolling the verticalScrolling to set
     */
    public void setVerticalScrolling(Scrolling verticalScrolling) {
        this.verticalScrolling = verticalScrolling;
    }

    /**
     * @return the horizontalParallaxBackgroundScrolling
     */
    public ParallaxBackgroundScrolling getHorizontalParallaxBackgroundScrolling() {
        return horizontalParallaxBackgroundScrolling;
    }

    /**
     * @param horizontalParallaxBackgroundScrolling the
     * horizontalParallaxBackgroundScrolling to set
     */
    public void setHorizontalParallaxBackgroundScrolling(ParallaxBackgroundScrolling horizontalParallaxBackgroundScrolling) {
        this.horizontalParallaxBackgroundScrolling = horizontalParallaxBackgroundScrolling;
    }

    /**
     * @return the verticalParallaxBackgroundScrolling
     */
    public ParallaxBackgroundScrolling getVerticalParallaxBackgroundScrolling() {
        return verticalParallaxBackgroundScrolling;
    }

    /**
     * @param verticalParallaxBackgroundScrolling the
     * verticalParallaxBackgroundScrolling to set
     */
    public void setVerticalParallaxBackgroundScrolling(ParallaxBackgroundScrolling verticalParallaxBackgroundScrolling) {
        this.verticalParallaxBackgroundScrolling = verticalParallaxBackgroundScrolling;
    }

    public enum Scrolling {

        SPRITE,
        MANUAL,
        START,
        END
    }

    public enum ParallaxBackgroundScrolling {

        NORMAL,
        START,
        END
    }

    public static int pixelsToTiles(float pixels) {
        //return ((int) pixels) >> TILEMAP_TILE_SIZE_BITS;
        return Math.round(pixels) >> TILEMAP_TILE_SIZE_BITS;
    }

    public static int tilesToPixels(int tiles) {
        return tiles << TILEMAP_TILE_SIZE_BITS;
    }

    public void update(long time) {
        int i;
        for (i = 0; i < this.spritesBack.size(); i++) {
            this.spritesBack.get(i).update(time);
        }
        int cantidad = this.sprites.size();
        for (i = 0; i < cantidad; i++) {
            this.sprites.get(i).update(time);
            if (cantidad != this.sprites.size()) {
                cantidad = this.sprites.size();
                i--;
            }
        }
        for (i = 0; i < this.spritesFront.size(); i++) {
            this.spritesFront.get(i).update(time);
        }
    }

    public void draw(Graphics g, int screenWidth, int screenHeight) {
        g.setAlpha(255);
        //Horizontal Scrolling
        int mapWidth = TileMap.tilesToPixels(this.width);
        int offsetX = 0, offsetY = 0;
        switch (this.getHorizontalScrolling()) {
            case SPRITE:
                if (this.scrollingSprite != null) {
                    offsetX = screenWidth / 2 - Math.round(this.scrollingSprite.getX()) - TILEMAP_TILE_SIZE;
                    offsetX = Math.min(offsetX, 0);
                    offsetX = Math.max(offsetX, screenWidth - mapWidth);
                }
                break;
            case START:
                offsetX = 0;
                break;
            case END:
                offsetX = screenWidth - mapWidth;
                break;
        }
        //Vertical Scrolling
        int mapHeight = TileMap.tilesToPixels(this.height);
        switch (this.getVerticalScrolling()) {
            case SPRITE:
                if (this.scrollingSprite != null) {
                    offsetY = screenHeight / 2 - Math.round(this.scrollingSprite.getY()) - TILEMAP_TILE_SIZE;
                    offsetY = Math.min(offsetY, 0);
                    offsetY = Math.max(offsetY, screenHeight - mapHeight);
                }
                break;
            case START:
                offsetY = 0;
                break;
            case END:
                offsetY = screenHeight - mapHeight;
                break;
        }
        //Draws Parallax Background
        int x = 0, y = 0;
        if (this.parallaxBackground != null) {
            switch (this.horizontalParallaxBackgroundScrolling) {
                case NORMAL:
                    x = offsetX * (screenWidth - this.parallaxBackground.getWidth()) / (screenWidth - mapWidth);
                    break;
                case END:
                    x = screenWidth - this.parallaxBackground.getWidth();
                    break;
            }
            switch (verticalParallaxBackgroundScrolling) {
                case NORMAL:
                    y = offsetY * (screenHeight - this.parallaxBackground.getHeight()) / (screenHeight - mapHeight);
                    break;
                case END:
                    y = screenHeight - this.parallaxBackground.getHeight();
                    break;
            }
            g.drawImage(this.parallaxBackground, x, y);
        }
        // Draw visible tiles
        int firstTileX = TileMap.pixelsToTiles(-offsetX);
        int lastTileX = firstTileX + TileMap.pixelsToTiles(screenWidth) + 1;
        int posX, posY;
        Image image;
        for (y = 0; y < this.height; y++) {
            for (x = firstTileX; x < lastTileX; x++) {
                posX = TileMap.tilesToPixels(x) + offsetX;
                posY = TileMap.tilesToPixels(y) + offsetY;
                image = null;
                if (x < this.tilesFondo.length && y < this.tilesFondo[x].length) {
                    image = this.tilesFondo[x][y];
                }
                if (image != null) {
                    g.drawImage(image, posX, posY);
                }
                image = null;
                if (x < this.tilesSolid.length && y < this.tilesSolid[x].length) {
                    image = this.tilesSolid[x][y];
                }
                if (image != null) {
                    g.drawImage(image, posX, posY);
                }
                image = null;
                if (x < this.tilesTecho.length && y < this.tilesTecho[x].length) {
                    image = this.tilesTecho[x][y];
                }
                if (image != null) {
                    g.drawImage(image, posX, posY);
                }
            }
        }
        int i;
        for (i = 0; i < this.spritesBack.size(); i++) {
            this.spritesBack.get(i).draw(g, screenWidth, screenHeight, offsetX, offsetY);
        }
        for (i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).draw(g, screenWidth, screenHeight, offsetX, offsetY);
        }
        for (i = 0; i < this.spritesFront.size(); i++) {
            this.spritesFront.get(i).draw(g, screenWidth, screenHeight, offsetX, offsetY);
        }
        g.setAlpha(255);
        for (y = 0; y < this.height; y++) {
            for (x = firstTileX; x < lastTileX; x++) {
                posX = TileMap.tilesToPixels(x) + offsetX;
                posY = TileMap.tilesToPixels(y) + offsetY;
                image = null;
                if (x < this.tilesSuper.length && y < this.tilesSuper[x].length) {
                    image = this.tilesSuper[x][y];
                }
                if (image != null) {
                    g.drawImage(image, posX, posY);
                }
            }
        }
    }

    public final void buildMap() {
        this.CSVMap = new ArrayList<List<String>>();
        List<List<String>> tiles = this.CSVMap;
        //Read String from csv located on MapName...
        String text = ResourceManager.loadText(this.mapName);
        int i = 0;
        String[] lines = Util.split(text, "\r\n");
        for (i = 0; i < lines.length; i++) {
            String linea = lines[i];
            if (linea == null) {
                break;
            }
            String[] arr = null;
            List<String> tilesHorizontal = new ArrayList();
            if (linea.indexOf('#') != 0) {
                arr = Util.split(linea, ",");
                width = Math.max(width, arr.length);
                tilesHorizontal.addAll(Arrays.asList(arr));
            }
            tiles.add(tilesHorizontal);
        }
        height = tiles.size();
        tilesSolid = new Image[width][];
        tilesFondo = new Image[width][];
        tilesSuper = new Image[width][];
        tilesTecho = new Image[width][];
        /*
         Codename bug: tilesTecho = new Image[width][height];
         is not working.
         */
        for (i = 0; i < width ; i++) {
            tilesSolid[i] = new Image[height];
            tilesFondo[i] = new Image[height];
            tilesSuper[i] = new Image[height];
            tilesTecho[i] = new Image[height];
        }
        int x = 0, y = 0;
        String[] tileImages = getTileList();
        for (y = 0; y < height; y++) {
            List<String> values = tiles.get(y);
            for (x = 0; x < width; x++) {
                String[] arrValue = null;
                try {
                    if (values.size() == 1) {
                        break;
                    }
                    if (values.get(x) != null && !values.get(x).equals("")) {
                        arrValue = Util.split(values.get(x), "|");
                        for (i = 0; i < arrValue.length; i++) {
                            String value = arrValue[i].trim().toLowerCase();
                            if (!value.equals("") && !value.startsWith("x") && !value.startsWith("y") && !value.startsWith("z")) {
                                //String lowerValue = value.toLowerCase();
                                if (value.indexOf("f") == 0) {
                                    //Tiles Fondo: Los que empiezan con f o F
                                    tilesFondo[x][y] = Image.createImage(tileImages[Integer.parseInt(value.substring(1), 10)]);
                                } else if (value.indexOf("t") == 0) {
                                    //Tiles Techo: Los que empiezan con t o T
                                    tilesTecho[x][y] = Image.createImage(tileImages[Integer.parseInt(value.substring(1), 10)]);
                                } else if (value.indexOf("s") == 0) {
                                    //Tiles Super: Los que empiezan con s o S
                                    tilesSuper[x][y] = Image.createImage(tileImages[Integer.parseInt(value.substring(1), 10)]);
                                    //} else if (valorMinusculas.StartsWith("a")) {
                                    //} else if (valorMinusculas.StartsWith("d")) {
                                } else {
                                    //Tiles Solidos: Los que empiezan con un numero
                                    //(Tiles por defecto)
                                    tilesSolid[x][y] = Image.createImage(tileImages[Integer.parseInt(value, 10)]);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        rebuildMap();
        if (!isReady()) {
            ready = true;
            //currentGame.gameLoop();
            //requestAnimationFrame(tileMapInstance.game.animate);
        }

    }

    public void rebuildMap() {
        this.sprites.clear();
        this.spritesBack.clear();
        this.spritesFront.clear();
        List<List<String>> tiles = this.CSVMap;
        int x = 0, y = 0;
        String[] arr;
        for (y = 0; y < this.height; y++) {
            List<String> values = tiles.get(y);
            for (x = 0; x < this.width; x++) {
                String[] arrValue = new String[0];
                try {
                    if (values.get(x) != null && !values.get(x).equals("")) {
                        arrValue = Util.split(values.get(x), "|");
                        for (int i = 0; i < arrValue.length; i++) {
                            String value = arrValue[i];
                            if (!value.trim().equals("")) {
                                String lowerValue = value.toLowerCase();
                                if ((lowerValue.indexOf('x') == 0) || (lowerValue.indexOf('y') == 0) || (lowerValue.indexOf('z') == 0)) {
                                    String spriteName = value.substring(1).trim();
                                    int index = spriteName.indexOf('(');
                                    int yOffset = 0;
                                    int xOffset = 0;
                                    if (index != -1) {
                                        String config = spriteName.substring(index + 1, spriteName.length() - 1).toLowerCase();
                                        spriteName = spriteName.substring(0, index);
                                        config = config.trim();
                                        while (!config.equals("")) {
                                            config = config.trim();
                                            String temp = null;
                                            if (config.indexOf('+') == 0) {
                                                config = config.substring(1);
                                                //Revisar!
                                                arr = Util.split(config, "+");
                                                temp = arr[0];
                                                //arr = Util.split(temp, "-");
                                                if (temp.indexOf('y') == 0) {
                                                    yOffset += Float.parseFloat(temp.substring(1));
                                                } else if (temp.indexOf('x') == 0) {
                                                    xOffset += Float.parseFloat(temp.substring(1));
                                                }
                                                config = config.substring(temp.length());
                                            } else if (config.indexOf('-') == 0) {
                                                config = config.substring(1);
                                                arr = Util.split(config, "+");
                                                temp = arr[0];
                                                //arr = temp.split("-");
                                                if (temp.indexOf('y') == 0) {
                                                    yOffset -= Float.parseFloat(temp.substring(1));
                                                } else if (temp.indexOf('x') == 0) {
                                                    xOffset -= Float.parseFloat(temp.substring(1));
                                                }
                                                config = config.substring(temp.length());
                                            }
                                        }
                                    }
                                    Sprite sprite = null;
                                    if (lowerValue.indexOf('x') == 0) {
                                        if (this.spriteFactory != null) {
                                            sprite = this.spriteFactory.getSprite(spriteName);
                                            sprite.setX(tilesToPixels(x) + xOffset);
                                            sprite.setY(tilesToPixels(y) + yOffset);
                                            this.spritesBack.add(sprite);
                                        }
                                    }
                                    if (lowerValue.indexOf('y') == 0) {
                                        if (this.spriteFactory != null) {
                                            sprite = this.spriteFactory.getSprite(spriteName);
                                            sprite.setX(tilesToPixels(x) + xOffset);
                                            sprite.setY(tilesToPixels(y) + yOffset);
                                            this.sprites.add(sprite);
                                        }
                                    }
                                    if (lowerValue.indexOf('z') == 0) {
                                        if (this.spriteFactory != null) {
                                            sprite = this.spriteFactory.getSprite(spriteName);
                                            sprite.setX(tilesToPixels(x) + xOffset);
                                            sprite.setY(tilesToPixels(y) + yOffset);
                                            this.spritesFront.add(sprite);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        }
        if (this.scrollingSprite != null) {
            this.scrollingSprite.setX(tilesToPixels(this.posScrollingSpriteTileX));
            this.scrollingSprite.setY(tilesToPixels(this.posScrollingSpriteTileY));
            this.sprites.add(this.scrollingSprite);
        }
    }

    public Point getCollisionTile(Sprite sprite, float newX, float newY) {
        if (sprite.getAnimation() != null && sprite.getAnimation().getImage() != null) {
            // Puntos a Pixeles
            float desdeX = Math.min(sprite.getX(), newX);
            float desdeY = Math.min(sprite.getY(), newY);
            float hastaX = Math.max(sprite.getX(), newX);
            float hastaY = Math.max(sprite.getY(), newY);
            // Pixeles a Tiles
            int desdeTileX = pixelsToTiles(desdeX);
            /*if (desdeTileX != desdeX * TILEMAP_TILE_SIZE) {
             desdeTileX--;
             }*/
            int desdeTileY = pixelsToTiles(desdeY);
            /*if (desdeTileY != desdeY * TILEMAP_TILE_SIZE) {
             desdeTileY--;
             }*/

            int hastaTileX = pixelsToTiles(hastaX + sprite.getAnimation().getImage().getWidth() - 1);
            int hastaTileY = pixelsToTiles(hastaY + sprite.getAnimation().getImage().getHeight() - 1);
            // check each tile for a collision
            int x, y;
            boolean limitExceeded = false;
            for (x = desdeTileX; x <= hastaTileX; x++) {
                for (y = desdeTileY; y <= hastaTileY; y++) {
                    //Boundary detection
                    //x < 0
                    /*if (x < 0) {
                     // existe colision, retorna el punto
                     this.pointCache.setX(-1);
                     this.pointCache.setY((int) sprite.getY());
                     limitExceeded = true;
                     } else if (x >= this.width) {
                     this.pointCache.setX(this.width);
                     this.pointCache.setY((int) sprite.getY());
                     limitExceeded = true;
                     }
                     if (y < 0) {
                     this.pointCache.setX((int) sprite.getX());
                     this.pointCache.setY(-1);
                     limitExceeded = true;
                     } else if (y >= this.height) {
                     this.pointCache.setX((int) sprite.getX());
                     this.pointCache.setY(this.height);
                     limitExceeded = true;
                     }
                     if (limitExceeded) {
                     return this.pointCache;
                     }*/
                    //Validación de límites de matrices
                    if ((x < 0 || x >= this.width) || (y < 0 || y >= this.height)) {
                        //Colision con Tiles de tipo solido
                        // existe colision, retorna el punto
                        if (BOUNDARY_COLLISION) {
                            this.pointCache.setX(x);
                            this.pointCache.setY(y);
                            return this.pointCache;
                        }
                    } else {
                        if (this.tilesSolid[x][y] != null) {
                            this.pointCache.setX(x);
                            this.pointCache.setY(y);
                            return this.pointCache;
                        }
                        //Colision con Tiles de tipo techo
                        if (sprite.getY() < newY
                                && (sprite.getY() + sprite.getAnimation().getImage().getHeight()) <= (TileMap.tilesToPixels(y) + 8)
                                && this.tilesTecho[x][y] != null) {
                            // existe colision, retorna el punto
                            this.pointCache.setX(x);
                            this.pointCache.setY(y);
                            return this.pointCache;
                        }
                    }
                }
            }
        }
        // No se encontro colision

        return null;
    }

    public Sprite getCollisionSprite(Sprite sprite, float x, float y) {
        if (sprite != null) {
            //Version multithread
            //var arrSprite = this.sprites.array.slice(0);
            Sprite[] arrSprite = new Sprite[getSprites().size()];
            arrSprite = this.getSprites().toArray(arrSprite);
            for (Sprite otroSprite : arrSprite) {
                if (otroSprite != null
                        && otroSprite.getAnimation() != null
                        && otroSprite.getAnimation().getImage() != null
                        && sprite.getAnimation() != null
                        && sprite.getAnimation().getImage() != null && otroSprite != sprite) {
                    if (x < otroSprite.getX() + otroSprite.getAnimation().getImage().getWidth()
                            && x + sprite.getAnimation().getImage().getWidth() > otroSprite.getX()
                            && y < otroSprite.getY() + otroSprite.getAnimation().getImage().getHeight()
                            && y + sprite.getAnimation().getImage().getWidth() > otroSprite.getY()) {
                        // si existe colision, retorna el sprite
                        return otroSprite;
                    }
                }
            }
        }
        // no collision found
        return null;
    }

    public void updateCollisions(Sprite sprite, long time) {
        float dx, oldX, newX, dy, oldY, newY;
        if (sprite != null) {
            if (sprite.isSpriteSolid() || sprite.isTileSolid()) {
                dx = sprite.getSpeedX();
                oldX = sprite.getX();
                newX = oldX + dx * time;
                dy = sprite.getSpeedY();
                oldY = sprite.getY();
                newY = oldY + dy * time;
                if (sprite.isTileSolid()) {
                    // Corregir x
                    Point tile = this.getCollisionTile(sprite, newX, sprite.getY());
                    if (tile != null) {
                        sprite.setMarkX(true);
                        // line up with the tile boundary
                        if (dx > 0) {
                            float x = tilesToPixels(tile.getX())
                                    - sprite.getAnimation().getImage().getWidth();
                            sprite.setX(x);
                        } else if (dx < 0) {
                            float x = tilesToPixels(tile.getX() + 1);
                            sprite.setX(x);
                        }
                        sprite.event_tileHorizontalCollision();
                    }
                    // Corregir y
                    tile = this.getCollisionTile(sprite, sprite.getX(), newY);
                    if (tile != null) {
                        sprite.setMarkY(true);
                        // line up with the tile boundary
                        if (dy > 0) {
                            sprite.setY(tilesToPixels(tile.getY()) - sprite.getAnimation().getImage().getHeight());
                        } else if (dy < 0) {
                            sprite.setY(tilesToPixels(tile.getY() + 1));
                        }
                        sprite.event_tileVerticalCollision();
                    }
                }
                //if (objSprite.solidoSprites || objSprite is BoxCollisionSprite) {
                if (sprite.isSpriteSolid()) {
                    //Colision Horizontal Sprites
                    Sprite otroSprite = this.getCollisionSprite(sprite, newX, sprite.getY());
                    if (otroSprite != null) {
                        otroSprite.event_spriteHorizontalCollision(sprite);
                    }
                    //Colision Vertical Sprites
                    otroSprite = this.getCollisionSprite(sprite, sprite.getX(), newY);
                    if (otroSprite != null) {
                        otroSprite.event_spriteVerticalCollision(sprite);
                    }
                }
            }
        }
    }

    private String[] getTileList() {
        String[] tileImages = new String[TILEMAP_MAX_TILE];
        for (int i = 0; i < TILEMAP_MAX_TILE; i++) {
            String filename = "" + i;
            if (i < 10 && TILEMAP_TILE_MIN_DIGITS == 2) {
                filename = "0" + filename;
            }
            if (i < 100 && TILEMAP_TILE_MIN_DIGITS == 3) {
                filename = "0" + filename;
            }
            if (i < 1000 && TILEMAP_TILE_MIN_DIGITS == 4) {
                filename = "0" + filename;
            }
            if (i < 10000 && TILEMAP_TILE_MIN_DIGITS == 5) {
                filename = "0" + filename;
            }
            if (i < 100000 && TILEMAP_TILE_MIN_DIGITS == 6) {
                filename = "0" + filename;
            }
            tileImages[i] = TILEMAP_BASE_DIR + filename + ".png";
        }
        return tileImages;
    }

    /**
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the ready
     */
    public boolean isReady() {
        return ready;
    }
}
