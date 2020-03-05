package com.sachinrana.gitgram.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity

@Dao
interface RepositoryDao {

    @Insert(onConflict = REPLACE)
     fun saveRepositories(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM `repository` where page = :page")
     fun getRepositoriesByPage(page: Long): LiveData<List<RepositoryEntity>>
}