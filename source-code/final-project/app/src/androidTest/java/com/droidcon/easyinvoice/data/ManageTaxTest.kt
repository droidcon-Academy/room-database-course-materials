package com.droidcon.easyinvoice.data

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droidcon.easyinvoice.repositories.TaxRepositoryImpl
import com.droidcon.easyinvoice.ui.home.HomeActivity
import com.droidcon.easyinvoice.ui.home.taxes.ManageTaxes
import com.droidcon.easyinvoice.ui.home.taxes.TaxesViewModel
import com.droidcon.easyinvoice.ui.theme.AppTheme
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ManageTaxTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    private lateinit var db: EasyInvoiceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, EasyInvoiceDatabase::class.java).build()
    }

    @Test
    fun testAddingTaxUI() {
        val viewModel = TaxesViewModel(TaxRepositoryImpl(db.getTaxDao()))

        composeTestRule.activity.setContent {
            AppTheme {
                ManageTaxes(viewModel, rememberNavController())
            }
        }

        composeTestRule.onNodeWithText("description").performTextInput("Tax1")
        composeTestRule.onNodeWithText("tax value").performImeAction()
        composeTestRule.onNodeWithText("tax value").performTextInput("5")
        composeTestRule.onNodeWithText("tax value").performImeAction()
        composeTestRule.onNodeWithText("Add").performClick()

        val result = runBlocking {
            db.getTaxDao().getTaxes().take(1).firstOrNull()?.find { it.desc == "Tax1" }
        }

        assert(result != null)
    }

    @After
    fun cleanUp() {
        db.close()
    }

}