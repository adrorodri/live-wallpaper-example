package com.adrorodri.livewallpapertest.utils;

import android.content.Context;
import android.content.res.Resources;

public class DisplayUtils {

    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels + getNavigationBarHeight(context);
    }

    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    private static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
