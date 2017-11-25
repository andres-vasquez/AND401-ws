package com.example.android_instructor.webservices1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_instructor.webservices1.model.Clima;
import com.example.android_instructor.webservices1.model.Pronostico;
import com.google.gson.Gson;

import org.w3c.dom.Text;

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

    public ApiService apiService;

    private String apiKey = "c561bbc24015a96ce6e3a1a8a5b74d64";
    private String units = "metric";

    private ImageView climaImageView;
    private TextView minTextView;
    private TextView maxTextView;
    private TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        btnGetPosts = (Button)findViewById(R.id.btnGetPosts);
        btnGetPost = (Button)findViewById(R.id.btnGetPost);
        btnPost = (Button)findViewById(R.id.btnPost);

        climaImageView = (ImageView) findViewById(R.id.climaImageView);
        minTextView = (TextView) findViewById(R.id.minTextView);
        maxTextView = (TextView) findViewById(R.id.maxTextView);
        tempTextView = (TextView) findViewById(R.id.tempTextView);


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.baseUrl("https://jsonplaceholder.typicode.com/").build();
                .baseUrl("http://api.openweathermap.org/").build();
        apiService = retrofit.create(ApiService.class);


        btnGetPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = 40.748041;
                double lon = -73.994275;
                Call<Clima> call = apiService.obtenerClimaPorLugar(lat,lon,apiKey,units);
                call.enqueue(new Callback<Clima>() {
                    @Override
                    public void onResponse(Call<Clima> call, final Response<Clima> response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Clima clima = response.body();
                                tempTextView.setText(String.valueOf(clima.getMain().getTemp()));
                                minTextView.setText(String.valueOf(clima.getMain().getTempMin()));
                                maxTextView.setText(String.valueOf(clima.getMain().getTempMax()));
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Clima> call, final Throwable t) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context,"Error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        btnGetPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = -16.524067;
                double lon = -68.112095;
                Call<Pronostico> call = apiService.obtenerPronosticoPorLugar(lat,lon,apiKey);
                call.enqueue(new Callback<Pronostico>() {
                    @Override
                    public void onResponse(Call<Pronostico> call, Response<Pronostico> response) {
                        //mostrarResltado(new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<Pronostico> call, final Throwable t) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context,"Error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*PostObj postObj = new PostObj();
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
                });*/
            }
        });
    }
}
