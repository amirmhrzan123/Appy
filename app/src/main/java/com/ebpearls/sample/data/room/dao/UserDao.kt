package com.ebpearls.sample.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ebpearls.sample.base.BaseDao
import com.ebpearls.sample.ui.login.LoginResponse

@Dao
interface UserDao : BaseDao<LoginResponse> {

    @Query("select * from user order by _id ASC limit 1")
    fun getUserById(): LiveData<LoginResponse>
}