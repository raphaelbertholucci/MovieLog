package com.bertholucci.movielog.domain.di

import com.bertholucci.movielog.domain.interactor.GetFavorites
import com.bertholucci.movielog.domain.interactor.GetMovieByIdFromDB
import com.bertholucci.movielog.domain.interactor.GetMovieDetails
import com.bertholucci.movielog.domain.interactor.GetNowPlayingMovies
import com.bertholucci.movielog.domain.interactor.GetPopularMovies
import com.bertholucci.movielog.domain.interactor.GetTopRatedMovies
import com.bertholucci.movielog.domain.interactor.GetUpcomingMovies
import com.bertholucci.movielog.domain.interactor.InsertMovieIntoDB
import com.bertholucci.movielog.domain.interactor.RemoveMovieFromDB
import com.bertholucci.movielog.domain.interactor.SearchMovies
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetFavorites(repository = get()) }
    factory { GetNowPlayingMovies(repository = get()) }
    factory { GetPopularMovies(repository = get()) }
    factory { GetTopRatedMovies(repository = get()) }
    factory { GetUpcomingMovies(repository = get()) }
    factory { SearchMovies(repository = get()) }
    factory { GetMovieByIdFromDB(repository = get()) }
    factory { GetMovieDetails(repository = get()) }
    factory { InsertMovieIntoDB(repository = get()) }
    factory { RemoveMovieFromDB(repository = get()) }
}