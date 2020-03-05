package com.sachinrana.gitgram.data.remote.model

import android.os.Parcel
import android.os.Parcelable
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity

data class getRepoApiResponse(var items: List<RepositoryEntity>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(RepositoryEntity.CREATOR)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<getRepoApiResponse> {
        override fun createFromParcel(parcel: Parcel): getRepoApiResponse {
            return getRepoApiResponse(
                parcel
            )
        }

        override fun newArray(size: Int): Array<getRepoApiResponse?> {
            return arrayOfNulls(size)
        }
    }

}