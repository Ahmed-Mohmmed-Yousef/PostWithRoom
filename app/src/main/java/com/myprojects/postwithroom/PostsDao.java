package com.myprojects.postwithroom;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PostsDao {

    @Insert
    Completable insertPost(Post post);

    @Query("SELECT * FROM posts_table")
    Single<List<Post>> getPosts();
}
