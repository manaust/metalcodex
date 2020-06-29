package be.satanica.metalcodex.ui.fragments.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseScreenFragment : BaseFragment() {

    @get:DrawableRes
    abstract val iconRes: Int

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setHomeAsUpIndicator(iconRes)
        super.onViewCreated(view, savedInstanceState)
    }
}
