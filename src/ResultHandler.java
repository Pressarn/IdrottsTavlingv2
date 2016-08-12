/**
 * Created by SimonSchwieler on 2016-04-07.
 */

//rodi0231_sisc7379_arho2993

public class ResultHandler {

    private Result result;
    private Event event;
    private Participant participant;

    public ResultHandler(Event e, Result r, Participant p) {
        this.event = e;
        this.participant = p;
        this.result = r;
    }

    public Result getResultFromList() {
        return result;
    }

    public Participant getParticipant(){
        return participant;
    }

    public Event getEvent() {
        return event;
    }

    public String toString(){
        return "   ---" + result.getResult() + " " + event.getEventName() + " " +  participant.getFirstName() + " " + participant.getLastName();
    }
}

