package com.example.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doadzikirapp.adapter.DoaDzikirAdapter
import com.example.doadzikirapp.R
import com.example.doadzikirapp.databinding.ActivityDzikirHarianBinding
import com.example.doadzikirapp.model.DoaDzikirItem

class DzikirHarianActivity : AppCompatActivity() {
    private var _binding: ActivityDzikirHarianBinding? = null
    private val binding get() = _binding as ActivityDzikirHarianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // show navigation button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = ActivityDzikirHarianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        providingDzikirDatas()
        initView()
    }
    private fun initView() {
        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(providingDzikirDatas())
        binding.rvDzikirDoaHarian.adapter = mAdapter
        binding.rvDzikirDoaHarian.layoutManager = LinearLayoutManager(this@DzikirHarianActivity)
    }

    private fun providingDzikirDatas() :List<DoaDzikirItem> {
        val titleDzikir = resources.getStringArray(R.array.arr_dzikir_doa_harian)
        val arabicDzikir = resources.getStringArray(R.array.arr_lafaz_dzikir_doa_harian)
        val translateDzikir = resources.getStringArray(R.array.arr_terjemah_dzikir_doa_harian)

        val listData = arrayListOf<DoaDzikirItem>()
        for (i in titleDzikir.indices) {
            val dzikir = DoaDzikirItem(
                titleDzikir[i],
                arabicDzikir[i],
                translateDzikir[i]
            )
            listData.add(dzikir)
        }
        return listData
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