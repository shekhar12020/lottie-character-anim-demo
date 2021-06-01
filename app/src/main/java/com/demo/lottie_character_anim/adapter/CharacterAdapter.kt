package com.demo.lottie_character_anim.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.demo.lottie_character_anim.R

class CharacterAdapter() : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {
    private lateinit var itemList: ArrayList<Int>
    private lateinit var mContext: Context
    private var selectedPosition = -1

    constructor(context: Context, item: ArrayList<Int>) : this() {
        this.mContext = context
        this.itemList = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_view_charater_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.talkAnimation.speed = 6f
        holder.talkAnimation.imageAssetsFolder = "images/"
        holder.talkAnimation.setAnimation("character_anim.json")

//        if(selectedPosition == position)
//            holder.layoutId.setBackgroundColor(R.drawable.rounde_boarder_grey);
//        else
//            holder.layoutId.setBackgroundColor(R.drawable.rounde_boarder_white);

//        holder.itemView.setOnClickListener {
//            selectedPosition=position;
//            notifyDataSetChanged();
//        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var talkAnimation: LottieAnimationView
        internal var layoutId: LinearLayout
        init {
            talkAnimation = itemView.findViewById<View>(R.id.talkAnimation) as LottieAnimationView
            layoutId = itemView.findViewById<View>(R.id.layoutId) as LinearLayout
        }
    }

//    interface OnItemClickListener {
//        fun onItemClick(position: Int, item : DataModule, view : View)
//    }
}