package com.myprojects.postwithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewPost;
    EditText titleET, bodyET;
    Button insertBtn, getBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPost = findViewById(R.id.post_recyclerView);
        titleET = findViewById(R.id.etTitle);
        bodyET = findViewById(R.id.etBody);
        insertBtn = findViewById(R.id.btnInsert);
        getBtn = findViewById(R.id.btnGet);

        PostAdapter postAdapter = new PostAdapter();
        recyclerViewPost.setAdapter(postAdapter);

        final PostsDatabase postsDatabase = PostsDatabase.getInstance(this);


        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postsDatabase.postsDao().insertPost(new Post(0, new User(0, "Ahmed"), titleET.getText().toString(), bodyET.getText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                titleET.setText("");
                                bodyET.setText("");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });


        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postsDatabase.postsDao().getPosts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<Post> posts) {
                                postAdapter.setList(posts);
                                postAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });



    }
}