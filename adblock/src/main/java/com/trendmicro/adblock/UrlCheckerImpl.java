package com.trendmicro.adblock;


import com.trend.lazyinject.annotation.ComponentImpl;

/**
 * Created by swift_gan on 2018/4/10.
 */
@ComponentImpl
public class UrlCheckerImpl implements UrlChecker {

    AdBlocker adBlocker = new AdBlockerImpl();
    PrivacyBlocker privacyBlocker = new PrivacyBlockerImpl();

    @Override
    public AdBlocker adBlocker() {
        return adBlocker;
    }

    @Override
    public PrivacyBlocker privacyBlocker() {
        return privacyBlocker;
    }
}
