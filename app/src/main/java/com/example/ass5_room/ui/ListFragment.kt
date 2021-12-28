package com.example.ass5_room.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ass5_room.adapter.SchoolAdapter
import com.example.ass5_room.adapter.StudentAdapter
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.entity.Student
import com.example.ass5_room.database.relation.SchoolWithStudent
import com.example.ass5_room.databinding.FragmentListSchoolBinding
import com.example.ass5_room.viewmodel.SchoolViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListSchoolBinding
    private val viewModel: SchoolViewModel by activityViewModels()

    private lateinit var schoolAdapter: SchoolAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListSchoolBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRcSchools()
        registerObserver()
        initAction()

    }


    private fun initRcSchools() {
        schoolAdapter = SchoolAdapter()
        binding.rvSchools.adapter = schoolAdapter
        binding.rvSchools.layoutManager = LinearLayoutManager(context)
    }

    private fun registerObserver() {
        viewModel.getAllSchoolsWithStudent().observe(requireActivity(), Observer {
            val list = it ?: return@Observer
            schoolAdapter.setData(list as ArrayList<SchoolWithStudent>)
        })
    }

    private fun initAction() {

        binding.btnAddSchool.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddSchoolFragment()
            findNavController().navigate(action)
        }

        schoolAdapter.setOnClickDeleteSchool { schoolAndStudent ->
            viewModel.deleteSchool(schoolAndStudent.school)
        }

        schoolAdapter.setOnClickEditSchool { schoolWithStudent ->
            UpdateSchoolDialog(schoolWithStudent.school).show(activity!!.supportFragmentManager, null)
        }

        binding.btnAddStudent.setOnClickListener {
            DialogInputStudent().show(childFragmentManager, null)
        }

        schoolAdapter.setOnClickDeleteStudent { student ->
            viewModel.deleteStudent(student)
        }

    }

}


