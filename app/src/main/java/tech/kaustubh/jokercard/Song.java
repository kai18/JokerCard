package tech.kaustubh.jokercard;

/**
 * Created by kaustubh on 6/10/17.
 */

public class Song {
    private String title = null;
    private String album = null;
    private String artist = null;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

}
