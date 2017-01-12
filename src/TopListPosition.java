import java.util.Comparator;

/**
 * Created by SimonSchwieler on 2016-08-11.
 */

public class TopListPosition implements Comparable<TopListPosition> {

    private String name;
    private double score;
    private Event event;

    public TopListPosition(String name, double score, Event event) {
        this.name = name;
        this.score = score;
        this.event = event;
    }

    @Override
    public int compareTo(TopListPosition o) {
        return 0;
    }

    public class ScoreComparator implements Comparator<TopListPosition> {
        public int compare(TopListPosition t1, TopListPosition t2){

            if (t1.score == t2.score)
                return 0;
            else if (t1.score > t2.score)
                return 1;
            else
                return -1;

        }
    }

    public String toString(){
        return name + " " + event.getEventName() + " " + score + " ";
    }

    public String getName(){
        return name;
    }

    public double getScore(){
        return score;
    }

    public Event getEvent(){
        return event;
    }



}