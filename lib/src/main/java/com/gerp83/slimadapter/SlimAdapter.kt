package com.gerp83.slimadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class SlimAdapter() : RecyclerView.Adapter<SlimViewHolder>(), View.OnClickListener {

    private var layoutIds: ArrayList<Int> = ArrayList()
    private var classes: ArrayList<Class<*>> = ArrayList()
    private var items: ArrayList<*>? = null
    private var slimItemClickListener: SlimItemClickListener? = null
    private var recyclerView: RecyclerView? = null

    constructor(layoutId: Int, clazz: Class<*>) : this() {
        this.layoutIds.add(layoutId)
        this.classes.add(clazz)
    }

    constructor(layoutIds: ArrayList<Int>, classes: ArrayList<Class<*>>) : this()  {
        this.layoutIds = layoutIds
        this.classes = classes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlimViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutIds[viewType], parent, false)
        view.setOnClickListener(this)
        var viewHolder: SlimViewHolder? = null
        try {
            val newClazz = Class.forName(classes[viewType].name)
            viewHolder = newClazz.getConstructor(View::class.java).newInstance(view) as SlimViewHolder
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: SlimViewHolder, position: Int) {
        holder.fill(position, items!![position])
    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewTypeFromObject(position)
    }

    /**
     * need to @Override this, if more then one ViewHolder type needed
     *
     * @param position position in the adapter
     * @return the ViewHolder type of the position
     */
    open fun getItemViewTypeFromObject(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    override fun onClick(view: View) {
        if (slimItemClickListener != null && recyclerView != null) {
            val currentPosition = recyclerView?.getChildLayoutPosition(view)
            slimItemClickListener?.onItemClicked(currentPosition!!, getItem(currentPosition))
        }
    }

    /**
     * get an item with position
     *
     * @param position item position
     */
    fun getItem(position: Int): Any? {
        return items?.get(position)
    }

    /**
     * get all items
     */
    fun getItems(): ArrayList<*>? {
        return items
    }

    /**
     * get layout ids array
     */
    fun getLayoutIds(): ArrayList<Int> {
        return layoutIds
    }

    /**
     * get classes array
     */
    fun getClasses(): ArrayList<Class<*>> {
        return classes
    }

    /**
     * add new items
     *
     * @param newItems ArrayList of any type of items
     */
    fun add(newItems: ArrayList<*>?) {
        items = newItems
        notifyDataSetChanged()
    }

    /**
     * clear items
     *
     */
    fun clear() {
        if (items != null) {
            items?.clear()
        }
        notifyDataSetChanged()
    }

    /**
     * set View.OnClickListener for rows
     *
     * @param itemClickListener SlimItemClickListener
     */
    fun addOnItemClickListener(itemClickListener: SlimItemClickListener) {
        slimItemClickListener = itemClickListener
    }

    /**
     * remove View.OnClickListener for rows
     *
     */
    fun removeOnItemClickListener() {
        slimItemClickListener = null
    }

    /**
     * set RecyclerView for later use
     *
     * @param recyclerView RecyclerView
     */
    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }


}