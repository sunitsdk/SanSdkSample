package com.ushareit.open.holder;
import android.view.View;
import android.widget.TextView;

import com.ushareit.open.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NativeAdViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;

    public NativeAdViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.title);
    }

    public void onBindViewHolder(String data) {
        mTitle.setText(data);
    }
}
