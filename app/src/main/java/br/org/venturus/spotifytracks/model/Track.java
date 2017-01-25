package br.org.venturus.spotifytracks.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by matuki on 1/25/17.
 */

public class Track {

    Album album;

    List<Artist> artists;

    String id;

    String name;

    @SerializedName("preview_url")
    String previewUrl;

    @SerializedName("track_number")
    String trackNumber;

    String uri;

    public Album getAlbum() {
        return album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public String getUri() {
        return uri;
    }
}
