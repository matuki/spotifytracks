package br.org.venturus.spotifytracks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import br.org.venturus.spotifytracks.http.SpotifyService;
import br.org.venturus.spotifytracks.model.Image;
import br.org.venturus.spotifytracks.model.Track;
import br.org.venturus.spotifytracks.model.TracksContainer;
import br.org.venturus.spotifytracks.view.TrackListAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static br.org.venturus.spotifytracks.http.SpotifyService.BASE_URL;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    TrackListAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

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
                        List<Image> images = track.getAlbum().getImages();
                        for (Image image : images) {
                            Log.d("debug", "Image url: " + image.getUrl());
                        }
                    }

                    mAdapter = new TrackListAdapter(tracks);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<TracksContainer> call, Throwable t) {
                Log.d("debug", "Failure getting tracks:S " + t.getMessage());
            }
        });
    }
}
