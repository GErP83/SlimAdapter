package com.gerp83.slimadaptertest;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gerp83.lazyfinder.LazyFinder;
import com.gerp83.slimadapter.SlimViewHolder;

/**
 * Created by gerp83
 */

public class ViewHolder2 extends SlimViewHolder{

    private TextView textView = null;

    public ViewHolder2(Context context, View itemView) {
        super(itemView);
        this.context = context;
        LazyFinder.findAll(context,this, itemView);
    }

    @Override
    public void fill(int position, Object data) {
        textView.setText("Text2");
    }


}
