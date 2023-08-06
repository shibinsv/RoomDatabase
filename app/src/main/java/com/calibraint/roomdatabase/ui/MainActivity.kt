package com.calibraint.roomdatabase.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.calibraint.roomdatabase.databinding.ActivityMainBinding
import com.calibraint.roomdatabase.room.AppDatabase
import com.calibraint.roomdatabase.viewmodels.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private var database: AppDatabase? = null
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        database = AppDatabase.database
        setContentView(binding.root)
        scope.launch {
            getData()
        }

    }

    private fun initView() {
        adapter = UserAdapter(viewModel.userData.value)
        binding.rvUserList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.userData.observe(this) {
            scope.launch {
                database?.let { appDataBase ->
                    viewModel.updateData(appDataBase, onCompletion = {
                        getData()
                    })
                }
            }
            initView()
        }
        scope.launch {
            viewModel.userData(onError = { error ->
                mainScope.launch {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun getData() {
        val data = database?.userDao()?.getAll()
        Log.e("MainActivity", "dataInDatabase: $data", )
    }
}