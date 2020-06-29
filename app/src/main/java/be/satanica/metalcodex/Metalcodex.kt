package be.satanica.metalcodex

import android.app.Application
import androidx.preference.PreferenceManager
import be.satanica.metalcodex.common.helpers.ThemeHelper
import timber.log.Timber

class Metalcodex : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        ThemeHelper.setDarkMode(
            PreferenceManager
                .getDefaultSharedPreferences(applicationContext)
                .getBoolean(getString(R.string.key_theme), false)
        )
    }
}
