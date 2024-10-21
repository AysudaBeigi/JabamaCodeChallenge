package com.jabama.challenge.presentation.ui

import android.app.Activity
import android.os.Bundle
import org.koin.android.ext.android.inject

class LoginUriActivity : Activity() {
    private val ITokenRepository: com.jabama.challenge.domain.ITokenRepository by inject()
    private val IAccessTokenDataSource: com.jabama.challenge.domain.IAccessTokenDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.login_uri_activity)
    }

    override fun onResume() {
        super.onResume()

    }
}