package com.nlcode.cn1.core.input;

import java.util.HashMap;

/**
 *
 * @author User
 */
public class ScreenpadState {
    HashMap<Screenpad.Button, Boolean> pressedButtons;
    
    ScreenpadState() {
        this.pressedButtons = new HashMap<Screenpad.Button, Boolean>();
        for (Screenpad.Button button : Screenpad.Button.values()) {
            pressedButtons.put(button, Boolean.FALSE);
        }
    }

    public boolean isButtonDown(Screenpad.Button button) {
        /*Boolean r = pressedKeys.get(key);
         return r == null ? false : r;*/
        return pressedButtons.get(button);
    }

    public void inactivate() {
        Screenpad.inactiveObjects.add(this);
        Screenpad.activeObjects.remove(this);
    }
}