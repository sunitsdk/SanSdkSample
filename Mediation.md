# Mediation 

## AD NETWORKS
### AdColony

If you plan to use the San SDK to load and display ads from AdColony via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

AdColony currently supports the following ad formats.

| San Formats    | AdColony Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |

#### Step 1.Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:adcolony:4.1.0.8"// San mediation sdk
}
```

#### Step 2.Update Your Android Manifest
Add the admob key to meta-data in <application>
```
<application
        ...>

        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="your admob key" />

    </application>

```

### Admob

If you plan to use the San SDK to load and display ads from Admob via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

Admob currently supports the following ad formats.

| San Formats    | Admob Ad Formats |
| -------------- | ---------------- |
| Banner         | Banner           |
| Interstitial   | Interstitial     |
| Rewarded Video | Rewarded Video   |
| Native         | Native           |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:admob:19.5.0.8"// San mediation sdk
}
```


### AppLovin

If you plan to use the San SDK to load and display ads from AppLovin via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

AppLovin currently supports the following ad formats.

| San Formats    | AppLovin Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |

#### Step 1.Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:applovin:9.12.8.8"// San mediation sdk
}
```
#### Step 2.Update Your Android Manifest
Add the admob key to meta-data in <application>
```
<application
        ...>

        <meta-data android:name="applovin.sdk.key"
                    android:value="your applovin key"/>

    </application>

```


### FacebookAudienceNetwork

If you plan to use the San SDK to load and display ads from Facebook via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

Facebook currently supports the following ad formats.

| San Formats    | Facebook Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |
| Native         | Native              |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:facebook:6.2.0.8"// San mediation sdk
}
```


### Fyber

If you plan to use the San SDK to load and display ads from Fyber via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

Fyber currently supports the following ad formats.

| San Formats    | Fyber Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:fyber:7.7.4.8"// San mediation sdk
}
```


### IronSource

If you plan to use the San SDK to load and display ads from IronSource via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

IronSource currently supports the following ad formats.

| San Formats    | IronSource Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:ironsource:6.10.2.8"// San mediation sdk
}
```


### InMobi

If you plan to use the San SDK to load and display ads from InMobi via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

InMobi currently supports the following ad formats.

| San Formats    | InMobi Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |
| Native | Native |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:inmobi:9.0.9.8"// San mediation sdk
}
```


### Mopub

If you plan to use the San SDK to load and display ads from Mopub via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

Mopub currently supports the following ad formats.

| San Formats    | Mopub Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |
| Native | Native |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:mopub:5.14.0.8"// San mediation sdk
}
```


### Mintegral

If you plan to use the San SDK to load and display ads from Mintegral via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

Mintegral currently supports the following ad formats.

| San Formats    | Mintegral Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |
| Native | Native |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:mintegral:14.6.01.8"// San mediation sdk
}
```


### TopOn

If you plan to use the San SDK to load and display ads from TopOn via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

TopOn currently supports the following ad formats.

| San Formats    | TopOn Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:topon:5.7.1.8"// San mediation sdk
}
```

### UnityAds

If you plan to use the San SDK to load and display ads from UnityAds via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

UnityAds currently supports the following ad formats.

| San Formats    | UnityAds Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:unityads:3.4.2.8"// San mediation sdk
}
```


### Vungle

If you plan to use the San SDK to load and display ads from Vungle via mediation, use this article for integration instructions and information unique to the ad network. 

#### Supported Ad Formats

Vungle currently supports the following ad formats.

| San Formats    | Vungle Ad Formats |
| -------------- | ------------------- |
| Banner         | Banner              |
| Interstitial   | Interstitial        |
| Rewarded Video | Rewarded Video      |

#### Add the San Mediation SDK

open your project and update the app module’s `build.gradle` to have the following `repositories` and `dependencies`:

```
repositories {
    // ... other project repositories
    maven {url "https://dl.bintray.com/sunitsdk/mediation"}// San mediation sdk
}
//...

dependencies {
    // ... other project dependencies
    implementation "com.sunit.mediation:vungle:6.7.1.8"// San mediation sdk
}
```


## Native Ads Use the SanNativeAdRenderer

- Create an XML layout
The icon view and the media view must use `com.san.ads.MediaView`
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/native_outer_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@android:color/white">

    <com.san.ads.MediaView
        android:id="@+id/native_icon_image"
        android:layout_width="64dp"
        android:layout_height="64dp"
        android:layout_alignParentStart="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentTop="true"
        android:layout_marginStart="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="10dp"
        android:background="@null"
        android:contentDescription="@null" />

    <TextView
        android:id="@+id/native_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentTop="true"
        android:layout_marginStart="84dp"
        android:layout_marginLeft="84dp"
        android:layout_marginTop="32dp"
        android:textColor="@android:color/darker_gray"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/native_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/native_icon_image"
        android:layout_alignParentStart="true"
        android:layout_alignParentLeft="true"
        android:layout_marginStart="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="10dp"
        android:textColor="@android:color/darker_gray" />

    <com.san.ads.MediaView
        android:id="@+id/native_main_image"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_below="@+id/native_text"
        android:layout_alignParentStart="true"
        android:layout_alignParentLeft="true"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="10dp"
        android:layout_marginRight="10dp"
        android:background="@null"
        android:scaleType="centerCrop" />

    <Button
        android:id="@+id/native_button"
        android:layout_width="70dp"
        android:layout_height="wrap_content"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRight="true"
        android:layout_marginTop="10dp"
        android:layout_marginEnd="10dp"
        android:layout_marginRight="10dp"
        android:background="@drawable/ads_button_bg"
        android:clickable="true"
        android:focusable="true"
        android:textColor="@color/white"
        android:textStyle="bold" />

    <FrameLayout
        android:id="@+id/ad_choise"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"/>

</RelativeLayout>

```

- Use the SanNativeAdRenderer,It ADAPTS to the mediation adapter

```
  BaseNativeAd nativeAd;//The BaseNativeAd is obtained from onNativeLoaded()
  SanNativeAdRenderer adRenderer = new SanNativeAdRenderer(
          new SanNativeAdRenderer.SViewBinder.Builder(R.layout.ad_item_layout_mediation)
                  .iconImageId(R.id.native_icon_image)
                  .mainImageId(R.id.native_main_image)
                  .titleId(R.id.native_title)
                  .textId(R.id.native_text)
                  .callToActionId(R.id.native_button)
                  .build());
  if (getContext()==null)
      return;
  View adView = adRenderer.createAdView(getContext(), nativeAd, null);
  adRenderer.renderAdView(adView, nativeAd);
  mAdContainer.removeAllViews();
  mAdContainer.addView(adView);
```

