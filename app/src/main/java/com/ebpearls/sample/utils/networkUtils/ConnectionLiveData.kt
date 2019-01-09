package com.example.ebpearls.dwell.util.connectiviyHelper

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


/**
 * @author Prajeet Naga
 *
 *  A class to check the internet connection available/unavailable
 *  The observer activity will gets triggerd on internet connection change
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ConnectionLiveData : NetworkCallback() {

    var isConnected: MutableLiveData<Boolean> = MutableLiveData()

    private val networkRequest: NetworkRequest = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()

    fun enable(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    // Likewise, you can have a disable method that simply calls ConnectivityManager#unregisterCallback(networkRequest) too.

    override fun onAvailable(network: Network) {
        // Do what you need to do here
        Log.d("Network", "Connected")

        try {
            val timeoutMs = 1500
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            socket.connect(socketAddress, timeoutMs)
            socket.close()
            isConnected.postValue(true)
        } catch (e: IOException) {
            isConnected.postValue(false)
        }

    }

    override fun onLost(network: Network?) {
        Log.d("Network", "Disconnect")
        isConnected.postValue(false)
    }
}