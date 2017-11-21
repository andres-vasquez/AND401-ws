package com.example.android_instructor.webservices1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Button btnGetPosts;
    private Button btnGetPost;
    private Button btnPost;

    private TextView txtResultado;
    public ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        btnGetPosts = (Button)findViewById(R.id.btnGetPosts);
        btnGetPost = (Button)findViewById(R.id.btnGetPost);
        btnPost = (Button)findViewById(R.id.btnPost);
        txtResultado = (TextView)findViewById(R.id.txtResultado);


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/").build();
        apiService = retrofit.create(ApiService.class);


        btnGetPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<PostObj>> call = apiService.getPosts();
                call.enqueue(new Callback<List<PostObj>>() {
                    @Override
                    public void onResponse(Call<List<PostObj>> call, Response<List<PostObj>> response) {
                        List<PostObj> resultados = response.body();
                        mostrarResltado(new Gson().toJson(resultados));
                    }

                    @Override
                    public void onFailure(Call<List<PostObj>> call, Throwable t) {
                        mostrarResltado("Error: "+t.getMessage());
                    }
                });
            }
        });

        btnGetPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostObj> call = apiService.getPost(2);
                call.enqueue(new Callback<PostObj>() {
                    @Override
                    public void onResponse(Call<PostObj> call, Response<PostObj> response) {
                        PostObj resultado = response.body();
                        mostrarResltado(new Gson().toJson(resultado));
                    }

                    @Override
                    public void onFailure(Call<PostObj> call, Throwable t) {
                        mostrarResltado("Error: "+t.getMessage());
                    }
                });
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostObj postObj = new PostObj();
                postObj.setId(5000);
                postObj.setTitle("Android rocks!");
                postObj.setBody("Este es un body");
                postObj.setUserId(56);

                Call<PostObj> call = apiService.enviarPost(postObj);
                call.enqueue(new Callback<PostObj>() {
                    @Override
                    public void onResponse(Call<PostObj> call, Response<PostObj> response) {
                        PostObj resultado = response.body();
                        mostrarResltado(new Gson().toJson(resultado));
                    }

                    @Override
                    public void onFailure(Call<PostObj> call, Throwable t) {
                        mostrarResltado("Error: "+t.getMessage());
                    }
                });
            }
        });
    }

    private void mostrarResltado(final String resultado){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResultado.setText(resultado);
            }
        });
    }
}
