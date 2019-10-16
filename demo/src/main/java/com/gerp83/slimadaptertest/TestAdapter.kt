package com.gerp83.slimadaptertest

import com.gerp83.slimadapter.SlimAdapter
import java.util.ArrayList

class TestAdapter(layoutIds: ArrayList<Int>, classes: ArrayList<Class<*>>): SlimAdapter(layoutIds, classes) {

    override fun getItemViewTypeFromObject(position: Int): Int {
        if (position % 2 != 0) {
            return 1

        } else if (position % 3 != 0) {
            return 2

        }
        return 0
    }
}