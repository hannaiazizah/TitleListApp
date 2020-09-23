package com.hazizah.titlelist.domain

sealed class Failure : Throwable() {
    class NotFoundFailure: Failure()
    class ServerFailure : Failure()
    class UnknownFailure: Failure()
}