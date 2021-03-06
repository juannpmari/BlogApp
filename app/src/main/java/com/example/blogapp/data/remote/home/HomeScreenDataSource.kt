package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {
    suspend fun getLatestPosts(): Result<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot=FirebaseFirestore.getInstance().collection("posts").get().await()
        for (post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let{ fbpost -> //Transformo el document en un Post
                postList.add(fbpost)
            }
        }
        return Result.Success(postList)
    }
}