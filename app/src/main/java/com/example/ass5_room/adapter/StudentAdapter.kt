package com.example.ass5_room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ass5_room.R
import com.example.ass5_room.database.entity.Student


class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
    private var students = ArrayList<Student>()
    private var mListener: ((Student) -> Unit) ?= null
    inner class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var studentName: TextView?= null
        var studentSemester: TextView?= null

        var deleteStudent: ImageView?= null

        init {
            studentName = itemView.findViewById(R.id.tv_student_name)
            studentSemester = itemView.findViewById(R.id.tv_student_semester)

            deleteStudent = itemView.findViewById(R.id.btn_delete_student)
        }

        fun bind(student: Student){
            studentName!!.text = student.studentName
            studentSemester!!.text = student.semester.toString()

            deleteStudent!!.setOnClickListener {
                mListener?.invoke(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val studentView = inflater.inflate(R.layout.layout_student_item, parent, false)
        return StudentViewHolder(studentView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount() = students.size

    fun setData(newList: ArrayList<Student>){
        val diffUtil = ItemStudentDiffUtils(students, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        students.clear()
        students.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnDeleteStudent(listener: (Student) -> Unit){
        mListener = listener
    }
}