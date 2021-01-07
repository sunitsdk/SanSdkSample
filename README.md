# SHAREit-OpenSDK接入文档

## 集成

### 步骤 1.引入sdk

在相应buidl.gradle中引用SHAREit-OpenSDK 库

```
api "com.sunit:shareit-ad-open:3.0.0.0"
```

在工程根目录下的build.gradle中增加远程库地址

```
allprojects {
    repositories {
        // shareit open sdk
        maven {
            url "https://dl.bintray.com/sunitsdk/SUnit"
        }
    }
}
```

### 步骤 2.增加相关权限

声明以下权限:

```
<!-- Required permissions -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- Optional permissions. Will pass Lat/Lon values when available. Choose either Coarse or Fine -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```

### 步骤 3：添加网络安全配置文件

Android 9.0 (API 28)默认会阻塞明文(非https)流量，这可能会阻止广告正确服务。为了缓解这一问题，在Android 9.0或以上版本上运行应用的发行商应该确保添加一个网络安全配置文件。这样做允许列表清除文本流量，并允许非https广告服务。

1. 在您App的 AndroidManifest.xml 中增加如下:

   ```
    <manifest>
        <application
            ...
            android:networkSecurityConfig="@xml/network_security_config"
            ...>
        </application>
    </manifest>
   ```

2. 在 `network_security_config.xml` 文件中, 增加 `base-config` 并设置 `cleartextTrafficPermitted=true`:

   ```
    <?xml version="1.0" encoding="utf-8"?>
     <network-security-config>
        ...
        <base-config cleartextTrafficPermitted="true">
            <trust-anchors>
                <certificates src="system"/>
            </trust-anchors>
        </base-config>
        <domain-config cleartextTrafficPermitted="false">
            <domain includeSubdomains="true">example.com</domain>
            <domain includeSubdomains="true">cdn.example2.com</domain>
        </domain-config>
        ...
    </network-security-config>
   ```

**注意:**当' base-config '允许HTTP通信时，为了保护某些域，添加' domain-config '确保某些域将始终使用HTTPS。

### 步骤 4. 在你的应用中配置广告单元

在后台申请相关AppKey及广告单元ID。



一旦你完成了上述步骤，你就可以开始在你的应用程序中显示广告，配置广告单元，如下面的链接所示，为你的广告格式:

- [Banner](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E6%A8%AA%E5%B9%85%E5%B9%BF%E5%91%8A-banner)
- [Native](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E5%8E%9F%E7%94%9F%E5%B9%BF%E5%91%8A-native)
- [Interstitial](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E6%8F%92%E5%B1%8F%E5%B9%BF%E5%91%8A-interstitial)
- [Rewarded Video](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E6%BF%80%E5%8A%B1%E8%A7%86%E9%A2%91%E5%B9%BF%E5%91%8A-rewardedvideo)



## 初始化

在集成了SHAREit OPEN SDK并创建了广告单元之后，必须在发送任何广告请求之前调用ShareItAd.init();进行初始化

推荐在Application中进行初始化，如下

```
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareItAd.init(this);
    }
}
```



## 版本改动

### 3.0.0.0 

OpenSDK 初始版本



## 横幅广告 Banner

横幅广告通常显示在应用程序屏幕的顶部或底部。将其添加到您的应用只需几行代码。

###  先决条件

在将横幅广告集成到您的应用之前：

1. 申请对应广告位，并将[SDK集成](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E9%9B%86%E6%88%90)到您的项目中。
2. 初始化

### 在您的应用中加载横幅广告

#### 步骤1.在XML布局中为横幅广告定义一个广告位

首先将此XML块包含到您`Activity`的或`Fragment`布局中。我们稍后将填写详细信息：

```
<com.ushareit.ads.ShareItBannerView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```

#### 步骤2.将广告加载到横幅广告位

接下来，在您的`Activity`或`Fragment`代码中，声明一个实例变量`ShareItBannerView`：

```
private ShareItBannerView banner;
```

您应该已经在SHAREit Ad的网站上创建了一个广告单元，并收到了一个广告单元ID。现在，您将使用它来识别应用中的广告单元，并从SHAREit AD请求与您的用户相关的广告。

在“Activity”`onCreate()`或“Fragment”的`onCreateView()`方法中，设置您`ShareitBannerView`的广告单元ID，然后只需调用`loadAd()`即可获取并显示广告：

```
banner = (ShareItBannerView)findViewById(R.id.adview);
banner.setAdUnitId(adUnitId);
banner.setAdSize(adSize);
banner.loadAd();
```

#### 监听器

```
banner.setBannerAdListener(new ShareItBannerView.BannerAdListener() {
    @Override
    public void onBannerLoaded(@NonNull ShareItBannerView shareItBannerView) {
        Log.d(TAG,"onBannerLoaded");//加载成功
    }

    @Override
    public void onBannerFailed(ShareItBannerView shareItBannerView, AdException e) {
        Log.d(TAG,"onBannerFailed exception = " + e.getMessage());//加载失败
    }

    @Override
    public void onBannerClicked(ShareItBannerView shareItBannerView) {
        Log.d(TAG,"onBannerClicked");//广告点击
    }

    @Override
    public void onBannerImpression(ShareItBannerView shareItBannerView) {
        Log.d(TAG,"onBannerImpression");//广告展示
    }
});
```

#### 销毁

```
banner.destory();
```

#### 预加载

使用preload方法进行提前预加载，可以减少在展示时机时load的时长

```
banner.preload();
```


## 原生广告 Native

原生广告让你能够以与现有设计保持一致的方式盈利。这样你就可以设计与应用外观和感觉一致的广告布局。SDK自动处理图像缓存和参数跟踪，这样你就可以专注于如何、何时和何处显示广告。

### 先决条件

