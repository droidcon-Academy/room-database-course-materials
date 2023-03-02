package com.droidcon.easyinvoice.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import com.droidcon.easyinvoice.ui.BaseActivity
import com.droidcon.easyinvoice.ui.home.nav.HomeNavHost
import com.droidcon.easyinvoice.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeNavHost()
            }
        }
    }
}