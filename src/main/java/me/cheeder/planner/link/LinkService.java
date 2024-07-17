package me.cheeder.planner.link;

import me.cheeder.planner.trip.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository repository;

    public LinkService(LinkRepository repository) {
        this.repository = repository;
    }

    public LinkResponse registerLink(LinkRequestPayload payload,
                                     Trip trip) {
        Link newLink = new Link(
                payload.url(),
                payload.title(),
                trip
        );

        this.repository.save(newLink);

        return new LinkResponse(newLink.getId());
    }

    public List<LinkData> getAllLinksFromTrip(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(
                activity -> new LinkData(
                        activity.getId(),
                        activity.getTitle(),
                        activity.getUrl()
                )
        ).toList();
    }
}
