package com.trendmicro.browser.pages;

import android.content.Context;
import android.util.AttributeSet;

import com.trendmicro.browser.R;
import com.trendmicro.browser.view.NinjaRelativeLayout;

/**
 * Created by swift_gan on 2018/4/12.
 */

public class TrendBrowserHomeTab extends NinjaRelativeLayout {

    InfoShowView infoShowView;

    public TrendBrowserHomeTab(Context context) {
        super(context);
        init();
    }

    public TrendBrowserHomeTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TrendBrowserHomeTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        infoShowView = findViewById(R.id.info_view);
    }

    @Override
    public void activate() {
        super.activate();
        init();
        if (infoShowView != null) {
            infoShowView.refresh();
        }
    }
}
