package com.example.moviemvvm.presentation.di

import com.example.moviemvvm.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvm.presentation.di.movie.MovieSubComponent
import com.example.moviemvvm.presentation.di.tvShow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
        [AppModule::class,
        CacheDataModule::class,
        DataBaseModule::class,
        LocalDataModule::class,
        NetModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
        UseCaseModule::class]
)
interface AppComponent {
    fun movieSubComponent():MovieSubComponent.Factory
    fun artistSubComponent():ArtistSubComponent.Factory
    fun tvShowSubComponent():TvShowSubComponent.Factory
}