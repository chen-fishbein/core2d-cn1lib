package com.nlcode.cn1.core;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.nlcode.cn1.core.audio.SoundEffect;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 *
 * @author NLCodePc01
 */
public class ResourceManager {

    private static final ResourceManager instance = new ResourceManager();

    private ResourceManager() {
    }

    public static String loadText(String path) {
        String text = "";
        InputStream in = Display.getInstance().getResourceAsStream(Form.class, path);
        if (in != null) {
            try {
                text = com.codename1.io.Util.readToString(in);
                in.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return text;
    }

    //Image Cache
    private static final HashMap<String, Image> imgCache = new HashMap<String, Image>();

    /*
     Loads an Image and stores on cache the next request of the same image
     */
    public static Image loadImage(String path) {
        Image img = imgCache.get(path);
        if (img == null) {
            try {
                img = Image.createImage(path);
                imgCache.put(path, img);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return img;
    }

    /*
     Clears image cache
     */
    public static void clearImageCache() {
        imgCache.clear();
    }

    public static SoundEffect loadSound(String path) {
        SoundEffect s = null;
        if (path.endsWith(".mp3")) {
            s = loadSound(path, "audio/mp3");
        }else if (path.endsWith(".wav")){
            s = loadSound(path, "audio/wav");
        }
        return s;
    }

    public static SoundEffect loadSound(String path, String mimeType) {
        SoundEffect s = null;
        InputStream is = Display.getInstance().getResourceAsStream(instance.getClass(), path);
        try {
            Media m = MediaManager.createMedia(is, mimeType);
            s = new SoundEffect(path, m);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return s;
    }
}