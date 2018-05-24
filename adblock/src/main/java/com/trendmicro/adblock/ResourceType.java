package com.trendmicro.adblock;

import android.text.TextUtils;

/**
 * Created by swift_gan on 2018/4/9.
 */

public class ResourceType {

    public static boolean isScript(String url) {
        if (TextUtils.isEmpty(url))
            return false;
        String s = url.toLowerCase();
        return s.endsWith(".js");
    }

    public static boolean isImage(String url) {
        if (TextUtils.isEmpty(url))
            return false;
        String s = url.toLowerCase();
        return s.endsWith(".png")
                || s.endsWith(".bmp")
                || s.endsWith(".gif")
                || s.endsWith(".jpg")
                || s.endsWith(".jpeg")
                || s.endsWith(".jpe")
                || s.endsWith(".svg")
                || s.endsWith(".tif")
                || s.endsWith(".tiff")
                || s.endsWith(".ief")
                || s.endsWith(".rgb");
    }

    public static boolean isCss(String url) {
        if (TextUtils.isEmpty(url))
            return false;
        String s = url.toLowerCase();
        return s.endsWith(".css");
    }

}
