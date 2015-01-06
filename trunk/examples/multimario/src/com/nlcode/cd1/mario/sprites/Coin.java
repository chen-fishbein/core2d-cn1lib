package com.nlcode.cd1.mario.sprites;

import com.nlcode.cn1.core.graphics.Animation;
import com.nlcode.cn1.core.ResourceManager;
import com.nlcode.cn1.core.graphics.Sprite;

/**
 *
 * @author NLCodePc01
 */
public class Coin extends Sprite {

    public Coin() {
        Animation anim = new Animation();
        anim.addFrame(ResourceManager.loadImage("/s_coin_01.png"), 200);
        anim.addFrame(ResourceManager.loadImage("/s_coin_02.png"), 200);
        anim.addFrame(ResourceManager.loadImage("/s_coin_03.png"), 200);
        anim.addFrame(ResourceManager.loadImage("/s_coin_04.png"), 200);
        setAnimation(anim);
        setActive(true);
    }

    @Override
    public void event_spriteHorizontalCollision(Sprite sprite) {
        coinCollision(sprite);
    }

    @Override
    public void event_spriteVerticalCollision(Sprite sprite) {
        coinCollision(sprite);
    }

    private void coinCollision(Sprite sprite) {
        if (sprite instanceof Mario) {
            Mario objMario = ((Mario) sprite);
            //this.sound.replay();
            objMario.setCoins(objMario.getCoins() + 1);
            this.getStage().getSprites().remove(this);
        }
    }
}
