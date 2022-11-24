package com.interview.app

import android.app.Application
import androidx.room.Room
import com.interview.app.base.Constants
import com.interview.app.db.CharactersDao
import com.interview.app.db.ThronesDB
import com.interview.app.network.ApiInterface
import com.interview.app.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
}

val apiModule = module {

    fun provideThronesApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    single { provideThronesApi(get()) }
}


val networkModule = module {

    // Provide HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Provide OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Provide Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    // Provide ApiInterface
    single {
        get<Retrofit>().create(ApiInterface::class.java)
    }
}


val databaseModule = module {

    fun provideDatabase(application: Application): ThronesDB {
        return Room.databaseBuilder(
            application.applicationContext,
            ThronesDB::class.java, Constants.DB_NAME.reversed()
        ).fallbackToDestructiveMigration().build()
    }

    fun provideCharactersDao(thronesDB: ThronesDB): CharactersDao {
        return thronesDB.charactersDao()
    }

    single { provideDatabase(get()) }
    single { provideCharactersDao(get()) }
}
