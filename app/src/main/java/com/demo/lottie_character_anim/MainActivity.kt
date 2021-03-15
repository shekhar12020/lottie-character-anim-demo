package com.demo.lottie_character_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val talkAnimation: LottieAnimationView = findViewById(R.id.talkAnimation);
        talkAnimation.imageAssetsFolder = "images/"
        talkAnimation.setAnimation("character_anim.json")
        talkAnimation.playAnimation()
        talkAnimation.loop(true)
    }
}