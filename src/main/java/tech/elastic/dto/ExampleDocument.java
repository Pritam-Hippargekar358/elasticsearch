package tech.elastic.dto;

import java.time.LocalDate;
import java.util.List;

public class ExampleDocument {
    private String name;
    private String email;
    private Boolean isActive;
    private Float rating;
    private LocalDate dob;
    private String gender;
    private Integer numberOfChild;
    private Address address;
    private Location location;
    private List<Comment> comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getNumberOfChild() {
        return numberOfChild;
    }

    public void setNumberOfChild(Integer numberOfChild) {
        this.numberOfChild = numberOfChild;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ExampleDocument{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", rating=" + rating +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", numberOfChild=" + numberOfChild +
                ", address=" + address +
                ", location=" + location +
                ", comments=" + comments +
                '}';
    }
}
