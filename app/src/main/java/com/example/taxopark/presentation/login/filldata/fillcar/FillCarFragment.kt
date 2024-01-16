package com.example.taxopark.presentation.login.filldata.fillcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.taxopark.databinding.FragmentFillCarBinding
import com.example.taxopark.domain.model.color.ColorData
import com.example.taxopark.domain.preference.UserPreferenceManager
import com.example.taxopark.utils.constant.StepConst
import com.example.taxopark.utils.resource.Resource
import com.example.taxopark.utils.resource.ResourceState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FillCarFragment : Fragment() {

    lateinit var viewBinding: FragmentFillCarBinding
    private val preferenceManager: UserPreferenceManager by inject()
    private val fillCarViewModel: FillCarViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentFillCarBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillCarViewModel.getAllCar()
        fillCarViewModel.getAllColor()
        viewBinding.nextDetailCarFbn.setOnClickListener {
            preferenceManager.updateStepData(StepConst.FILL_LICENSE_DATA)
        }

        viewBinding.fbnBack.setOnClickListener {
            preferenceManager.updateStepData(-1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fillCarViewModel.colorResponse.observe(this, ::updateCarColorUi)

    }


    fun updateCarColorUi(resource: Resource<List<ColorData>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.SUCCESS -> {
                    updateAutoColorCompleteUI(it.data)

                }

                ResourceState.LOADING -> {}
                ResourceState.ERROR -> {}
            }
        }
    }

    fun updateAutoColorCompleteUI(data: List<ColorData>?) {
        val colorNameList = data?.map { it.name }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            colorNameList ?: emptyList()
        )

        viewBinding.autoCarColor.apply {
            setAdapter(adapter)
            setOnItemClickListener { parent, _, position, _ ->
                val selectedColorName = parent.getItemAtPosition(position) as? String
//                val selectedData = selectedColorName?.let { colorNameToDataMap?.get(it) }
//                idSetter(selectedData?.id?.toInt())
            }
            threshold = 1
        }
    }

}