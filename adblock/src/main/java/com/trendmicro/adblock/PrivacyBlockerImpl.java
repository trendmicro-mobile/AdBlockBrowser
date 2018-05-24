package com.trendmicro.adblock;

import android.text.TextUtils;
/**
 * Created by swift_gan on 2018/4/10.
 */

public class PrivacyBlockerImpl implements UrlChecker.PrivacyBlocker {

    @Override
    public synchronized boolean init(String[] listPaths) {
        return AdBlockerUtils.initPrivacyBlocker(listPaths);
    }

    @Override
    public boolean isTracker(String requestUrl, String pageUrl, String accept) {
        if (TextUtils.isEmpty(requestUrl))
            return false;
        return AdBlockerUtils.isPrivacy(requestUrl, AdBlockerUtils.getDomain(pageUrl), AdBlockerUtils.getContentType(requestUrl, accept));
    }
}
