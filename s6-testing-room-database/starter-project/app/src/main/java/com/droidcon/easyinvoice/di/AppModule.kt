package com.droidcon.easyinvoice.di

import android.content.Context
import com.droidcon.easyinvoice.data.EasyInvoiceDatabase
import com.droidcon.easyinvoice.repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideEasyInvoiceDatabase(@ApplicationContext context: Context): EasyInvoiceDatabase = EasyInvoiceDatabase.invoke(context)

    @Provides
    fun provideBusinessRepository(db: EasyInvoiceDatabase): BusinessRepository = BusinessRepositoryImpl(db.getBusinessDao())

    @Provides
    fun provideCustomerRepository(db: EasyInvoiceDatabase): CustomerRepository = CustomerRepositoryImpl(db.getCustomerDao())

    @Provides
    fun provideTaxRepository(db: EasyInvoiceDatabase): TaxRepository = TaxRepositoryImpl(db.getTaxDao())

    @Provides
    fun provideInvoiceRepository(db: EasyInvoiceDatabase): InvoiceRepository = InvoiceRepositoryImpl(db.getInvoiceDao())

}