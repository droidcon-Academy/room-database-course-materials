package com.droidcon.easyinvoice.ui.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.home.invoices.InvoicesScreen
import com.droidcon.easyinvoice.ui.home.invoices.detail.InvoiceDetail
import com.droidcon.easyinvoice.ui.home.invoices.manage.AddInvoiceItem
import com.droidcon.easyinvoice.ui.home.invoices.manage.PickBusinessScreen
import com.droidcon.easyinvoice.ui.home.invoices.manage.PickCustomerScreen
import com.droidcon.easyinvoice.ui.home.invoices.manage.PickTaxScreen


fun NavGraphBuilder.invoiceNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.Invoices.Home.route,
        route = AppScreen.Invoices.route
    ) {
        composable(AppScreen.Invoices.Home.route) {
            InvoicesScreen(navController)
        }

        composable(AppScreen.Invoices.InvoiceDetail.route) {
            InvoiceDetail(navController)
        }

        navigation(
            startDestination = AppScreen.Invoices.ManageInvoice.PickBusiness.route,
            route = AppScreen.Invoices.ManageInvoice.route
        ) {

            composable(AppScreen.Invoices.ManageInvoice.PickBusiness.route) {
                PickBusinessScreen(navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.PickCustomer.route) {
                PickCustomerScreen(navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.PickTax.route) {
                PickTaxScreen(navController)
            }

            composable(AppScreen.Invoices.ManageInvoice.AddItems.route) {
                AddInvoiceItem(navController)
            }
        }
    }
}