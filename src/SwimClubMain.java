import java.util.*;
import java.io.*;

public class SwimClubMain {
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    public static ArrayList<Member> memberList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        //Import Data
        importEmployeeData();
        importMemberData();

        //Run Program
        mainMenu();

        //Update files
        exportEmployeeData();
        exportMemberData();

    }

    //Methods

    public static void mainMenu() {
        Scanner userInput = new Scanner(System.in);
        //Main menu
        boolean quit = false;
        do {
            System.out.println("Log ind som:\n1) Formand\n2) Kasserer\n3) Træner\n0) Afslut program");
            int option = userInput.nextInt();

            switch (option) {
               /*
                   Case 1 = Formand menu
                   Case 2 = Kasserer menu
                   Case 3 = Træner menu
                   Case 0 = Afslut program
               */

                case 1:
                    //Formand
                    System.out.println("1) Opret nyt medlem\n2) Opdater medlems oplysninger\n3) Opret ny ansat\n4) Opdater ansattes oplysninger\n0) Tilbage");
                    option = userInput.nextInt();
                    if (option == 1) {
                        createMember(userInput);
                    } else if (option == 2) {
                        //Opdater medlem
                        //TODO Create update member method - updateMember();
                    } else if (option == 3) {
                        //Opret ny ansat
                        createEmployee(userInput);
                    } else if (option == 4) {
                        //Opdater ansat
                        //TODO Create update employee method - updateEmployee();
                        break;

                    } else {
                        //Gå tilbage til mainMenu
                    }
                    break;
                case 2:
                    //Kasserer
                    System.out.println("1) Se oversigt over medlemmer i restance\n2) Registrer betalinger\n0) Tilbage");
                    option = userInput.nextInt();

                    if (option == 1) {
                        //TODO add  showMembers("Restance"); method
                    } else if (option == 0) {
                        //Gå tilbage til mainMenu
                        break;
                    } else {
                        //TODO add registerPayment(); method
                    }
                    break;
                case 3:
                    //Træner
                    System.out.println("1) Se top 5\n2) Registrer rekorder\n3) Registrer stævne\n0) Tilbage");
                    option = userInput.nextInt();
                    if (option == 1) {
                        System.out.println("Vælg hvilken disciplin du vil se en top 5 af");
                        System.out.println("1) Butterfly\n2)Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
                        option = userInput.nextInt();
                        //TODO (Move switch to method) showTopFive(Diciplin);
                        switch (option) {
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
                    } else if (option == 2) {
                        System.out.println("Register Record");
                        //TODO Show list of competitors
                        System.out.println("Enter id of the member you want to add the record to");
                        //TODO add registerRecords(); method
                        break;
                    } else {
                        System.out.println("Enter competition");
                        // TODO add enterCompetion(); method
                        break;
                    }
                case 0:
                    //Afslut program
                    quit = true;
                    break;
            }
        } while (!quit);
        System.out.println("Tak og farvel.");
    }

    public static void importEmployeeData() throws FileNotFoundException {
        Scanner employeeData = new Scanner(new File("Data/employee.txt"));
        while (employeeData.hasNextLine()) {
            String line = employeeData.nextLine();
            String[] arr = line.split(",");
            if (arr[0].equalsIgnoreCase("Manager")) {
                Employee manager = new Manager(arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
                employeeList.add(manager);
            } else if (arr[0].equalsIgnoreCase("Cashier")) {
                Employee cashier = new Cashier(arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
                employeeList.add(cashier);
            } else if (arr[0].equalsIgnoreCase("Coach")) {
                Employee coach = new Coach(arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
                employeeList.add(coach);
            } else {
                System.out.println("Fejl i indlæsning af data");
            }
        }
    }

    public static void exportEmployeeData() throws FileNotFoundException {
        PrintStream employeeData = new PrintStream(new File("Data/employee.txt"));
        for (int i = 0; i < employeeList.size(); i++) {
            employeeData.print(employeeList.get(i) + "\n");
        }
    }
    //dummy comment

    public static void createMember(Scanner userInput) {
        userInput.nextLine();
        System.out.print("Medlemmets fornavn(e): ");
        String firstName = userInput.nextLine();
        System.out.print("Medlemmets efternavn: ");
        String lastName = userInput.next();
        System.out.print("Medlemmets alder: ");
        int age = userInput.nextInt();
        System.out.print("Medlemmets køn Mand/Kvinde (M/K): "); //True = Kvinde, False = Mand
        String genderChoice = userInput.next();
        Boolean gender = false;
        if (genderChoice.equalsIgnoreCase("K")) {
            gender = true;
        }
        System.out.print("Medlemmets adresse: ");
        userInput.nextLine(); //Empty read line to finish reading current line
        String address = userInput.nextLine();
        System.out.print("Medlemmets email: ");
        String email = userInput.nextLine();
        System.out.print("Medlemmets telefonnummber: ");
        int phoneNumber = userInput.nextInt();
        System.out.print("Medlemmets medlemsstatus Aktiv/Passiv (A/P): ");
        String strMembershipStatus = userInput.next();
        Boolean membershipStatus = false; //True = aktiv, false = passiv
        if (strMembershipStatus.equalsIgnoreCase("A")) {
            membershipStatus = true;
        }
        Member member = new Member(firstName, lastName, age, gender, address, email, phoneNumber, membershipStatus, false);
        System.out.print("Medlemmets medlemstype Konkurrence/Motionist (K/M): ");
        String choice = userInput.next();
        Boolean membershipType = false;
        if (choice.equalsIgnoreCase("K")) {
            membershipType = true; //True = Konkurrencesvømmer, False = Motionist
            createCompetitor(userInput, member);
            member.setHasPaid(true);
        }
        System.out.println("Bekræft følgende oplysninger:");
        System.out.println("Fornavn: " + member.getFirstName());
        System.out.println("Efternavn: " + member.getLastName());
        System.out.println("Alder: " + member.getAge());
        System.out.println("Køn: " + member.getStrGender());
        System.out.println("Adresse: " + member.getAddress());
        System.out.println("E-mail: " + member.getEmail());
        System.out.println("Telefon Nummer: " + member.getPhoneNumber());
        System.out.println("Medlemsstatus: " + member.getStrMembershipStatus());
        System.out.println("medlemstype: " + member.getStrMembershipType());
        if (membershipType == true) {
            System.out.println("Træner: " + member.strCoach());
            System.out.println("Disicplin:" + member.printDiscipline());
        }
        System.out.println("Er oplysninger korrekt?\n1) Ja\n2) Nej");
        int option = userInput.nextInt();
        if (option == 2) {
            updateMember(userInput, member);
        }
        System.out.println("Medlem oprettet");
        memberList.add(member);
    }

    public static void registerPayment(Scanner userInput) {
        System.out.println("Indtast mail til medlem:");
        String mail = userInput.next();
        int index = 0;
        for (Member member : memberList) {
            if (member.getEmail() == mail) { // Leder efter userInput
                if (member.getHasPaid() == false) {
                    System.out.println("Der betales: " + member.getSubscription());
                    member.setHasPaid(true);
                } else {
                    System.out.println("Der er i forvejen registreret en betaling");
                    System.out.println(member.getFirstName() + member.getLastName() + "har fået registreret en betaling på" + member.getSubscription() + "kr.");
                }
            }
            index++;
        }
        if (index == memberList.size()) { //
            System.out.println("Mail findes ikke"); // Index er kørt hele listen igennem uden et match
        }
    }

    public static void updateMember(Scanner userInput, Member member) {
        System.out.println("Hvad vil du ændre:\n1) Fornavn\n2) Efternavn\n3) Alder\n4) Adresse\n5) Email\n6) Tlf.nr\n7) Træner\n8) Disciplin ");
        String input;
        int option = userInput.nextInt();
        switch (option) {
            case 1:
                // Fornavn
                System.out.print("Indtast nyt fornavn: ");
                input = userInput.next();
                member.setFirstName(input);
                System.out.println("Fornavn ændret");
                break;
            case 2:
                //Efternavn
                System.out.print("Indtast nyt efternavn: ");
                input = userInput.next();
                member.setLastName(input);
                System.out.println("Efternavn ændret");
                break;
            case 3:
                //Alder
                System.out.println("Indtast ny alder");
                option = userInput.nextInt();
                member.setAge(option);
                System.out.println("Alder ændret");
                break;
            case 4:
                //Adresse
                System.out.println("Indtast ny adresse");
                input = userInput.next();
                member.setAddress(input);
                System.out.println("Adresse ændret");
                break;
            case 5:
                //Email
                System.out.println("Indtast ny email");
                input = userInput.next();
                member.setEmail(input);
                System.out.println("Email ændret");
                break;
            case 6:
                //Tlf.nr
                System.out.println("Indtast nyt tlf.nr");
                option = userInput.nextInt();
                member.setPhoneNumber(option);
                System.out.println("Telefonnummer ændret");
                break;
            case 7:
                //Træner
                if (member.getMembershipType()) {
                    System.out.print("Vælg træner: ");
                    showCoaches();
                    option = userInput.nextInt();
                    member.setCoach(employeeList.get(option - 1));
                }else{
                    System.out.println("Medlemmet er ikke konkurrence svømmer");
                }
                break;
            case 8:
                //Discipline
                if(member.getMembershipType()) {
                    System.out.println("Vælg disiplin");
                    System.out.println("Du er tilknyttet den/disse disiplin(er): ");
                    member.displayDiscipline();
                    System.out.println("Hvilken disiplin vil du ændre: ");
                    int d = userInput.nextInt() - 1;
                    System.out.println("Hvilken disiplin vil du tilføje");
                    System.out.println("1) Butterfly\n2) Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
                    Discipline discipline = new Discipline(option);
                    member.getDisciplineList().set(d, discipline);
                    break;
                } else {
                    System.out.println("Medlemmet er ikke knyttet til nogen disciplin\nda medlemmet ikke er konkurrencesvømmer");
                }
        }

    }

    public static void showCoaches() {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i) instanceof Coach) {
                System.out.println("" + (i + 1) + ") " + employeeList.get(i));
            }
        }
    }

    public static void createCompetitor(Scanner userInput, Member member) {
        System.out.println("Tilknyt en træner: ");
        showCoaches();
        int option = userInput.nextInt();
        member.setCoach(employeeList.get(option - 1));
        System.out.println("Vælg svømme disciplin");
        System.out.println("1) Butterfly\n2) Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
        option = userInput.nextInt();
        Discipline discipline = new Discipline(option);
        member.updateDiciplineList(discipline);
    }


    public static void createEmployee(Scanner userInput) {
        System.out.println("1) Opret ny formand\n2) Opret ny kasserer\n3) Opret ny træner");
        int option = userInput.nextInt();
        userInput.nextLine();
        System.out.print("Fornavn(e): ");
        String firstName = userInput.nextLine();
        System.out.print("Efternavn: ");
        String lastName = userInput.next();
        userInput.nextLine(); //Tom read line for at sikre line read
        System.out.print("Adresse: ");
        String address = userInput.nextLine();
        System.out.print("Tlf.nr: ");
        int phoneNumber = userInput.nextInt();
        switch (option) {
            case 0:
                break;
            case 1:
                Employee manager = new Manager(firstName, lastName, address, phoneNumber);
                employeeList.add(manager);
                break;
            case 2:
                Employee cashier = new Cashier(firstName, lastName, address, phoneNumber);
                employeeList.add(cashier);
                break;
            case 3:
                Employee coach = new Coach(firstName, lastName, address, phoneNumber);
                employeeList.add(coach);
                break;
            default:
                System.out.println("Indtast venligst 1,2 eller 3");
                createEmployee(userInput);
                break;
        }
    }

    public static void importMemberData() throws FileNotFoundException {
        Scanner memberData = new Scanner(new File("Data/member.txt"));
        while (memberData.hasNextLine()) {
            // if (memberData.nextLine() != null) {
            String line = memberData.nextLine();
            String[] arr = line.split(",");
            Member member = new Member(arr[0], arr[1], Integer.parseInt(arr[2]), Boolean.parseBoolean(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6]), Boolean.parseBoolean(arr[7]), Boolean.parseBoolean(arr[8]));
            if (arr.length > 11) {//Hvis medlemmet er konkurrencesvømmer
                member.setMembershipType(true);
                for (int i = 0; i < employeeList.size(); i++) {
                    if (employeeList.get(i).getPhoneNumber() == Integer.parseInt(arr[12])) {
                        member.setCoach(employeeList.get(i));
                    }
                }
                //ADD DISCIPLINES
                for (int i = 0; i < arr.length - 13; i++) {
                    Discipline d = new Discipline(arr[i + 13]);
                    member.updateDiciplineList(d);
                }

            }
            memberList.add(member);
            //  }
        }
    }

    public static void exportMemberData() throws FileNotFoundException {
        PrintStream memberData = new PrintStream(new File("Data/member.txt"));
        for (int i = 0; i < memberList.size(); i++) {
            memberData.print(memberList.get(i) + "\n");
        }
    }

    /*  METHODS
    *   updateMember();
    *   loadMemberData();
    *
    *   showMembers();
    *   registerPayments();
    *   registerRecords();
    */

}
