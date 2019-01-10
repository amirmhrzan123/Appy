package com.ebpearls.sample.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ebpearls.sample.BR

import com.ebpearls.sample.R
import com.ebpearls.sample.Status
import com.ebpearls.sample.base.BaseActivity
import com.ebpearls.sample.data.prefs.PrefsManager
import com.ebpearls.sample.databinding.ActivityLoginBinding
import com.ebpearls.sample.ui.profile.ProfileActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(), LoginNavigator {


    override fun getLayoutId(): Int = R.layout.activity_login

    private val loginViewModel: LoginViewModel by viewModel()

    override fun getViewModel(): LoginViewModel = loginViewModel

    override fun getBindingVariable(): Int {
        return BR.viewModel

    }

    private val prefManager: PrefsManager by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefManager.setAccessToken("asdfsdgf")
        println(prefManager.getAccessToken())
        loginViewModel.setNavigator(this)


    }

    override fun onLoginSuccess() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
        loginViewModel.doLogin().observe(this@LoginActivity, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showLoading("")
                }
                Status.SUCCESS -> {
                    hideLoading()
                    Toast.makeText(this@LoginActivity, it.data!!.email, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                }
                Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
