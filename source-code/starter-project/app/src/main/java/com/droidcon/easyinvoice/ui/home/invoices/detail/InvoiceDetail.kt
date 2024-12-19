/*
 * Copyright (c) 2022. Droidcon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.droidcon.easyinvoice.ui.home.invoices.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droidcon.easyinvoice.ui.theme.AppTheme
import com.droidcon.easyinvoice.ui.theme.spacing
import com.droidcon.easyinvoice.R

@Composable
fun InvoiceDetail(navController: NavController) {

    val spacing = MaterialTheme.spacing

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(spacing.medium)
    ) {

        Text(
            text = "DroidCon",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Berlin, Germany",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Divider(modifier = Modifier.padding(top = spacing.medium))

        Text(
            text = stringResource(id = R.string.invoice),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Divider(modifier = Modifier.padding(bottom = spacing.medium))

        Row(
            modifier = Modifier.padding(bottom = spacing.medium)
        ) {
            Column(
                modifier = Modifier.weight(0.6f)
            ) {
                Text(
                    text = stringResource(id = R.string.to),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Android Club",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Bangalore, India",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                Text(
                    text = stringResource(id = R.string.date),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "30th April 2022",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


        Row {
            TableCell(text = stringResource(id = R.string.particulars), heading = true, weight = 0.45f)
            TableCell(text = stringResource(id = R.string.qty), alignment = TextAlign.End, heading = true, weight = 0.15f)
            TableCell(text = stringResource(id = R.string.price), alignment = TextAlign.End, heading = true, weight = 0.2f)
            TableCell(text = stringResource(id = R.string.amount), alignment = TextAlign.End, heading = true, weight = 0.2f)
        }

        Row {
            TableCell(text = "Item Description", weight = 0.45f)
            TableCell(text = "3", alignment = TextAlign.End, weight = 0.15f)
            TableCell(text = "100", alignment = TextAlign.End, weight = 0.2f)
            TableCell(text = "300", alignment = TextAlign.End, weight = 0.2f)
        }


        Row {
            TableCell(text = stringResource(id = R.string.total_amount), heading = true, alignment = TextAlign.End, weight = 0.8f)
            TableCell(text = "$300", alignment = TextAlign.End, weight = 0.2f)
        }

        Row {
            TableCell(text = "GST 18%", heading = true, alignment = TextAlign.End, weight = 0.8f)
            TableCell(text = "$54", alignment = TextAlign.End, weight = 0.2f)
        }

        Row {
            TableCell(text = stringResource(id = R.string.invoice_amount), heading = true, alignment = TextAlign.End, weight = 0.8f)
            TableCell(text = "$354", alignment = TextAlign.End, weight = 0.2f)
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InvoiceDetailPreviewLight() {
    AppTheme {
        InvoiceDetail(rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InvoiceDetailPreviewDark() {
    AppTheme {
        InvoiceDetail(rememberNavController())
    }
}