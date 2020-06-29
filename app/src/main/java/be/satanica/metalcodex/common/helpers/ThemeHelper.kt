package be.satanica.metalcodex.common.helpers

import androidx.appcompat.app.AppCompatDelegate

class ThemeHelper {
    companion object {
        fun setDarkMode(darkmode: Boolean) {
            if (darkmode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
