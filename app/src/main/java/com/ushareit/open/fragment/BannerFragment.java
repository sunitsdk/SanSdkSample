package com.ushareit.open.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ushareit.ads.ShareItBannerView;
import com.ushareit.ads.base.AdException;
import com.ushareit.open.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BannerFragment extends Fragment {
    private static final String TAG = "BannerFragment";
    private static final String BANNER_UNIT_ID_320x50 = "wm_banner_320x50";
    private static final String BANNER_UNIT_ID_300x250 = "wm_banner_300x250";

    private FrameLayout mBannerContainer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_banner, container, false);
        final Button banner50 = root.findViewById(R.id.btn_banner_ad);
        banner50.setText("Banner320x50: " + BANNER_UNIT_ID_320x50);
        mBannerContainer = root.findViewById(R.id.banner_container);
        banner50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAndShowBannerAd(ShareItBannerView.AdSize.HEIGHT_50, BANNER_UNIT_ID_320x50);
            }
        });


        final Button banner250 = root.findViewById(R.id.btn_banner_ad2);
        banner250.setText("Banner300x250: " + BANNER_UNIT_ID_300x250);
        banner250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAndShowBannerAd(ShareItBannerView.AdSize.HEIGHT_250, BANNER_UNIT_ID_300x250);
            }
        });
        return root;
    }

    private void loadAndShowBannerAd(ShareItBannerView.AdSize adSize, String adUnitId) {
        ShareItBannerView banner = new ShareItBannerView(getContext());
        banner.setAdUnitId(adUnitId);
        banner.setAdSize(adSize);
        banner.setBannerAdListener(new ShareItBannerView.BannerAdListener() {
            @Override
            public void onBannerLoaded(@NonNull ShareItBannerView shareItBannerView) {
                Log.d(TAG,"onBannerLoaded");
                mBannerContainer.removeAllViews();
                mBannerContainer.addView(shareItBannerView);
            }

            @Override
            public void onBannerFailed(ShareItBannerView shareItBannerView, AdException e) {
                Log.d(TAG,"onBannerFailed exception = " + e.getMessage());
            }

            @Override
            public void onBannerClicked(ShareItBannerView shareItBannerView) {
                Log.d(TAG,"onBannerClicked");
            }

            @Override
            public void onBannerImpression(ShareItBannerView shareItBannerView) {
                Log.d(TAG,"onBannerImpression");
            }
        });
        banner.loadAd();
    }
}