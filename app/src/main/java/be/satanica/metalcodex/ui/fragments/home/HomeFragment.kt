package be.satanica.metalcodex.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import be.satanica.metalcodex.R
import be.satanica.metalcodex.databinding.FragmentHomeBinding
import be.satanica.metalcodex.ui.fragments.base.BaseScreenFragment

class HomeFragment : BaseScreenFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    override val iconRes: Int
        get() = R.drawable.ic_menu

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val navController = findNavController()

        binding.searchView.setOnQueryTextFocusChangeListener { _, _ -> closeDrawer() }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                val action = HomeFragmentDirections.actionSearch(query)
                navController.navigate(action)
                return true
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.setQuery("", false)
    }
}
