package com.trendmicro.browser.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by swift_gan on 2017/12/22.
 */

public class ViewUtils {


    private static String sNavBarOverride;

    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
    private static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
    private static final String NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape";
    private static final String NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width";
    private static final String SHOW_NAV_BAR_RES_NAME = "config_showNavigationBar";

    static {
        // Android allows a system property to override the presence of the navigation bar.
        // Used by the emulator.
        // See https://github.com/android/platform_frameworks_base/blob/master/policy/src/com/android/internal/policy/impl/PhoneWindowManager.java#L1076
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
                sNavBarOverride = null;
            }
        }
    }

    public static int sp2px(Context context, float spValue) {
        if (spValue == 0)
            return 0;
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue) {
        if (dipValue == 0)
            return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static float px2dp(Context context, int pxValue) {
        if (pxValue == 0)
            return 0;

        final float scale = context.getResources().getDisplayMetrics().density;
        return (float) ((pxValue) / scale);
    }
    public static int getCurrentOrientation(Context context) {
        boolean bReverse = isActivityReverseNow(context);
        if (isActivityPortraitNow(context)) {
            return (bReverse ? ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else {
            return (bReverse ? ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
    public static boolean isActivityReverseNow(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int rotation = windowManager.getDefaultDisplay().getRotation();
        boolean bReverse = (rotation >= Surface.ROTATION_180);
        if(isActivityPortraitNow(context)) {
            if(rotation % 2 == 1)
                bReverse = !bReverse;
        }
        return bReverse;
    }
    public static boolean isActivityPortraitNow(Context context) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
    }
    public static int getNavigationBarHeight(Context context) {
        boolean bPortrait = isActivityPortraitNow(context);
        Resources res = context.getResources();
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar(context)) {
                String key;
                if (bPortrait) {
                    key = NAV_BAR_HEIGHT_RES_NAME;
                } else {
                    key = NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME;
                }
                return getInternalDimensionSize(res, key);
            }
        }
        return result;
    }
    @TargetApi(14)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier(SHOW_NAV_BAR_RES_NAME, "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag (see static block)
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static boolean updateViewVisibility(View v, int visibility) {
        boolean bRet = false;
        if(v == null)
            return false;

        int old = v.getVisibility();
        if(old != visibility)
        {
            v.setVisibility(visibility);
            bRet = true;
        }

        return bRet;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        return drawableToBitmap(drawable, w, h);
    }

    public static Bitmap drawableToBitmap(Drawable drawable, int w, int h) {
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public static boolean isSoftKeyboardShow(View rootView) {
        int screenHeight = rootView.getContext().getResources().getDisplayMetrics().heightPixels;
        int screenWidth = rootView.getContext().getResources().getDisplayMetrics().widthPixels;
        int threshold = screenHeight / 3;
        int rootViewBottom = rootView.getBottom();
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        int visibleBottom = rect.bottom;
        int heightDiff = rootViewBottom - visibleBottom;
        return heightDiff > threshold;
    }


}
