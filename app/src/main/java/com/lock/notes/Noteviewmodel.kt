package com.lock.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Noteviewmodel(application: Application) :AndroidViewModel(application) {
     val allnotes: LiveData<List<Note>>
    val repositroy: Noterepository
    init {
        val dao=Notedatabase.getDatabase(application).noteDao()
        repositroy = Noterepository(dao)
        allnotes = repositroy.allnotes
    }
    fun deletenote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repositroy.delete(note)
    }
    fun insertnote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repositroy.insert(note)
    }

}