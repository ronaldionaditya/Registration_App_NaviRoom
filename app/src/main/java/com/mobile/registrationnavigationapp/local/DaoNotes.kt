package com.mobile.registrationnavigationapp.local

import androidx.room.*

@Dao
interface DaoNotes {

    @Query("SELECT * FROM notes")
    fun getAll() : List<Notes>

    @Query("SELECT * FROM notes WHERE favorite = :filter")
    fun getFav(filter: Boolean) : List<Notes>

    @Insert
    fun insert(notes: Notes)

    @Update
    fun update(notes: Notes)

    @Delete
    fun delete(notes: Notes)
}