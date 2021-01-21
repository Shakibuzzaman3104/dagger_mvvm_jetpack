package com.example.dagger_first.ui.main


import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.dagger_first.BaseActivity
import com.example.dagger_first.R
import com.example.dagger_first.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        initNavigationController()
    }


    /*  private fun navigateToProfileFragment() {
          supportFragmentManager.beginTransaction()
              .replace(R.id.main_container, PostsFragment()).commit()

      }*/

    fun initNavigationController() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            activityMainBinding.drawerLayout
        )
        NavigationUI.setupWithNavController(activityMainBinding.navView, navController)
        activityMainBinding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                return true
            }
            android.R.id.home -> {
                if (activityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    return true
                } else
                    return false
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileScreen, null, navOptions)
            }
            R.id.nav_posts -> {
                if (isValidDestination(R.id.postsScreen))
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postsScreen)
            }
        }
        item.isChecked = true
        activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return false
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination != Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        ).currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            activityMainBinding.drawerLayout
        )
    }
}