package src.main.java;

import java.time.LocalDate;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class Books extends LibraryItem {
    @CsvBindByName(column = "TITLE")
    private String title;

    @CsvBindByName(column = "AUTHOR")
    private String author;

    @CsvBindByName(column = "PUBLISHER")
    private String publisher;

    @CsvBindByName(column = "ISBN")
    private String isbn;

    @CsvBindByName(column = "SERIES")
    private String series;

    @CsvBindByName(column = "RATING")
    private Double rating;

    @CsvBindByName(column = "DESCRIPTION")
    private String description;

    @CsvBindByName(column = "LANGUAGE")
    private String language;

    @CsvBindByName(column = "GENRE")
    private String genre;

    @CsvBindByName(column = "RELEASEDATE")
    @CsvDate("yyyy-MM-dd")
    private LocalDate releaseDate;

//unborrowed books, borrowid and borrow date stay null as default
    public Books(){
        //empty constructor required for Java Beans
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        //inherits toString method from LibraryItem and adds the book specific fields
        return super.toString() + "Books [title=" + title + ", author=" + author + ", publisher=" + publisher + ", isbn=" + isbn + ", series=" + series + ", rating=" + rating + ", description=" + description + ", language=" + language + ", genre=" + genre + ", releaseDate=" + releaseDate + "]";
    }

}
