package com.example.latest_instagram_cloning_1;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("se7burTl2I77dFUUrYq4EZiXrgLoOt2bgTmAr2P8")
                // if defined
                .clientKey("3CnNR09VDcB4JR6wICoT40voIwOwC9dsbhmMp5pG")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
