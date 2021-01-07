package com.ushareit.open;

import com.ushareit.ads.openapi.ShareItAd;

import androidx.multidex.MultiDexApplication;

public class ShareItApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareItAd.init(ShareItApplication.this);
    }
}
