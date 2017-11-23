import java.util.ArrayList;

public class Coach extends Employee {
    //Fields                                                                          
    protected ArrayList<Member> memberlist = new ArrayList<>();

    //Constructor                                                                     
    public Coach(String firstName, String lastName, String adress, int phoneNumber) {
        super("Coach", firstName, lastName, adress, phoneNumber);

    }

    //Constructor der tager i mod et array                                            
    /*public Coach(ArrayList<Member> memberlist){
        this. memberlist = memberlist;                                                
    } */


    //Getters                                                                         

    public ArrayList<Member> getMemberlist() {
        return memberlist;
    }


    //Setters                                                                         

    public void setMemberlist(ArrayList<Member> memberlist) {
        this.memberlist = memberlist;
    }
}                                                                                     
