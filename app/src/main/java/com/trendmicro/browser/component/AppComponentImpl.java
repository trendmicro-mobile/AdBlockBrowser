package com.trendmicro.browser.component;

import android.app.Application;

import com.trend.lazyinject.annotation.ComponentImpl;
import com.trendmicro.browser.BrowserApplication;

/**
 * Created by swift_gan on 2018/5/24.
 */
@ComponentImpl
public class AppComponentImpl implements AppComponent {
    @Override
    public Application globalContext() {
        return BrowserApplication.app;
    }
}
