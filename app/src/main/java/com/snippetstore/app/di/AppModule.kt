package com.snippetstore.app.di

import android.app.Application
import androidx.room.Room
import com.snippetstore.app.data.local.SnippetDatabase
import com.snippetstore.app.data.repository.SnippetRepository
import com.snippetstore.app.data.repository.SnippetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSnippetDatabase(app: Application): SnippetDatabase =
        Room.databaseBuilder(
            app,
            SnippetDatabase::class.java,
            "snippet_db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideSnippetRepository(db: SnippetDatabase): SnippetRepository =
        SnippetRepositoryImpl(db.snippetDao)
}
