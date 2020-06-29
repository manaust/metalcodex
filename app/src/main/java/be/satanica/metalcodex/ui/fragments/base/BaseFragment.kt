package be.satanica.metalcodex.ui.fragments.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import be.satanica.metalcodex.ui.activities.MainActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private lateinit var mainActivity: MainActivity

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        disposables.addAll(*subscriptions())
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    open fun subscriptions(): Array<Disposable> {
        return emptyArray()
    }

    protected fun addSubscription(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected fun addSubscriptions(vararg disposable: Disposable) {
        disposables.addAll(*disposable)
    }

    fun closeDrawer() {
        mainActivity.closeDrawer()
    }
}
