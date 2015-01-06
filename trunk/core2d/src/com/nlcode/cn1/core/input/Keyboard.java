package com.nlcode.cn1.core.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author NLCodeCoreI7
 */
public class Keyboard {

    private static HashMap<Integer, Keyboard.Key> cache;

    public static Keyboard.Key findByKeyCode(int keyCode) {
        if (cache == null) {
            cache = new HashMap<Integer, Keyboard.Key>();
            for (Keyboard.Key key : Keyboard.Key.values()) {
                cache.put(key.getKeyCode(), key);
            }
        }
        return cache.get(keyCode);
    }

    private static final HashMap<Keyboard.Key, Boolean> currentKeys = new HashMap<Keyboard.Key, Boolean>();

    /**
     * @return the pendingKeys
     */
    public static HashMap<Keyboard.Key, Boolean> getCurrentKeys() {
        return currentKeys;
    }

    public static KeyboardState getState() {
        KeyboardState ks = getNewInstance();
        for (Key key : currentKeys.keySet()) {
            ks.pressedKeys.put(key, currentKeys.get(key));
        }
        return ks;
    }

    static final List<KeyboardState> inactiveObjects = new ArrayList<KeyboardState>();
    static final List<KeyboardState> activeObjects = new ArrayList<KeyboardState>();

    private static KeyboardState getNewInstance() {
        KeyboardState ks;
        // check to see if there is a spare one
        if (inactiveObjects.size() > 0) {
            ks = inactiveObjects.remove(0);
        } else {
            // none left, construct a new one
            ks = new KeyboardState();
        }
        activeObjects.add(ks);
        return ks;
    }

    public enum Key {

        A('a'),
        ADD('+'),
        ASTERISK('*'),
        ALT(0), // UNSUPPORTED
        B('b'),
        BACKSPACE(8),
        BACKSLASH('\\'),
        C('c'),
        CAPS_LOCK(20),
        CLOSE_BRACKET(']'),
        COMMA(','),
        CTRL(0),// UNSUPPORTED 
        D('d'),
        DASH('-'),
        DELETE(127),
        DIVIDE('/'),
        DOWN(-92), //PC DOWN
        E('e'),
        END(35),
        ENTER(-90),
        EQUAL('='),
        ESC(27),
        F('f'),
        F1(112),
        F2(113),
        F3(114),
        F4(115),
        F5(116),
        F6(117),
        F7(118),
        F8(119),
        F9(120),
        F10(121),
        F11(122),
        F12(123),
        G('g'),
        H('h'),
        HOME(36),
        I('i'),
        INSERT(155),
        J('j'),
        K('k'),
        L('l'),
        LEFT(-93),
        LEFT_WINDOW(524),
        M('m'),
        MINUS('-'),
        MULTIPLY(106),
        N('n'),
        NUM_LOCK(144),
        NUMBER_0('0'),
        NUMBER_1('1'),
        NUMBER_2('2'),
        NUMBER_3('3'),
        NUMBER_4('4'),
        NUMBER_5('5'),
        NUMBER_6('6'),
        NUMBER_7('7'),
        NUMBER_8('8'),
        NUMBER_9('9'),
        NUMPAD_NUMBER_0(96),
        NUMPAD_NUMBER_1(97),
        NUMPAD_NUMBER_2(98),
        NUMPAD_NUMBER_3(99),
        NUMPAD_NUMBER_4(100),
        NUMPAD_NUMBER_5(101),
        NUMPAD_NUMBER_6(102),
        NUMPAD_NUMBER_7(103),
        NUMPAD_NUMBER_8(104),
        NUMPAD_NUMBER_9(105),
        O('o'),
        OPEN_BRACKET(']'),
        P('p'),
        PERIOD('.'),
        PLUS(107),
        PAGE_UP(33),
        PAGE_DOWN(34),
        PAUSE_BREAK(19),
        Q('q'),
        QUOTE(129),
        R('r'),
        RIGHT(-94),
        RIGHT_WINDOW(525),
        S('s'),
        SCROLL_LOCK(0), //UNKNOWN
        SEMICOLON(';'),
        SHIFT(16),
        SLASH('/'),
        SPACE(-90),
        SUBSTRACT('-'),
        T('t'),
        TAB(0), //UNSUPPORTED
        U('u'),
        UP(-91),
        V('v'),
        W('w'),
        X('x'),
        XPERIA_PLAY_UP(-23448),
        XPERIA_PLAY_DOWN(-23449),
        XPERIA_PLAY_LEFT(-23446),
        XPERIA_PLAY_RIGHT(-23447),
        XPERIA_PLAY_R(0), //UNSUPPORTED
        XPERIA_PLAY_L(0), //UNSUPPORTED
        XPERIA_PLAY_TRIANGLE(0), //UNSUPPORTED
        XPERIA_PLAY_SQUARE(0), //UNSUPPORTED
        XPERIA_PLAY_START(0), //UNSUPPORTED
        XPERIA_PLAY_SELECT(0), //UNSUPPORTED
        XPERIA_PLAY_CIRCLE(-23452),
        XPERIA_PLAY_X(-23450),
        Y('y'),
        Z('z');

        private final int keyCode;

        Key(int keyCode) {
            this.keyCode = keyCode;
        }

        /**
         * @return the keyCode
         */
        public int getKeyCode() {
            return keyCode;
        }

        private static HashMap<Integer, Keyboard.Key> cache;

        public static Keyboard.Key findByKeyCode(int keyCode) {
            if (cache == null) {
                cache = new HashMap<Integer, Keyboard.Key>();
                for (Keyboard.Key key : Keyboard.Key.values()) {
                    cache.put(key.getKeyCode(), key);
                }
            }
            return cache.get(keyCode);
        }
    }
}