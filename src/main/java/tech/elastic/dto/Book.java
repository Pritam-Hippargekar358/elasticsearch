package tech.elastic.dto;

public class Book //implements Serializable
{
    private String title;
    private String publish;
    private String date;
    private Authors authors;
    private Location location;

    public Book() {
    }

    public Book(String title, String publish, String date, Authors authors, Location location) {
        this.title = title;
        this.publish = publish;
        this.date = date;
        this.authors = authors;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publish='" + publish + '\'' +
                ", date='" + date + '\'' +
                ", authors=" + authors +
                ", location=" + location +
                '}';
    }
}
