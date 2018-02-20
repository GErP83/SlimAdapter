package com.gerp83.slimadaptertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gerp83.slimadapter.SlimItemClickListener;

import java.util.ArrayList;

/**
 * Created by GErP83 on 2017. 09. 24..
 *
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ArrayList<Integer> layoutIds = new ArrayList<>();
        ArrayList<Class> classes = new ArrayList<>();
        layoutIds.add(R.layout.view_layout_1);
        layoutIds.add(R.layout.view_layout_2);
        layoutIds.add(R.layout.view_layout_3);

        classes.add(ViewHolder1.class);
        classes.add(ViewHolder2.class);
        classes.add(ViewHolder3.class);

        TestAdapter adapter = new TestAdapter(this, layoutIds, classes);
        adapter.setRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        ArrayList<String> arr = new ArrayList<>();
        for(int i = 0; i < 36; i++) {
            arr.add(null);
        }
        adapter.addItems(arr);

        adapter.setOnItemClickListener(new SlimItemClickListener() {
            @Override
            public void onItemClicked(int position, Object item) {
                Toast.makeText(MainActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
