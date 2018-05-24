package com.trendmicro.browser.browser;


import android.support.annotation.UiThread;

import com.trendmicro.browser.view.NinjaWebView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BrowserContainer {

    private static List<AlbumController> list = new LinkedList<>();

    public static AlbumController get(int index) {
        return list.get(index);
    }

    public static List<PageChangeListener> listeners = new CopyOnWriteArrayList<>();

    public synchronized static void set(AlbumController controller, int index) {
        if (list.get(index) instanceof NinjaWebView) {
            ((NinjaWebView) list.get(index)).destroy();
        }
        list.set(index, controller);
        notifyPageChange();
    }

    public synchronized static void add(AlbumController controller) {
        list.add(controller);
        notifyPageChange();
    }

    public synchronized static void add(AlbumController controller, int index) {
        list.add(index, controller);
        notifyPageChange();
    }

    public synchronized static void remove(int index) {
        if (list.get(index) instanceof NinjaWebView) {
            ((NinjaWebView) list.get(index)).destroy();
        }
        list.remove(index);
        notifyPageChange();
    }

    public synchronized static void remove(AlbumController controller) {
        if (controller instanceof NinjaWebView) {
            ((NinjaWebView) controller).destroy();
        }
        list.remove(controller);
        notifyPageChange();
    }

    public static int indexOf(AlbumController controller) {
        return list.indexOf(controller);
    }

    public static List<AlbumController> list() {
        return list;
    }

    public static int size() {
        return list.size();
    }

    public synchronized static void clear() {
        for (AlbumController albumController : list) {
            if (albumController instanceof NinjaWebView) {
                ((NinjaWebView) albumController).destroy();
            }
        }
        list.clear();
        notifyPageChange();
    }

    public static void addPageChangeListener(PageChangeListener listener) {
        listeners.add(listener);
    }

    public static void removePageChangeListener(PageChangeListener listener) {
        listeners.remove(listener);
    }

    @UiThread
    private static void notifyPageChange() {
        for (PageChangeListener listener:listeners) {
            listener.onPageChanged(list);
        }
    }

    @FunctionalInterface
    public interface PageChangeListener {
        void onPageChanged(List<AlbumController> pages);
    }

}
