package src.main.java;

import java.time.LocalDate;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class CDs extends LibraryItem{
    @CsvBindByName(column = "TITLE")
    private String name;

    @CsvBindByName(column = "ARTIST")
    private String artist;

    @CsvBindByName(column = "GENRE")
    private String genre;

    @CsvBindByName(column = "RELEASEDATE")
    @CsvDate("yyyy-MM-dd")
    private LocalDate releaseDate;

    @CsvBindByName(column = "RATING")
    private float audienceScore;


    // unborrowed CDs
    public CDs(){
        //empty constructor required for Java Beans
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public float getAudienceScore() {
        return audienceScore;
    }

    public void setAudienceScore(float audienceScore) {
        this.audienceScore = audienceScore;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setYear(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        //inheriting toString method from LibraryItem and adding CD specific fields
        return super.toString() + "CDs [name=" + name + ", genre=" + genre + ", artist=" + artist + ", audienceScore=" + audienceScore + ", rottenTomatoes=" + ", releaseDate=" + releaseDate + "]";
    }
}
