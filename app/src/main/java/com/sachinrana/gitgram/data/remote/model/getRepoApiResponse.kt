package com.sachinrana.gitgram.data.remote.model

import com.sachinrana.gitgram.data.local.entity.RepositoryEntity

data class getRepoApiResponse(
    var items: List<RepositoryEntity>) {
}