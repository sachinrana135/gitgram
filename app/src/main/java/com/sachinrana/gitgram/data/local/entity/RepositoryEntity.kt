package com.sachinrana.gitgram.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repository")
data class  RepositoryEntity(

    @PrimaryKey(autoGenerate = true)
    var localId: Long = 0,

    var page: Long,

    @SerializedName("name")
    var name: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("author")
    var author: String?,

    @SerializedName("avatar")
    var avatar: String?,

    @SerializedName("currentPeriodStars")
    var currentPeriodStars: Long,

    @SerializedName("stars")
    var stars: Long,

    @SerializedName("forks")
    var forks: Long,

    @SerializedName("language")
    var language: String?,

    @SerializedName("languageColor")
    var languageColor: String?
)

