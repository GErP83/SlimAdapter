package com.gerp83.slimadaptertest

import android.view.View
import android.widget.TextView
import com.gerp83.slimadapter.SlimViewHolder

class ViewHolder1(itemView: View): SlimViewHolder(itemView) {

    private var textView: TextView = itemView.findViewById(R.id.textView)

    override fun fill(position: Int, data: Any) {
        textView.text = "Text"
    }

}