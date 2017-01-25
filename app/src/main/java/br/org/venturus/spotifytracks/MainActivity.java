package br.org.venturus.spotifytracks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import br.org.venturus.spotifytracks.http.SpotifyService;
import br.org.venturus.spotifytracks.model.Track;
import br.org.venturus.spotifytracks.model.TracksContainer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static br.org.venturus.spotifytracks.http.SpotifyService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        SpotifyService apiService =
                retrofit.create(SpotifyService.class);


        Call<TracksContainer> call = apiService.getArtistTopTracks("0SfsnGyD8FpIN4U4WCkBZ5", "BR");
        call.enqueue(new Callback<TracksContainer>() {
            @Override
            public void onResponse(Call<TracksContainer> call, Response<TracksContainer> response) {
                int statusCode = response.code();
                TracksContainer tracksContainer = response.body();
                Log.d("debug", "TracksContainer received: " + tracksContainer);
                if (tracksContainer != null && tracksContainer.getTracks().size() > 0) {
                    List<Track> tracks = tracksContainer.getTracks();
                    for (Track track : tracks) {
                        Log.d("debug", "Track name: " + track.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<TracksContainer> call, Throwable t) {
                Log.d("debug", "Failure getting tracks " + t.getMessage());
            }
        });
    }
}
