package me.cheeder.planner.activities;

import jakarta.persistence.*;
import me.cheeder.planner.trip.Trip;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activities")
public class Activity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "occurs_at", nullable = false)
    private LocalDateTime occursAt;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getOccursAt() {
        return occursAt;
    }

    public void setOccursAt(LocalDateTime occursAt) {
        this.occursAt = occursAt;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Activity() {
    }

    public Activity(UUID id, LocalDateTime occursAt, Trip trip) {
        this.id = id;
        this.occursAt = occursAt;
        this.trip = trip;
    }
}
