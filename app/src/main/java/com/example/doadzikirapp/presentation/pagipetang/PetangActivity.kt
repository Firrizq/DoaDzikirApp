package com.example.doadzikirapp.presentation.pagipetang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doadzikirapp.adapter.DoaDzikirAdapter
import com.example.doadzikirapp.R
import com.example.doadzikirapp.databinding.ActivityPetangBinding
import com.example.doadzikirapp.model.DataDoaDzikir

class PetangActivity : AppCompatActivity() {
    private var _binding : ActivityPetangBinding? = null
    private val binding get() = _binding as ActivityPetangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.dzikir_petang)
        // show navigation button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = ActivityPetangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDzikirPetang.apply {
            val mAdapter = DoaDzikirAdapter()
            mAdapter.setData(DataDoaDzikir.listDzikirPetang())
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}