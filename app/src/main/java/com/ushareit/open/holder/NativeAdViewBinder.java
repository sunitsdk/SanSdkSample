package com.ushareit.open.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sunit.mediation.render.AdMobNativeAdRenderer;
import com.sunit.mediation.render.FacebookNativeAdRenderer;
import com.sunit.mediation.render.MoPubNativeAdRenderer;
import com.ushareit.ads.AdMobNativeAd;
import com.ushareit.ads.BaseNativeAd;
import com.ushareit.ads.FacebookNativeAd;
import com.ushareit.ads.MoPubNativeAd;
import com.ushareit.ads.SUnitNativeAdRenderer;
import com.ushareit.ads.ShareItNativeAd;

import java.util.HashMap;
import java.util.Map;

import static com.ushareit.ads.ShareItAdRender.AD_CHOICES_VIEW;

public class NativeAdViewBinder {
    private final int layoutId;
    private final int titleId;
    private final int textId;
    private final int callToActionId;
    private final int mainImageId;
    private final int iconImageId;
    @NonNull
    final Map<String, Integer> extras;

    private NativeAdViewBinder(@NonNull final NativeAdViewBinder.Builder builder) {
        this.layoutId = builder.layoutId;
        this.titleId = builder.titleId;
        this.textId = builder.textId;
        this.callToActionId = builder.callToActionId;
        this.mainImageId = builder.mainImageId;
        this.iconImageId = builder.iconImageId;
        this.extras = builder.extras;
    }

    @Nullable
    public View createAdView(Context context, BaseNativeAd nativeAd, ViewGroup parent) {
        if (context == null || nativeAd == null)
            return null;
        View adView = null;
        if (nativeAd instanceof ShareItNativeAd) {
            adView = getMidasOwnNativeView(context, (ShareItNativeAd) nativeAd, this, parent);
        } else if (nativeAd instanceof MoPubNativeAd) {
            adView = getMopubNativeView(context, (MoPubNativeAd) nativeAd, this, parent);
        } else if (nativeAd instanceof FacebookNativeAd) {
            adView = getFbNativeView(context, (FacebookNativeAd) nativeAd, this, parent);
        } else if (nativeAd instanceof AdMobNativeAd) {
            adView = getAdmobNativeView(context, (AdMobNativeAd) nativeAd, this, parent);
        }
        return adView;
    }

    private View getMidasOwnNativeView(@NonNull Context context, ShareItNativeAd nativeAd, NativeAdViewBinder nativeAdViewBinder, ViewGroup parent) {
        SUnitNativeAdRenderer midasAdRenderer = new SUnitNativeAdRenderer(
                new SUnitNativeAdRenderer.SUnitViewBinder
                        .Builder(nativeAdViewBinder.layoutId)
                        .iconImageId(nativeAdViewBinder.iconImageId)
                        .mainImageId(nativeAdViewBinder.mainImageId)
                        .titleId(nativeAdViewBinder.titleId)
                        .textId(nativeAdViewBinder.textId)
                        .callToActionId(nativeAdViewBinder.callToActionId)
                        .build());
        View adView = midasAdRenderer.createAdView(context, parent);
        midasAdRenderer.renderAdView(adView, nativeAd);
        return adView;
    }

    private View getMopubNativeView(@NonNull Context context, MoPubNativeAd nativeAd, NativeAdViewBinder nativeAdViewBinder, ViewGroup parent) {
        MoPubNativeAdRenderer moPubAdRenderer = new MoPubNativeAdRenderer(
                new MoPubNativeAdRenderer.MoPubViewBinder.Builder(nativeAdViewBinder.layoutId)
                        .iconImageId(nativeAdViewBinder.iconImageId)
                        .mainImageId(nativeAdViewBinder.mainImageId)
                        .titleId(nativeAdViewBinder.titleId)
                        .textId(nativeAdViewBinder.textId)
                        .callToActionId(nativeAdViewBinder.callToActionId)
                        .build());

        View adView = moPubAdRenderer.createAdView(context, parent);
        moPubAdRenderer.renderAdView(adView, nativeAd);
        return adView;
    }

