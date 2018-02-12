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

public abstract class SlimAdapter extends RecyclerView.Adapter<SlimViewHolder> {

    protected Context context;
    private ArrayList data;
    protected ArrayList<Integer> layoutIds;
    protected ArrayList<Class> classes;
    private OnItemClickListener onItemClickListener;
    private RecyclerView recyclerView;

    public interface OnItemClickListener {
        void onItemClicked(Object item);
    }

    public abstract int getItemViewTypeFromObject(int position);


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
            if(onItemClickListener != null && recyclerView != null) {
                onItemClickListener.onItemClicked(getItem(recyclerView.getChildLayoutPosition(view)));
            }
        }
    };

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    /**
     * set View.OnClickListener for rows
     *
     * @param onItemClickListener OnItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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