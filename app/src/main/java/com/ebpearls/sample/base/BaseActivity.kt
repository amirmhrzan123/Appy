package com.ebpearls.sample.base

import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.ebpearls.dwell.util.ProgressDialogHelper

abstract class BaseActivity<M : BaseViewModel<*>, V : ViewDataBinding> : AppCompatActivity() {
    private var mViewDataBinding: V? = null
    private var mViewModel: M? = null
    lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        observeLiveData()
    }

    private fun observeLiveData() {
        mViewModel?.let {
            it.isLoadingLiveData.observe(this, Observer {
                //message in show isk optional
                if (it.isLoading) showLoading(it.message)
                else
                    hideLoading()
            })
        }
    }

    fun hideLoading() {
        progressDialog?.let {
            it.dismiss()
        }
    }

    fun showLoading(message: String) {
        progressDialog = ProgressDialogHelper.progressDialog(this, message)
        progressDialog.show()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.setLifecycleOwner(this)
        mViewDataBinding!!.executePendingBindings()
    }

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): M

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int
}