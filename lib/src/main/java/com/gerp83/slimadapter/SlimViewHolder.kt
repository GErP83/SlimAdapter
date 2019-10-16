package com.gerp83.slimadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SlimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun fill(position: Int, data: Any)

}