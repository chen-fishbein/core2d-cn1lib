package com.nlcode.cn1.core.input;

/**
 *
 * @author NLCodeCoreI7
 */
public class InputEvent {
    
    public static void registerScreenPadButtonPress(Screenpad.Button button) {
        Screenpad.getCurrentKeys().put(button, Boolean.TRUE);
    }
    
    public static void registerScreenPadButtonRelease(Screenpad.Button button) {
        Screenpad.getCurrentKeys().put(button, Boolean.FALSE);
    }

    public static void registerKeyPress(Keyboard.Key key) {
        Keyboard.getCurrentKeys().put(key, Boolean.TRUE);
    }

    public static void registerkeyRelease(Keyboard.Key key) {
        Keyboard.getCurrentKeys().put(key, Boolean.FALSE);
    }
    
    public static void registerKeyPress(int keyCode) {
        Keyboard.getCurrentKeys().put(Keyboard.findByKeyCode(keyCode), Boolean.TRUE);
    }

    public static void registerKeyRelease(int keyCode) {
        Keyboard.getCurrentKeys().put(Keyboard.findByKeyCode(keyCode), Boolean.FALSE);
    }

    public static void registerTouchMovement(int[] x, int[] y) {
        int touchCount = TouchPanel.getCapabilities().getMaximumTouchCount();
        TouchPanel.TouchLocation[] currentTL = TouchPanel.getCurrentTouchLocations();
        for (int i = 0; i < touchCount; i++) {
            if (i < x.length) {
                currentTL[i].getPosition().setX(x[i]);
                currentTL[i].getPosition().setY(y[i]);
                currentTL[i].setState(TouchPanel.TouchLocationState.Moved);
            } else {
                currentTL[i].getPosition().setX(-1);
                currentTL[i].getPosition().setY(-1);
                currentTL[i].setState(TouchPanel.TouchLocationState.Invalid);
            }
        }
    }

    public static void registerTouchPress(int[] x, int[] y) {
        int touchCount = TouchPanel.getCapabilities().getMaximumTouchCount();
        TouchPanel.TouchLocation[] currentTL = TouchPanel.getCurrentTouchLocations();
        for (int i = 0; i < touchCount; i++) {
            if (i < x.length) {
                currentTL[i].getPosition().setX(x[i]);
                currentTL[i].getPosition().setY(y[i]);
                currentTL[i].setState(TouchPanel.TouchLocationState.Pressed);
            } else {
                currentTL[i].getPosition().setX(-1);
                currentTL[i].getPosition().setY(-1);
                currentTL[i].setState(TouchPanel.TouchLocationState.Invalid);
            }
        }
    }

    public static void registerTouchRelease(int[] x, int[] y) {
        int touchCount = TouchPanel.getCapabilities().getMaximumTouchCount();
        TouchPanel.TouchLocation[] currentTL = TouchPanel.getCurrentTouchLocations();
        for (int i = 0; i < touchCount; i++) {
            if (i < x.length) {
                currentTL[i].getPosition().setX(x[i]);
                currentTL[i].getPosition().setY(y[i]);
                currentTL[i].setState(TouchPanel.TouchLocationState.Released);
            } else {
                currentTL[i].getPosition().setX(-1);
                currentTL[i].getPosition().setY(-1);
                currentTL[i].setState(TouchPanel.TouchLocationState.Invalid);
            }
        }
    }
}