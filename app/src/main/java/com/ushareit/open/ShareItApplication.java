package com.ushareit.open;

import com.ushareit.ads.openapi.SanAd;

import androidx.multidex.MultiDexApplication;

public class ShareItApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        SanAd.init(ShareItApplication.this);
    }
}
