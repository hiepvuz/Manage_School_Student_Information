package com.example.ass5_room.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.relation.SchoolWithStudent

class ItemSchoolDiffUtils(
    private val oldList: ArrayList<SchoolWithStudent>,
    private val newList: ArrayList<SchoolWithStudent>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].school == newList[newItemPosition].school
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (schoolName, schoolAddress) = oldList[oldItemPosition].school
        val (schoolName1, schoolAddress1) = newList[newItemPosition].school
        val students = oldList[oldItemPosition].students
        val students1 = newList[newItemPosition].students


        Log.d("TAG", "$schoolName $schoolName1 ${students.size} == ${students1.size}")
        return schoolName == schoolName1 && schoolAddress == schoolAddress1
                && oldList[oldItemPosition].students.size == newList[newItemPosition].students.size
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        if (oldList[oldItemPosition].students.size != newList[newItemPosition].students.size) {
            Log.d(
                "TAG1", "${oldList[oldItemPosition].students.size} " +
                        "== ${newList[newItemPosition].students.size}"
            )
            return true
        }
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}