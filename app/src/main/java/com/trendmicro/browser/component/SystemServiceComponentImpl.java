package com.trendmicro.browser.component;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.content.Context;
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

import com.trend.lazyinject.annotation.ComponentImpl;
import com.trend.lazyinject.annotation.Inject;

/**
 * Created by swift_gan on 2018/1/26.
 */
@ComponentImpl
public class SystemServiceComponentImpl implements SystemService {

    @Inject(component = AppComponent.class)
    Context context;

    WindowManager windowManager;
    ActivityManager activityManager;
    PackageManager packageManager;
    UserManager userManager;
    UsageStatsManager usageStatsManager;
    TelephonyManager telephonyManager;
    UsbManager usbManager;
    WifiManager wifiManager;
    PowerManager powerManager;
    SensorManager sensorManager;
    NotificationManager notificationManager;
    DisplayManager displayManager;
    AppOpsManager opsManager;
    AudioManager audioManager;
    AlarmManager alarmManager;
    BatteryManager batteryManager;
    AppWidgetManager widgetManager;
    AccessibilityManager accessibilityManager;
    InputMethodManager inputMethodManager;

    @Override
    public WindowManager windowManager() {
        if (windowManager == null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    @Override
    public ActivityManager activityManager() {
        if (activityManager == null) {
            activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }
        return activityManager;
    }

    @Override
    public PackageManager packageManager() {
        if (packageManager == null) {
            packageManager = context.getPackageManager();
        }
        return packageManager;
    }

    @Override
    public UserManager userManager() {
        if (userManager == null) {
            userManager = (UserManager) context.getSystemService(Context.USER_SERVICE);
        }
        return userManager;
    }

    @Override
    public UsageStatsManager usageManager() {
        if (usageStatsManager == null) {
            usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        }
        return usageStatsManager;
    }

    @Override
    public TelephonyManager telephoneManager() {
        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        }
        return telephonyManager;
    }

    @Override
    public UsbManager usbManager() {
        if (usbManager == null) {
            usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        }
        return usbManager;
    }

    @Override
    public WifiManager wifiManager() {
        if (wifiManager == null) {
            wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        }
        return wifiManager;
    }

    @Override
    public PowerManager powerManager() {
        if (powerManager == null) {
            powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        }
        return powerManager;
    }

    @Override
    public SensorManager sensorManager() {
        if (sensorManager == null) {
            sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        }
        return sensorManager;
    }

    @Override
    public NotificationManager notificationManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    @Override
    public DisplayManager displayManager() {
        if (displayManager == null) {
            displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        }
        return displayManager;
    }

    @Override
    public AppOpsManager opsManager() {
        if (opsManager == null) {
            opsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        }
        return opsManager;
    }

    @Override
    public AudioManager audioManager() {
        if (audioManager == null) {
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        return audioManager;
    }

    @Override
    public AlarmManager alarmManager() {
        if (alarmManager == null) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        return alarmManager;
    }

    @Override
    public BatteryManager batteryManager() {
        if (batteryManager == null) {
            batteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        }
        return batteryManager;
    }

    @Override
    public AppWidgetManager widgetManager() {
        if (widgetManager == null) {
            widgetManager = (AppWidgetManager) context.getSystemService(Context.APPWIDGET_SERVICE);
        }
        return widgetManager;
    }

    @Override
    public AccessibilityManager accessibilityManager() {
        if (accessibilityManager == null) {
            accessibilityManager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        }
        return accessibilityManager;
    }

    @Override
    public InputMethodManager inputMethodManager() {
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        return inputMethodManager;
    }
}
