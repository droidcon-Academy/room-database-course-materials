package com.droidcon.easyinvoice.ui.home.invoices.manage

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.droidcon.easyinvoice.R
import com.droidcon.easyinvoice.ui.theme.AppTheme
import com.droidcon.easyinvoice.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddInvoiceItem(navController: NavController) {
    val context = LocalContext.current
    val spacing = MaterialTheme.spacing

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
            ) {
                Icon(Icons.Filled.Done, stringResource(id = R.string.empty))
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.medium)
            ) {
                Text(
                    text = stringResource(id = R.string.add_invoice_items),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = spacing.medium)
                )

                InvoiceItemInput()

                Divider()

                Text(
                    text = stringResource(id = R.string.invoice),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = spacing.extraSmall, bottom = spacing.extraSmall)
                )

                Divider(modifier = Modifier.padding(bottom = spacing.medium))

                LazyColumn {

                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceItemInput() {
    val focusManager = LocalFocusManager.current
    val spacing = MaterialTheme.spacing

    Column(
        modifier = Modifier.wrapContentHeight()
    ) {
        Row {
            TextField(
                value = "",
                onValueChange = { },
                label = { Text(text = stringResource(id = R.string.desc)) },
                modifier = Modifier
                    .weight(2f)
                    .padding(end = spacing.small),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                ),
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onSurface),
            )

            TextField(
                value = "",
                onValueChange = {
                },
                label = { Text(text = stringResource(id = R.string.qty)) },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                ),
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onSurface),
            )

            TextField(
                value = "",
                onValueChange = {
                },
                label = { Text(text = stringResource(id = R.string.price)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = spacing.small),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    },
                ),
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onSurface),
            )
        }
        TextButton(
            onClick = {
                focusManager.clearFocus()
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = spacing.medium),
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.empty),
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = stringResource(id = R.string.update),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun InvoiceItemCard(index: Int) {
    val spacing = MaterialTheme.spacing
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Item Description",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .weight(0.5f)
                .padding(end = spacing.medium)
        )

        Text(
            text = "5",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(0.1f)
        )

        Text(
            text = "50",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(0.2f)
                .padding(start = spacing.medium, end = spacing.extraSmall)
        )

        IconButton(
            onClick = {
            },
            modifier = Modifier.weight(0.1f)
        ) {
            Image(
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(id = R.string.empty),
                modifier = Modifier.size(ButtonDefaults.IconSize),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
            )
        }

        IconButton(
            onClick = {
            },
            modifier = Modifier.weight(0.1f)
        ) {
            Image(
                imageVector = Icons.Filled.Clear,
                contentDescription = stringResource(id = R.string.empty),
                modifier = Modifier.size(ButtonDefaults.IconSize),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewAddInvoiceItemLight() {
    AppTheme {
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAddInvoiceItemDark() {
    AppTheme {
    }
}