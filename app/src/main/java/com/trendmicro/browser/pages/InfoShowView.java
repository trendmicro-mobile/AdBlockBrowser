package com.trendmicro.browser.pages;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trendmicro.browser.R;
import com.trendmicro.browser.urlchecker.CheckerHistory;

/**
 * Created by swift_gan on 2018/4/12.
 */

public class InfoShowView extends RelativeLayout {

    TextView adBlocked, privacyBlocked;


    public InfoShowView(Context context) {
        super(context);
        init(context);
    }

    public InfoShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InfoShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_info_show, this, true);
        adBlocked = findViewById(R.id.info_ad_blocked);
        privacyBlocked = findViewById(R.id.info_privacy_blocked);
    }

    public void refresh() {
        adBlocked.setText(CheckerHistory.getInstance().adChecked() + "");
        privacyBlocked.setText(CheckerHistory.getInstance().privacyChecked() + "");
    }

}
