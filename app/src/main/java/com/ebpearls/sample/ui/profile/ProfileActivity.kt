package com.ebpearls.sample.ui.profile

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ebpearls.sample.BR
import com.ebpearls.sample.R
import com.ebpearls.sample.Status
import com.ebpearls.sample.base.BaseActivity
import com.ebpearls.sample.databinding.ActivityProfileBinding
import com.ebpearls.sample.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : BaseActivity<ProfileViewModel, ActivityProfileBinding>(), ProfileNavigator {


    override fun getLayoutId(): Int = R.layout.activity_profile

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun getViewModel(): ProfileViewModel = profileViewModel

    override fun getBindingVariable(): Int {
        return BR.viewModel

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel.fetchPersonalInfo().observe(this@ProfileActivity, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showLoading("")
                }
                Status.SUCCESS -> {
                    hideLoading()
                    Toast.makeText(this@ProfileActivity, it.data!!.email, Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(this@ProfileActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
