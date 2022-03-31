package com.lock.notes

import androidx.lifecycle.LiveData

class Noterepository(private val notedao:Notedao) {
    val allnotes: LiveData<List<Note>> = notedao.getallnotes()

    suspend fun insert(note: Note){
        notedao.insert(note)
    }
    suspend fun delete(note: Note){
        notedao.delete(note)
    }
}