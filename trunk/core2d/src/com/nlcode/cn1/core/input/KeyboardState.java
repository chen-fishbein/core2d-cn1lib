package com.nlcode.cn1.core.input;

import java.util.HashMap;

/**
 *
 * @author AntonioJazo
 */
public class KeyboardState {
   
    HashMap<Keyboard.Key, Boolean> pressedKeys;
    
    KeyboardState() {
        this.pressedKeys = new HashMap<Keyboard.Key, Boolean>();
        for (Keyboard.Key key : Keyboard.Key.values()) {
            pressedKeys.put(key, Boolean.FALSE);
        }
    }

    public boolean isKeyDown(Keyboard.Key key) {
        /*Boolean r = pressedKeys.get(key);
         return r == null ? false : r;*/
        return pressedKeys.get(key);
    }

    public void inactivate() {
        Keyboard.inactiveObjects.add(this);
        Keyboard.activeObjects.remove(this);
    }
}
