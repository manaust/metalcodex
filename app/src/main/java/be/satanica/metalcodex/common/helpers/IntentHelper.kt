package be.satanica.metalcodex.common.helpers

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import timber.log.Timber

class IntentHelper {

    companion object {
        fun facebookIntent(pm: PackageManager, url: String): Intent {
            var uri: Uri = Uri.parse(url)
            try {
                val applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0)
                if (applicationInfo.enabled) uri = Uri.parse("fb://facewebmodal/f?href=$url")
            } catch (e: PackageManager.NameNotFoundException) {
                Timber.w("Facebook is not installed")
            }
            return Intent(Intent.ACTION_VIEW, uri)
        }

        fun spotifyIntent(pm: PackageManager, type: String, url: String): Intent {
            var uri = Uri.parse(String.format("https://open.spotify.com/%s/%s", type, url))
            try {
                pm.getPackageInfo("com.spotify.music", 0)
                uri = Uri.parse(String.format("spotify:%s:%s", type, url))
            } catch (e: PackageManager.NameNotFoundException) {
                Timber.w("Spotify is not installed")
            }
            return Intent(Intent.ACTION_VIEW, uri)
        }

        fun discordIntent(pm: PackageManager, url: String): Intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}
