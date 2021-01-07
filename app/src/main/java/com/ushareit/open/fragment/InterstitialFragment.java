package com.ushareit.open.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ushareit.ads.ShareItInterstitial;
import com.ushareit.ads.base.AdException;
import com.ushareit.open.R;

public class InterstitialFragment extends Fragment {
    private static final String TAG = "InterstitialFragment";
    private static final String INTERSTITIAL_UNIT_ID = "wm_Inter";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_interstitial, container, false);
        final Button button = root.findViewById(R.id.btn_interstitial_ad);
        button.setText("Interstitial Ad: " + INTERSTITIAL_UNIT_ID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAndShowInterstitialAd();
            }
        });
        return root;
    }

    private void loadAndShowInterstitialAd() {
        ShareItInterstitial interstitial = new ShareItInterstitial(getContext(), INTERSTITIAL_UNIT_ID);
        interstitial.setInterstitialAdListener(new ShareItInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(final ShareItInterstitial shareItInterstitial) {
                Log.d(TAG, "onInterstitialLoaded");
                shareItInterstitial.show();
            }

            @Override
            public void onInterstitialFailed(ShareItInterstitial shareItInterstitial, AdException e) {
                Log.d(TAG,"onInterstitialFailed e = " + e.getMessage());
            }

            @Override
            public void onInterstitialShown(ShareItInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialShown");
            }

            @Override
            public void onInterstitialClicked(ShareItInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialClicked");
            }

            @Override
            public void onInterstitialDismissed(ShareItInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialDismissed");
            }
        });
        interstitial.load();
    }
}