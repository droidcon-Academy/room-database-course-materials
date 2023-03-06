package com.droidcon.easyinvoice.ui.home.invoices

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droidcon.easyinvoice.R
import com.droidcon.easyinvoice.data.entities.InvoiceWithItems
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.commons.EmptyScreen
import com.droidcon.easyinvoice.ui.commons.UserConfirmationDialog
import com.droidcon.easyinvoice.ui.faker.FakeViewModelProvider
import com.droidcon.easyinvoice.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoicesScreen(viewModel: InvoiceViewModel, navController: NavController) {
    val context = LocalContext.current
    val invoices = viewModel.invoices.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppScreen.Invoices.ManageInvoice.route)
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.empty))
            }
        },
        content = {
            if (invoices.value.isEmpty()) {
                EmptyScreen(title = "No Invoice Available") {

                }
            } else {
                Invoices(
                    invoices = invoices.value,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun Invoices(invoices: List<InvoiceWithItems>, viewModel: InvoiceViewModel, navController: NavController) {
    val invoiceDeleteConfirmation = remember { mutableStateOf<Int?>(null) }

    LazyColumn {
        items(invoices) { item ->
            InvoiceCard(
                invoice = item,
                onClick = {
                    viewModel.currentInvoice.value = item
                    navController.navigate(AppScreen.Invoices.InvoiceDetail.route)
                },
                onMenuClick = {
                    when (it) {
                        InvoiceMenu.Delete -> {
                            invoiceDeleteConfirmation.value = item.invoice.id
                        }
                        InvoiceMenu.Edit -> {
                            viewModel.setUpdating(item.invoice)
                            navController.navigate(AppScreen.Invoices.ManageInvoice.route)
                        }
                        InvoiceMenu.MarkAsPaid -> {
                            viewModel.setPaidStatus(item.invoice.id!!, true)
                        }
                        InvoiceMenu.MarkAsUnPaid -> {
                            viewModel.setPaidStatus(item.invoice.id!!, false)
                        }
                    }
                }
            )
        }
    }

    if (invoiceDeleteConfirmation.value != null) {
        UserConfirmationDialog { confirmation ->
            if (confirmation) {
                viewModel.deleteInvoice(invoiceDeleteConfirmation.value)
            }
            invoiceDeleteConfirmation.value = null
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InvoicesPreviewLight() {
    AppTheme {
        InvoicesScreen(FakeViewModelProvider.provideInvoiceViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InvoicesPreviewDark() {
    AppTheme {
        InvoicesScreen(FakeViewModelProvider.provideInvoiceViewModel(), rememberNavController())
    }
}