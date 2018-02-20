package com.gerp83.slimadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created by gerp83
 * Class for fast creating RecyclerView.Adapter
 */

public class SlimAdapter extends RecyclerView.Adapter<SlimViewHolder> {

    protected Context context;
    private ArrayList data;
    protected ArrayList<Integer> layoutIds;
    protected ArrayList<Class> classes;
    private SlimItemClickListener itemClickListener;
    private RecyclerView recyclerView;

    public SlimAdapter(Context context, Integer layoutId, Class clazz){
        this.context = context;
        this.layoutIds = new ArrayList<>();
        this.layoutIds.add(layoutId);
        this.classes = new ArrayList<>();
        this.classes.add(clazz);
    }

    public SlimAdapter(Context context, ArrayList<Integer> layoutIds, ArrayList<Class> classes){
        this.context = context;
        this.layoutIds = layoutIds;
        this.classes = classes;
    }

    @Override
    public SlimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIds.get(getItemViewType(viewType)), null);
        view.setOnClickListener(onClickListener);

        SlimViewHolder viewHolder = null;
        try {
            Class<?> newClazz = Class.forName(classes.get(getItemViewType(viewType)).getName());
            Constructor<?> constructor = newClazz.getConstructor(Context.class, View.class);
            viewHolder = (SlimViewHolder) constructor.newInstance(context, view);
            viewHolder.setAdapter(this);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SlimViewHolder holder, int position) {
        holder.fill(position, data.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewTypeFromObject(position);
    }

    /**
     * need to  @Override this, if more then one ViewHolder type needed
     *
     * @param position position in the adapter
     * @return the ViewHolder type of the position
     */
    public int getItemViewTypeFromObject(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * update with rows data
     *
     * @param data ArrayList of any type of data
     */
    public void addItems(ArrayList data){
        this.data = data;
        notifyDataSetChanged();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(itemClickListener != null && recyclerView != null) {
                int currentPosition = recyclerView.getChildLayoutPosition(view);
                itemClickListener.onItemClicked(currentPosition, getItem(currentPosition));
            }
        }
    };

    public SlimItemClickListener getOnItemClickListener() {
        return this.itemClickListener;
    }

    /**
     * set View.OnClickListener for rows
     *
     * @param itemClickListener SlimItemClickListener
     */
    public void setOnItemClickListener(SlimItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
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