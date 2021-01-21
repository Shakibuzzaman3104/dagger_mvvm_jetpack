package com.example.dagger_first.ui.main.posts

class PostsResource<T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T?): PostsResource<T> {
            return PostsResource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): PostsResource<T> {
            return PostsResource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): PostsResource<T> {
            return PostsResource(Status.LOADING, data, null)
        }


    }
}