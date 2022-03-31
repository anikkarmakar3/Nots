package com.lock.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lock.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), inoteadapter {

    lateinit var viewModel: Noteviewmodel
    /*lateinit var binding: ActivityMainBinding*/
    lateinit var recyclerView: RecyclerView
    lateinit var inputdata:EditText
    lateinit var inserdata:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.recylerView.layoutManager=LinearLayoutManager(this)*/
        recyclerView=findViewById(R.id.recyclerView)

        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=Notesrvadapter(this,this)
        recyclerView.adapter=adapter
        /*recylerview.layoutManager=LinearLayoutManager(this)
        val adapter=Notesrvadapter(this,this)
        recylerview.adapter=adapter*/

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(Noteviewmodel::class.java)
        viewModel.allnotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })

    }

    fun insertnote(view: View) {
        inputdata = findViewById(R.id.input)
        /*inputdata.findViewById<EditText>(R.id.input)*/
        val fetchdata=inputdata.text.toString()
        if (fetchdata.isNotEmpty()){
            Toast.makeText(this,"Data Is Insert",Toast.LENGTH_LONG).show()
            viewModel.insertnote(Note(fetchdata))
            Toast.makeText(this,"Data insert Success",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"You Have To Fill Data First",Toast.LENGTH_LONG).show()
        }
    }
    override fun onitemclick(note: Note) {
        viewModel.deletenote(note)
        Toast.makeText(this,"Data Delete Success",Toast.LENGTH_SHORT).show()
    }
}