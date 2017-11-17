import java.util.Scanner;

public class SwimClubMain {
    public static void main(String[] args) {
        mainMenu();

    }

    //Methods
    public static void mainMenu() {
        Scanner userInput = new Scanner(System.in);
        //Main menu
        System.out.println("Login som:\n1) Formand\n2) Kasserer\n3) Træner");


        int option = userInput.nextInt();

        switch (option) {
            /*
                Case 1 = Formand menu
                Case 2 = Kasserer menu
                Case 3 = Træner menu
            */


            case 1:
                //Formand
                System.out.println("1) Opret nyt medlem\n2) Opdater medlems oplysninger");
                option = userInput.nextInt();
                if (option == 1) {
                    //Opret medlem
                    System.out.print("Enter members first name: ");
                    String firstname = userInput.next();
                    System.out.print("Enter members last name: ");
                    String lastname = userInput.next();
                    System.out.print("Enter members age: ");
                    int age = userInput.nextInt();
                    System.out.print("Male or Female (M or F): ");
                    String gender = userInput.next();
                    System.out.print("Enter members adress: ");
                    userInput.nextLine(); //Empty read line to finish reading current line
                    String adress = userInput.nextLine();
                    System.out.print("Enter membership status (Active or Passive (A/P): ");
                    Boolean membershipStatus; //True = aktiv - false = passiv
                    Boolean competitor; //true = konkurrencesvømmer - false = motionist

                    //TODO Create member method - createMember();
                } else {
                    //Opdater medlem
                    //TODO Create update member method - updateMember();
                }
                break;
            case 2:
                //Kasserer
                System.out.println("1) Se oversigt over medlemmer i restance\n2) Registrer betalinger");
                option = userInput.nextInt();

                if (option == 1) {
                    //TODO add  showMembers("Restance"); method
                } else {
                    //TODO add registerPayment(); method
                }
                break;
            case 3:
                //Træner
                System.out.println("1) Se top 5\n2) Registrer rekorder");
                option = userInput.nextInt();
                if (option == 1) {
                    //TODO add showTopFive(); method
                } else {
                    //TODO add registerRecords(); method
                }
                break;

        }
    }

    /*  METHODS
    *   createMember();
    *   updateMember();
    *   loadMemberData();
    *
    *   showMembers();
    *   registerPayments();
    *   registerRecords();
    */

}
