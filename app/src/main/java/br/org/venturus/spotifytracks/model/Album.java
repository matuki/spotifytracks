package br.org.venturus.spotifytracks.model;

import java.util.List;

/**
 * Created by matuki on 1/25/17.
 */

public class Album {

    List<Image> images;

    String name;


    public String getName() {
        return name;
    }

    public List<Image> getImages() {
        return images;
    }

}
