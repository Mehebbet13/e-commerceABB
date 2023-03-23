package com.example.e_commerceabb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceabb.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment?
                ?: return
        val navController = host.navController
        window.statusBarColor = applicationContext.getColor(R.color.transparent)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            changeVisibilityOfBottomMenu(destination.id)
        }
    }

    private fun changeVisibilityOfBottomMenu(destinationId: Int) {
        when (destinationId) {
            R.id.onboardingFragment, R.id.aboutAppFragment, R.id.welcomeFragment ->
                binding.bottomNav.isVisible = false
            else -> binding.bottomNav.isVisible = true
        }
    }
}

