package com.sachinrana.gitgram.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repository")
class RepositoryEntity(

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(localId)
        parcel.writeLong(page)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(author)
        parcel.writeString(avatar)
        parcel.writeLong(currentPeriodStars)
        parcel.writeLong(stars)
        parcel.writeLong(forks)
        parcel.writeString(language)
        parcel.writeString(languageColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryEntity> {
        override fun createFromParcel(parcel: Parcel): RepositoryEntity {
            return RepositoryEntity(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryEntity?> {
            return arrayOfNulls(size)
        }
    }

    /*companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RepositoryEntity> = object : Parcelable.Creator<RepositoryEntity> {
            override fun createFromParcel(source: Parcel): RepositoryEntity = RepositoryEntity(source)
            override fun newArray(size: Int): Array<RepositoryEntity?> = arrayOfNulls(size)
        }
    }*/
}
