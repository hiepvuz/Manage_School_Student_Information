package com.example.ass5_room.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.ass5_room.R
import com.example.ass5_room.database.entity.Student
import com.example.ass5_room.viewmodel.SchoolViewModel


class DialogInputStudent : DialogFragment() {
    private val viewModel: SchoolViewModel by activityViewModels()

    private lateinit var studentName: EditText
    private lateinit var studentSemester: EditText
    private lateinit var spinnerSchool: Spinner
    private lateinit var btnAddStudent: Button
    private lateinit var btnCancel: Button
    private var schoolsName = arrayListOf<String>()
    var adapter : ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_input_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentName = view.findViewById(R.id.et_student_name)
        studentSemester = view.findViewById(R.id.et_student_semester)
        spinnerSchool = view.findViewById(R.id.spinnerSchool)
        btnAddStudent = view.findViewById(R.id.btn_add_Student)
        btnCancel = view.findViewById(R.id.btn_cancel)

        adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, schoolsName)

        initAction()
        registerObserver()

    }

    private fun setSpinner() {
        adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter!!.notifyDataSetChanged()
        spinnerSchool.adapter = adapter
    }

    private fun initAction() {
        btnAddStudent.setOnClickListener {
            handleClickAdd()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun registerObserver() {
        viewModel.getAllSchoolsWithStudent().observe(requireActivity(), Observer {
            it.forEach {
                schoolsName.add(it.school.schoolName.trim())
                setSpinner()
            }
        })
    }

    private fun handleClickAdd() {
        if (!inputValid(studentName.text.toString())
            && !inputValid(studentSemester.text.toString())
            && !inputValid(spinnerSchool.selectedItem.toString())
        ) {
            viewModel.insertStudent(
                Student(
                    0,
                    studentName.text.toString(),
                    studentSemester.text.toString().toInt(),
                    spinnerSchool.selectedItemId.toInt() + 1
                )
            )
            dismiss()
        } else Toast.makeText(context, "Info invalid!!", Toast.LENGTH_SHORT).show()
    }

    private fun inputValid(input: String) = input.isEmpty()

}