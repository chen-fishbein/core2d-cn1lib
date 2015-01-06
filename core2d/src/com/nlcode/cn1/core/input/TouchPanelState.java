package com.nlcode.cn1.core.input;

import com.codename1.ui.geom.Point;

/**
 *
 * @author NLCode
 */
public class TouchPanelState {

    private final TouchPanel.TouchLocation[] touchLocations;

    TouchPanelState() {
        touchLocations = new TouchPanel.TouchLocation[TouchPanel.getCapabilities().getMaximumTouchCount()];
        for (int i = 0; i < touchLocations.length; i++) {
            touchLocations[i] = new TouchPanel.TouchLocation(i, new Point(-1, -1), TouchPanel.TouchLocationState.Invalid);
        }
    }

    /**
     * @return the touchLocations
     */
    public TouchPanel.TouchLocation[] getTouchLocations() {
        return touchLocations;
    }

    public void inactivate() {
        TouchPanel.inactiveObjects.add(this);
        TouchPanel.activeObjects.remove(this);
    }
}