package me.cheeder.planner.activity;

import me.cheeder.planner.trip.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    private final ActivityRepository repository;

    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public ActivityResponse registerActivity(ActivityRequestPayload payload,
                                             Trip trip) {
        Activity newActivity = new Activity(payload.title(), payload.occurs_at(), trip);

        this.repository.save(newActivity);

        return new ActivityResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivitiesFromTrip(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(
                activity -> new ActivityData(
                        activity.getId(),
                        activity.getTitle(),
                        activity.getOccursAt()
                )
        ).toList();
    }
}
