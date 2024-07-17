package me.cheeder.planner.participant;

import me.cheeder.planner.trip.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    private ParticipantRepository repository;

    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }

    public void registerParticipantsToEvent(List<String> participantsToInvite,
                                            Trip trip) {

        List<Participant> participants = participantsToInvite.stream().map(
                email -> new Participant(email, trip)
        ).toList();

        this.repository.saveAll(participants);

        System.out.println(participants.getFirst().getId());
    }

    public ParticipantCreateResponse registerParticipantToEvent(String email,

                                                                Trip trip) {
        Participant newParticipant = new Participant(email, trip);
        this.repository.save(newParticipant);

        return new ParticipantCreateResponse(newParticipant.getId());
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId) {}

    public void triggerConfirmationEmailToParticipant(String email) {}

    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(
                participant -> new ParticipantData(
                        participant.getId(),
                        participant.getName(),
                        participant.getEmail(),
                        participant.getConfirmed())
        ).toList();
    }
}
