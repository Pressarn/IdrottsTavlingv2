/**
 * Created by SimonSchwieler on 2016-04-07.
 */
//rodi0231_sisc7379_arho2993

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

    private Scanner keyboard = new Scanner(System.in);
    ArrayList<Event> eventArrayList = new ArrayList<>();
    ArrayList<Result> resultArrayList = new ArrayList<>();
    ArrayList<Participant> participantArrayList = new ArrayList<>();
    private int id = 99;

    public String readString(String prompt) {
        System.out.print("> " + prompt);
        return keyboard.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        int number = keyboard.nextInt();
        keyboard.nextLine();
        return number;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        double number = keyboard.nextDouble();
        keyboard.nextLine();
        return number;
    }

    private String readCommand() {
        return readString("Command: ").toLowerCase();
    }

    private void initiate() {
        System.out.println("Welcome");
    }

    private void writeMenu() {
        System.out.println("Following commandos are possible:");
        System.out.println(" Add event");
        System.out.println(" Add participant");
        System.out.println(" Remove participant");
        System.out.println(" Add result");
        System.out.println(" Participant");
        System.out.println(" Teams");
        System.out.println(" Message (followed by your message)");
        System.out.println(" Reinitialize");
        System.out.println(" Exit");
    }

    private void manageCommand(String command) {

        if (command.matches("message.+")) {
            message(command);
        }
        else if (getEvent(command) != null){
            resultEvent(command);
        }

        else {
            switch (command) {
                case "add event":
                    addEvent();
                    break;
                case "add participant":
                    addParticipant();
                    break;
                case "remove participant":
                    removeParticipant();
                    break;
                case "add result":
                    addResultParticipant();
                    break;
                case "participant":
                    resultParticipant();
                    break;
                case "result event":
                    break;
                case "teams":
                    resultTeam();
                    break;
                case "message":
                    break;
                case "reinitialize":
                    reinitialize();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println("Wrong command: " + command);
                    writeMenu();
            }
        }
    }

    private void addEvent() {

        String eventName = "";
        while (eventName.isEmpty() || getEvent(eventName) != null) {
            eventName = readString("Enter name of event: ");
            eventName = eventName.trim();
            if (eventName.isEmpty()) {
                System.out.println("Names can't be empty!");
            } else if (getEvent(eventName) != null) {
                System.out.println("Duplicate event name!");
            }

        }
         normalizer(eventName);
//        eventName = eventName.toLowerCase();
//        char[] name = eventName.toCharArray();
//        name[0] = ("" + (name[0])).toUpperCase().charAt(0);
//        eventName = new String(name);

        int attemptsAllowed = readInt("Attempts allowed: ");

        while (attemptsAllowed <= 0){
            attemptsAllowed = readInt("Number to small, write something else: ");
        }

        String biggerBetterString = readString("Bigger better? (yes/no): ");
        boolean biggerBetter = false;
        boolean biggerBetterOptionDone = false;

        while(!biggerBetterOptionDone) {

            if (biggerBetterString.equalsIgnoreCase("yes") || biggerBetterString.equalsIgnoreCase("y")) {
                biggerBetter = true;
                biggerBetterOptionDone = true;
            } else if (biggerBetterString.equalsIgnoreCase("no") || biggerBetterString.equalsIgnoreCase("n")) {
                biggerBetter = false;
                biggerBetterOptionDone = true;
            } else {
                biggerBetterString = readString("Wrong insertion, write something else: ");
            }
        }

        eventName = eventName.trim().substring(0,1).toUpperCase() + eventName.substring(1).toLowerCase();
        Event e = new Event(eventName, attemptsAllowed, biggerBetter);
        eventArrayList.add(e);

        System.out.println(e.getEventName() + " has been added.");
    }

    private static String normalizer(String s){
        s = s.toLowerCase();
        char[] name = s.toCharArray();
        name[0] = ("" + (name[0])).toUpperCase().charAt(0);
        s = new String(name);
        return s;
    }

    private void addParticipant() {

        String firstName = readString("Enter participants first name: ");
        firstName = firstName.trim();

        while (firstName.equals("")) {
            System.out.println("Names can't be empty!");

            firstName = readString("Enter participants first name: ");
            firstName = firstName.trim();
        }

        String lastName = readString("Enter participants last name: ");
        lastName = lastName.trim();

        while (lastName.equals("")) {
            System.out.println("Names can't be empty!");

            lastName = readString("Enter participants last name: ");
            lastName = lastName.trim();
        }

        String teamName = readString("Enter participants team: ");
        teamName = teamName.trim();

        while (teamName.equals("")) {
            System.out.println("Names can't be empty!");

            teamName = readString("Enter participants team: ");
            teamName = teamName.trim();
        }

        firstName = normalizer(firstName);
        lastName = normalizer(lastName);
        teamName = normalizer(teamName);
//        firstName = firstName.trim().substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
//        lastName = lastName.trim().substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
//        teamName = teamName.trim().substring(0,1).toUpperCase() + teamName.substring(1).toLowerCase();
//        int id = 99;

        Participant p = new Participant(firstName, lastName, teamName, id);
        participantArrayList.add(p);

        System.out.println(p.getFirstName() + " " + p.getLastName() + " from "
                + p.getTeamName() + " with number " + p.getId() + " has been added.");
    }

    private void removeParticipant() {

        int enterID = readInt("Enter ID on participant you would like to remove: ");

        boolean participantFound = false;

        for (int x = 0; x < participantArrayList.size(); x++) {

            if (participantArrayList.get(x).getId() == (enterID)) {

                System.out.println(
                        participantArrayList.get(x).getFirstName() + " " + participantArrayList.get(x).getLastName()
                                + " from " + participantArrayList.get(x).getTeamName() + " with number "
                                + participantArrayList.get(x).getId() + " removed");
                participantArrayList.remove(x);
                participantFound = true;

            }
        }

        if (!participantFound) {
            System.out.println("No participant with number " + enterID + " exists.");

        }
    }

    private void addResultParticipant() {

        int writtenId = readInt("Enter participants ID: ");
        boolean participantFound = false;


        for (int x = 0; x < participantArrayList.size(); x++) {
            if (participantArrayList.get(x).getId() == (writtenId)) {
                addResultEvent(participantArrayList.get(x));
                participantFound = true;
            }
        }
        if (!participantFound) {
            System.out.println("No participant with number " + writtenId + " exists.");
        }
    }


    public void addResultEvent(Participant p) {

        String enterEventName = readString("Enter event: ");

        Event e = getEvent(enterEventName);
        if (e == null){
            System.out.println("No event called " + enterEventName + " exists.");
            return;
        }

        if (p.getAmountOfAttempts(e) < e.getAttemptsAllowed()) {

            double result = readDouble("Result for " + p.getFirstName() + " " + p.getLastName() + " from " + p.getTeamName()
                    + " in the event " + e.getEventName() + ": ");

            while (result < 0) {
                result = readDouble("To low value entered, write something else: ");
            }

            Result r = new Result(result, p, e);
            p.setResultToList(e, r);

            System.out.println("Result " + result + " in " + e.getEventName() + " has been registred.");
        }
        else {
            System.out.println("To many tries!");
        }
    }


    private void resultParticipant() {

        int participantID = readInt("Resultlist for participants.\nType in the ID number: ");
        Participant p = getParticipant(participantID);

        try {

            String allResults = "";
            for (Event e : eventArrayList) {


                ArrayList<Double> resultsByEvent = p.getResultsByEvent(e);
                String resultString = "";
                if(!resultsByEvent.isEmpty()){
                    for (Double r : resultsByEvent){
                        resultString += r;
                        if(resultsByEvent.indexOf(r) != resultsByEvent.size() -1){
                            resultString += ", ";
                        }
                    }
                    allResults += "Results for " + p + " in " + e.getEventName() + ": " + resultString + "\n";
                }
            }
            if (allResults.length() == 0){
                System.out.print("No results found");
            }
            else{
                System.out.print(allResults);
            }
        }
        catch(NullPointerException e){
            System.out.println("No participant with that ID");

        }
    }


    static class TopListPosition {
        public final String name;
        public final double score;
        public final Event event;

        public TopListPosition(String name, double score, Event event) {
            this.name = name;
            this.score = score;
            this.event = event;
        }
    }

    private void resultEvent(String command) {
        System.out.println("Results for " + command + ":");

        String event = command;
        Event e = getEvent(event);

        List<TopListPosition> topList = new ArrayList<>();
        for (Participant participant : this.participantArrayList) {
            ArrayList<ResultList> participantsResults = participant.getResultListByEvent(e);
            for (ResultList resultList : participant.getResultListByEvent(e)) {
                if (participantsResults.isEmpty()){
                    System.out.println("No event with that name or no results found.");

                }
                else{
                    topList.add(new TopListPosition(participant.getFirstName() + " " + participant.getLastName(),
                            resultList.getResult().getResult(),
                            resultList.getEvent()));
                }
            }
        }
        Collections.sort(topList, new Comparator<TopListPosition>() {
                    public int compare(TopListPosition obj1, TopListPosition obj2) {
                        if (e.getBiggerBetter())
                            return (int) (obj2.score - obj1.score);
                        else
                            return (int) (obj1.score - obj2.score);
                    }
                }
        );
        int placement = 1;
        TopListPosition previousPosition = null;
        for (int i = 0; i < topList.size(); i++) {
            TopListPosition currentPosition = topList.get(i);
            if (previousPosition != null) {
                if(currentPosition.score != previousPosition.score){
                    placement = i;
                    placement++;
                }
            }
            previousPosition = currentPosition;

            System.out.println((placement) + " " + currentPosition.score  + " " + currentPosition.name);

        }

    }


    private Integer getPosition(Participant p, final Event event) {
        List<TopListPosition> topList = new ArrayList<>();

        for (Participant participant : this.participantArrayList) {
            for (ResultList resultList : participant.getResults()) {
                if (resultList.getEvent().getEventName().equalsIgnoreCase(event.getEventName())) {
                    topList.add(new TopListPosition(participant.getFirstName() + " " + participant.getLastName(),
                            resultList.getResult().getResult(),
                            resultList.getEvent()));
                }
            }
        }

        Collections.sort(topList, new Comparator<TopListPosition>() {
            public int compare(TopListPosition obj1, TopListPosition obj2) {
                if (event.getBiggerBetter())
                    return (int) (obj2.score - obj1.score);
                else
                    return (int) (obj1.score - obj2.score);
            }
        });

        double firstPlace = topList.get(0).score;
        double secondPlace = 0;
        double thirdPlace = 0;

        for(TopListPosition topListPosition: topList) {
            double score = topListPosition.score;

            if(score >= firstPlace) {
                firstPlace = score;
            } else if(score >= secondPlace) {
                secondPlace = score;
            } else if(score >= thirdPlace) {
                thirdPlace = score;
            }
        }

        for(TopListPosition topListPosition: topList) {
            if (topListPosition.name.equals(p.getFirstName() + " " + p.getLastName())) {
                double score = topListPosition.score;
                if(firstPlace == score) {
                    return 1;
                } else if(secondPlace == score) {
                    return 2;
                } else if(thirdPlace == score) {
                    return 3;
                }
            }
        }

        return null;
    }


