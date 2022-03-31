package com.lock.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Notedao {

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from notes_data order by id ASC")
    fun getallnotes(): LiveData<List<Note>>

}