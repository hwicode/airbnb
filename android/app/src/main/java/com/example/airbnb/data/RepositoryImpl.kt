package com.example.airbnb.data

import com.example.airbnb.data.remote.LoginDataSource
import com.example.airbnb.domain.Repository
import com.example.airbnb.domain.model.CalendarDay
import com.example.airbnb.ui.common.CalendarUtil
import org.joda.time.LocalDateTime

class RepositoryImpl(private val loginDataSource: LoginDataSource) : Repository {

    override suspend fun getAccessToken():List<String>{
        return loginDataSource.getAccessToken()
    }
}