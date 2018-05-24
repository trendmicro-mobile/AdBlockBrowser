package com.trendmicro.adblock;

import com.trend.lazyinject.annotation.Component;
import com.trend.lazyinject.annotation.Provide;

/**
 * Created by swift_gan on 2018/4/10.
 */
@Component
public interface UrlChecker {

    @Provide
    AdBlocker adBlocker();
    @Provide
    PrivacyBlocker privacyBlocker();

    interface AdBlocker {
        boolean init(String[] listPaths);
        boolean isAd(String requestUrl, String pageUrl, String accept);
    }

    interface PrivacyBlocker {
        boolean init(String[] listPaths);
        boolean isTracker(String requestUrl, String pageUrl, String accept);
    }

}