    private View getFbNativeView(@NonNull Context context, FacebookNativeAd nativeAd, NativeAdViewBinder nativeAdViewBinder, ViewGroup parent) {
        FacebookNativeAdRenderer fbAdRenderer = new FacebookNativeAdRenderer(
                new FacebookNativeAdRenderer.FacebookViewBinder.Builder(nativeAdViewBinder.layoutId)
                        .adIconViewId(nativeAdViewBinder.iconImageId)
                        .mediaViewId(nativeAdViewBinder.mainImageId)
                        .titleId(nativeAdViewBinder.titleId)
                        .textId(nativeAdViewBinder.textId)
                        .callToActionId(nativeAdViewBinder.callToActionId)
                        .addExtra(AD_CHOICES_VIEW, nativeAdViewBinder.extras.get(AD_CHOICES_VIEW) == null ? 0 : nativeAdViewBinder.extras.get(AD_CHOICES_VIEW))
                        .build());
        View adView = fbAdRenderer.createAdView(context, parent);
        fbAdRenderer.renderAdView(adView, nativeAd);
        return adView;
    }

    private View getAdmobNativeView(@NonNull Context context, AdMobNativeAd nativeAd, NativeAdViewBinder nativeAdViewBinder, ViewGroup parent) {
        AdMobNativeAdRenderer adMobAdRenderer = new AdMobNativeAdRenderer(
                new AdMobNativeAdRenderer.AdMobViewBinder.Builder(nativeAdViewBinder.layoutId)
                        .iconImageId(nativeAdViewBinder.iconImageId)
                        .mediaLayoutId(nativeAdViewBinder.mainImageId)
                        .titleId(nativeAdViewBinder.titleId)
                        .textId(nativeAdViewBinder.textId)
                        .callToActionId(nativeAdViewBinder.callToActionId)
                        .addExtra(AD_CHOICES_VIEW, nativeAdViewBinder.extras.get(AD_CHOICES_VIEW) == null ? 0 : nativeAdViewBinder.extras.get(AD_CHOICES_VIEW))
                        .build());
        View adView = adMobAdRenderer.createAdView(context, parent);
        adMobAdRenderer.renderAdView(adView, nativeAd);
        return adView;
    }

    public final static class Builder {
        private final int layoutId;
        private int titleId;
        private int textId;
        private int callToActionId;
        private int mainImageId;
        private int iconImageId;

        @NonNull
        private Map<String, Integer> extras;

        public Builder(final int layoutId) {
            this.layoutId = layoutId;
            this.extras = new HashMap<>();
        }

        @NonNull
        public final NativeAdViewBinder.Builder titleId(final int titleId) {
            this.titleId = titleId;
            return this;
        }

        @NonNull
        public final NativeAdViewBinder.Builder textId(final int textId) {
            this.textId = textId;
            return this;
        }

        @NonNull
        public final NativeAdViewBinder.Builder callToActionId(final int callToActionId) {
            this.callToActionId = callToActionId;
            return this;
        }

        @NonNull
        public final NativeAdViewBinder.Builder mainImageId(final int mediaLayoutId) {
            this.mainImageId = mediaLayoutId;
            return this;
        }

        @NonNull
        public final NativeAdViewBinder.Builder iconImageId(final int iconImageId) {
            this.iconImageId = iconImageId;
            return this;
        }

        @NonNull
        public final NativeAdViewBinder.Builder addExtras(final Map<String, Integer> resourceIds) {
            this.extras = new HashMap<>(resourceIds);
            return this;
        }

        @NonNull
        public final NativeAdViewBinder.Builder addExtra(final String key, final int resourceId) {
            this.extras.put(key, resourceId);
            return this;
        }

        @NonNull
        public final NativeAdViewBinder build() {
            return new NativeAdViewBinder(this);
        }
    }


}
