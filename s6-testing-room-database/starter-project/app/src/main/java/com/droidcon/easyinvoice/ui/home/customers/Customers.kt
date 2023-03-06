package com.droidcon.easyinvoice.ui.home.customers

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
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.commons.EmptyScreen
import com.droidcon.easyinvoice.ui.faker.FakeViewModelProvider
import com.droidcon.easyinvoice.ui.home.mybusinesses.MyBusiness
import com.droidcon.easyinvoice.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Customers(viewModel: CustomersViewModel, navController: NavController) {

    val context = LocalContext.current
    val customers = viewModel.customers.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppScreen.Customers.ManageCustomer.route)
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.empty))
            }
        },
        content = {
            if (customers.value.isNotEmpty()) {
                LazyColumn {
                    items(customers.value) { item ->
                        Customer(
                            customer = item,
                            onClick = {
                                viewModel.setUpdating(item)
                                navController.navigate(AppScreen.Customers.ManageCustomer.route)
                            }
                        )
                    }
                }
            } else {
                EmptyScreen(
                    title = stringResource(id = R.string.empty_customer),
                    onRefresh = {}
                )
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CustomersPreviewLight() {
    AppTheme {
        Customers(FakeViewModelProvider.provideCustomerViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomersPreviewDark() {
    AppTheme {
        Customers(FakeViewModelProvider.provideCustomerViewModel(), rememberNavController())
    }
}
