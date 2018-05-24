package com.trendmicro.browser.component;

import android.app.Application;

import com.trend.lazyinject.annotation.Component;
import com.trend.lazyinject.annotation.Provide;

/**
 * Created by swift_gan on 2018/5/24.
 */
@Component
public interface AppComponent {
    @Provide
    Application globalContext();
}
