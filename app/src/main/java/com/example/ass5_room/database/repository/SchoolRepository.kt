package com.example.ass5_room.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.ass5_room.database.DatabaseManager
import com.example.ass5_room.database.dao.SchoolDao
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.entity.Student
import com.example.ass5_room.database.relation.SchoolWithStudent
import com.example.ass5_room.utils.StandardDispatcher
import io.reactivex.rxjava3.core.Completable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SchoolRepository(app: Application) {

    private val schoolDao: SchoolDao

    init {
        val dataBase: DatabaseManager = DatabaseManager.getDatabase(app)
        schoolDao = dataBase.schoolDao()
    }

     fun insertSchool(school: School) : Completable {
        return schoolDao.insertSchool(school)
    }

      fun updateSchool(school: School) : Completable {
        return schoolDao.updateSchool(school)
    }

      fun deleteSchool(school: School): Completable {
        return schoolDao.deleteSchool(school)
    }

      fun deleteStudent(student: Student) : Completable {
        return schoolDao.deleteStudent(student)
    }

      fun insertStudent(student: Student) : Completable {
        return schoolDao.insertStudent(student)
    }

     fun getAllSchool(): LiveData<List<School>> {
        return schoolDao.getAllSchools()
    }

    fun getAllSchoolsWithStudents(): LiveData<List<SchoolWithStudent>> {
        return schoolDao.getAllSchoolsWithStudents()
    }
}

//    private val schools = listOf(
//        School(0, "FPT","Hanoi"),
//        School(0, "Tran Hung Dao", "Hung Yen"),
//        School(0, "HUST", "VN")
//    )
//
//    private val students = listOf(
//        Student(0, "Tobey Marguire", 2, 1),
//        Student(0, "Mo Salah", 5, 2),
//        Student(0, "Virgil", 6, 2),
//        Student(0, "Tom Hà Lan", 1,3),
//        Student(0, "Klopp", 2, 3)
//    )
//
//    suspend fun addData() {
//        schools.forEach {
//            schoolDao.insertSchool(it)
//        }
//    }

// }