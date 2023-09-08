package com.example.doadzikirapp.presentation.pagipetang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doadzikirapp.adapter.DoaDzikirAdapter
import com.example.doadzikirapp.R
import com.example.doadzikirapp.databinding.ActivityPagiBinding
import com.example.doadzikirapp.model.DataDoaDzikir

class PagiActivity : AppCompatActivity() {
    private var _binding : ActivityPagiBinding? = null
    private val binding get() = _binding as ActivityPagiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change title text in action bar
        title = resources.getString(R.string.dzikir_pagi)
        // show navigation button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = ActivityPagiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDzikirPagi.apply {
            val mAdapter = DoaDzikirAdapter()
            mAdapter.setData(DataDoaDzikir.listDzikirPagi())
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