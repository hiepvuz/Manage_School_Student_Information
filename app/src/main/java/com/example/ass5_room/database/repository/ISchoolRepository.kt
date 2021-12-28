package com.example.ass5_room.database.repository

import androidx.lifecycle.LiveData
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.entity.Student

interface ISchoolRepository {
     fun insertSchool(school: School)
     fun updateSchool(school: School)
     fun deleteSchool(school: School)
     fun deleteStudent(student: Student)
     fun insertStudent(student: Student)

    fun getAllSchool() : LiveData<List<School>>
}
