# SHAREit Open SDK document

## Get Started with SHAREit Open SDK

Welcome to SHAREit Ad! This navigation will provide the comprehensive guidelines including the SDK integration document, Midas platform's configuration guides, advanced features and classic monetization cases, which will navigate you to use SHAREit Open Mediation quickly and maximize your monetization.

Aggregate twelve mainstream online advertising platforms including AdColony, Admob, AppLovin, Facebook, Fyber, InMobi, Ironsource, Mintegral, Mopub, TopOn, UnityAds, Vungle with native, banner , interstitial and rewarded video Ad available.

## Integrate the SHAREit Open SDK for Android

### Step 1. Download the SHAREit Ad Android SDK

The Shareit Open SDK is available as an AAR via Bintray.To add the shareit-ad-open sdk dependency, open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
	// ... other project repositories
	maven {url "https://dl.bintray.com/sunitsdk/SUnit"}// shareit open sdk
}
//...

dependencies {
    // ... other project dependencies
		api "com.sunit:shareit-ad-open:3.0.0.0"// shareit open sdk
}
```

To support Java 8, add the language feature support:

```
android {
	compileOptions {
		sourceCompatibility JavaVersion.VERSION_1_8
		targetCompatibility JavaVersion.VERSION_1_8
	}
}
```

### Step 2.Update Your Android Manifest

Upate your AndroidManifest.xml in order to complete the SDK integration. Add the following permissions and activity declarations according to the bundle you are integrating.

1. Declare the following permissions:

```
<!-- Required permissions -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- Optional permissions. Will pass Lat/Lon values when available. Choose either Coarse or Fine -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```

### Step 3：Add network_security_config.xml

Oct 01, 2020 - Android 9.0 (API 28) blocks cleartext (non-HTTPS) traffic by default, which can prevent ads from serving correctly. To mitigate that, publishers whose apps run on Android 9.0 or above should ensure to add a network security config file. Doing so allowlists cleartext traffic and allows non-HTTPS ads to serve.

1. In your AndroidManifest.xml file, add the following:

   ```
    <manifest>
        <application
            ...
            android:networkSecurityConfig="@xml/network_security_config"
            ...>
        </application>
    </manifest>
   ```

2. In your `network_security_config.xml` file, add a `base-config` that sets `cleartextTrafficPermitted` to `true`:

   ```
    <?xml version="1.0" encoding="utf-8"?>
     <network-security-config>
        ...
        <base-config cleartextTrafficPermitted="true">
            <trust-anchors>
                <certificates src="system"/>
            </trust-anchors>
        </base-config>
        ...
    </network-security-config>
   ```

### Step 4. Configure Ad Units in Your App

Once you’ve completed the above steps, you can start displaying ads in your application by configuring the ad units as shown in the link below for your ad format:

- [Banner](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#banner-ads)
- [Native](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#native-ads)
- [Interstitial](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#interstitial-ads)
- [Rewarded Video](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#rewarded-video-ads)


## Initialize

After you have integrated the Shareit Ad open SDK and created an ad unit, you must call `ShareItAd.init()` **before you send any ad requests**. Initialization is **required** for a number of new functionalities:

It is recommended to initialize in `Application onCreate()`

```
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareItAd.init(this);
    }
}
```



## Changelog

### 3.0.0.0 (January 8,2021)

- OpenSDK initial release



## Banner Ads

Banner ads usually appear at the top or bottom of your app’s screen. Adding one to your app takes just a few lines of code.

###  Prerequisites

Before integrating banner ads in your app:

1. create an account, create an **App**, and create an **Ad unit** using the format ‘Banner’.
2. Follow our steps to [integrate the SHAREit Open SDK](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#integrate-the-shareit-open-sdk-for-android) into your project.
3. Integrated the Shareit Ad open SDK

### Loading Banner Ads in Your App

#### Step 1.Define a Slot for Your Banner Ad in Your XML Layout

Start by including this XML block to your `Activity`’s or `Fragment`’s layout. We will fill in the details later:

```
<com.ushareit.ads.ShareItBannerView
    android:layout_width=""
    android:layout_height=""/>
