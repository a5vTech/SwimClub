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
