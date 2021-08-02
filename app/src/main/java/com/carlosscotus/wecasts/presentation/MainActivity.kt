package com.carlosscotus.wecasts.presentation

import android.content.res.Configuration
import android.content.res.TypedArray
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.core.view.get
import com.carlosscotus.wecasts.databinding.ActivityMainBinding
import com.carlosscotus.wecasts.presentation.fragments.FavoritesFragment
import com.carlosscotus.wecasts.presentation.adapters.FragmentAdapter
import com.carlosscotus.wecasts.presentation.fragments.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import com.carlosscotus.wecasts.R
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        with(binding.content.viewPager) {
            offscreenPageLimit = 2
            isUserInputEnabled = false
            adapter = FragmentAdapter(
                this@MainActivity,
                listOf(
                    HomeFragment.newInstance(),
                    FavoritesFragment.newInstance()
                )
            )
        }
    }

    private fun setupTabLayout() {
        val titles = arrayOf("Para você", "Meus Podcasts")
        TabLayoutMediator(binding.tabs, binding.content.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_search)?.actionView as androidx.appcompat.widget.SearchView?)
            ?.let {
                it.maxWidth = Int.MAX_VALUE
            }
        return true
    }
}