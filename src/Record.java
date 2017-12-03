public class Record {
    private int timeInSeconds;
    private Discipline discipline;
    private String competition;
    private String date;

    public Record(Discipline discipline, String competition, String date, int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
        this.competition = competition;
        this.date = date;
        this.discipline = discipline;

    }

//Lav getter som returner Disciplin og tid

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public String getDate() {
        return date;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ("" + discipline + ";" + competition + ";" + date + ";" + timeInSeconds);
    }
}