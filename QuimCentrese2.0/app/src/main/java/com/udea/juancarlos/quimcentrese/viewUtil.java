package com.udea.juancarlos.quimcentrese;

import android.os.Build;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bomberoskrg on 2016/09/21.
 */

public class viewUtil {
    private static final AtomicInteger viewIdGenerator = new AtomicInteger(15000000);

    int generateViewId() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return generateUniqueViewId();
        } else {
            return View.generateViewId();
        }
    }

    private  int generateUniqueViewId() {
        while (true) {
            final int result = viewIdGenerator.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (viewIdGenerator.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
