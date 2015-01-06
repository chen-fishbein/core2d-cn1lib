package com.nlcode.cn1.core.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author User
 */
public class Screenpad {
    
    private static final HashMap<Screenpad.Button, Boolean> currentButtons = new HashMap<Screenpad.Button, Boolean>();

    /**
     * @return the pendingKeys
     */
    static HashMap<Screenpad.Button, Boolean> getCurrentKeys() {
        return currentButtons;
    }

    public static ScreenpadState getState() {
        ScreenpadState sp = getNewInstance();
        for (Button button : currentButtons.keySet()) {
            sp.pressedButtons.put(button, currentButtons.get(button));
        }
        return sp;
    }

    static final List<ScreenpadState> inactiveObjects = new ArrayList<ScreenpadState>();
    static final List<ScreenpadState> activeObjects = new ArrayList<ScreenpadState>();

    private static ScreenpadState getNewInstance() {
        ScreenpadState sp;
        // check to see if there is a spare one
        if (inactiveObjects.size() > 0) {
            sp = inactiveObjects.remove(0);
        } else {
            // none left, construct a new one
            sp = new ScreenpadState();
        }
        activeObjects.add(sp);
        return sp;
    }

    public enum Button {

        UP,
        DOWN,
        LEFT,
        RIGHT,
        B1,
        B2,
        B3,
        B4,
        B5,
        B6,
        B8
    }
}