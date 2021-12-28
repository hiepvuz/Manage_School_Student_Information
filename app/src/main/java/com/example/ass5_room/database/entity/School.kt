package com.example.ass5_room.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ass5_room.utils.Constants.COLUMN_SCHOOL_ADDRESS
import com.example.ass5_room.utils.Constants.COLUMN_SCHOOL_ID
import com.example.ass5_room.utils.Constants.COLUMN_SCHOOL_NAME
import com.example.ass5_room.utils.Constants.SCHOOL_TABLE
import java.io.Serializable

@Entity(
    tableName = SCHOOL_TABLE
)
data class School(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_SCHOOL_ID)
    val schoolId: Int,
    @ColumnInfo(name = COLUMN_SCHOOL_NAME)
    var schoolName: String,
    @ColumnInfo(name = COLUMN_SCHOOL_ADDRESS)
    var schoolAddress: String
) : Serializable