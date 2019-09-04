package com.gerp83.slimadapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gerp83
 * ViewHolder for SlimAdapter
 *
 */

public abstract class SlimViewHolder extends RecyclerView.ViewHolder {

    public abstract void fill(int position, Object data);

    public SlimViewHolder(View itemView) {
        super(itemView);
    }

}