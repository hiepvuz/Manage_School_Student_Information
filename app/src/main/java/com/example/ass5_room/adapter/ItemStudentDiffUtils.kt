package com.example.ass5_room.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.ass5_room.database.entity.Student

class ItemStudentDiffUtils(
    private val oldStudents: ArrayList<Student>,
    private val newStudents: ArrayList<Student>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldStudents.size

    override fun getNewListSize() = newStudents.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldStudents[oldItemPosition] == newStudents[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (studentName, studentGrade, schoolName) = oldStudents[oldItemPosition]
        val (studentName1, studentGrade1, schoolName1) = newStudents[newItemPosition]
        return studentName == studentName1 &&
                studentGrade == studentGrade1 &&
                schoolName == schoolName1
    }

}