/**
 * Created by SimonSchwieler on 2016-04-07.
 */

//rodi0231_sisc7379_arho2993


import java.util.ArrayList;

public class Participant {

    private static int next_id = 99;

    private String firstName;
    private String lastName;
    private String teamName;
    private int id;
    private ArrayList<ResultList> results = new ArrayList<>();

    public Participant(String firstName, String lastName, String teamName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamName = teamName;
        this.id = ++Participant.next_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getId() {
        return id;
    }

    public static void reinitializeID(){
        next_id = 99;
    }

    public void setResultToList(Event event, Result result, Participant participant){

        results.add(new ResultList(event, result, participant));


    }

    public Double getBestResultBiggerBetterTrue(Event event) {

        if (!getResultsByEvent(event).isEmpty()) {

            double max = Double.MIN_VALUE;
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i).getEvent().equals(event) && results.get(i).getResultFromList().getResult() > max) {
                    max = results.get(i).getResultFromList().getResult();
                }
            }
            return max;

        }
        else{
            return -1.0;
        }
    }

    public Double getBestResultBiggerBetterFalse(Event event){
        {

            if (!getResultsByEvent(event).isEmpty()) {

                double min = Double.MAX_VALUE;
                for (int i = 0; i < results.size(); i++) {
                    if (results.get(i).getEvent().equals(event) && results.get(i).getResultFromList().getResult() < min) {
                        min = results.get(i).getResultFromList().getResult();
                    }
                }
                return min;

            }
            else{
                return -1.0;
            }
        }

    }

    public ArrayList<Double> getResultsByEvent(Event event){

        ArrayList<Double> gatheredResults = new ArrayList<>();
        for (ResultList r : results){
            if(event.getEventName().equalsIgnoreCase(r.getEvent().getEventName())) {
                gatheredResults.add(r.getResultFromList().getResult());

            }

        }

        return gatheredResults;
    }

    public String toString(){
        return firstName;
    }

    public int getAmountOfAttempts (Event e){
        int counter = 0;
        for(ResultList r : results){
            if (e.getEventName().equalsIgnoreCase(r.getEvent().getEventName())){
                counter++;
            }
        }
        return counter;
    }

    public ArrayList<ResultList> getResults() {
        return results;
    }

}
