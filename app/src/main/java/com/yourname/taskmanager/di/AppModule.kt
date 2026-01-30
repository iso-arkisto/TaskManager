package com.yourname.taskmanager.di

import android.app.Application
import androidx.room.Room
import com.yourname.taskmanager.data.db.MainDb
import com.yourname.taskmanager.data.repository.AddItemRepository
import com.yourname.taskmanager.data.repository.AddItemRepositoryImpl
import com.yourname.taskmanager.data.repository.NoteItemRepository
import com.yourname.taskmanager.data.repository.NoteItemRepositoryImpl
import com.yourname.taskmanager.data.repository.ShoppingListRepository
import com.yourname.taskmanager.data.repository.ShoppingListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDb(context: Application): MainDb {
        return Room.databaseBuilder(
            context = context,
            MainDb::class.java,
            "task_manager_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingListRepository(mainDb: MainDb): ShoppingListRepository {
        return ShoppingListRepositoryImpl(mainDb.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideNoteItemRepository(mainDb: MainDb): NoteItemRepository {
        return NoteItemRepositoryImpl(mainDb.noteItemDao)
    }

    @Provides
    @Singleton
    fun provideAddItemRepository(mainDb: MainDb): AddItemRepository {
        return AddItemRepositoryImpl(mainDb.addItemDao)
    }
}