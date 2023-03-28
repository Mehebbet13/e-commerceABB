package com.example.e_commerceabb

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceabb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment?
                ?: return
        navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            changeVisibilityOfBottomMenu(destination.id)
            when (destination.id) {
                R.id.homeFragment -> {
                    window?.navigationBarColor = ContextCompat.getColor(this, R.color.main)
                }
                R.id.fillProfileFragment -> {
                    handleAppBarsColor(R.color.main)
                }
                else -> {
                    handleAppBarsColor(R.color.transparent)
                }
            }
        }
//        binding.bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_home -> {
//                    findNavController(it.itemId).navigate(R.id.homeFragment)
//                    true
//                }
//                R.id.nav_profile -> {
//                    findNavController(it.itemId).navigate(R.id.fillProfileFragment)
//                    true
//                }
//                else -> it.onNavDestinationSelected(navController) || super.onOptionsItemSelected(it)
//            }
//        }
    }

    private fun handleAppBarsColor(color: Int) {
        window.navigationBarColor = applicationContext.getColor(color)
        window.statusBarColor = applicationContext.getColor(R.color.transparent)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun changeVisibilityOfBottomMenu(destinationId: Int) {
        when (destinationId) {
            R.id.homeFragment ->
                binding.bottomNav.isVisible = true
            R.id.fillProfileFragment ->
                binding.bottomNav.isVisible = true
            R.id.ordersFragment ->
                binding.bottomNav.isVisible = true
            else -> binding.bottomNav.isVisible = false
        }
    }
}