```

#### Step 2. Load an Ad Into the Banner Slot

Next, in your `Activity` or `Fragment` code, declare an instance variable for your `ShareItBannerView`：

```
private ShareItBannerView banner;
```

You should already have created an ad unit on Midas’s site and received an Ad Unit ID. You’ll use it now to identify that ad unit in your app and request ads from Midas that are relevant for your users.

In your Activity’s `onCreate()` or your Fragment’s `onCreateView()` method, set your `ShareitBannerView` Ad Unit ID, then simply call `loadAd()` to fetch and display the ad:

```
banner = (ShareItBannerView)findViewById(R.id.adview);
banner.setAdUnitId(adUnitId);
banner.setAdSize(adSize);
banner.loadAd();
```

When the hosting `Activity` or `Fragment` is destroyed, be sure to also destroy the `MoPubView` by calling:

```
banner.destory();
```

###  Using the Delegate

```
banner.setBannerAdListener(new ShareItBannerView.BannerAdListener() {
    @Override
    public void onBannerLoaded(@NonNull ShareItBannerView shareItBannerView) {
    		//the banner has successfully retrieved an ad.
        Log.d(TAG,"onBannerLoaded");
    }

    @Override
    public void onBannerFailed(ShareItBannerView shareItBannerView, AdException e) {
    		//the banner has failed to retrieve an ad.
        Log.d(TAG,"onBannerFailed exception = " + e.getMessage());
    }

    @Override
    public void onBannerClicked(ShareItBannerView shareItBannerView) {
    		//the user has tapped on the banner.
        Log.d(TAG,"onBannerClicked");
    }

    @Override
    public void onBannerImpression(ShareItBannerView shareItBannerView) {
    		//the banner has showed
        Log.d(TAG,"onBannerImpression");
    }
});
```

### Ad Preload

Using the `preload()` to preload in advance reduces the load time at presentation time

```
banner.preload();
```


## Native Ads

Native ads let you monetize your app in a way that’s consistent with its existing design.  You can design the ad layout to be consistent with the look and feel of your app. The SDK automatically handles image caching and metrics tracking so you can focus on how, when, and where to display ads.

### Prerequisites

Before integrating native ads into your app:

1. create an account, create an **App**, and create an **Ad unit** using the format ‘Native’.
2. Follow our steps to [integrate the SHAREit Open SDK](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#integrate-the-shareit-open-sdk-for-android) into your project.
3. Integrated the Shareit Ad open SDK

####  Step 1. Request the Native Ad

```
ShareItNative shareItNative = new ShareItNative(getContext(), adUnitId, new ShareItNative.NativeNetworkListener() {
    @Override
    public void onNativeLoaded(BaseNativeAd nativeAd) {
        // Called when the ad for the given adUnitId has loaded.
        Log.d(TAG, "onNativeLoaded");
    }

    @Override
    public void onNativeFailed(AdException e) {
        // Called when a ad fails to load for the given adUnitId. 
        Log.d(TAG, "onNativeFailed e = " + e.getMessage() + " code = " + e.getCode());
    }

    @Override
    public void onImpression(BaseNativeAd baseNativeAd) {
        // Called when a ad shown
        Log.d(TAG, "onImpression");
    }

    @Override
    public void onClick(BaseNativeAd baseNativeAd) {
     		// Called when a ad is clicked
        Log.d(TAG, "onClick");
    }
});
shareItNative.loadAd();//Request ad
```

#### Step 2：Create an XML layout

The Sample:

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/native_outer_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@android:color/white">

    <com.ushareit.ads.MediaView
        android:id="@+id/native_icon_image"
        android:layout_width="64dp"
        android:layout_height="64dp"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_marginLeft="10dp"
        android:layout_marginStart="10dp"
        android:layout_marginTop="10dp"
        android:background="@null"
        android:contentDescription="@null" />

    <TextView
        android:id="@+id/native_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_marginLeft="84dp"
        android:layout_marginStart="84dp"
        android:layout_marginTop="32dp"
        android:textColor="@android:color/darker_gray"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/native_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_below="@+id/native_icon_image"
        android:layout_marginLeft="10dp"
        android:layout_marginStart="10dp"
        android:layout_marginTop="10dp"
        android:textColor="@android:color/darker_gray" />

    <com.ushareit.ads.MediaView
        android:id="@+id/native_main_image"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_below="@+id/native_text"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:layout_marginTop="10dp"
        android:background="@null"
        android:scaleType="centerCrop" />

    <Button
        android:id="@+id/native_cta"
        android:layout_width="70dp"
        android:layout_height="wrap_content"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRight="true"
        android:layout_marginEnd="10dp"
        android:layout_marginRight="10dp"
        android:layout_marginTop="10dp"
        android:background="@drawable/ads_button_bg"
        android:textColor="@color/white"
        android:clickable="true"
        android:focusable="true"
        android:textStyle="bold" />

    <FrameLayout
        android:id="@+id/ad_choise"
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"/>

</RelativeLayout>
```