//    Integer getPosition(Participant p, Event event) {
//        List<TopListPosition> topList = new ArrayList<>();
//        for (Participant participant : this.participantArrayList) {
//            for (ResultList resultList : participant.getResults()) {
//                if (resultList.getEvent().getEventName().equalsIgnoreCase(event.getEventName())) {
//                    topList.add(new TopListPosition(participant.getFirstName() + " " + participant.getLastName(),
//                            resultList.getResult().getResult(),
//                            resultList.getEvent()));
//                }
//            }
//        }
//        Collections.sort(topList, new Comparator<TopListPosition>() {
//            public int compare(TopListPosition obj1, TopListPosition obj2) {
//                if (event.getBiggerBetter())
//                    return (int) (obj2.score - obj1.score);
//                else
//                    return (int) (obj1.score - obj2.score);
//            }
//        });
//        int i = 1;
//        for (TopListPosition tlp : topList) {
//            if (tlp.name.equals(p.getFirstName() + " " + p.getLastName()))
//                return i;
//            ++i;
//        }
//        return null;
//    }

    private static class TeamMedals {
        public int firstPlace;
        public int secondPlace;
        public int thirdPlace;

        public TeamMedals(int fp, int sp, int tp) {
            this.firstPlace = fp;
            this.secondPlace = sp;
            this.thirdPlace = tp;
        }
    }

    private static class TeamResult {
        public final TeamMedals teamMedals;
        public final String team;
        public TeamResult(TeamMedals teamMedals, String team) {
            this.teamMedals = teamMedals;
            this.team = team;
        }
    }

    private void resultTeam() {
        if (participantArrayList.isEmpty()) {
            System.out.println("No teams available");

        } else {
            Map<String, TeamMedals> teams = new HashMap<>();
            for (Participant participant : this.participantArrayList) {
                for (Event event : this.eventArrayList) {
                    Integer position = this.getPosition(participant, event);
                    if (position != null) {
                        TeamMedals teamMedals = teams.get(participant.getTeamName());
                        if (teamMedals == null) {
                            teamMedals = new TeamMedals(0, 0, 0);
                            teams.put(participant.getTeamName(), teamMedals);
                        }
                        if (position == 1) {
                            teamMedals.firstPlace++;
                        } else if (position == 2) {
                            teamMedals.secondPlace++;
                        } else if (position == 3) {
                            teamMedals.thirdPlace++;
                        }
                    }
                }
            }
            List<TeamResult> teamResults = new ArrayList<>();
            for (Entry<String, TeamMedals> entry : teams.entrySet()) {
                teamResults.add(new TeamResult(entry.getValue(), entry.getKey()));
            }
            Collections.sort(teamResults, new Comparator<TeamResult>() {
                public int compare(TeamResult obj1, TeamResult obj2) {
                    if (obj1.teamMedals.firstPlace != obj2.teamMedals.firstPlace) {
                        return obj2.teamMedals.firstPlace - obj1.teamMedals.firstPlace;
                    }
                    if (obj1.teamMedals.secondPlace != obj2.teamMedals.secondPlace) {
                        return obj2.teamMedals.secondPlace - obj1.teamMedals.secondPlace;
                    }
                    return obj2.teamMedals.thirdPlace - obj1.teamMedals.thirdPlace;
                }
            });
            System.out.println("1st    2nd     3rd    Team name");
            System.out.println("*******************************");
            for (TeamResult teamResult : teamResults) {
                System.out.println(teamResult.teamMedals.firstPlace + "    " +
                        teamResult.teamMedals.secondPlace + "       " +
                        teamResult.teamMedals.thirdPlace + "      " +
                        teamResult.team);
            }
        }
    }

    private void message(String message) {

        message = message.replaceFirst("message", "");

        if (message.length() > 56) {
            message = message.substring(0, 56);

        }

        char fill = ' ';

        String toPad = "#";
        String specialInLine = new String(new char[toPad.length() + 56 - message.length()]).replace('\0', fill) + toPad;
        System.out.println("");
        System.out.println("############################################################");
        System.out.println("#                                                          #");
        System.out.print("# " + message.toUpperCase());
        System.out.println(specialInLine);
        System.out.println("#                                                          #");
        System.out.println("############################################################");

    }

    private void reinitialize() {

        String message = " ALL DATA HAS BEEN REMOVED";
        Participant.reinitializeID();
        char fill = ' ';

        String toPad = "#";
        String specialInLine = new String(new char[toPad.length() + 57 - message.length()]).replace('\0', fill) + toPad;
        System.out.println("");
        System.out.println("############################################################");
        System.out.println("#                                                          #");
        System.out.print("#" + message);
        System.out.println(specialInLine);
        System.out.println("#                                                      	   #");
        System.out.println("############################################################");

        while (participantArrayList.size() > 0) {
            participantArrayList.clear();
        }

        while (resultArrayList.size() > 0) {
            resultArrayList.clear();
        }

        while (eventArrayList.size() > 0) {
            eventArrayList.clear();
        }
    }

    private void exit() {
        System.out.println("Good bye!");
        System.exit(0);
    }

    private void execute() {
        writeMenu();
        while (true) {
            String command = readCommand();
            manageCommand(command);
        }
    }

    private Participant getParticipant(int id) {
        for (Participant p : participantArrayList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private Event getEvent (String eventName){
        for (Event e : eventArrayList){
            if (e.getEventName().equalsIgnoreCase(eventName)){
                return e;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Main competition = new Main();
        competition.initiate();
        competition.execute();
        competition.exit();
    }

}
