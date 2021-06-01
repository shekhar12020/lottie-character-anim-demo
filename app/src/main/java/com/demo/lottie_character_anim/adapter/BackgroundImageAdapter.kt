package com.demo.lottie_character_anim.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.demo.lottie_character_anim.R

class BackgroundImageAdapter() : RecyclerView.Adapter<BackgroundImageAdapter.MyViewHolder>() {
    private lateinit var itemList: ArrayList<Int>
    private lateinit var mContext: Context
    private  lateinit var onItemClickListener : OnItemClickListener
    private var selectedPosition = -1

    constructor(context: Context, item: ArrayList<Int>, listener : OnItemClickListener) : this() {
        this.mContext = context
        this.onItemClickListener = listener
        this.itemList = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_view_bg_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = itemList.get(position)
        holder.imgBackground.setImageResource(item)

        if(selectedPosition == position)
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(item)
            selectedPosition = position;
            notifyDataSetChanged();
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imgBackground: ImageView
        init {
            imgBackground = itemView.findViewById<View>(R.id.imgBackground) as ImageView
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item : Int)
    }
}