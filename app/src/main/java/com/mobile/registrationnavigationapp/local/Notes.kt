package com.mobile.registrationnavigationapp.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "notes")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "note")
    var note: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null


)