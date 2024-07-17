package me.cheeder.planner.link;

import jakarta.persistence.*;
import me.cheeder.planner.trip.Trip;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Link() {
    }

    public Link(UUID id, String url, String title, Trip trip) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.trip = trip;
    }

    public Link(String url, String title, Trip trip) {
        this.title = title;
        this.url = url;
        this.trip = trip;
    }
}
