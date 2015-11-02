package io.github.bobhostern.bellre.bell.local;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bobho_000 on 10/6/2015.
 * Loads and manages Pixmaps. Why? Because of disposal.
 */
public class PixmapLoader implements Disposable {
    private Map<String, Pixmap> pixmaps;

    public PixmapLoader() {
        pixmaps = new HashMap<String, Pixmap>();
    }

    Pixmap loadPixmap(FileHandle handle) {
        Pixmap pixmap = null;
        if (!pixmaps.containsKey(handle.path())) {
            pixmap = new Pixmap(handle);
            pixmaps.put(handle.path(), pixmap);
        } else
            pixmap = pixmaps.get(handle.path());
        return pixmap;
    }

    @Override
    public void dispose() {
        for (Pixmap pm : pixmaps.values()) {
            pm.dispose();
        }
    }
}
