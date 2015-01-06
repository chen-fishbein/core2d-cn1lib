package com.nlcode.cn1.core.input;

import com.codename1.ui.Display;
import com.codename1.ui.geom.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NLCode
 */
public class TouchPanel {

    //Continue according to: http://www.xnawiki.com/index.php/Touch_Input
    //http://www.luisleo.net/touch-events-in-xna/
    private static final TouchLocation[] currentTouchLocations = fill();

    private static TouchLocation[] fill() {
        TouchLocation[] tl = new TouchLocation[TouchPanel.getCapabilities().getMaximumTouchCount()];
        for (int i = 0; i < tl.length; i++) {
            tl[i] = new TouchLocation(i, new Point(-1, -1), TouchLocationState.Invalid);
        }
        return tl;
    }

    public static TouchPanelState getState() {
        TouchPanelState tps = getNewInstance();
        for (int i = 0; i < currentTouchLocations.length; i++) {
            tps.getTouchLocations()[i].setPosition(currentTouchLocations[i].getPosition());
            tps.getTouchLocations()[i].setState(currentTouchLocations[i].getState());
        }
        return tps;
    }

    /**
     * @return the touch panel capabilities
     */
    public static TouchPanelCapabilities getCapabilities() {
        if (touchPanelCapabilitiesInstance == null) {
            touchPanelCapabilitiesInstance = new TouchPanelCapabilities();
        }
        return touchPanelCapabilitiesInstance;
    }

    private static TouchPanelCapabilities touchPanelCapabilitiesInstance;

    /**
     * @return the currentTouchLocations
     */
    static TouchLocation[] getCurrentTouchLocations() {
        return currentTouchLocations;
    }

    public static class TouchPanelCapabilities {

        private final int maximumTouchCount;
        private final boolean connected;

        private TouchPanelCapabilities() {
            connected = Display.getInstance().isTouchScreenDevice() || Display.getInstance().isPureTouch() || Display.getInstance().isMultiTouch();
            // Arbitrary number for now, until codenameone includes a way for
            // knowing this.
            maximumTouchCount = 3;
        }

        /**
         * @return the maximumTouchCount
         */
        public int getMaximumTouchCount() {
            return maximumTouchCount;
        }

        /**
         * @return the connected
         */
        public boolean isConnected() {
            return connected;
        }
    }

    static final List<TouchPanelState> inactiveObjects = new ArrayList<TouchPanelState>();
    static final List<TouchPanelState> activeObjects = new ArrayList<TouchPanelState>();

    private static TouchPanelState getNewInstance() {
        TouchPanelState tps = null;
        // check to see if there is a spare one
        if (inactiveObjects.size() > 0) {
            tps = inactiveObjects.remove(0);
        } else {
            // none left, construct a new one
            tps = new TouchPanelState();
        }
        activeObjects.add(tps);
        return tps;
    }
    
    public static class TouchLocation {

        private int id;
        private Point position;
        private TouchLocationState state;

        public TouchLocation(int id, Point position, TouchLocationState state) {
            this.id = id;
            this.position = position;
            this.state = state;
        }

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the position
         */
        public Point getPosition() {
            return position;
        }

        /**
         * @return the state
         */
        public TouchLocationState getState() {
            return state;
        }

        /**
         * @param id the id to set
         */
        void setId(int id) {
            this.id = id;
        }

        /**
         * @param position the position to set
         */
        void setPosition(Point position) {
            this.position = position;
        }

        /**
         * @param state the state to set
         */
        void setState(TouchLocationState state) {
            this.state = state;
        }
    }

    public enum TouchLocationState {

        Invalid,
        Moved,
        Pressed,
        Released
    }
}
