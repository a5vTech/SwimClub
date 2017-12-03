import java.util.*;

public class Member implements Comparable<Member> {
    private String firstName;
    private String lastName;
    private int age;
    private Boolean gender; //True = Kvinde, False = Mand 
    private String address;
    private String email;
    private int phoneNumber;
    private Boolean membershipStatus; //True = aktivt, False = passivt 
    private Boolean membershipType = false; //True = KonkurrencesvÃ?Â¸mmer, False = Motionist
    private Boolean membershipAgeGroup = false; //True = Junior, False = Senior
    private double subscription;
    private Employee coach;
    private Boolean arrear = false; //True = i restance, False = ikke i restance
    private int bestRecord = 99999999;

    private ArrayList<Discipline> disciplineList = new ArrayList<>();
    private ArrayList<Record> recordList = new ArrayList<>();


    // Constructors
    public Member(String firstName, String lastName, int age, boolean gender, String address, String email, int phoneNumber, boolean membershipStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipStatus = membershipStatus;
        subscription = Subscription();

    }

    public Member(String firstName, String lastName, int age, boolean gender, String address, String email, int phoneNumber, boolean membershipStatus, boolean arrear) {
        this(firstName, lastName, age, gender, address, email, phoneNumber, membershipStatus);
        this.arrear = arrear;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public double getSubscription() {
        return subscription;
    }

    public boolean getArrear() {
        return arrear;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public String strCoach() {
        if (coach == null) {
            return "";
        }
        String strCoach = "";
        strCoach += coach.getTitle() + ";";
        strCoach += coach.getFirstName() + ";";
        strCoach += coach.getLastName() + ";";
        strCoach += coach.getAddress() + ";";
        strCoach += coach.getPhoneNumber();
        return strCoach;
    }

    public void setButterflyRecord() {
        int[] butterflyRecords = new int[recordList.size()];
        for (int i = 0; i < recordList.size(); i++) {
            if (recordList.get(i).getDiscipline().getDiscipline().equalsIgnoreCase("butterfly")) {
                butterflyRecords[i] = recordList.get(i).getTimeInSeconds();
            }
        }
        Arrays.sort(butterflyRecords);
        this.bestRecord = butterflyRecords[0];
    }


    public void setRecord(Discipline discipline) {
        String strDiscipline = discipline.getDiscipline();

        int[] disciplineRecords = new int[recordList.size()];
        for (int i = 0; i < recordList.size(); i++) {
            for (int k = 0; k < recordList.size(); k++) {
                if (recordList.get(k).getDiscipline().getDiscipline().equalsIgnoreCase(strDiscipline)) {
                    disciplineRecords[i] = recordList.get(k).getTimeInSeconds();
                }
            }
        }
        Arrays.sort(disciplineRecords);
        bestRecord = disciplineRecords[0];
    }


    public int getBestRecord() {
        return bestRecord;
    }

    public int compareTo(Member other) {
        if (bestRecord > other.getBestRecord()) {
            return 1;
        } else if (bestRecord < other.getBestRecord()) {
            return -1;
        } else {
            return 0;
        }
    }


    public String getStrGroup() {
        String strGroup = "Junior";
        if (!membershipAgeGroup) {
            strGroup = "Senior";
        }
        return strGroup;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMembershipStatus(Boolean membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public void setMembershipAgeGroup(Boolean membershipAgeGroup) {
        this.membershipAgeGroup = membershipAgeGroup;
    }

    public String strRecords() {
        String strRecords = "";
        for (int i = 0; i < recordList.size(); i++) {
            strRecords += ";" + recordList.get(i).getDiscipline() + ";" + recordList.get(i).getCompetition() + ";" + recordList.get(i).getDate() + ";" + recordList.get(i).getTimeInSeconds();
        }
        return strRecords;
    }

    public String strDiscipline() {
        Discipline discipline;
        String strDiscipline = "";
        for (int i = 0; i < disciplineList.size(); i++) {
            strDiscipline += ";" + disciplineList.get(i);
        }
        return strDiscipline;
    }

    public String printDiscipline() {
        Discipline discipline;
        String strDiscipline = "";
        for (int i = 0; i < disciplineList.size(); i++) {
            strDiscipline += " " + disciplineList.get(i);
        }
        return strDiscipline;
    }

    public String displayDiscipline() {
        String displayDiscipline = "";
        for (int i = 0; i < disciplineList.size(); i++) {
            displayDiscipline += "" + (i + 1) + ") " + disciplineList.get(i) + "\n";
        }
        return displayDiscipline;
    }

    public double Subscription() {
        if (membershipStatus = true && age < 18 && age > 0) {
            membershipAgeGroup = true;
            return 1000;
        }
        if (membershipStatus = true && age >= 18 && age < 60) {
            membershipAgeGroup = false;
            return 1600;
        }
        if (membershipStatus = false && age >= 60) {
            return 1600 * 0.75;
        }
        return 500;
    }

    public String getStrGender() {
        if (gender == true) {
            return "Kvinde";
        }else{
            return "Mand";
        }
    }

    public String getStrMembershipStatus() {
        if (membershipStatus = true) {
            return "Aktivt";
        }
        return "Passivt";
    }

    public Boolean getMembershipType() {
        return membershipType;
    }

    public String getStrMembershipType() {
        if (membershipType == true) {
            return "Konkurrencesvømmer";
        }
        return "Motionist";
    }

    public void setArrear(boolean arrear) {
        this.arrear = arrear;
    }

    public void setCoach(Employee coach) {
        this.coach = coach;
    }

    public void updateDiciplineList(Discipline discipline) {

        disciplineList.add(discipline);
    }

    public void updateRecordList(Record record) {
        recordList.add(record);
    }

    public int getDisciplineAmount() {
        int amount = 0;
        for (Discipline discipline : disciplineList) {
            amount++;
        }
        return amount;
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public void setMembershipType(Boolean membershipType) {
        this.membershipType = membershipType;
    }

    public Discipline getDiscipline(int option) {
        return disciplineList.get(option);
    }

    public String getStrMember() {
        String strCoach = strCoach();
        String strDiscipline = strDiscipline();
        String strRecords = strRecords();
        int disciplineAmount = getDisciplineAmount();
        if (membershipType) {
            return String.format("%s;%s;%d;%b;%s;%s;%d;%b;%b;%s;%b;%d%s%s", firstName, lastName, age, gender, address, email, phoneNumber, membershipStatus, membershipType, strCoach, arrear, disciplineAmount, strDiscipline, strRecords);
        } else {
            return String.format("%s;%s;%d;%b;%s;%s;%d;%b;%b;%s;%b;%d%s%s", firstName, lastName, age, gender, address, email, phoneNumber, membershipStatus, membershipType, strCoach, arrear, disciplineAmount, strDiscipline, strRecords);
        }

    }

    public String toString() {
        String strCoach = strCoach();
        String strDiscipline = strDiscipline();
        String strRecords = strRecords();
        int disciplineAmount = getDisciplineAmount();
        return String.format("%s;%s;%d;%b;%s;%s;%d;%b;%b;%s;%b;%d%s%s", firstName, lastName, age, gender, address, email, phoneNumber, membershipStatus, membershipType, strCoach, arrear, disciplineAmount, strDiscipline, strRecords);
    }
}

