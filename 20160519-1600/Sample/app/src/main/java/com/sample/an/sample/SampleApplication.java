package com.sample.an.sample;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by cindy on 5/16/16.
 */
public class SampleApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        //Notice this initialization code here
        ActiveAndroid.initialize(this);
    }
}
