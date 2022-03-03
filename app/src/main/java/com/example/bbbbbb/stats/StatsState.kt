package com.example.bbbbbb.stats

import com.example.bbbbbb.model.Metrics
import com.example.bbbbbb.model.Post

data class StatsState(
    val post: Post,
    val metrics: Metrics
) {
    companion object {
        private const val SOMETHING_WENT_WRONG = "Something went wrong"

        fun from(post: Result<Post>, metrics: Result<Metrics>): Result<StatsState> {
            return when {
                post.isFailure or metrics.isFailure -> {
                    Result.failure(Throwable(SOMETHING_WENT_WRONG))
                }
                else -> {
                    Result.success(StatsState(post.getOrThrow(), metrics.getOrThrow()))
                }
            }
        }
    }
}
