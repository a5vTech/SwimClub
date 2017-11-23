import java.util.*;

public class Member {
    private String firstName;
    private String lastName;
    private int age;
    private Boolean gender; //True = Kvinde, False = Mand 
    private String address;
    private String email;
    private int phoneNumber;
    private Boolean membershipStatus; //True = aktivt, False = passivt 
    private Boolean membershipType; //True = KonkurrencesvÃƒÂ¸mmer, False = Motionist
    private Boolean membershipAgeGroup = false; //True = Junior, False = Senior
    private double subscription;
    private Employee coach;
    private Boolean hasPaid;
    private ArrayList<Discipline> disciplineList = new ArrayList<>();

    // Constructors
    public Member(String firstName, String lastName, int age, boolean gender, String address, String email, int phoneNumber, boolean membershipStatus, boolean hasPaid){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hasPaid = hasPaid;
        this.membershipStatus = membershipStatus;
        subscription = Subscription();

    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public double getSubscription(){
        return subscription;
    }

    public boolean getHasPaid(){
        return hasPaid;
    }

    public int getAge(){
        return age;
    }

    public String getAddress(){
        return address;
    }

    public int getPhoneNumber(){
        return phoneNumber;
    }

    public ArrayList<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public String strCoach(){
        if(coach == null){
            return " no coach";
        }
        String strCoach = "";
        strCoach += coach.getTitle()+",";
        strCoach += coach.getFirstName()+",";
        strCoach += coach.getLastName()+",";
        strCoach += coach.getPhoneNumber();
        return strCoach;
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


    public String strDiscipline(){
        Discipline discipline;
        String strDiscipline = "";
        for(int i = 0; i < disciplineList.size(); i++){
            strDiscipline += ","+ disciplineList.get(i);
        }
        return strDiscipline;
    }

    public String printDiscipline(){
        Discipline discipline;
        String strDiscipline = "";
        for(int i = 0; i < disciplineList.size(); i++){
            strDiscipline += " " + disciplineList.get(i);
        }
        return strDiscipline;
    }

    public String displayDiscipline(){
        Discipline discipline;
        String displayDiscipline = "";
        for(int i = 0; i < disciplineList.size(); i++){
            displayDiscipline += "" + (i+1) + ") " + disciplineList.get(i)+"\n";
        }
        return displayDiscipline;
    }

    public double Subscription(){
        if(membershipStatus = true && age < 18 && age > 0){
            membershipAgeGroup = true;
            return 1000;
        }
        if(membershipStatus = true && age >= 18 && age < 60){
            membershipAgeGroup = false;
            return 1600;
        }
        if(membershipStatus = false && age >= 60){
            return 1600 * 0.75;
        }
        return 500;
    }

    public String getStrGender(){
        if(gender = true){
            return "Kvinde";
        }
        return "Mand";
    }

    public String getStrMembershipStatus(){
        if(membershipStatus = true){
            return "Aktivt";
        }
        return "Passivt";
    }

    public Boolean getMembershipType() {
        return membershipType;
    }

    public String getStrMembershipType(){
        if(membershipType = true){
            return "Konkurrencesvømmer";
        }
        return "Motionist";
    }

    public void setHasPaid(boolean hasPaid){
        this.hasPaid = true;
    }

    public void setCoach(Employee coach){
        this.coach = coach;
    }
    public void updateDiciplineList(Discipline discipline){
        disciplineList.add(discipline);
    }

    public void setMembershipType(Boolean membershipType){
        this.membershipType = membershipType;
    }

    public String toString(){
        String strCoach = strCoach();
        String strDiscipline = strDiscipline();
        return String.format("%s,%s,%d,%b,%s,%s,%d,%b,%b,%s%s, %b",firstName,lastName,age,gender,address,email,phoneNumber,membershipStatus,membershipType,strCoach,strDiscipline, hasPaid);
    }
}

