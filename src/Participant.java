import java.util.ArrayList;
import java.util.List;

public class Participant {

    private static int next_id = 99;

    private String firstName;
    private String lastName;
    private String teamName;
    private int id;
    private ArrayList<ResultList> results = new ArrayList<ResultList>();



    public Participant(String firstName, String lastName, String teamName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamName = teamName;
        this.id = ++Participant.next_id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public static void reinitializeID(){
        next_id = 99;
    }

    public void setResult(Event e, double score){

        ArrayList<ResultList> tmpList = this.getResults();

        Result r = new Result(score);

        ResultList rl = new ResultList(e,r);

        tmpList.add(rl);

        this.setResults(tmpList);
    }


    public void setResult2(Event e, Result result){

        ArrayList<ResultList> tmpList = this.getResults();

        Result r = new Result(result.getResult());

        ResultList rl = new ResultList(e,r);

        tmpList.add(rl);

        this.setResults(tmpList);
    }

    public void setResultToList(Event event, Result result){

        results.add(new ResultList(event, result));


    }

    public ArrayList<Double> getResultsByEvent(Event event){

        ArrayList<Double> gatheredResults = new ArrayList<>();
        for (ResultList r : results){
            if(event.getEventName().equalsIgnoreCase(r.getEvent().getEventName())) {
                gatheredResults.add(r.getResult().getResult());

            }

        }

        return gatheredResults;
    }

    public List<ResultList> getResultListByEvent(Event event) {

        List<ResultList> gatheredEvents = new ArrayList<>();

        for (ResultList rl : results) {

            if ((event.getEventName().equalsIgnoreCase(rl.getEvent().getEventName()))) {
                gatheredEvents.add(rl);
            }
        }

        return gatheredEvents;
    }

    public void countDownAttempts(Event e){

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
    public void setResults(ArrayList<ResultList> results) {
        this.results = results;
    }



}