package com.example.ass5_room.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.ass5_room.utils.Constants.COLUMN_SCHOOL_ID
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.entity.Student

data class SchoolWithStudent(
    @Embedded val school: School,
    @Relation(
        parentColumn = COLUMN_SCHOOL_ID,
        entityColumn = COLUMN_SCHOOL_ID
    )
    val students: List<Student>
)