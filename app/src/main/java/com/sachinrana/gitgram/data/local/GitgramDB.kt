package com.sachinrana.gitgram.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class GitgramDB: RoomDatabase() {
    abstract fun RepositoryDao(): RepositoryDao
}