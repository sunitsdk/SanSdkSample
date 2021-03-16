package com.ushareit.open.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.san.ads.SanBannerView;
import com.ushareit.ads.base.AdException;
import com.ushareit.open.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BannerFragment extends Fragment {
    private static final String TAG = "BannerFragment";
    private static final String BANNER_PLACEMENT_ID_320x50 = "ad:san_wmlt_banner1";
    private static final String BANNER_PLACEMENT_ID_300x250 = "ad:san_wmlt_banner300-250";

    private FrameLayout mBannerContainer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_banner, container, false);
        final Button banner50 = root.findViewById(R.id.btn_banner_ad);
        banner50.setText("Banner320x50");
        mBannerContainer = root.findViewById(R.id.banner_container);
        banner50.setOnClickListener(view -> loadAndShowBannerAd(SanBannerView.AdSize.HEIGHT_50, BANNER_PLACEMENT_ID_320x50));


        final Button banner250 = root.findViewById(R.id.btn_banner_ad2);
        banner250.setText("Banner300x250");
        banner250.setOnClickListener(view -> loadAndShowBannerAd(SanBannerView.AdSize.HEIGHT_250, BANNER_PLACEMENT_ID_300x250));
        return root;
    }

    private void loadAndShowBannerAd(SanBannerView.AdSize adSize, String placementId) {
        SanBannerView banner = new SanBannerView(getContext());
        banner.setAdUnitId(placementId);
        banner.setAdSize(adSize);
        banner.setBannerAdListener(new SanBannerView.BannerAdListener() {
            @Override
            public void onBannerLoaded(@NonNull SanBannerView sanBannerView) {
                Log.d(TAG,"onBannerLoaded");
                mBannerContainer.removeAllViews();
                mBannerContainer.addView(sanBannerView);
            }

            @Override
            public void onBannerFailed(SanBannerView sanBannerView, AdException e) {
                Log.d(TAG,"onBannerFailed exception = " + e.getMessage());
            }

            @Override
            public void onBannerClicked(SanBannerView sanBannerView) {
                Log.d(TAG,"onBannerClicked");
            }

            @Override
            public void onBannerImpression(SanBannerView sanBannerView) {
                Log.d(TAG,"onBannerImpression");
            }
        });
        banner.loadAd();
    }
}