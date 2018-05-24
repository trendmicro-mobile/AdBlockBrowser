package com.trendmicro.browser;

import android.app.Application;

import com.trend.lazyinject.annotation.Inject;
import com.trend.lazyinject.lib.LazyInject;
import com.trendmicro.adblock.AdBlockerUtils;
import com.trendmicro.adblock.UrlChecker;
import com.trendmicro.adblock.UrlCheckerBuildMap;

import java.io.File;

/**
 * Created by swift_gan on 2018/5/24.
 */

public class BrowserApplication extends Application {

    public static Application app;

    @Inject
    UrlChecker.AdBlocker adBlocker;
    @Inject
    UrlChecker.PrivacyBlocker privacyBlocker;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        LazyInject.addBuildMap(UrlCheckerBuildMap.class, AppBuildMap.class);
        initBlockList();
    }

    private void initBlockList() {
        new Thread(() -> {
            initUrlChecker("adlist", false);
            initUrlChecker("privacylist", true);
        }).start();
    }

    private void initUrlChecker(String path, boolean isPrivacy) {
        AdBlockerUtils.copyAssetFolder(getAssets(), path, getFilesDir().getAbsolutePath() + "/" + path);
        File[] files = new File(getFilesDir().getAbsolutePath() + "/" + path).listFiles();
        String[] paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            paths[i] = files[i].getAbsolutePath();
        }
        if (isPrivacy) {
            privacyBlocker.init(paths);
        } else {
            adBlocker.init(paths);
        }
    }

}
