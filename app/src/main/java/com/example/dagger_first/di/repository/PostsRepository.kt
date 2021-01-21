package com.example.dagger_first.di.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.dagger_first.SessionManager
import com.example.dagger_first.models.ModelPost
import com.example.dagger_first.network.main.MainApi
import com.example.dagger_first.ui.main.posts.PostsResource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val mainApi: MainApi,
    private val sessionManager: SessionManager
) {
    // private var posts: MutableLiveData<PostsResource<List<ModelPost>>>? = null

/*    fun getPosts(): LiveData<PostsResource<List<ModelPost>>> {

        if (posts == null) {
            posts = MediatorLiveData()
            posts?.value = PostsResource.loading(null)

            val source: LiveData<PostsResource<List<ModelPost>>> =
                LiveDataReactiveStreams.fromPublisher {
                    mainApi.getPosts(sessionManager.getAuthUser().value?.data?.id!!)
                        .onErrorReturn {
                            Log.d("MyError", it.message.toString())
                            val p = ModelPost()
                            p.id = -1
                            val post = ArrayList<ModelPost>()
                            post.add(p)
                            post
                        }.map { post ->
                            if (post.isNotEmpty()) {
                                if (post[0].id == -1) {
                                    Log.d("Map", "Error: ${post}")
                                    return@map PostsResource.error(
                                        "Couldn't Authenticate",
                                        null
                                    )
                                }
                            }
                            return@map PostsResource.success(post)

                        }.subscribeOn(Schedulers.io())

                }

            posts?.addSource(source) {
                // Log.d("MyPosts", ": ${post.data}")
                posts?.value = it
                posts?.removeSource(source)
            }
        }


        return posts!!
    }*/


    fun getPosts(): LiveData<PostsResource<List<ModelPost>>> {
        val posts: MutableLiveData<PostsResource<List<ModelPost>>> = MutableLiveData()
        posts.value = PostsResource.loading(null)

        mainApi.getPosts(sessionManager.getAuthUser().value?.data?.id!!)
            .onErrorReturn {
                Log.d("MyError", it.message.toString())
                val p = ModelPost()
                p.id = -1
                val post = ArrayList<ModelPost>()
                post.add(p)
                post
            }.map { post ->
                if (post.isNotEmpty()) {
                    if (post[0].id == -1) {
                        Log.d("Map", "Error: ${post}")
                       PostsResource.error(
                            "Couldn't Authenticate",
                            null
                        )
                    }
                }
                Log.d("Working", "getPosts: " + post.get(0).title)
                PostsResource.success(post)

            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                posts.value = it
            }


        return posts
    }

}