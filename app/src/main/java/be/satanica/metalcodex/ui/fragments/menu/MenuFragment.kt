package be.satanica.metalcodex.ui.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import be.satanica.metalcodex.BuildConfig
import be.satanica.metalcodex.R
import be.satanica.metalcodex.common.helpers.IntentHelper
import be.satanica.metalcodex.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.version, BuildConfig.VERSION_NAME),
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.spotify.setOnClickListener {
            startActivity(
                IntentHelper.spotifyIntent(
                    requireActivity().packageManager,
                    "playlist",
                    "58SYxmrrBNQXKvzxXVA04X"
                )
            )
        }

        binding.facebook.setOnClickListener {
            startActivity(
                IntentHelper.facebookIntent(
                    requireActivity().packageManager,
                    "https://www.facebook.com/Satanica-111474740374553/"
                )
            )
        }

        binding.discord.setOnClickListener {
            startActivity(
                IntentHelper.discordIntent(
                    requireActivity().packageManager,
                    "https://discord.gg/Jdxqrk2"
                )
            )
        }

        binding.darkmode.setOnClickListener {
            val currentTheme = getDefaultSharedPreferences(requireContext())
                .getBoolean(getString(R.string.key_theme), false)

            getDefaultSharedPreferences(requireContext())
                .edit()
                .putBoolean(getString(R.string.key_theme), !currentTheme)
                .apply()
        }
    }
}
