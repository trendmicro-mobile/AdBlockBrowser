package com.trendmicro.browser.component;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.hardware.display.DisplayManager;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;

import com.trend.lazyinject.annotation.Component;
import com.trend.lazyinject.annotation.Provide;

/**
 * Created by swift_gan on 2018/1/26.
 */
@Component
public interface SystemService {
    @Provide
    WindowManager windowManager();
    @Provide
    ActivityManager activityManager();
    @Provide
    PackageManager packageManager();
    @Provide
    UserManager userManager();
    @Provide
    UsageStatsManager usageManager();
    @Provide
    TelephonyManager telephoneManager();
    @Provide
    UsbManager usbManager();
    @Provide
    WifiManager wifiManager();
    @Provide
    PowerManager powerManager();
    @Provide
    SensorManager sensorManager();
    @Provide
    NotificationManager notificationManager();
    @Provide
    DisplayManager displayManager();
    @Provide
    AppOpsManager opsManager();
    @Provide
    AudioManager audioManager();
    @Provide
    AlarmManager alarmManager();
    @Provide
    BatteryManager batteryManager();
    @Provide
    AppWidgetManager widgetManager();
    @Provide
    AccessibilityManager accessibilityManager();
    @Provide
    InputMethodManager inputMethodManager();
}
