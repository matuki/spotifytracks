package br.org.venturus.spotifytracks.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matuki on 1/25/17.
 */

public class SpotifyTopArtistTracksResponse {

    List<Track> tracks;

    public SpotifyTopArtistTracksResponse() {
        tracks = new ArrayList<Track>();
    }

    public static SpotifyTopArtistTracksResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        SpotifyTopArtistTracksResponse spotifyTopArtistTracksResponse = gson.fromJson(response, SpotifyTopArtistTracksResponse.class);
        return spotifyTopArtistTracksResponse;
    }
}
