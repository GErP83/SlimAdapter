package com.gerp83.slimadaptertest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gerp83.slimadapter.SlimItemClickListener
import java.util.ArrayList

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val layoutIds = ArrayList<Int>()
        val classes = ArrayList<Class<*>>()
        layoutIds.add(R.layout.view_layout_1)
        layoutIds.add(R.layout.view_layout_2)
        layoutIds.add(R.layout.view_layout_3)

        classes.add(ViewHolder1::class.java)
        classes.add(ViewHolder2::class.java)
        classes.add(ViewHolder3::class.java)

        val adapter = TestAdapter(layoutIds, classes)
        adapter.setRecyclerView(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        val arr = ArrayList<String>()
        for (i in 0..35) {
            arr.add("" + i)
        }
        adapter.add(arr)

        adapter.addOnItemClickListener(object : SlimItemClickListener {
            override fun onItemClicked(position: Int, item: Any?) {
                Toast.makeText(this@MainActivity, "Position: $position", Toast.LENGTH_SHORT).show()
            }
        })

    }

}