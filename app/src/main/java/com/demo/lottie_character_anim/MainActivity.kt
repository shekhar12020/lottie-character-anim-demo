package com.demo.lottie_character_anim

import android.animation.Animator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    val rules: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        0 - 30 -------- U
//                31 - 60 -------- T,H
//        61 - 90 -------- Q,W
//        91 - 120-------- O
//                121- 150-------- L
//                151- 180-------- F,V
//        181- 210-------- C,H,J,S,H
//        211- 240-------- C,D,G,K,R,N,S
//        241- 270-------- B,M,P
//        271- 300-------- AEI

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

        val wordToSpell = "olalaleooooo"
        findViewById<TextView>(R.id.wordToSpellText).text = wordToSpell

        val talkAnimation: LottieAnimationView = findViewById(R.id.talkAnimation);
        talkAnimation.speed = 4.3f
        talkAnimation.imageAssetsFolder = "images/"
        talkAnimation.setAnimation("character_anim.json")

        val lock: Any = Any()

        var charPosition = 0

        var range = getRange(wordToSpell[charPosition++])
        talkAnimation.setMinAndMaxFrame(range.split("-")[0].toInt(), range.split("-")[1].toInt())
        talkAnimation.addAnimatorListener((object : Animator.AnimatorListener {
            override fun onAnimationStart(anim: Animator?) {
            }

            override fun onAnimationEnd(anim: Animator?) {
                talkAnimation.removeAllAnimatorListeners()
                if (charPosition < wordToSpell.length) {
                    talkAnimation.addAnimatorListener(this)
                    range = getRange(wordToSpell[charPosition++])
                    talkAnimation.setMinAndMaxFrame(range.split("-")[0].toInt(), range.split("-")[1].toInt())
                    talkAnimation.playAnimation()
                } else {
                    charPosition = 0
                    talkAnimation.addAnimatorListener(this)
                    var range = getRange(wordToSpell[charPosition++])
                    talkAnimation.setMinAndMaxFrame(range.split("-")[0].toInt(), range.split("-")[1].toInt())
                    talkAnimation.playAnimation()
                }
            }

            override fun onAnimationRepeat(anim: Animator?) {
            }

            override fun onAnimationCancel(anim: Animator?) {
            }
        }))
        talkAnimation.playAnimation()
    }

    private fun getRange(char: Char): String {
        for (rule in rules) {
            if (rule.contains(char, true)) {
                return rule.split("/")[1]
            }
        }
        return "241-270"
    }
}