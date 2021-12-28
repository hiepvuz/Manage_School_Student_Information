package com.example.ass5_room.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.databinding.DialogUpdateSchoolBinding
import com.example.ass5_room.viewmodel.SchoolViewModel

class UpdateSchoolDialog(private val school: School?) : DialogFragment() {
    private var binding: DialogUpdateSchoolBinding? = null

    private val viewModel: SchoolViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogUpdateSchoolBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding!!.etSchoolName.setText(school?.schoolName)
        binding!!.etSchoolAddress.setText(school?.schoolAddress)

        binding!!.btAction.setOnClickListener {
            school?.apply {
                schoolName = binding!!.etSchoolName.text.toString()
                schoolAddress = binding!!.etSchoolAddress.text.toString()
                viewModel.updateSchool(this)
            }
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}