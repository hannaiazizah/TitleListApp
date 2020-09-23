package com.hazizah.titlelist.domain

sealed class Failure : Throwable() {
    class NetworkFailure: Failure()
    class ServerFailure : Failure()
    class UnknownFailure: Failure()
}