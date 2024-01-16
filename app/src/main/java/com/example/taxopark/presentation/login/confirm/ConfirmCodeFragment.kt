package com.example.taxopark.presentation.login.confirm

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taxopark.R
import com.example.taxopark.databinding.FragmentConfirmCodeBinding
import com.example.taxopark.domain.preference.UserPreferenceManager
import com.example.taxopark.utils.input.PinViewUtils
import com.example.taxopark.utils.text.TextViewStyleUtils
import com.example.taxopark.utils.time.ResendTimerUtil
import org.koin.android.ext.android.inject

class ConfirmCodeFragment : Fragment() {

    private lateinit var viewBinding: FragmentConfirmCodeBinding
    private val preferenceManager: UserPreferenceManager by inject()
    private lateinit var imm: InputMethodManager
    companion object {
        const val DURATION_TIME: Long = 60_000
        const val INTERVAL_TIME: Long = 1_000
    }

    private lateinit var timer: ResendTimerUtil
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentConfirmCodeBinding.inflate(inflater, container, false)
        timer = ResendTimerUtil(durationMillis = DURATION_TIME,
            intervalMillis = INTERVAL_TIME,
            oonTick = {
                viewBinding.countDownTextView.text =
                    if ((it / 1000) >= 10) "00:${it / 1000}" else "00:0${it / 1000}"
            },
            oonFinish = { updateCountDownTextView() }
        ).apply { start() }
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phone = "+998 ${preferenceManager.getPhone()}"
        val text =
            "$phone ${getString(R.string.show_detail_input_code)}"
        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        with(viewBinding) {
            pinView.requestFocus()
            imm.showSoftInput(pinView, InputMethodManager.SHOW_IMPLICIT)
            PinViewUtils.setPinViewTextChangedListener(pinView) {
//                inputPasswordViewModel.confirmPassword(
//                    ConfirmationRequest(preferenceManager.getTokenForPhone().orEmpty(), it.toInt())
//                )
            }
        }

        viewBinding.buttonConfirm.setOnClickListener {
            findNavController().navigate(R.id.fillDataFragment)
        }
        viewBinding.textViewShow.text = TextViewStyleUtils.bold(phone,text)
        viewBinding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }


    }


    private fun updateCountDownTextView() {
        with(viewBinding.countDownTextView) {
            text = getString(R.string.resend_sms)
            isClickable = true
            isFocusable = true
            addRipple()
            setTextColor(Color.parseColor("#014EA8"))
            setOnClickListener {
//                inputPasswordViewModel.resendSMS(
//                    ResendSmsRequest(
//                        preferenceManager.getTokenForPhone().orEmpty()
//                    )
//                )
                timer.stop()
                viewBinding.pinView.text?.clear()
                timer.start()
                /* Resend action here */
            }
        }
    }
    private fun View.addRipple() = with(TypedValue()) {
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
        setBackgroundResource(resourceId)
    }

    private fun View.shake() {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_animation))
    }

    private fun View.correct() {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.correct_anim))
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.stop()
    }
}