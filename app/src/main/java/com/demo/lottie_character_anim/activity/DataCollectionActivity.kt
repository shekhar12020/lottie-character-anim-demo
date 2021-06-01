package com.demo.lottie_character_anim.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.lottie_character_anim.R
import com.demo.lottie_character_anim.adapter.BackgroundImageAdapter
import com.demo.lottie_character_anim.adapter.CharacterAdapter
import kotlinx.android.synthetic.main.activity_data_collection.*
import kotlin.properties.Delegates

class DataCollectionActivity : AppCompatActivity() {
    private var bgImaige : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_collection)
        edtInpute.setText("Creating a Terms & Conditions for your application or website can take a lot of time. You could either spend tons of money on hiring a lawyer, or you could simply use our service and get a unique Terms & Conditions fully custumized to your website.")

        rvCharacter.setHasFixedSize(true)
        rvCharacter.layoutManager = LinearLayoutManager(this@DataCollectionActivity, LinearLayoutManager.HORIZONTAL,false)
        rvCharacter.adapter = CharacterAdapter(this@DataCollectionActivity, getCharaterData())


        rvBackground.layoutManager = LinearLayoutManager(this@DataCollectionActivity, LinearLayoutManager.HORIZONTAL,false)
        rvBackground.adapter = BackgroundImageAdapter(this@DataCollectionActivity, getCharaterData(),onclickListener)

        btnCreate.setOnClickListener {
            val intent = Intent(this@DataCollectionActivity,MainActivity::class.java)
            intent.putExtra("BgImage",bgImaige)
            intent.putExtra("inputValue",edtInpute.text.toString())
            startActivity(intent)
        }

    }

    private val onclickListener = object : BackgroundImageAdapter.OnItemClickListener{
        override fun onItemClick(item: Int) {
            bgImaige = item
        }
    }


    private fun getCharaterData(): ArrayList<Int> {
        val imageList = ArrayList<Int>()
        imageList.add(R.drawable.bg1)
        imageList.add(R.drawable.bg2)
        imageList.add(R.drawable.bg3)
        imageList.add(R.drawable.bg4)
        imageList.add(R.drawable.bg1)
        imageList.add(R.drawable.bg2)
        imageList.add(R.drawable.bg3)
        imageList.add(R.drawable.bg4)
        imageList.add(R.drawable.bg1)
        imageList.add(R.drawable.bg2)
        imageList.add(R.drawable.bg3)
        imageList.add(R.drawable.bg4)

        return imageList;
    }

}