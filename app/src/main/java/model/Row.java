package model;

import java.util.ArrayList;



public class Row {

    private ArrayList<Album> albums;

    private ArrayList<Genere> generes;

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public ArrayList<Genere> getGeneres() {
        return generes;
    }

    public void setGeneres(ArrayList<Genere> generes) {
        this.generes = generes;
    }
}
