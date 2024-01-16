package com.example.taxopark.presentation.login.filldata.fillperson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taxopark.R
import com.example.taxopark.databinding.FragmentFillLicenseBinding
import com.example.taxopark.databinding.FragmentFillPersonBinding
import com.example.taxopark.domain.preference.UserPreferenceManager
import com.example.taxopark.utils.constant.StepConst.FILL_CAR_DATA
import com.example.taxopark.utils.dialog.DatePickerUtils
import com.example.taxopark.utils.valid.ValidationUtils
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject

class FillPersonFragment : Fragment() {



    private lateinit var viewBinding: FragmentFillPersonBinding
    private val preferenceManager: UserPreferenceManager by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentFillPersonBinding.inflate(inflater,container,false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding){

            viewBinding.nextDetailCarFbn.setOnClickListener {

                val name = viewBinding.inputNameEt.editText?.text.toString()
                val surname = viewBinding.inputSurnameEt.editText?.text.toString()
                val birthDay = viewBinding.inputBirthDayEt.editText?.text.toString()
                val gender = if (viewBinding.selectManRadio.isChecked) 1 else 2

                if (!isValidInput(inputNameEt, ValidationUtils::isValidName, R.string.input_name) ||
                    !isValidInput(
                        inputSurnameEt,
                        ValidationUtils::isValidName,
                        R.string.input_surname
                    ) ||
                    !isValidInput(
                        inputBirthDayEt,
                        ValidationUtils::isValidBirthday,
                        R.string.input_birth
                    )
                ) {
                    return@setOnClickListener
                }


                preferenceManager.updateStepData(FILL_CAR_DATA)
            }

            with(inputBirthEt) {
                keyListener = null
                setOnClickListener {
                    DatePickerUtils.showDatePickerDialog(requireActivity()) {
                        viewBinding.inputBirthDayEt.error = null
                        setText(it)
                    }
                }
            }

            setupGenderSelectButtons()
        }
    }


    private fun FragmentFillPersonBinding.setupGenderSelectButtons() {
        selectManButton.setOnClickListener { setGender(true) }
        selectWomenButton.setOnClickListener { setGender(false) }
        selectManRadio.setOnClickListener { setGender(true) }
        selectWomenRadio.setOnClickListener { setGender(false) }
    }

    private fun FragmentFillPersonBinding.setGender(isMale: Boolean) {
        selectManRadio.isChecked = isMale
        selectWomenRadio.isChecked = !isMale
    }

    private fun isValidInput(
        inputLayout: TextInputLayout,
        validationFunction: (String) -> Boolean,
        errorMessageId: Int
    ): Boolean {
        val input = inputLayout.editText?.text.toString()
        if (!validationFunction(input)) {
            inputLayout.error = getString(errorMessageId)
            return false
        }
        inputLayout.error = null
        return true
    }

}