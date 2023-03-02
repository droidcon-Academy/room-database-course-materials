package com.droidcon.easyinvoice.ui.home.nav

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.home.mybusinesses.ManageMyBusiness
import com.droidcon.easyinvoice.ui.home.mybusinesses.MyBusinessViewModel
import com.droidcon.easyinvoice.ui.home.mybusinesses.MyBusinesses
import com.droidcon.easyinvoice.ui.utils.getViewModelInstance

fun NavGraphBuilder.businessNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.MyBusinesses.Home.route,
        route = AppScreen.MyBusinesses.route
    ) {

        composable(AppScreen.MyBusinesses.Home.route) {
            val vm = navController.getViewModelInstance<MyBusinessViewModel>(navBackStackEntry = it, route = AppScreen.MyBusinesses.route)
            MyBusinesses(vm, navController)
        }

        composable(AppScreen.MyBusinesses.ManageMyBusiness.route) {
            val vm = navController.getViewModelInstance<MyBusinessViewModel>(navBackStackEntry = it, route = AppScreen.MyBusinesses.route)
            ManageMyBusiness(vm, navController)
        }

    }
}