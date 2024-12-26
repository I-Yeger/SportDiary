package com.bsuir.sportdiary.app.screens.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bsuir.sportdiary.R
import com.bsuir.sportdiary.databinding.ActivityApplicationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class   ApplicationActivity : AppCompatActivity() {

    private var _binding: ActivityApplicationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigationView)
        val navController = findNavController(R.id.fragment_app)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.sportListFragment,
            R.id.nutritionListFragment,
            R.id.logoutFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_app)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}