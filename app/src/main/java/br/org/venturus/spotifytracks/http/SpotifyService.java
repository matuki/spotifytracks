package br.org.venturus.spotifytracks.http;

import br.org.venturus.spotifytracks.model.TracksContainer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by matuki on 1/25/17.
 */

public interface SpotifyService {

    public static final String BASE_URL = "https://api.spotify.com/v1/";

    @GET("artists/{id}/top-tracks")
    Call<TracksContainer> getArtistTopTracks(@Path("id") String artistId, @Query("country") String country);



}
