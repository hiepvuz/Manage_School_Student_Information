package com.example.ass5_room.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.ass5_room.R
import com.example.ass5_room.database.entity.Student
import com.example.ass5_room.database.relation.SchoolWithStudent


class SchoolAdapter :
    RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    private var schoolsWithStudent = arrayListOf<SchoolWithStudent>()

    private var mListenerDeleteStudent: ((Student) -> Unit)? = null
    private var mListenerUpdateStudent: ((Student) -> Unit)? = null
    private var mListenerDeleteSchool: ((SchoolWithStudent) -> Unit)? = null
    private var mListenerUpdateSchool: ((SchoolWithStudent) -> Unit)? = null

    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val schoolView: View = inflater.inflate(R.layout.layout_school_item, parent, false)
        return SchoolViewHolder(schoolView)
    }

    override fun onBindViewHolder(holderSchool: SchoolViewHolder, position: Int) {
        holderSchool.bind(schoolsWithStudent[position])
    }

    override fun onBindViewHolder(
        holder: SchoolViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            payloads.forEach {
                if (it == true) {
                    holder.studentAdapter.setData(schoolsWithStudent[position].students as ArrayList<Student>)
                }
            }
        } else super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount() = schoolsWithStudent.size

    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvSchoolName: TextView? = null
        private var tvSchoolAddress: TextView? = null
        private var btnDeleteSchool: ImageView? = null
        private var btnEditSchool: ImageView? = null

        private var rvStudent: RecyclerView? = null
        private var btnExpand: ImageView? = null

        val studentAdapter = StudentAdapter()
        private var isShow: Boolean? = null

        init {
            tvSchoolName = itemView.findViewById(R.id.tv_school_name)
            tvSchoolAddress = itemView.findViewById(R.id.tv_school_address)

            btnDeleteSchool = itemView.findViewById(R.id.btn_delete_school)
            btnEditSchool = itemView.findViewById(R.id.btn_edit_school)
            btnExpand = itemView.findViewById(R.id.btn_expand_more)

            rvStudent = itemView.findViewById(R.id.rv_students)
        }

        fun bind(schoolWithStudents: SchoolWithStudent) {
            val school = schoolWithStudents.school
            val students = schoolWithStudents.students

            tvSchoolName!!.text = school.schoolName
            tvSchoolAddress!!.text = school.schoolAddress

            actionShow(students)
            actionDeleteSchool(schoolWithStudents)
            actionEditSchool(schoolWithStudents)
            actionDeleteStudent()

        }


        private fun actionShow(students: List<Student>) {
            btnExpand?.setOnClickListener {
                when (isShow) {
                    null -> {
                        studentAdapter.setData(students as ArrayList<Student>)
                        rvStudent!!.layoutManager = LinearLayoutManager(context)
                        rvStudent!!.adapter = studentAdapter
                        isShow = true
                    }
                    false -> {
                        isShow = true
                        rvStudent?.isVisible = true
                    }
                    else -> {
                        isShow = false
                        rvStudent?.isVisible = false
                    }
                }
            }
        }

        private fun actionDeleteStudent() {
            studentAdapter.setOnDeleteStudent { student ->
                mListenerDeleteStudent?.invoke(student)
            }
        }

        private fun actionEditSchool(schoolWithStudent : SchoolWithStudent){
            btnEditSchool?.setOnClickListener{
                mListenerUpdateSchool?.invoke(schoolWithStudent)
            }
        }

        private fun actionDeleteSchool(schoolWithStudent: SchoolWithStudent) {
            btnDeleteSchool?.setOnClickListener {
                mListenerDeleteSchool?.invoke(schoolWithStudent)
            }
        }

        }


    fun setData(newList: ArrayList<SchoolWithStudent>) {
        val schoolDiffUtilCallBack = ItemSchoolDiffUtils(schoolsWithStudent, newList)
        val diffResult = DiffUtil.calculateDiff(schoolDiffUtilCallBack)
        schoolsWithStudent = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnClickEditSchool (listener: (SchoolWithStudent) -> Unit){
        mListenerUpdateSchool = listener

    }
    fun setOnClickDeleteSchool(listener: ((SchoolWithStudent) -> Unit)) {
        mListenerDeleteSchool = listener
    }

    fun setOnClickDeleteStudent(listener: ((Student) -> Unit)) {
        mListenerDeleteStudent = listener
    }

}
