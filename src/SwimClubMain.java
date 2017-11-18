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
                    System.out.print("Medlemmets fornavn: ");
                    String firstname = userInput.next();
                    System.out.print("Medlemmets efternavn: ");
                    String lastname = userInput.next();
                    System.out.print("Medlemmets alder: ");
                    int age = userInput.nextInt();
                    System.out.print("Medlemmets køn (M/D): ");
                    String gender = userInput.next();
                    System.out.print("Medlemmets adresse: ");
                    userInput.nextLine(); //Empty read line to finish reading current line
                    String adress = userInput.nextLine();
                    System.out.print("Medlemmets status (Aktiv/Passiv (A/P): ");
                    String strMembershipStatus = userInput.next();
                    Boolean membershipStatus = false; //True = aktiv - false = passiv
                    if(strMembershipStatus.equalsIgnoreCase("A")){
                        membershipStatus = true;
                    }
                    System.out.print("Enter membership type (Competitor or  ");
                    Boolean competitor; //true = konkurrencesvømmer - false = motionist

                    //TODO Create member method - createMember(); / Create member object and add to Arraylist members
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
                    System.out.println("Vælg hvilken disciplin du vil se en top 5 af");
                    System.out.println("1) Butterfly\n2)Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
                    option = userInput.nextInt();
                    //TODO (Move switch to method) showTopFive(Diciplin);
                    switch (option){
                        case 1: //Butterfly top 5
                            break;
                        case 2: //Crawl
                            break;
                        case 3: //Rygcrawl
                            break;
                        case 4: //Brystsvømning
                            break;
                        case 5: //Hundesvømning
                            break;
                        default: //Hvis andet end "Disciplin"
                            break;

                    }
                    //TODO add showTopFive(); method
                } else {
                    System.out.println("Register Record");
                    //TODO Show list of competitors
                    System.out.println("Enter id of the member you want to add the record to");
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
