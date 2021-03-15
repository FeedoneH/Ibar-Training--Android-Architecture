package com.example.moviemvvm.presentation

import android.app.Application
import com.example.moviemvvm.BuildConfig
import com.example.moviemvvm.presentation.di.*
import com.example.moviemvvm.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvm.presentation.di.movie.MovieSubComponent
import com.example.moviemvvm.presentation.di.tvShow.TvShowSubComponent
import dagger.android.DaggerApplication

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY)).build()
    }

    override fun createmovieSubComponent(): MovieSubComponent {
    return appComponent.movieSubComponent().create()
    }

    override fun createartistSubComponent(): ArtistSubComponent {
       return appComponent.artistSubComponent().create()
    }

    override fun createtvShowSubComponent(): TvShowSubComponent {
      return appComponent.tvShowSubComponent().create()
    }

}