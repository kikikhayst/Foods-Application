package com.rizqirama.uasmobiledua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rizqirama.uasmobiledua.model.Food
import com.rizqirama.uasmobiledua.ui.fragment.FoodFragment

class MainActivity : AppCompatActivity() {
    private lateinit var menu : Menu
    private lateinit var menuItem : MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()
        callFragment(0, FoodFragment())
    }

    private fun setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item->
            when(item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0,FoodFragment())
                }
            }
            false
        }
    }

    private fun callFragment(int: Int, fragment: Fragment) {
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        callFragment(fragment)
    }

    private fun callFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}