package com.itis.group11801.fedotova.smartfasting.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.navigator.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.injectMainActivity(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        navigator.attachNavController(navController, R.navigation.mobile_navigation)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news,
                R.id.navigation_drink_tracker,
                R.id.navigation_tracker,
                R.id.navigation_statistics
            )
        )
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        bottom_nav.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            menu?.findItem(R.id.navigation_settings)?.isVisible =
                destination.id == R.id.navigation_drink_tracker || destination.id == R.id.navigation_choose_dialog
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.navigate(R.id.action_navigation_drink_tracker_to_navigation_settings)
        return super.onOptionsItemSelected(item)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.getStringExtra(INTENT_EXTRA) == OPEN_TRACKER) navController.navigate(R.id.navigation_tracker)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachNavController(navController)
    }

    companion object {
        const val INTENT_EXTRA = "smartfasting.intent_extra"
        const val OPEN_TRACKER = "smartfasting.open_tracker"
    }
}
