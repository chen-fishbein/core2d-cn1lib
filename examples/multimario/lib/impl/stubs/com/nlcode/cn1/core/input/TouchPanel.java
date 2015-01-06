package com.nlcode.cn1.core.input;


/**
 * 
 *  @author NLCode
 */
public class TouchPanel {

	public TouchPanel() {
	}

	public static TouchPanelState getState() {
	}

	/**
	 *  @return the touch panel capabilities
	 */
	public static TouchPanel.TouchPanelCapabilities getCapabilities() {
	}

	public static class TouchPanelCapabilities {


		/**
		 *  @return the maximumTouchCount
		 */
		public int getMaximumTouchCount() {
		}

		/**
		 *  @return the connected
		 */
		public boolean isConnected() {
		}
	}

	public static class TouchLocation {


		public TouchPanel.TouchLocation(int id, com.codename1.ui.geom.Point position, TouchPanel.TouchLocationState state) {
		}

		/**
		 *  @return the id
		 */
		public int getId() {
		}

		/**
		 *  @return the position
		 */
		public com.codename1.ui.geom.Point getPosition() {
		}

		/**
		 *  @return the state
		 */
		public TouchPanel.TouchLocationState getState() {
		}
	}

	public static final class TouchLocationState {


		public static final TouchPanel.TouchLocationState Invalid;

		public static final TouchPanel.TouchLocationState Moved;

		public static final TouchPanel.TouchLocationState Pressed;

		public static final TouchPanel.TouchLocationState Released;

		public static TouchPanel.TouchLocationState[] values() {
		}

		public static TouchPanel.TouchLocationState valueOf(String name) {
		}
	}
}
