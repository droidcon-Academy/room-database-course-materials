package com.droidcon.easyinvoice.ui.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.home.taxes.ManageTaxes
import com.droidcon.easyinvoice.ui.home.taxes.Taxes
import com.droidcon.easyinvoice.ui.home.taxes.TaxesViewModel
import com.droidcon.easyinvoice.ui.utils.getViewModelInstance

fun NavGraphBuilder.taxNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.Taxes.Home.route,
        route = AppScreen.Taxes.route
    ) {
        composable(AppScreen.Taxes.Home.route) {
            val vm = navController.getViewModelInstance<TaxesViewModel>(it, AppScreen.Taxes.route)
            Taxes(vm, navController)
        }

        composable(AppScreen.Taxes.ManageTaxes.route) {
            val vm = navController.getViewModelInstance<TaxesViewModel>(it, AppScreen.Taxes.route)
            ManageTaxes(vm, navController)
        }
    }
}