package com.trendmicro.browser.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.NonNull;


/**
 * Created by swift_gan on 2018/4/11.
 */

public class DrawableUtils {

    @NonNull
    public static Bitmap getRoundedNumberImage(Context context, int number, int width, int height, int color, int thickness) {
        final String text;

        if (number > 99) {
            text = "\u221E";
        } else {
            text = String.valueOf(number);
        }

        final Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(image);
        final Paint paint = new Paint();
        paint.setColor(color);
        final Typeface boldText = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        paint.setTypeface(boldText);
        paint.setTextSize(ViewUtils.dp2px(context, 14));
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        int radius = ViewUtils.dp2px(context, 2);

        final RectF outer = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRoundRect(outer, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        radius--;
        final RectF inner = new RectF(thickness, thickness, canvas.getWidth() - thickness, canvas.getHeight() - thickness);
        canvas.drawRoundRect(inner, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        final int xPos = (canvas.getWidth() / 2);
        final int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));

        canvas.drawText(String.valueOf(text), xPos, yPos, paint);

        return image;
    }
}
