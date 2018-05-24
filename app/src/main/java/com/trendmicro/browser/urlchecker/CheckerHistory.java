package com.trendmicro.browser.urlchecker;

import android.content.Context;
import android.content.SharedPreferences;

import com.trend.lazyinject.annotation.Inject;
import com.trendmicro.browser.component.AppComponent;

/**
 * Created by swift_gan on 2018/4/12.
 */

public class CheckerHistory {

    private static CheckerHistory INSTANCE = new CheckerHistory();
    private static String KEY_CHECK_HISTORY = "sp_url_check_history";
    private static String KEY_CHECK_AD_HISTORY = "ad_check_history";
    private static String KEY_CHECK_PRIVACY_HISTORY = "privacy_check_history";

    SharedPreferences sharedPreferences;

    @Inject(component = AppComponent.class)
    Context context;

    public static CheckerHistory getInstance() {
        return INSTANCE;
    }


    private CheckerHistory() {
        sharedPreferences = context.getSharedPreferences(KEY_CHECK_HISTORY, Context.MODE_PRIVATE);
    }

    public void addAdChecked() {
        synchronized (KEY_CHECK_AD_HISTORY) {
            sharedPreferences.edit().putInt(KEY_CHECK_AD_HISTORY, adChecked() + 1).apply();
        }
    }

    public void addPrivacyChecked() {
        synchronized (KEY_CHECK_PRIVACY_HISTORY) {
            sharedPreferences.edit().putInt(KEY_CHECK_PRIVACY_HISTORY, privacyChecked() + 1).apply();
        }
    }

    public int adChecked() {
        return sharedPreferences.getInt(KEY_CHECK_AD_HISTORY, 0);
    }

    public int privacyChecked() {
        return sharedPreferences.getInt(KEY_CHECK_PRIVACY_HISTORY, 0);
    }

}
