/**
 * Created by SimonSchwielerz on 2016-08-12.
 */
public  class TeamMedals {


    private int firstPlace;
    private int secondPlace;
    private int thirdPlace;

    public TeamMedals(int fp, int sp, int tp) {
        this.firstPlace = fp;
        this.secondPlace = sp;
        this.thirdPlace = tp;
    }

    public int getFirstPlace(){
        return firstPlace;
    }
    public int getSecondPlace(){
        return secondPlace;
    }
    public int getThirdPlace(){
        return thirdPlace;
    }
    public void incrementFirstPlace(){
        firstPlace++;
    }
    public void incrementSecondPlace(){
        secondPlace++;
    }
    public void incrementThirdPlace(){
        thirdPlace++;
    }
}