在将横幅广告集成到您的应用之前：

1. 申请对应广告位，并将[SDK集成](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E9%9B%86%E6%88%90)到您的项目中。
2. 初始化

### 在您的应用中加载原生广告

#### 步骤1：请求原生广告

```
ShareItNative shareItNative = new ShareItNative(getContext(), adUnitId, new ShareItNative.NativeNetworkListener() {
    @Override
    public void onNativeLoaded(BaseNativeAd nativeAd) {
        Log.d(TAG, "onNativeLoaded");//加载成功
    }

    @Override
    public void onNativeFailed(AdException e) {//加载失败
        Log.d(TAG, "onNativeFailed e = " + e.getMessage() + " code = " + e.getCode());
    }

    @Override
    public void onImpression(BaseNativeAd baseNativeAd) {
        Log.d(TAG, "onImpression");//广告被展示
    }

    @Override
    public void onClick(BaseNativeAd baseNativeAd) {
        Log.d(TAG, "onClick");//广告被点击
    }
});
shareItNative.loadAd();//发起广告请求
```

#### 步骤2：创建原生广告布局

布局文件示例：

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

![1111](https://tva1.sinaimg.cn/large/0081Kckwly1gkqw1r0346j30u50qcteb.jpg)

#### 步骤3：展示原生广告

- 解析实体展示方式

  ```
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

- 使用Renderer方式

```
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

#### 预加载

使用preload方法进行提前预加载，可以减少在展示时机时load的时长

```
shareItNative.preload();
```



## 插屏广告 Interstitial

插页广告提供全屏体验，与条幅广告相比，插页广告通常会结合富媒体提供更高层次的交互性。例如，在完成游戏关卡后，或等待加载新视图时。使用ShareItInterstitial对象及其相关的监听器来获取和显示应用程序中的插播广告。

###  先决条件

在将插屏广告集成到您的应用之前：

1. 申请对应广告位，并将[SDK集成](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E9%9B%86%E6%88%90)到您的项目中。
2. 初始化

### 在您的应用中加载插屏广告

#### 步骤1：请求

```
ShareItInterstitial interstitial = new ShareItInterstitial(getContext(), INTERSTITIAL_UNIT_ID);
interstitial.setInterstitialAdListener(new ShareItInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(final ShareItInterstitial shareItInterstitial) {
                Log.d(TAG, "onInterstitialLoaded");//加载成功
            }

            @Override
            public void onInterstitialFailed(ShareItInterstitial shareItInterstitial, AdException e) {
                Log.d(TAG,"onInterstitialFailed e = " + e.getMessage());//加载失败
            }

            @Override
            public void onInterstitialShown(ShareItInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialShown");//广告被展示
            }

            @Override
            public void onInterstitialClicked(ShareItInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialClicked");//广告被点击
            }

            @Override
            public void onInterstitialDismissed(ShareItInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialDismissed");//广告关闭
            }
        });
interstitial.load();//发起请求
```

#### 步骤2：展示

```
if(shareItInterstitial.isReady()){//广告准备完毕
    shareItInterstitial.show();//展示广告
}
```

#### 步骤3：销毁

在Activity销毁时或相应时机进行销毁

```
shareItInterstitial.destory();
```

#### 预加载

使用preload方法进行提前预加载，可以减少在展示时机时load的时长

```
interstitial.preload();
```



## 激励视频广告 RewardedVideo

激励视频广告是保持用户沉浸于你的应用并赚取广告收益的有效方法。激励通常以游戏内货币的形式出现(游戏帮助:如金币、金币、升级道具等)，并在玩家成功完成视频后分发给他们。

###  先决条件

在将激励视频广告集成到您的应用之前：

1. 申请对应广告位，并将[SDK集成](https://github.com/sunitsdk/ShareitOpenSDKDemo#%E9%9B%86%E6%88%90)到您的项目中。
2. 初始化

### 在您的应用中加载激励视频广告

#### 步骤1：请求

```
ShareItRewardedAd rewardedAd = new ShareItRewardedAd(getContext(), REWARDED_UNIT_ID);
rewardedAd.setRewardedAdListener(new ShareItRewardedAd.RewardedVideoAdListener() {
    @Override
    public void onRewardedAdLoaded(final ShareItRewardedAd shareItRewardedAd) {
        Log.d(TAG, "onRewardedAdLoaded");//加载成功
    }

    @Override
    public void onRewardedAdFailed(ShareItRewardedAd shareItRewardedAd, AdException e) {
        Log.d(TAG, "onRewardedAdFailed e = " + e.getMessage());//加载失败
    }

    @Override
    public void onRewardedAdShown(ShareItRewardedAd shareItRewardedAd) {
        Log.d(TAG, "onRewardedAdShown");//广告被展示
    }

    @Override
    public void onRewardedAdClicked(ShareItRewardedAd shareItRewardedAd) {
        Log.d(TAG, "onRewardedAdClicked");//广告被点击
    }

    @Override
    public void onRewardedVideoClosed(ShareItRewardedAd shareItRewardedAd) {
        Log.d(TAG, "onRewardedVideoClosed");//广告关闭
    }

    @Override
    public void onRewardedVideoCompleted(ShareItRewardedAd shareItRewardedAd) {
        Log.d(TAG, "onRewardedVideoCompleted");//激励回调
    }
});
rewardedAd.load();//发起请求
```

#### 步骤2：展示

```
if (shareItRewardedAd.isReady()) {//广告准备完毕
    shareItRewardedAd.show();//展示广告
}
```

#### 步骤3：销毁

在Activity销毁时或相应时机进行销毁

```
shareItRewardedAd.destory();
```

#### 预加载

使用preload方法进行提前预加载，可以减少在展示时机时load的时长

```
shareItRewardedAd.preload();
```

