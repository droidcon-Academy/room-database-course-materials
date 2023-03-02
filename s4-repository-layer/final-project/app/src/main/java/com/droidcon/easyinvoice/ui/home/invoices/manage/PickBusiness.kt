package com.droidcon.easyinvoice.ui.home.invoices.manage

import android.content.res.Configuration
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droidcon.easyinvoice.ui.theme.AppTheme
import com.droidcon.easyinvoice.ui.utils.toast

@Composable
fun PickBusinessScreen(navController: NavController) {
    val context = LocalContext.current
}

@Composable
fun PickBusiness(navController: NavController) {
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PickBusinessPreviewLight() {
    AppTheme {
        PickBusiness(rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PickBusinessPreviewDark() {
    AppTheme {
        PickBusiness(rememberNavController())
    }
}