package com.hasbrain.howfastareyou;

import android.app.Application;
import android.content.Context;

/**
 * Created by levinh on 01/08/2016.
 */
public class MainApplication extends Application {

    public static Context mSharedContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedContext = this;
    }
}
