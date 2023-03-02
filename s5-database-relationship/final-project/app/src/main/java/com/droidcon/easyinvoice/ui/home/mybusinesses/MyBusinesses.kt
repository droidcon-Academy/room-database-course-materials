package com.droidcon.easyinvoice.ui.home.mybusinesses

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droidcon.easyinvoice.R
import com.droidcon.easyinvoice.data.entities.Business
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.commons.EmptyScreen
import com.droidcon.easyinvoice.ui.faker.FakeViewModelProvider
import com.droidcon.easyinvoice.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBusinesses(viewModel: MyBusinessViewModel, navController: NavController) {
    val context = LocalContext.current
    val businesses = viewModel.businesses.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppScreen.MyBusinesses.ManageMyBusiness.route)
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.empty))
            }
        },
        content = {
            if (businesses.value.isNotEmpty()) {
                LazyColumn {
                    items(businesses.value) { item ->
                        MyBusiness(
                            business = item,
                            onClick = {
                                viewModel.setUpdating(item)
                                navController.navigate(AppScreen.MyBusinesses.ManageMyBusiness.route)
                            }
                        )
                    }
                }
            } else {
                EmptyScreen(title = "No Business Exist") {

                }
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MyBusinessesPreviewLight() {
    AppTheme {
        MyBusinesses(FakeViewModelProvider.provideMyBusinessViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyBusinessesPreviewDark() {
    AppTheme {
        MyBusinesses(FakeViewModelProvider.provideMyBusinessViewModel(), rememberNavController())
    }
}