package com.trendmicro.browser.browser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;

import com.trendmicro.browser.R;
import com.trendmicro.browser.unit.BrowserUnit;
import com.trendmicro.browser.unit.IntentUnit;

public class NinjaDownloadListener implements DownloadListener {
    private Context context;

    public NinjaDownloadListener(Context context) {
        super();
        this.context = context;
    }


    @Override
    public void onDownloadStart(final String url, String userAgent, final String contentDisposition, final String mimeType, long contentLength) {
        final Context holder = IntentUnit.getContext();
        if (holder == null || !(holder instanceof Activity)) {
            BrowserUnit.download(context, url, contentDisposition, mimeType);
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(holder);
        builder.setCancelable(false);

        builder.setTitle(R.string.dialog_title_download);
        builder.setMessage(URLUtil.guessFileName(url, contentDisposition, mimeType));

        builder.setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BrowserUnit.download(holder, url, contentDisposition, mimeType);
            }
        });

        builder.setNegativeButton(R.string.dialog_button_negative, null);
        builder.create().show();
    }
}
