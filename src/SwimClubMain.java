import java.util.*;
import java.io.*;

public class SwimClubMain {
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    public static ArrayList<Member> memberList = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {
        //Import Data
        importEmployeeData(); //Importer ansatte
        importMemberData(); //Importer medlemmer
        //Start program
        mainMenu(); //Kør hovedmenu
        //Update files
        exportEmployeeData(); //Eksporter ansatte til fil
        exportMemberData(); //Eksporter medlemmer til fil
    }
    //Methods

    //MAIN MENU

    public static void mainMenu() throws FileNotFoundException {
        System.out.println("Velkommen til Svømmeklubben Delfinens medlemssystem");
        Scanner userInput = new Scanner(System.in);
        //Main menu
        boolean quit = false;
        do {
            System.out.println("\nHOVEDMENU\n1) Formand menu\n2) Kasserer menu\n3) Træner menu\n0) Afslut program");
            System.out.print("Vælg: ");
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
                    System.out.println("\nFORMAND\n1) Opret nyt medlem\n2) Opdater medlems oplysninger\n3) Opret ny ansat\n4) Opdater ansattes oplysninger\n0) Tilbage");
                    System.out.print("Vælg: ");
                    option = userInput.nextInt();
                    if (option == 1) {
                        createMember(userInput);
                    } else if (option == 2) {
                        System.out.println("\nOPDATER OPLYSNINGER");
                        for (int i = 0; i < memberList.size(); i++) {
                            System.out.println((i + 1) + ") " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName() + " Telefonnummer: " + memberList.get(i).getPhoneNumber());
                        }
                        System.out.print("Vælg: ");
                        int memberChoice = userInput.nextInt() - 1;
                        updateMember(userInput, memberList.get(memberChoice));
                    } else if (option == 3) {
                        System.out.println("\n1) Opret ny formand\n2) Opret ny kasserer\n3) Opret ny træner\n0) Tilbage");
                        System.out.print("Vælg: ");
                        option = userInput.nextInt();
                        userInput.nextLine();
                        if (option == 1) { //Ny formand
                            System.out.println("\nNuværende formand:");
                            int indexOfManager = -1;
                            for (int i = 0; i < employeeList.size(); i++) {
                                if (employeeList.get(i) instanceof Manager) {
                                    System.out.println("Navn: " + employeeList.get(i).getName() + "\nAdresse: " + employeeList.get(i).getAddress() + "\nTelefonnummer: " + employeeList.get(i).getPhoneNumber());
                                    indexOfManager = i;
                                }
                            }
                            System.out.println("\nNy formand");
                            Employee employee = createEmployee(userInput, 1);
                            if (indexOfManager == -1) {
                                employeeList.add(employee);
                            } else {
                                employeeList.set(indexOfManager, employee);
                            }
                        } else if (option == 2) { //Ny kasserer
                            System.out.println("\nNuværende kasserer:");
                            int indexOfCashier = -1;
                            for (int i = 0; i < employeeList.size(); i++) {
                                if (employeeList.get(i) instanceof Cashier) {
                                    System.out.println("Navn: " + employeeList.get(i).getName() + "\nAdresse: " + employeeList.get(i).getAddress() + "\nTelefonnummer: " + employeeList.get(i).getPhoneNumber());
                                    indexOfCashier = i;
                                }
                            }
                            System.out.println("\nNy kasserer");
                            Employee employee = createEmployee(userInput, 2);
                            if (indexOfCashier == -1) {
                                employeeList.add(employee);
                            } else {
                                employeeList.set(indexOfCashier, employee);
                            }
                        } else if (option == 3) { //Ny træner
                            System.out.println("\nNy Træner:");
                            Employee employee = createEmployee(userInput, 3);
                            employeeList.add(employee);
                        } else {
                            break;
                        }
                    } else if (option == 4) {
                        //Opdater ansat
                        //TODO Create update employee method - updateEmployee();  ------ NEEDS TO BE CREATED (02-12-2017)

                    } else {
                        //Gå tilbage til mainMenu
                    }
                    break;
                case 2:
                    //Kasserer
                    System.out.println("\nKASSERER\n1) Registrer betaling\n2) Vis medlemskaber i restance\n0) Tilbage");
                    System.out.print("Vælg: ");
                    boolean showArrear = false;
                    option = userInput.nextInt();
                    if (option == 1) {
                        showArrearMembers(userInput, showArrear);
                        break;
                    } else if (option == 2) {
                        showArrear = true;
                        showArrearMembers(userInput, showArrear);
                        break;
                    } else {
                        //Gå tilbage til mainMenu
                        break;
                    }
                case 3:
                    //Træner
                    System.out.println("\nTRÆNER\n1) Se top 5\n2) Registrer rekord\n3) Registrer stævnetid\n0) Tilbage");
                    System.out.print("Vælg: ");
                    option = userInput.nextInt();
                    boolean competitionRecord = false;
                    if (option == 1) {
                        showTopFive(userInput);
                    } else if (option == 2 || option == 3) {
                        if (option == 2) {
                            System.out.println("\nREGISTRER REKORD");
                        } else {
                            System.out.println("\nREGISTRER STÆVNETID");
                        }
                        for (int k = 0; k < memberList.size(); k++) {
                            if (memberList.get(k).getMembershipType()) { //Hvis medlemmet er konkurrencesvømmer
                                System.out.println((k + 1) + ")" + memberList.get(k).getFirstName() + " " + memberList.get(k).getLastName() + " " + memberList.get(k).getPhoneNumber());
                            } else {
                                System.out.print("Ingen konkurrencesvømmere tilgængelige.");
                            }
                        }
                        System.out.print("Vælg: ");
                        int choice = userInput.nextInt() - 1;
                        if (option == 2) {
                            registerRecord(userInput, choice, competitionRecord);
                        } else {
                            competitionRecord = true;
                            registerRecord(userInput, choice, competitionRecord);
                        }
                        break;
                    } else {
                        break;
                    }
                    break;
                case 0:
                    //Afslut program
                    quit = true;
                    break;
            }
        } while (!quit);
        System.out.println("Tak og farvel.");
    }

    //IMPORT METHODS
    public static void importEmployeeData() throws FileNotFoundException {
        Scanner employeeData = new Scanner(new File("Data/employee.txt"));
        while (employeeData.hasNextLine()) {
            String line = employeeData.nextLine();
            String[] arr = line.split(";");
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

    public static void importMemberData() throws FileNotFoundException {
        Scanner memberData = new Scanner(new File("Data/member.txt"));
        while (memberData.hasNextLine()) {
            String line = memberData.nextLine();
            String[] arr = line.split(";");
            //System.out.print(Arrays.toString(arr));
            //System.out.println("DEBUGMEDCAPS " + arr.length + " " + Arrays.toString(arr));
            //System.out.println(arr2[4]);
            //  int x = arr.length;
            // System.out.println("DEBUGMEDEKSTRACAPS " + (x));
            Member member = new Member(arr[0], arr[1], Integer.parseInt(arr[2]), Boolean.parseBoolean(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6]), Boolean.parseBoolean(arr[7]), Boolean.parseBoolean(arr[8]));
            if (arr.length > 12) {//Hvis medlemmet er konkurrencesvømmer
                member.setMembershipType(true);

                for (int i = 0; i < employeeList.size(); i++) {
                    if (employeeList.get(i).getPhoneNumber() == Integer.parseInt(arr[13])) {
                        member.setCoach(employeeList.get(i));
                    }
                }
                // member.setArrear(Boolean.parseBoolean(arr[14]));
                //ADD DISCIPLINES
                for (int i = 0; i < Integer.parseInt(arr[15]); i++) {
                    //  System.out.print("\n\nDEBUGGGGGG: " + arr[i + 16] + "\n\n");
                    Discipline discipline = new Discipline(arr[i + 16]);
                    member.updateDiciplineList(discipline);
                }
                //ADD Records
                for (int i = 15 + Integer.parseInt(arr[15]) + 1; i < arr.length; i += 4) {
                    Discipline discipline = new Discipline(0);
                    for (int k = 0; k < member.getDisciplineList().size(); k++) {
                        if (arr[i].equalsIgnoreCase(member.getDisciplineList().get(k).getDiscipline())) {
                            discipline = member.getDisciplineList().get(k);
                        }
                    }
                    Record record = new Record(discipline, arr[i + 1], arr[i + 2], Integer.parseInt(arr[i + 3]));
                    member.updateRecordList(record);
                }
            }
            if (!member.getMembershipType()) {
                System.out.println(arr[9]);
                member.setArrear(Boolean.parseBoolean(arr[9]));
            }
            memberList.add(member);
        }
    }

    //EXPORT METHODS
    public static void exportMemberData() throws FileNotFoundException {
        PrintStream memberData = new PrintStream(new File("Data/member.txt"));
        for (int i = 0; i < memberList.size(); i++) {
            memberData.print(memberList.get(i) + "\n");
        }
    }

    public static void exportEmployeeData() throws FileNotFoundException {
        PrintStream employeeData = new PrintStream(new File("Data/employee.txt"));
        for (int i = 0; i < employeeList.size(); i++) {
            employeeData.print(employeeList.get(i) + "\n");
        }
    }
    //UPDATE METHODS

    public static void updateMember(Scanner userInput, Member member) {
        if (!member.getMembershipType()) {
            System.out.println("\nHvad vil du ændre?\n1) Fornavn\n2) Efternavn\n3) Alder\n4) Adresse\n5) Email\n6) Telefonnummer");
        } else {
            System.out.println("\nHvad vil du ændre:\n1) Fornavn\n2) Efternavn\n3) Alder\n4) Adresse\n5) Email\n6) Telefonnummer\n7) Træner\n8) Disciplin ");
        }
        String input;
        System.out.print("Vælg: ");
        int option = userInput.nextInt();
        switch (option) {
            case 1:
                // Fornavn
                System.out.println("Indtastede fornavn: " + member.getFirstName());
                System.out.print("Indtast nyt fornavn: ");
                input = userInput.next();
                member.setFirstName(input);
                System.out.println("Fornavn ændret.");
                break;
            case 2:
                //Efternavn
                System.out.println("Indtastede efternavn: " + member.getLastName());
                System.out.print("Indtast nyt efternavn: ");
                input = userInput.next();
                member.setLastName(input);
                System.out.println("Efternavn ændret.");
                break;
            case 3:
                //Alder
                System.out.println("Indtastede alder: " + member.getAge());
                System.out.println("Indtast ny alder");
                option = userInput.nextInt();
                member.setAge(option);
                System.out.println("Alder ændret.");
                break;
            case 4:
                //Adresse
                System.out.println("Indtastede adresse: " + member.getAddress());
                System.out.println("Indtast ny adresse");
                input = userInput.next();
                member.setAddress(input);
                System.out.println("Adresse ændret.");
                break;
            case 5:
                //Email
                System.out.println("Indtastede email: " + member.getEmail());
                System.out.println("Indtast ny email");
                input = userInput.next();
                member.setEmail(input);
                System.out.println("Email ændret.");
                break;
            case 6:
                //Tlf.nr
                System.out.println("Indtastede telefonnummer: " + member.getPhoneNumber());
                System.out.println("Indtast nyt telefonnummer");
                option = userInput.nextInt();
                member.setPhoneNumber(option);
                System.out.println("Telefonnummer ændret.");
                break;
            case 7:
                //Træner
                if (member.getMembershipType()) {
                    System.out.println("Tilknyttede træner: " + member.strCoach());
                    System.out.println("Tilknyt ny træner");
                    showCoaches();
                    System.out.print("Vælg: ");
                    option = userInput.nextInt();
                    member.setCoach(employeeList.get(option - 1));
                } else {
                    System.out.println("Medlemmet er ikke konkurrencesvømmer");
                }
                break;
            case 8:
                //Discipline
                if (member.getMembershipType()) {
                    System.out.println("\nValgte disiplin(er):");
                    System.out.println(member.displayDiscipline());
                    System.out.println("Hvilken disiplin vil du ændre til?");
                    System.out.println("1) Butterfly\n2) Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
                    System.out.print("Vælg: ");
                    option = userInput.nextInt();
                    Discipline discipline = new Discipline(option);
                    member.getDisciplineList().set(0, discipline);
                    break;
                } else {
                    System.out.println("Medlemmet er ikke knyttet til nogen disciplin\nda medlemmet ikke er konkurrencesvømmer");
                    break;
                }
        }
        confirmDetails(userInput, member);

    }

    //SHOW LIST METHODS
    public static void showTopFive(Scanner userInput) {
        System.out.println("\nInden for hvilken disciplin vil du se top 5?");
        System.out.println("1) Butterfly\n2) Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
        System.out.print("Vælg: ");
        int option = userInput.nextInt();
        Discipline discipline = new Discipline(option);
        Member[] topFive = new Member[5];
        ArrayList<Member> recordMembers = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i++) { //Tjek alle medlemmer
            if (memberList.get(i).getMembershipType()) { //Hvis medlem er konkurrencesvømmer
                for (int k = 0; k < memberList.get(i).getRecordList().size(); k++) {
                    if (memberList.get(i).getRecordList().get(k).getDiscipline().getDiscipline().equals(discipline.getDiscipline())) {
                        memberList.get(i).setRecord(discipline);
                    }
                }
                recordMembers.add(memberList.get(i));

            }
        }
        Collections.sort(recordMembers);
        System.out.println("\nTOP FEM");
        for (int i = 0; i < 5; i++) {
            if (recordMembers.get(i).getBestRecord() > 60) {
                double doubleMinutes = Math.floor(recordMembers.get(i).getBestRecord() / 60);
                double doubleSeconds = recordMembers.get(i).getBestRecord() - (doubleMinutes * 60);
                int minutes = (int) doubleMinutes;
                int seconds = (int) doubleSeconds;
                System.out.println((i + 1) + ") " + recordMembers.get(i).getFirstName() + " " + recordMembers.get(i).getLastName() + " " + recordMembers.get(i).getPhoneNumber() + " Tid: " + minutes + " min " + seconds + " sek");
            } else {
                System.out.println((i + 1) + ") " + recordMembers.get(i).getFirstName() + " " + recordMembers.get(i).getLastName() + " " + recordMembers.get(i).getPhoneNumber() + " Tid: " + recordMembers.get(i).getBestRecord() + " sek");
            }
        }
        System.out.println("0) Tilbage");
        System.out.print("Vælg: ");
        option = userInput.nextInt();

    }

    public static void showCoaches() {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i) instanceof Coach) {
                System.out.println("" + (i + 1) + ") " + employeeList.get(i).getName() + " " + employeeList.get(i).getPhoneNumber());
            }
        }
    }

    public static void showArrearMembers(Scanner userInput, boolean showArrear) {
        if (showArrear) {
            System.out.println("\nMEDLEMSKABER I RESTANCE");
        } else {
            System.out.println("\nREGISTRER BETALING");
        }
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getArrear()) {
                System.out.println((i + 1) + ") " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName() + " " + memberList.get(i).getPhoneNumber());
            }
        }
        System.out.println("0) Tilbage");
        System.out.print("Vælg: ");
        int option = userInput.nextInt();
        if (option != 0 && !showArrear) {
            System.out.println("\n" + memberList.get(option - 1).getFirstName() + "\n" + memberList.get(option - 1).getLastName() + "\n" + memberList.get(option - 1).getPhoneNumber() + "\nBeløb i restance: " + memberList.get(option - 1).getSubscription() + ",-");
            System.out.println("\nRegistrer betaling?\n1) Ja\n2) Nej");
            System.out.print("Vælg: ");
            int option2 = userInput.nextInt();
            if (option2 == 1) {
                memberList.get(option - 1).setArrear(false);
                System.out.println("Betaling registreret.");
            }
        }
    }

    //MEMBER AND EMPLOYEE METHODS
    public static void createMember(Scanner userInput) {
        System.out.println("\nNYT MEDLEM");
        System.out.println("1) Konkurrencesvømmer\n2) Motionist");
        System.out.print("Vælg: ");
        int choice = userInput.nextInt();
        boolean membershipType = false; //True = Konkurrencesvømmer, False = Motionist
        if (choice == 1) {
            membershipType = true;
        } else {
            membershipType = false;
        }
        userInput.nextLine();
        System.out.print("\nIndtast fornavn(e): ");
        String firstName = userInput.nextLine();
        System.out.print("Indtast efternavn: ");
        String lastName = userInput.next();
        System.out.print("Indtast alder: ");
        int age = userInput.nextInt();
        System.out.print("Indtast køn - Mand/Kvinde (M/K): "); //True = Kvinde, False = Mand
        String genderChoice = userInput.next();
        Boolean gender;
        if (genderChoice.equalsIgnoreCase("K")) {
            gender = true;
        } else {
            gender = false;

        }
        System.out.print("Indtast adresse: ");
        userInput.nextLine(); //Empty read line to finish reading current line
        String address = userInput.nextLine();
        System.out.print("Indtast email: ");
        String email = userInput.nextLine();
        System.out.print("Indtast telefonnummber: ");
        int phoneNumber = userInput.nextInt();
        Boolean membershipStatus = true; //True, da medlemskab er aktivt ved oprettelse
        Boolean arrear = true; //True, da medlemskab ikke er betalt
        Member member = new Member(firstName, lastName, age, gender, address, email, phoneNumber, membershipStatus, arrear);
        if (membershipType) {
            createCompetitor(userInput, member);
        }
        member.setMembershipType(membershipType);

        confirmDetails(userInput, member);

        System.out.println("Medlem oprettet.");
        memberList.add(member);
    }

    public static void createCompetitor(Scanner userInput, Member member) {
        System.out.println("\nTilknyt en træner: ");
        showCoaches();
        System.out.print("Vælg: ");
        int option = userInput.nextInt();
        member.setCoach(employeeList.get(option - 1));
        System.out.println("\nVælg disciplin");
        newDiscipline(userInput,member);




        //Record record = new Record(discipline, "LOKAL", "DATO", 99999);
        //member.updateRecordList(record);
    }

    public static void newDiscipline(Scanner userInput,Member member){
        System.out.println("1) Butterfly\n2) Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
        System.out.print("Vælg: ");
        int option = userInput.nextInt();
        Discipline discipline = new Discipline(option);
        boolean alreadyIn = false;
        for(int i = 0; i < member.getDisciplineList().size(); i++){
            if(discipline.getDiscipline().equalsIgnoreCase(member.getDisciplineList().get(i).getDiscipline())){
                alreadyIn = true;
                System.out.println("\nMedlemmet er allerede tilknyttet denne disciplin, vælg venligst en anden disciplin");
                newDiscipline(userInput,member);
            }
        }

        if(!alreadyIn){
            member.updateDiciplineList(discipline);
            System.out.println("\nSkal medlemmet tilknyttes flere discipliner?\n1) Ja\n2) Nej");
            option = userInput.nextInt();
            if(option == 1){
                System.out.println("Vælg næste disciplin: ");
                newDiscipline(userInput, member);
            }

        }



    }

    public static void confirmDetails(Scanner userInput, Member member) {
        System.out.println("\nINDTASTEDE OPLYSNINGER\n----------------------");
        System.out.printf("Fornavn: %s\nEfternavn: %s\nAlder: %d\nKøn: %s\nAdresse: %s\nE-mail: %s\nTelefonnummer: %d\nMedlemsstatus: %s\nMedlemstype: %s\n", member.getFirstName(), member.getLastName(), member.getAge(), member.getStrGender(), member.getAddress(), member.getEmail(), member.getPhoneNumber(), member.getStrMembershipStatus(), member.getStrMembershipType());


        if (member.getMembershipType()) {
            System.out.println("Tilknyttet træner: " + member.strCoach());
            System.out.println("Disciplin(er):" + member.printDiscipline());
        }
        System.out.println("\nEr oplysningerne korrekte?\n1) Ja\n2) Nej");
        System.out.print("Vælg: ");
        int option = userInput.nextInt();
        if (option == 2) {
            updateMember(userInput, member);
        }
        System.out.println("Oplysninger opdateret.");
    }

    public static void registerRecord(Scanner userInput, int choice, boolean competitionTime) throws FileNotFoundException {
        String competition = "lokal";
        System.out.println("");
        if (competitionTime) {
            System.out.print("Indtast stævne: ");
            userInput.nextLine();
            competition = userInput.next();
        }
        System.out.print("Indtast dato (DD/MM/ÅÅ): ");
        String date = userInput.next();
        System.out.println("\nTid i minutter og sekunder: ");
        System.out.print("Indtast antal hele minutter: ");
        int minutes = userInput.nextInt();
        System.out.print("Indtast antal hele sekunder: ");
        int seconds = userInput.nextInt();
        int time = (minutes * 60) + seconds;
        for (int i = 0; i < memberList.get(choice).getDisciplineList().size(); i++) {
            System.out.println((i + 1) + ")" + memberList.get(choice).getDiscipline(i));
        }
        System.out.println("");
        System.out.print("Vælg disciplin: ");
        int option = userInput.nextInt();
        Record record = new Record(memberList.get(choice).getDiscipline(option - 1), competition, date, time);
        memberList.get(choice).updateRecordList(record);
        if (competitionTime) {
            System.out.print("Stævnetid registreret.");
        } else {
            System.out.print("Rekord registreret.");
        }
    }

    public static Employee createEmployee(Scanner userInput, int option) {
        System.out.print("Fornavn(e): ");
        String firstName = userInput.nextLine();
        System.out.print("Efternavn: ");
        String lastName = userInput.next();
        userInput.nextLine(); //Tom read line for at sikre line read
        System.out.print("Adresse: ");
        String address = userInput.nextLine();
        System.out.print("Telefonnummer: ");
        int phoneNumber = userInput.nextInt();

        if (option == 1) {
            Employee manager = new Manager(firstName, lastName, address, phoneNumber);
            System.out.println("Formand opdateret.");
            return manager;
        } else if (option == 2) {
            Employee cashier = new Cashier(firstName, lastName, address, phoneNumber);
            System.out.println("Kasserer opdateret.");
            return cashier;
        } else {
            Employee coach = new Coach(firstName, lastName, address, phoneNumber);
            System.out.println("Træner opdateret.");
            return coach;
        }
    }
}