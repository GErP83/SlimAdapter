package com.gerp83.slimadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created by gerp83
 * Class for fast creating RecyclerView.Adapter
 */

public class SlimAdapter extends RecyclerView.Adapter<SlimViewHolder> implements View.OnClickListener {

    private ArrayList<Object> items;
    private ArrayList<Integer> layoutIds;
    private ArrayList<Class> classes;
    private SlimItemClickListener itemClickListener;
    private RecyclerView recyclerView;

    public SlimAdapter(Integer layoutId, Class clazz){
        this.layoutIds = new ArrayList<>();
        this.layoutIds.add(layoutId);
        this.classes = new ArrayList<>();
        this.classes.add(clazz);
    }

    public SlimAdapter(ArrayList<Integer> layoutIds, ArrayList<Class> classes){
        this.layoutIds = layoutIds;
        this.classes = classes;
    }

    @NonNull
    @Override
    public SlimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIds.get(viewType), parent, false);
        view.setOnClickListener(this);

        SlimViewHolder viewHolder = null;
        try {
            Class<?> newClazz = Class.forName(classes.get(viewType).getName());
            Constructor<?> constructor = newClazz.getConstructor(View.class);
            viewHolder = (SlimViewHolder) constructor.newInstance(view);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SlimViewHolder holder, int position) {
        holder.fill(position, items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewTypeFromObject(position);
    }

    /**
     * need to @Override this, if more then one ViewHolder type needed
     *
     * @param position position in the adapter
     * @return the ViewHolder type of the position
     */
    public int getItemViewTypeFromObject(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void onClick(View view) {
        if(itemClickListener != null && recyclerView != null) {
            int currentPosition = recyclerView.getChildLayoutPosition(view);
            itemClickListener.onItemClicked(currentPosition, getItem(currentPosition));
        }
    }

    /**
     * get an item with position
     *
     * @param position item position
     */
    public Object getItem(int position) {
        return items.get(position);
    }

    /**
     * get all items
     */
    public ArrayList<Object> getItems() {
        return items;
    }

    /**
     * insert one item to the last position
     *
     * @param item Object of any type
     */
    public void add(Object item){
        items.add(item);
        notifyDataSetChanged();
    }

    /**
     * insert one item to the first position
     *
     * @param item Object of any type
     */
    public void addToFirst(Object item){
        items.add(0, item);
        notifyDataSetChanged();
    }

    /**
     * add new items
     *
     * @param newItems ArrayList of any type of items
     */
    public void add(ArrayList<Object> newItems){
        items = newItems;
        notifyDataSetChanged();
    }

    /**
     * insert items to the first position
     *
     * @param newItems ArrayList of any type of items
     */
    public void addToFirst(ArrayList<Object> newItems){
        items.addAll(0, newItems);
        notifyDataSetChanged();
    }

    /**
     * insert items to the last position
     *
     * @param newItems ArrayList of any type of items
     */
    public void addToLast(ArrayList<Object> newItems){
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    /**
     * clear items
     *
     */
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * set View.OnClickListener for rows
     *
     * @param clickListener SlimItemClickListener
     */
    public void addOnItemClickListener(SlimItemClickListener clickListener) {
        itemClickListener = clickListener;
    }

    /**
     * remove View.OnClickListener for rows
     *
     */
    public void removeOnItemClickListener() {
        itemClickListener = null;
    }

    /**
     * set RecyclerView for later use
     *
     * @param recyclerView RecyclerView
     */
    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

}