package com.droidcon.easyinvoice.ui.home.invoices.manage

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droidcon.easyinvoice.R
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.commons.EmptyScreen
import com.droidcon.easyinvoice.ui.home.invoices.InvoiceViewModel
import com.droidcon.easyinvoice.ui.home.taxes.Tax
import com.droidcon.easyinvoice.ui.theme.AppTheme

@Composable
fun PickTaxScreen(viewModel: InvoiceViewModel, navController: NavController) {
    val context = LocalContext.current
    val taxes = viewModel.taxes.collectAsState()

    if (taxes.value.isEmpty()) {
        EmptyScreen(title = stringResource(id = R.string.empty_taxes)) { }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.pick_a_tax),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(12.dp)
            )

            LazyColumn {
                items(taxes.value) { item ->
                    Tax(
                        tax = item,
                        onClick = {
                            viewModel.taxId.value = item.id
                            viewModel.addUpdateInvoice()
                            navController.navigate(AppScreen.Invoices.ManageInvoice.AddItems.route)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PickTax(navController: NavController) {

}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewPickTaxLight() {
    AppTheme {
        PickTax(rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPickTaxDark() {
    AppTheme {
        PickTax(rememberNavController())
    }
}