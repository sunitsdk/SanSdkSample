package com.ushareit.open.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.san.ads.SanRewardedAd;
import com.ushareit.ads.base.AdException;
import com.ushareit.open.R;

public class RewardedFragment extends Fragment {
    private static final String TAG = "RewardedFragment";
    private static final String REWARDED_PLACEMENT_ID = "san_wmlt_itl";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rewarded, container, false);
        final Button button = root.findViewById(R.id.btn_rewarded_ad);
        button.setText(String.format("Rewarded Ad: %s", REWARDED_PLACEMENT_ID));
        button.setOnClickListener(view -> loadAndShowRewardedAd());
        return root;
    }

    private void loadAndShowRewardedAd() {
        SanRewardedAd rewardedAd = new SanRewardedAd(getContext(), REWARDED_PLACEMENT_ID);
        rewardedAd.setRewardedAdListener(new SanRewardedAd.RewardedVideoAdListener() {
            @Override
            public void onRewardedAdLoaded(final SanRewardedAd shareItRewardedAd) {
                Log.d(TAG, "onRewardedAdLoaded");
                shareItRewardedAd.show();
            }

            @Override
            public void onRewardedAdFailed(SanRewardedAd shareItRewardedAd, AdException e) {
                Log.d(TAG, "onRewardedAdFailed e = " + e.getMessage());
            }

            @Override
            public void onRewardedAdShown(SanRewardedAd shareItRewardedAd) {
                Log.d(TAG, "onRewardedAdShown");
            }

            @Override
            public void onRewardedAdClicked(SanRewardedAd shareItRewardedAd) {
                Log.d(TAG, "onRewardedAdClicked");
            }

            @Override
            public void onRewardedVideoClosed(SanRewardedAd shareItRewardedAd) {
                Log.d(TAG, "onRewardedVideoClosed");
            }

            @Override
            public void onRewardedVideoCompleted(SanRewardedAd shareItRewardedAd) {
                Log.d(TAG, "onRewardedVideoCompleted");
            }
        });
        rewardedAd.load();
    }

}