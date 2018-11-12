package com.rocket.bottle.testapp;

import android.app.Application;
import com.rocket.bottle.testapp.database.room.AppDatabase;

public class BottleRocket extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeUi();
    }

    private void initializeUi() {
        appDatabase = AppDatabase.Companion.getInstance(getApplicationContext());
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
