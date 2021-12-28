package com.example.ass5_room.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.entity.Student
import com.example.ass5_room.utils.Constants.SCHOOL_TABLE
import com.example.ass5_room.database.relation.SchoolWithStudent
import com.example.ass5_room.utils.Constants
import io.reactivex.rxjava3.core.Completable


@Dao
interface SchoolDao {

    @Insert
    fun insertSchool(school: School): Completable

    @Update
    fun updateSchool(school: School): Completable

    @Update
    fun updateSchools(vararg schools: School): Completable

    @Delete
    fun deleteSchool(school: School): Completable

    @Query("SELECT * FROM $SCHOOL_TABLE")
    fun getAllSchools(): LiveData<List<School>>

    @Query("SELECT * FROM $SCHOOL_TABLE")
    fun getAllSchoolsWithStudents(): LiveData<List<SchoolWithStudent>>

    @Insert
    fun insertStudent(student: Student): Completable

    @Update
    fun updateStudent(student: Student): Completable

    @Delete
    fun deleteStudent(student: Student): Completable

    @Query("SELECT * FROM ${Constants.STUDENT_TABLE}")
    fun getStudentList(): LiveData<List<Student>>

}