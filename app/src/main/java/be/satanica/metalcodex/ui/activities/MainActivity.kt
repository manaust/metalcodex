package be.satanica.metalcodex.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import be.satanica.metalcodex.R
import be.satanica.metalcodex.common.helpers.ThemeHelper
import be.satanica.metalcodex.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding: ActivityMainBinding
    private var sidebarWidth: Float = 150f
    private var sidebar = false

    private lateinit var controller: NavController
    private val listener = NavController.OnDestinationChangedListener { _, _, _ -> closeDrawer() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setTheme(R.style.AppTheme)
        setContentView(view)
        controller = findNavController(R.id.nav_host_fragment)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.toolbar.setNavigationOnClickListener {
            when (findNavController(R.id.nav_host_fragment).currentDestination?.id) {
                R.id.navigation_home -> toggleDrawer()
                else -> onBackPressed()
            }
        }

        binding.sidebar.doOnLayout {
            // Move sidebar offscreen
            sidebarWidth = it.width.toFloat()
            it.translationX = -sidebarWidth
        }

        binding.fragmentContainer.setOnClickListener { closeDrawer() }
    }

    override fun onResume() {
        super.onResume()
        controller.addOnDestinationChangedListener(listener)
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        controller.removeOnDestinationChangedListener(listener)
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == getString(R.string.key_theme)) {
            ThemeHelper.setDarkMode(sharedPreferences.getBoolean(key, false))
        }
    }

    private fun toggleDrawer() {
        if (sidebar) closeDrawer()
        else openDrawer()
    }

    private fun openDrawer() {
        if (!sidebar) {
            sidebar = true
            binding.sidebar.animate().setDuration(200).translationX(0f).start()
            binding.appContainer.animate().setDuration(200).translationX(sidebarWidth).start()
        }
    }

    fun closeDrawer() {
        if (sidebar) {
            sidebar = false
            binding.sidebar.animate().setDuration(200).translationX(-sidebarWidth).start()
            binding.appContainer.animate().setDuration(200).translationX(0f).start()
        }
    }
}
