package com.gerp83.slimadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Created by gerp83
 * Class for fast creating RecyclerView.Adapter
 */

@SuppressWarnings("ConstantConditions")
public class SlimAdapter extends RecyclerView.Adapter<SlimViewHolder> implements View.OnClickListener {

    private ArrayList<Object> items;
    private ArrayList<Integer> layoutIds;
    private ArrayList<Class> classes;
    private SlimItemClickListener slimItemClickListener;
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
            viewHolder = (SlimViewHolder) newClazz.getConstructor(View.class).newInstance(view);
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
        if(slimItemClickListener != null && recyclerView != null) {
            int currentPosition = recyclerView.getChildLayoutPosition(view);
            slimItemClickListener.onItemClicked(currentPosition, getItem(currentPosition));
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
     * get layout ids array
     */
    public ArrayList<Integer> getLayoutIds() {
        return layoutIds;
    }

    /**
     * get classes array
     */
    public ArrayList<Class> getClasses() {
        return classes;
    }

    /**
     * insert one item to the last position
     *
     * @param item Object of any type
     */
    public void add(Object item){
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        notifyDataSetChanged();
    }

    /**
     * insert one item to the first position
     *
     * @param item Object of any type
     */
    public void addToFirst(Object item){
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(0, item);
        notifyDataSetChanged();
    }

    /**
     * add new items
     *
     * @param newItems ArrayList of any type of items
     */
    public void addArray(ArrayList<Object> newItems){
        items = newItems;
        notifyDataSetChanged();
    }

    /**
     * insert items to the first position
     *
     * @param newItems ArrayList of any type of items
     */
    public void addArrayToFirst(ArrayList<Object> newItems){
        if(items == null) {
            items = new ArrayList<>();
        }
        items.addAll(0, newItems);
        notifyDataSetChanged();
    }

    /**
     * insert items to the last position
     *
     * @param newItems ArrayList of any type of items
     */
    public void addArrayToLast(ArrayList<Object> newItems){
        if(items == null) {
            items = new ArrayList<>();
        }
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    /**
     * clear items
     *
     */
    public void clear() {
        if(items != null) {
            items.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * set View.OnClickListener for rows
     *
     * @param itemClickListener SlimItemClickListener
     */
    public void addOnItemClickListener(SlimItemClickListener itemClickListener) {
        slimItemClickListener = itemClickListener;
    }

    /**
     * remove View.OnClickListener for rows
     *
     */
    public void removeOnItemClickListener() {
        slimItemClickListener = null;
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