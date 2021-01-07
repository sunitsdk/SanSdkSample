package com.ushareit.open.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ushareit.open.R;
import com.ushareit.open.holder.EmptyViewHolder;
import com.ushareit.open.holder.NativeAdViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NativeAdListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mDataList;
    private View headView;
    private View footView;
    public static final int HEAD = 1;
    public static final int NORMAL = 2;
    public static final int FOOT = 3;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public NativeAdListAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mDataList = data;
    }

    public void setData(List<String> list) {
        this.mDataList = list;
        notifyDataSetChanged();
    }

    public void addHeadView(View headView) {
        this.headView = headView;
    }

    public void addFootView(View footView) {
        this.footView = footView;
    }

    public int getHeadViewCount() {
        return headView == null ? 0 : 1;
    }

    public int getFootViewCount() {
        return footView == null ? 0 : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeadViewCount()) return HEAD;
        if (position >= mDataList.size() + getHeadViewCount()) return FOOT;
        return NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEAD)
            return new EmptyViewHolder(headView);
        if (viewType == FOOT)
            return new EmptyViewHolder(footView);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ad_view, parent, false);
        return new NativeAdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (getItemViewType(position) == HEAD) return;
        if (getItemViewType(position) == FOOT) return;
        if (viewHolder instanceof NativeAdViewHolder) {
            NativeAdViewHolder holder = (NativeAdViewHolder) viewHolder;
            holder.onBindViewHolder(mDataList.get(position - getHeadViewCount()));
        }
        setItemClickListener(viewHolder, position);
    }

    private void setItemClickListener(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(position - getHeadViewCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size() + getHeadViewCount() + getFootViewCount();
    }
}
