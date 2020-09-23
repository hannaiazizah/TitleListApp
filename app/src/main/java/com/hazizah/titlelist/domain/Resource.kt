package com.hazizah.titlelist.domain

import androidx.annotation.IntDef

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(
    val status: Int,
    val data: T?,
    val error: Failure?
) {
    companion object {
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(failure: Failure): Resource<T> {
            return Resource(Status.ERROR, null, failure)
        }
    }

    class Status {
        @IntDef(
            SUCCESS,
            ERROR,
            LOADING
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class Status

        companion object {
            const val SUCCESS = 1
            const val ERROR = 2
            const val LOADING = 3
        }
    }
}