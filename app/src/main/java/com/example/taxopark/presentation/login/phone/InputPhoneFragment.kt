package com.example.taxopark.presentation.login.phone

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taxopark.R
import com.example.taxopark.databinding.FragmentInputPhoneBinding
import com.example.taxopark.domain.model.register.RegisterData
import com.example.taxopark.domain.model.register.RegisterRequest
import com.example.taxopark.domain.preference.UserPreferenceManager
import com.example.taxopark.utils.dialog.DialogUtils
import com.example.taxopark.utils.input.createPhoneNumberPlateEditText
import com.example.taxopark.utils.resource.Resource
import com.example.taxopark.utils.resource.ResourceState
import com.example.taxopark.utils.valid.ValidationUtils
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class InputPhoneFragment : Fragment() {


    private lateinit var viewBinding: FragmentInputPhoneBinding
    private val viewModel: InputPhoneViewModel by viewModel()
    private lateinit var loadingDialog: Dialog
    private var number = ""
    private val preferenceManager: UserPreferenceManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentInputPhoneBinding.inflate(inflater, container, false)
        loadingDialog = DialogUtils.loadingDialog(requireContext())
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createPhoneNumberPlateEditText(viewBinding.edtInputPhone, viewBinding.edtPhoneNumber)

        viewModel.registerResponse.observe(viewLifecycleOwner) {
            updateUi(it)
        }
        viewBinding.buttonContinue.setOnClickListener {
            if (!validateInputs()) return@setOnClickListener
            number = viewBinding.edtPhoneNumber.editText?.text.toString()
            val phone = "${viewBinding.edtPhoneNumber.prefixText}${number.replace(" ", "")}"
            viewModel.register(RegisterRequest(phone))
        }
    }

    private fun updateUi(resource: Resource<RegisterData>?) {
        resource?.let {
            when (it.state) {
                ResourceState.ERROR -> {
                    loadingDialog.dismiss()
                }

                ResourceState.LOADING -> {
                    loadingDialog.show()
                }

                ResourceState.SUCCESS -> {
                    loadingDialog.dismiss()
                    processSuccessState(number, it.data)
                }
            }
        }
    }


    private fun processSuccessState(number: String, data: RegisterData?) {

        preferenceManager.apply {
            data?.otp?.let { saveToken(it) }
            savePhone(number)
        }
        viewModel.clear()
        viewBinding.edtInputPhone.text?.clear()
        findNavController().navigate(R.id.confirmCodeFragment)

    }

    private fun validateInputs(): Boolean {
        with(viewBinding) {
            if (!isValidInput(
                    edtPhoneNumber,
                    ValidationUtils::isValidPhoneNumber,
                    getString(R.string.input_phone_number)
                )
            ) return false
        }
        return true
    }

    private fun isValidInput(
        input: TextInputLayout,
        validationFunction: (String) -> Boolean,
        errorMessage: String
    ): Boolean {
        if (!validationFunction(input.editText?.text.toString())) {
            input.error = errorMessage
            return false
        }
        input.error = null
        return true
    }
}