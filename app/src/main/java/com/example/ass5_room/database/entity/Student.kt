package com.example.ass5_room.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ass5_room.utils.Constants.COLUMN_SCHOOL_ID
import com.example.ass5_room.utils.Constants.COLUMN_STUDENT_ID
import com.example.ass5_room.utils.Constants.COLUMN_STUDENT_NAME
import com.example.ass5_room.utils.Constants.COLUMN_STUDENT_SEMESTER
import com.example.ass5_room.utils.Constants.STUDENT_TABLE


@Entity(
    tableName = STUDENT_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = School::class,
            parentColumns = arrayOf(COLUMN_SCHOOL_ID),
            childColumns = arrayOf(COLUMN_SCHOOL_ID),
            onDelete = ForeignKey.SET_DEFAULT
        )
    ]
)

data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_STUDENT_ID)
    val studentId: Int = 0,
    @ColumnInfo(name = COLUMN_STUDENT_NAME)
    val studentName: String,
    @ColumnInfo(name = COLUMN_STUDENT_SEMESTER)
    val semester: Int,
    @ColumnInfo(name = COLUMN_SCHOOL_ID)
    val schoolId: Int

)