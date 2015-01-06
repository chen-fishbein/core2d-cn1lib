package com.nlcode.cd1.mario;

import com.nlcode.cd1.mario.sprites.Coin;
import com.nlcode.cn1.core.graphics.Sprite;
import com.nlcode.cn1.core.graphics.SpriteFactory;


/**
 *
 * @author NLCodePc01
 */
public class MarioSpriteFactory implements SpriteFactory{

    public Sprite getSprite(String spriteName) {
        Sprite sprite = null;
        if (spriteName.equalsIgnoreCase("coin")){
            sprite = new Coin();
        }
        return sprite;
    }
    
}