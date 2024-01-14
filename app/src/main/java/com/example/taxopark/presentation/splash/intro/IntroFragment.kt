package com.example.taxopark.presentation.splash.intro

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.taxopark.R
import com.example.taxopark.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {

    private lateinit var viewBinding: FragmentIntroBinding
    var introNumber = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentIntroBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.fbnNext.setOnClickListener {
            introNumber ++
            when(introNumber){
                2 -> showIntro2()
                3 -> showIntro3()
                4 -> openLogin()
            }
        }
    }

    private fun openLogin() {
        val navController = findNavController()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(navController.graph.startDestinationId, true)
            .build()

        // Navigate to inputPhoneFragment with the specified NavOptions
        navController.navigate(R.id.inputPhoneFragment, null, navOptions)
    }

    private fun showIntro3() {
        val animator = ValueAnimator.ofInt(20, 30).apply {
            duration = 700
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Int
                viewBinding.progress.progress = progress.toFloat()
            }
            start()
        }
        animateTextChange("Buyurtma bering","Va sizni haydovchi o'zi manzilingizdan olib, manzilingizga yetkazib qo'yadi. Qimmatli vaqtingizni kerakli narsalarga sarflang!")



    }

    private fun showIntro2() {
        val animator = ValueAnimator.ofInt(10, 20).apply {
            duration = 700
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Int
                viewBinding.progress.progress = progress.toFloat()
            }
            start()
        }
        animateTextChange("Istalgan vaqtda","Istalgan kun uchun sayohatingizni rejalashtiring. Kutish, qidirish, kelishishish uchun endi vaqt sarflamang!")
//        viewBinding.textViewTitle.text = "Istalgan vaqtda"
//        viewBinding.textViewDesc.text = "Istalgan kun uchun sayohatingizni rejalashtiring. Kutish, qidirish, kelishishish uchun endi vaqt sarflamang!"
    }

    private fun animateTextChange(introText: String, desc: String) {
        val fadeOutTitle = ObjectAnimator.ofFloat(viewBinding.textViewTitle, "alpha", 1f, 0f).apply {
            duration = 500
        }
        val fadeOutDesc = ObjectAnimator.ofFloat(viewBinding.textViewDesc, "alpha", 1f, 0f).apply {
            duration = 500
        }


        fadeOutTitle.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                viewBinding.textViewTitle.text = introText
                Handler(Looper.getMainLooper()).postDelayed({
                    fadeIn(viewBinding.textViewTitle) // Fade in duration is 500ms
                }, 300)

            }
        })

        fadeOutDesc.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                viewBinding.textViewDesc.text = desc
                Handler(Looper.getMainLooper()).postDelayed({
                    fadeIn(viewBinding.textViewDesc) // Fade in duration is 500ms
                }, 300)

            }
        })

        fadeOutTitle.start()
        fadeOutDesc.start()
    }

    private fun fadeIn(view: View) {
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            duration = 500
            start()
        }
    }
}