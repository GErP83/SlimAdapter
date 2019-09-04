package com.gerp83.slimadaptertest;

import android.view.View;
import android.widget.TextView;

import com.gerp83.slimadapter.SlimViewHolder;

/**
 * Created by gerp83
 */

public class ViewHolder2 extends SlimViewHolder{

    private TextView textView;

    public ViewHolder2(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    @Override
    public void fill(int position, Object data) {
        textView.setText("Text2");
    }


}
