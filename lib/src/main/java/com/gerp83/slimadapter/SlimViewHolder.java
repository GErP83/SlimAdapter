package com.gerp83.slimadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gerp83
 * ViewHolder for SlimAdapter
 *
 */

public abstract class SlimViewHolder extends RecyclerView.ViewHolder {

    private SlimAdapter adapter;
    protected Context context;

    public SlimViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * set SlimAdapter for later use
     *
     * @param adapter SlimAdapter
     */
    public void setAdapter(SlimAdapter adapter) {
        this.adapter = adapter;
    }

    public SlimAdapter getAdapter() {
        return adapter;
    }

    /**
     * set Context for later use
     *
     * @param context Context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract void fill(int position, Object data);

}