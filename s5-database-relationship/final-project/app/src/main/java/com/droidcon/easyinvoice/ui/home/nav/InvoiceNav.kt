package com.droidcon.easyinvoice.ui.home.nav

import android.app.Activity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.home.HomeActivity
import com.droidcon.easyinvoice.ui.home.invoices.InvoiceItemViewModel
import com.droidcon.easyinvoice.ui.home.invoices.InvoiceViewModel
import com.droidcon.easyinvoice.ui.home.invoices.InvoicesScreen
import com.droidcon.easyinvoice.ui.home.invoices.detail.InvoiceDetail
import com.droidcon.easyinvoice.ui.home.invoices.manage.AddInvoiceItem
import com.droidcon.easyinvoice.ui.home.invoices.manage.PickBusinessScreen
import com.droidcon.easyinvoice.ui.home.invoices.manage.PickCustomerScreen
import com.droidcon.easyinvoice.ui.home.invoices.manage.PickTaxScreen
import com.droidcon.easyinvoice.ui.utils.getViewModelInstance
import dagger.hilt.android.EntryPointAccessors


fun NavGraphBuilder.invoiceNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.Invoices.Home.route,
        route = AppScreen.Invoices.route
    ) {
        composable(AppScreen.Invoices.Home.route) {
            val vm = navController.getViewModelInstance<InvoiceViewModel>(navBackStackEntry = it, route = AppScreen.Invoices.route)
            InvoicesScreen(vm, navController)
        }

        composable(AppScreen.Invoices.InvoiceDetail.route) {
            val vm = navController.getViewModelInstance<InvoiceViewModel>(navBackStackEntry = it, route = AppScreen.Invoices.route)
            InvoiceDetail(vm, navController)
        }

        navigation(
            startDestination = AppScreen.Invoices.ManageInvoice.PickBusiness.route,
            route = AppScreen.Invoices.ManageInvoice.route
        ) {

            composable(AppScreen.Invoices.ManageInvoice.PickBusiness.route) {
                val vm = navController.getViewModelInstance<InvoiceViewModel>(navBackStackEntry = it, route = AppScreen.Invoices.route)
                PickBusinessScreen(vm, navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.PickCustomer.route) {
                val vm = navController.getViewModelInstance<InvoiceViewModel>(navBackStackEntry = it, route = AppScreen.Invoices.route)
                PickCustomerScreen(vm, navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.PickTax.route) {
                val vm = navController.getViewModelInstance<InvoiceViewModel>(navBackStackEntry = it, route = AppScreen.Invoices.route)
                PickTaxScreen(vm, navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.AddItems.route) {
                val invoiceViewModel = navController.getViewModelInstance<InvoiceViewModel>(navBackStackEntry = it, route = AppScreen.Invoices.route)
                val invoiceId = invoiceViewModel.invoiceId.collectAsState()
                val factory = EntryPointAccessors.fromActivity(
                    LocalContext.current as Activity,
                    HomeActivity.ViewModelFactoryProvider::class.java
                ).invoiceItemViewModelFactory()

                invoiceId.value?.let {
                    val invoiceItemViewModel: InvoiceItemViewModel = viewModel(factory = InvoiceItemViewModel.provideInvoiceItemViewModelFactory(factory, it.toInt()))
                    AddInvoiceItem(invoiceItemViewModel, navController)
                }
            }
        }
    }
}