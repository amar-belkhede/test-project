package com.example.proto_datastore_stevdaza_san

import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proto_datastore_stevdaza_san.databinding.ActivityMainBinding
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Person>  by dataStore(
    "my_data",
    MySerializer(),
)

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.inputText.doAfterTextChanged {
            mainViewModel.updateValue(binding.inputText.text.toString())
        }

        mainViewModel.firstName.observe(this) {
            Log.d("error","firstname:" +it.firstName )
            binding.showText.text = it.firstName
        }

//        binding.sendFirstName.setOnClickListener {
//            Log.d("error", binding.inputText.text.toString())
////            mainViewModel.updateValue(binding.inputText.text.toString())
//        }

        binding.sendFirstName.setOnClickListener(View.OnClickListener {
            Log.d("error", binding.inputText.text.toString())
        })

        lifecycleScope.launch {
            while (true) {
                delay(2000)
                mainViewModel.updateValue("amar " + Calendar.getInstance().time)
            }
        }


    }

}