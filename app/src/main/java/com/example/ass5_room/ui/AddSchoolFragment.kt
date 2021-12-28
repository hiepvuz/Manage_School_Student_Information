package com.example.ass5_room.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.databinding.FragmentAddSchoolBinding
import com.example.ass5_room.viewmodel.SchoolViewModel

class AddSchoolFragment : Fragment() {

    private var binding: FragmentAddSchoolBinding? = null
    private val schoolViewModel: SchoolViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddSchoolBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding!!.btnAdd.setOnClickListener {
            if (!(binding!!.etSchoolAddress.text.isNullOrEmpty()) || !(binding!!.etSchoolName.text.isNullOrEmpty())) {
                var newSchoolId = 0
                schoolViewModel.getAllSchool().observe(this, {
                    newSchoolId = it.size + 1
                })

                val newSchool = School(
                    newSchoolId,
                    binding!!.etSchoolName.text.toString(),
                    binding!!.etSchoolAddress.text.toString()
                )
                schoolViewModel.insertSchool(newSchool)
                val action = AddSchoolFragmentDirections.actionAddSchoolFragmentToListFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Please fill all information", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}