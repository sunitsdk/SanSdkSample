package com.ushareit.open.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.san.ads.SanInterstitial;
import com.ushareit.ads.base.AdException;
import com.ushareit.open.R;

public class InterstitialFragment extends Fragment {
    private static final String TAG = "InterstitialFragment";
    private static final String INTERSTITIAL_UNIT_ID = "wm_Inter";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_interstitial, container, false);
        final Button button = root.findViewById(R.id.btn_interstitial_ad);
        button.setText(String.format("Interstitial Ad: %s", INTERSTITIAL_UNIT_ID));
        button.setOnClickListener(view -> loadAndShowInterstitialAd());
        return root;
    }

    private void loadAndShowInterstitialAd() {
        SanInterstitial interstitial = new SanInterstitial(getContext(), INTERSTITIAL_UNIT_ID);
        interstitial.setInterstitialAdListener(new SanInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(final SanInterstitial shareItInterstitial) {
                Log.d(TAG, "onInterstitialLoaded");
                shareItInterstitial.show();
            }

            @Override
            public void onInterstitialFailed(SanInterstitial shareItInterstitial, AdException e) {
                Log.d(TAG,"onInterstitialFailed e = " + e.getMessage());
            }

            @Override
            public void onInterstitialShown(SanInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialShown");
            }

            @Override
            public void onInterstitialClicked(SanInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialClicked");
            }

            @Override
            public void onInterstitialDismissed(SanInterstitial shareItInterstitial) {
                Log.d(TAG,"onInterstitialDismissed");
            }
        });
        interstitial.load();
    }
}