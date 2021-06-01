package com.demo.lottie_character_anim.activity

import android.animation.Animator
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.demo.lottie_character_anim.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {


    var tts: TextToSpeech? = null
    val rules: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        rules.add("U/0-30")
        rules.add("T,H/31-60")
        rules.add("Q,W/61-90")
        rules.add("O/91-120")
        rules.add("L/121-150")
        rules.add("F,V/151-180")
        rules.add("C,H,J,S,H/181-210")
        rules.add("C,D,G,K,R,N,S/211-240")
        rules.add("B,M,P/241-270")
        rules.add("A,E,I/271-300")

        val talkAnimation: LottieAnimationView = findViewById(R.id.talkAnimation);
        talkAnimation.speed = 6f
        talkAnimation.imageAssetsFolder = "images/"
        talkAnimation.setAnimation("character_anim.json")
        var background = intent.getIntExtra("BgImage",0)
        var inputText = intent.getStringExtra("inputValue")
        layoutId.setBackgroundResource(background)
        textView.setText(inputText)

        speakButton.setOnClickListener {

            tts?.speak(textView.text, TextToSpeech.QUEUE_FLUSH, null, null)

            var charPosition = 0

            var range = getRange(textView.text[charPosition++])
            talkAnimation.setMinAndMaxFrame(range.split("-")[0].toInt(), range.split("-")[1].toInt())
            talkAnimation.addAnimatorListener((object : Animator.AnimatorListener {
                override fun onAnimationStart(anim: Animator?) {
                }

                override fun onAnimationEnd(anim: Animator?) {
                    talkAnimation.removeAllAnimatorListeners()
                    if (charPosition < textView.text.length) {
                        talkAnimation.addAnimatorListener(this)
                        range = getRange(textView.text[charPosition++])
                        talkAnimation.setMinAndMaxFrame(range.split("-")[0].toInt(), range.split("-")[1].toInt())
                        talkAnimation.playAnimation()
//                } else {
//                    charPosition = 0
//                    talkAnimation.addAnimatorListener(this)
//                    range = getRange(wordToSpell[charPosition++])
//                    talkAnimation.setMinAndMaxFrame(range.split("-")[0].toInt(), range.split("-")[1].toInt())
//                    talkAnimation.playAnimation()
                    }
                }

                override fun onAnimationRepeat(anim: Animator?) {
                }

                override fun onAnimationCancel(anim: Animator?) {
                }
            }))
            talkAnimation.playAnimation()
        }
    }

    private fun getRange(char: Char): String {
        for (rule in rules) {
            if (rule.contains(char, true)) {
                return rule.split("/")[1]
            }
        }
        return "241-270"
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(applicationContext, "Language not supported", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Init failed", Toast.LENGTH_SHORT).show();
        }
    }
}