![NateAdSmple](https://tva1.sinaimg.cn/large/008eGmZEgy1gmgczlwc9nj30ex0i975z.jpg)



#### Step 3：Show Native Ads

- Method 1: Get ShareItNativeAd to show

```
  BaseNativeAd nativeAd;//The BaseNativeAd is obtained from onNativeLoaded（）
  ShareItNativeAd midasAd = (ShareItNativeAd) nativeAd;
  com.ushareit.ads.MediaView mediaView = findViewById(R.id.native_main_image);
  TextView title = findViewById(R.id.native_title);
  TextView text = findViewById(R.id.native_text);
  com.ushareit.ads.MediaView iconView = findViewById(R.id.native_icon_image);
  TextView button = findViewById(R.id.native_cta);
  
  title.setText(nativeAd.getTitle());
  text.setText(nativeAd.getText());
  button.setText(nativeAd.getCallToAction());
  AdViewRenderHelper.loadImage(getContext(), nativeAd.getIconUrl(), iconView);
  List<View> clickViews = new ArrayList<>();
  clickViews.add(mediaView);
  clickViews.add(iconView);
  clickViews.add(button);
  midasAd.registerViewForInteraction(adView, mediaView, clickViews);
```

- Method 2: Use the Renderer

```
  BaseNativeAd nativeAd;//The BaseNativeAd is obtained from onNativeLoaded（）
  ShareItNativeAd midasAd = (ShareItNativeAd) nativeAd;
  SUnitNativeAdRenderer midasAdRenderer = new SUnitNativeAdRenderer(
          new SUnitNativeAdRenderer.SUnitViewBinder.Builder(R.layout.ad_item_layout)
                  .iconImageId(R.id.native_icon_image)
                  .mainImageId(R.id.native_main_image)
                  .titleId(R.id.native_title)
                  .textId(R.id.native_text)
                  .callToActionId(R.id.native_cta)
                  .build());
  if (getContext() == null)
      return;
  View adView = midasAdRenderer.createAdView(getContext(), null);
  midasAdRenderer.renderAdView(adView, midasAd);
  mAdContainer.removeAllViews();
  mAdContainer.addView(adView);
```

### Ad Preload

Using the `preload()` to preload in advance reduces the load time at presentation time

```
shareItNative.preload();
```



## Interstitial Ads

Interstitial ads provide full-screen experiences, commonly incorporating rich media to offer a higher level of interactivity compared to banner ads. Interstitials are typically shown during natural transitions in your app; for example, after completing a game level, or while waiting for a new view to load. Use the `ShareItInterstitial` object and its associated listeners to fetch and display interstitial ads in your app.

###  Prerequisites

1. create an account, create an **App**, and create an **Ad unit** using the format ‘Interstitial’.
2. Follow our steps to [integrate the SHAREit Open SDK](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#integrate-the-shareit-open-sdk-for-android) into your project.
3. Integrated the Shareit Ad open SDK

### Load Interstitial Ads in Your App

#### Step 1. Create an Interstitial Ad

```
ShareItInterstitial interstitial = new ShareItInterstitial(getContext(), INTERSTITIAL_UNIT_ID);
interstitial.setInterstitialAdListener(new ShareItInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(final ShareItInterstitial shareItInterstitial) {
            		// The interstitial has been cached and is ready to be shown.
                Log.d(TAG, "onInterstitialLoaded");
            }

            @Override
            public void onInterstitialFailed(ShareItInterstitial shareItInterstitial, AdException e) {
            		// The interstitial has failed to load.
                Log.d(TAG,"onInterstitialFailed e = " + e.getMessage());
            }

            @Override
            public void onInterstitialShown(ShareItInterstitial shareItInterstitial) {
            		// The interstitial has been shown. 
                Log.d(TAG,"onInterstitialShown");
            }

            @Override
            public void onInterstitialClicked(ShareItInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialClicked");
            }

            @Override
            public void onInterstitialDismissed(ShareItInterstitial shareItInterstitial) {
                // The interstitial has being dismissed.
                Log.d(TAG,"onInterstitialDismissed");
            }
        });
interstitial.load();//发起请求
```

#### Step 2. Display an Interstitial Ad

If `isReady()` returns true, display the interstitial by calling the `show()` method

```
if(shareItInterstitial.isReady()){
    shareItInterstitial.show();
}
```

#### Step 3：Destory

When the interstitial Ad dismissed use the `destroy()`

```
shareItInterstitial.destory();
```

### Ad Preload

Using the `preload()` to preload in advance reduces the load time at presentation time

```
interstitial.preload();
```



## Rewarded Video Ads

Rewarded video ads are a great way to keep users engaged in your app while earning ad revenue. The reward generally comes in the form of in-game currency (gold, coins, power-ups, etc.) and is distributed to the user after a successful video completion.

###  Prerequisites

1. create an account, create an **App**, and create an **Ad unit** using the format ‘Rewarded Video’.
2. Follow our steps to [integrate the SHAREit Open SDK](https://github.com/sunitsdk/ShareitOpenSDKDemo/blob/master/README.md#integrate-the-shareit-open-sdk-for-android) into your project.
3. Integrated the Shareit Ad open SDK

### Basic Integration

####  Step 1： Request and Cache the Rewarded Video

```
ShareItRewardedAd rewardedAd = new ShareItRewardedAd(getContext(), REWARDED_UNIT_ID);
rewardedAd.setRewardedAdListener(new ShareItRewardedAd.RewardedVideoAdListener() {
    @Override
    public void onRewardedAdLoaded(final ShareItRewardedAd shareItRewardedAd) {
    	// Called when the video for the given adUnitId has loaded.
        Log.d(TAG, "onRewardedAdLoaded");
    }

    @Override
    public void onRewardedAdFailed(ShareItRewardedAd shareItRewardedAd, AdException e) {
    	// Called when a video fails to load for the given adUnitId. 
        Log.d(TAG, "onRewardedAdFailed e = " + e.getMessage());
    }

    @Override
    public void onRewardedAdShown(ShareItRewardedAd shareItRewardedAd) {
    	// Called when a rewarded video starts playing.
        Log.d(TAG, "onRewardedAdShown");
    }

    @Override
    public void onRewardedAdClicked(ShareItRewardedAd shareItRewardedAd) {
    	//  Called when a rewarded video is clicked.
        Log.d(TAG, "onRewardedAdClicked");
    }

    @Override
    public void onRewardedVideoClosed(ShareItRewardedAd shareItRewardedAd) {
   	// Called when a rewarded video is closed.
        Log.d(TAG, "onRewardedVideoClosed");
    }

    @Override
    public void onRewardedVideoCompleted(ShareItRewardedAd shareItRewardedAd) {
    	// Called when a rewarded video is completed and the user should be rewarded.
        Log.d(TAG, "onRewardedVideoCompleted");
    }
});
rewardedAd.load();//Request ad
```

#### Step 2：Display an Rewarded Ad

If `isReady()` returns true, display the interstitial by calling the `show()` method

```
if (shareItRewardedAd.isReady()) {
    shareItRewardedAd.show();
}
```

#### Step 3：Destory

When the Rewarded Ad dismissed use the `destroy()`

```
shareItRewardedAd.destory();
```

### Ad Preload

Using the `preload()` to preload in advance reduces the load time at presentation time

```
shareItRewardedAd.preload();
```
