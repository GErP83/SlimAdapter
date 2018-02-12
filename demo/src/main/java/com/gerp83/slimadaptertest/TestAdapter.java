package com.gerp83.slimadaptertest;

import android.content.Context;
import com.gerp83.slimadapter.SlimAdapter;
import java.util.ArrayList;

/**
 * Created by gerp83
 */

public class TestAdapter extends SlimAdapter{

    public TestAdapter(Context context, ArrayList<Integer> layoutIds, ArrayList<Class> classes) {
        super(context, layoutIds, classes);
    }

    @Override
    public int getItemViewTypeFromObject(int position) {
        if(position % 2 != 0) {
            return 1;

        } else if(position % 3 != 0) {
            return 2;

        }
        return 0;
    }

}
