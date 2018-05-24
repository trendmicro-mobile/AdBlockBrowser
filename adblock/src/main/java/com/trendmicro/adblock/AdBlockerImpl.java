package com.trendmicro.adblock;

import android.text.TextUtils;

/**
 * Created by swift_gan on 2018/4/10.
 */

public class AdBlockerImpl implements UrlChecker.AdBlocker {

    @Override
    public synchronized boolean init(String[] listPaths) {
        return AdBlockerUtils.initAdBlocker(listPaths);
    }


    @Override
    public boolean isAd(String requestUrl, String pageUrl, String accept) {
        if (TextUtils.isEmpty(requestUrl))
            return false;
        return AdBlockerUtils.isAd(requestUrl, AdBlockerUtils.getDomain(pageUrl), AdBlockerUtils.getContentType(requestUrl, accept));
    }
}
