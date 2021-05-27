package com.unhas.note.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.unhas.note.R
import com.unhas.note.database.entity.NoteEntity
import com.unhas.note.databinding.ActivityMainBinding
import com.unhas.note.ui.ViewModelFactory
import com.unhas.note.ui.insertupdate.InsertUpdateActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val factory = ViewModelFactory.getInstance(application)
        val mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        mainViewModel.getAllNotes().observe(this,{ noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        })

        adapter = NoteAdapter(this@MainActivity)

        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@MainActivity, InsertUpdateActivity::class.java)
                startActivityForResult(intent, InsertUpdateActivity.REQUEST_ADD)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == InsertUpdateActivity.REQUEST_ADD) {
                if (resultCode == InsertUpdateActivity.RESULT_ADD) {
                    showSnackbarMessage(getString(R.string.added))
                }
            } else if (requestCode == InsertUpdateActivity.REQUEST_UPDATE) {
                if (resultCode == InsertUpdateActivity.RESULT_UPDATE) {
                    showSnackbarMessage(getString(R.string.changed))
                } else if (resultCode == InsertUpdateActivity.RESULT_DELETE) {
                    showSnackbarMessage(getString(R.string.deleted))
                }
            }
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding?.root as View, message, Snackbar.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }
}