package com.example.ass5_room.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.entity.Student
import com.example.ass5_room.database.relation.SchoolWithStudent
import com.example.ass5_room.database.repository.SchoolRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class SchoolViewModel(app: Application) : AndroidViewModel(app) {

    private val schoolRepository: SchoolRepository = SchoolRepository(app)


    fun insertSchool(school: School) {
        schoolRepository.insertSchool(school).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {}
            .subscribe()
    }

    fun updateSchool(school: School){
        schoolRepository.updateSchool(school).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {}
            .subscribe()
    }

    fun deleteSchool(school: School){
        schoolRepository.deleteSchool(school).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {}
            .subscribe()
    }

    fun deleteStudent(student: Student) {
        schoolRepository.deleteStudent(student).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {}
            .subscribe()
    }

    fun insertStudent(student: Student) {
        schoolRepository.insertStudent(student).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {}
            .subscribe()
    }

    fun getAllSchool(): LiveData<List<School>> = schoolRepository.getAllSchool()

    fun getAllSchoolsWithStudent(): LiveData<List<SchoolWithStudent>> =
        schoolRepository.getAllSchoolsWithStudents()


}
