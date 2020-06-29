package be.satanica.metalcodex.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import be.satanica.metalcodex.databinding.FragmentTabBinding
import com.google.android.material.tabs.TabLayoutMediator

abstract class BaseTabFragment : Fragment() {

    private lateinit var binding: FragmentTabBinding

    abstract val fragments: Array<Fragment>

    @get:StringRes
    abstract val titleResources: IntArray

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        require(fragments.size == titleResources.size) {
            "Fragments and titles must contain the same amount of items."
        }
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.adapter = TabAdapter(this, fragments)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(titleResources[position])
        }.attach()
    }

    private class TabAdapter(
        parent: Fragment,
        private val fragments: Array<Fragment>
    ) : FragmentStateAdapter(parent) {

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}
