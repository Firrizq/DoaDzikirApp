package com.example.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doadzikirapp.adapter.DoaDzikirAdapter
import com.example.doadzikirapp.model.DataDoaDzikir.listQauliyah
import com.example.doadzikirapp.databinding.ActivityQauliyahShalatBinding

class QauliyahShalatActivity : AppCompatActivity() {
    // Implement viewBinding feature
    // viewBinding is a feature that makes it easier to write code that interacts with view
    // viewBinding use to replace findViewById
    private var _binding: ActivityQauliyahShalatBinding? = null
    private val binding get() = _binding as ActivityQauliyahShalatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // show navigation button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // initialize property of binding with layoutInflatter to get the layout
        _binding = ActivityQauliyahShalatBinding.inflate(layoutInflater)
        // replace argument of setContentView using viewBinding
        // this is for connect the view using view binding
        setContentView(binding.root)

        // set recycler view
        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(listQauliyah)
        binding.rvQauliyahShalat.adapter = mAdapter
        // layoutManager is a class to set our structure of display the dataset
        binding.rvQauliyahShalat.layoutManager = LinearLayoutManager(this)
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