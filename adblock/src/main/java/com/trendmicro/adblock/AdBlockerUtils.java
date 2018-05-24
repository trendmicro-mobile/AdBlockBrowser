package com.trendmicro.adblock;

import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by swift_gan on 2018/4/3.
 */

public class AdBlockerUtils {

    public final static int CONTENT_TYPE_NONE = 0;
    public final static int CONTENT_TYPE_IMAGE = 1;
    public final static int CONTENT_TYPE_STYLE = 2;
    public final static int CONTENT_TYPE_SCRIPT = 3;

    static {
        System.loadLibrary("ad_block");
    }

    public static String getDomain(String url) {
        try {
            URL u = new URL(url);
            String domain = u.getHost();
            return domain != null ? domain : "";
        } catch (Exception e) {
            return "";
        }
    }

    public static int getContentType(String requestUrl, String accept) {
        if (TextUtils.isEmpty(requestUrl))
            return CONTENT_TYPE_NONE;
        if (ResourceType.isScript(requestUrl)) {
            return CONTENT_TYPE_SCRIPT;
        } else if (ResourceType.isCss(requestUrl)) {
            return CONTENT_TYPE_STYLE;
        } else if (ResourceType.isImage(requestUrl)
                || (accept != null && accept.contains("image/") && !accept.contains("text/") && !accept.contains("application/") && !accept.contains("video/") && !accept.contains("audio/"))) {
            return CONTENT_TYPE_IMAGE;
        } else  {
            return CONTENT_TYPE_NONE;
        }
    }

    public static boolean copyAssetFolder(AssetManager assetManager,
                                          String fromAssetPath, String toPath) {
        try {
            String[] files = assetManager.list(fromAssetPath);
            new File(toPath).mkdirs();
            boolean res = true;
            for (String file : files)
                if (file.contains("."))
                    res &= copyAsset(assetManager,
                            fromAssetPath + "/" + file,
                            toPath + "/" + file);
                else
                    res &= copyAssetFolder(assetManager,
                            fromAssetPath + "/" + file,
                            toPath + "/" + file);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private static boolean copyAsset(AssetManager assetManager,
                                     String fromAssetPath, String toPath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(fromAssetPath);
            File file = new File(toPath);
            if (file.exists() && file.length() == in.available()) {
                return true;
            }
            file.createNewFile();
            out = new FileOutputStream(file);
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public static native boolean initAdBlocker(String[] ptns);

    public static native boolean isAd(String url, String domain, int contentType);

    public static native boolean initPrivacyBlocker(String[] ptns);

    public static native boolean isPrivacy(String url, String domain, int contentType);

}
