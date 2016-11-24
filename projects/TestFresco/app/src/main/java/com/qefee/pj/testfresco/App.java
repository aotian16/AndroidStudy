package com.qefee.pj.testfresco;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * App.
 * <ul>
 * <li>date: 2016/11/24</li>
 * </ul>
 *
 * @author tongjin
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
