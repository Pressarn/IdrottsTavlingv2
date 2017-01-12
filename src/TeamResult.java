/**
 * Created by SimonSchwieler on 2016-08-12.
 */


public class TeamResult {

    private TeamMedals teamMedals;
    private String team;

    public TeamResult(TeamMedals teamMedals, String team) {
        this.teamMedals = teamMedals;
        this.team = team;
    }
    public TeamMedals getTeamMedals(){
        return teamMedals;
    }

    public String getTeam(){
        return team;
    }
}