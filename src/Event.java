/**
 * Created by SimonSchwieler on 2016-04-07.
 */
//rodi0231_sisc7379_arho2993

public class Event {

    private String eventName;
    private int attemptsAllowed;
    private boolean biggerBetter;
    private int medal = 0;

    public Event(String eventName, int attemptsAllowed, boolean biggerBetter) {
        this.eventName = eventName;
        this.attemptsAllowed = attemptsAllowed;
        this.biggerBetter = biggerBetter;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAttemptsAllowed() {
        return attemptsAllowed;
    }

    public boolean getBiggerBetter() {
        return biggerBetter;
    }

    public int ammountOfMedalsAwarded(){
        return medal;
    }

    public void awardMedal(){
        ++medal;
    }

    public void resetMedals(){
        medal = 0;
    }

    public String toString(){
        return eventName + "";
    }


}


