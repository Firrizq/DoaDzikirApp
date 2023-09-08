package com.example.doadzikirapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.example.doadzikirapp.adapter.ArticleAdapter
import com.example.doadzikirapp.databinding.ActivityMainBinding
import com.example.doadzikirapp.model.ArticleItem
import com.example.doadzikirapp.presentation.ArticleDetailActivity
import com.example.doadzikirapp.presentation.DzikirHarianActivity
import com.example.doadzikirapp.presentation.DzikirSetiapSaatActivity
import com.example.doadzikirapp.presentation.pagipetang.PagiPetangActivity
import com.example.doadzikirapp.presentation.QauliyahShalatActivity
import com.example.doadzikirapp.utils.OnItemCallback
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var dotSliderIndicator = arrayOf<ImageView?>()

    // OnPageChangeCallBack is SubClass from ViewPager2 for
    // responding to changing state of the selected page
    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        // instance onPageSelected give you behavior to setting state of selected page
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            for (i in initData().indices) {
                dotSliderIndicator[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.inactive_dot)
                )
            }

            dotSliderIndicator[0]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        installSplashScreen()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cardQauliyahShalat = findViewById<MaterialCardView>(R.id.card_qauliyah)
        val cardDzikirSetiapSaat = findViewById<MaterialCardView>(R.id.card_dzikir)
        val cardDzikirHarian = findViewById<MaterialCardView>(R.id.card_dzikir_harian)
        val cardDzikirPagiPetang = findViewById<MaterialCardView>(R.id.card_dzikir_pagi_petang)

        // when cardview clicked, will be navigated to other page
        // intent is use dor navigate to other activity by clicking card view
        cardQauliyahShalat.setOnClickListener{
            //Intent(argument context, which activity you want to go
            val intent = Intent(this, QauliyahShalatActivity::class.java)
            startActivity(intent)
        }

        cardDzikirSetiapSaat.setOnClickListener(this)
        cardDzikirHarian.setOnClickListener(this)
        cardDzikirPagiPetang.setOnClickListener(this)

        setupViewPager()

        val mAdapter = ArticleAdapter()
        mAdapter.setData(initData())
        mAdapter.setOnItemClickCallback(object : OnItemCallback {
            override fun onItemClicked(item: ArticleItem) {
                val intent = Intent(this@MainActivity, ArticleDetailActivity::class.java)
                //navigate to DetailActivity with datas using putExtra
                intent.putExtra("title", item.titleArticle)
                intent.putExtra("content", item.contentArticle)
                intent.putExtra("image", item.imageArticle)
                startActivity(intent)
            }
        })

        binding.vpArticle.adapter = mAdapter
        binding.vpArticle.registerOnPageChangeCallback(slidingCallback)
    }

    private fun setupViewPager() {
        dotSliderIndicator = arrayOfNulls(initData().size)

        for (i in initData().indices) {
            dotSliderIndicator[i] = ImageView(this)
            dotSliderIndicator[i]?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.inactive_dot)
            )

            val params =  LinearLayoutCompat.LayoutParams(35, 35)
            params.marginStart = 8
            params.marginEnd = 8
            binding.dots.addView(dotSliderIndicator[i], params)
        }
    }

    private fun initData() : List<ArticleItem> {
        val titleArticle = resources.getStringArray(R.array.arr_title_artikel)
        val contentArticle = resources.getStringArray(R.array.arr_desc_artikel)
        val imageArticle = resources.obtainTypedArray(R.array.arr_img_artikel)

        val listData = arrayListOf<ArticleItem>()
        for (i in titleArticle.indices){
            val data = ArticleItem(
                titleArticle[i],
                imageArticle.getResourceId(i, 0),
                contentArticle[i]
            )
            listData.add(data)
        }
        imageArticle.recycle()
        return listData
    }

    override fun onClick(view: View?) {
       when(view?.id) {
           R.id.card_dzikir -> startActivity(Intent(this, DzikirSetiapSaatActivity::class.java))
           R.id.card_dzikir_harian -> startActivity(Intent(this, DzikirHarianActivity::class.java))
           R.id.card_dzikir_pagi_petang -> startActivity(Intent(this, PagiPetangActivity::class.java))
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}