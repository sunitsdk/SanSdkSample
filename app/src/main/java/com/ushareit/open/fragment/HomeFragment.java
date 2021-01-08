package com.ushareit.open.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ushareit.ads.BaseNativeAd;
import com.ushareit.ads.ShareItNative;
import com.ushareit.ads.base.AdException;
import com.ushareit.open.R;
import com.ushareit.open.adapter.NativeAdListAdapter;
import com.ushareit.open.holder.NativeAdViewBinder;

import java.util.ArrayList;
import java.util.List;

import static com.ushareit.ads.ShareItAdRender.AD_CHOICES_VIEW;

public class HomeFragment extends Fragment {
    private static final String TAG = "Native";

    private FrameLayout mAdContainer;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdContainer = view.findViewById(R.id.native_render_container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        NativeAdListAdapter adListAdapter = new NativeAdListAdapter(getContext(), getListData());
        adListAdapter.setOnItemClickListener(position -> loadAndShowNativeAd(getUnitIdData().get(position)));
        recyclerView.setAdapter(adListAdapter);
    }

    private List<String> getListData() {
        List<String> listData = new ArrayList<>();
        listData.add("NativeAd: wm_yuansheng");
        listData.add("NativeAd: test_native");
        return listData;
    }

    private List<String> getUnitIdData() {
        List<String> listData = new ArrayList<>();
        listData.add("wm_yuansheng");
        listData.add("test_native");
        return listData;
    }

    private void loadAndShowNativeAd(String adUnitId) {
        ShareItNative shareItNative = new ShareItNative(getContext(), adUnitId, new ShareItNative.NativeNetworkListener() {
            @Override
            public void onNativeLoaded(BaseNativeAd nativeAd) {
                Log.d(TAG, "onNativeLoaded");
                NativeAdViewBinder midasAdRenderer = new NativeAdViewBinder.Builder(R.layout.ad_item_layout)
                                .iconImageId(R.id.native_icon_image)
                                .mainImageId(R.id.native_main_image)
                                .titleId(R.id.native_title)
                                .textId(R.id.native_text)
                                .callToActionId(R.id.native_cta)
                                .addExtra(AD_CHOICES_VIEW, R.id.ad_choices)
                                .build();
                View adView = midasAdRenderer.createAdView(HomeFragment.this.getContext(), nativeAd, null);
                if (adView == null) return;

                mAdContainer.removeAllViews();
                mAdContainer.addView(adView);
            }

            @Override
            public void onNativeFailed(AdException e) {
                Log.d(TAG, "onNativeFailed e = " + e.getMessage() + " code = " + e.getCode());
            }

            @Override
            public void onImpression(BaseNativeAd baseNativeAd) {
                Log.d(TAG, "onImpression");
            }

            @Override
            public void onClick(BaseNativeAd baseNativeAd) {
                Log.d(TAG, "onClick");
            }
        });
        shareItNative.loadAd();
    }


}