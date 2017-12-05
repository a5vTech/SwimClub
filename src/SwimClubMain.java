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
        System.out.println("Velkommen til Svømmeklubben Delfinens medlemssystem");
        mainMenu(); //Kør hovedmenu
        //Update files
        exportEmployeeData(); //Eksporter ansatte til fil
        exportMemberData(); //Eksporter medlemmer til fil
    }
    //Methods

    //MAIN MENU

    public static void mainMenu() throws FileNotFoundException {

        Scanner userInput = new Scanner(System.in);
        //Main menu
        boolean quit = false;
        do {
            System.out.println("\nHOVEDMENU\n1) Formand menu\n2) Kasserer menu\n3) Træner menu\n0) Afslut program");
            System.out.print("Vælg: ");
            checkInput(userInput);
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
                    checkInput(userInput);
                    option = userInput.nextInt();
                    if (option == 1) {
                        //Opret medlem
                        createMember(userInput);
                    } else if (option == 2) {
                        //Opdater medlemsoplysninger
                        System.out.println("\nOPDATER OPLYSNINGER");
                        System.out.printf("%-5s %-10s %-15s %s\n%s\n","ID","FORNAVN","EFTERNAVN","TELEFONNUMMER","----------------------------------------------");
                        for (int i = 0; i < memberList.size(); i++) {
                            System.out.printf("%-5s %-10s %-15s %d\n",(i+1+")"),memberList.get(i).getFirstName(),memberList.get(i).getLastName(),memberList.get(i).getPhoneNumber());                           }
                        System.out.print("----------------------------------------------\n0)Tilbage\nVælg: ");
                        checkInput(userInput);
                        int memberChoice = userInput.nextInt() - 1;
                        if(memberChoice == -1){
                            break;
                        }
                        updateMember(userInput, memberList.get(memberChoice));
                    } else if (option == 3) {
                        //Opret ansat
                        System.out.println("\n1) Opret ny formand\n2) Opret ny kasserer\n3) Opret ny træner\n0) Tilbage");
                        System.out.print("Vælg: ");
                        checkInput(userInput);
                        option = userInput.nextInt();
                        userInput.nextLine();
                        if (option == 1) {
                            //Opret/ny formand
                            System.out.println("\nNuværende formand:");
                            int indexOfManager = -1;
                            for (int i = 0; i < employeeList.size(); i++) {
                                //Hvis der er en formand gemt i systemet så printer vi oplysningerne til terminalen
                                if (employeeList.get(i) instanceof Manager) {
                                    System.out.println("Navn: " + employeeList.get(i).getName() + "\nAdresse: " + employeeList.get(i).getAddress() + "\nTlf.: " + employeeList.get(i).getPhoneNumber());
                                    indexOfManager = i;
                                }
                            }
                            System.out.println("\nNy formand");
                            //Kør createEmployee metoden med Scanner og int. int værdien definerer hvilken ansat der skal oprettes
                            Employee employee = createEmployee(userInput, 1);
                            if (indexOfManager == -1) {
                                //Hvis der ikke er oprettet en formand så tilføjes en
                                employeeList.add(employee);
                            } else {
                                //Hvis der i forvejen er en formand, så erstattes formanden
                                employeeList.set(indexOfManager, employee);
                            }
                        } else if (option == 2) {
                            //Opret/ny kasserer
                            System.out.println("\nNuværende kasserer:");
                            int indexOfCashier = -1;
                            for (int i = 0; i < employeeList.size(); i++) {
                                //Hvis der er en kasserer gemt i systemet så printer vi oplysningerne til terminalen
                                if (employeeList.get(i) instanceof Cashier) {
                                    System.out.println("Navn: " + employeeList.get(i).getName() + "\nAdresse: " + employeeList.get(i).getAddress() + "\nTlf: " + employeeList.get(i).getPhoneNumber());
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
                        } else if (option == 3) {
                            //Opret ny  træner
                            System.out.println("\nNy Træner:");
                            Employee employee = createEmployee(userInput, 3);
                            employeeList.add(employee); //Tilføjer den nye træner
                        } else {
                            break;
                        }
                    } else if (option == 4) {
                        //Opdater ansat
                        System.out.println("");
                        System.out.printf("%-5s %-10s %-15s %s\n%s\n","ID","FORNAVN","EFTERNAVN","TELEFONNUMMER","----------------------------------------------");
                        for(int i = 0; i < employeeList.size(); i++){
                            System.out.printf("%-5s %-10s %-15s %d\n",(i+1+")"),employeeList.get(i).getFirstName(),employeeList.get(i).getLastName(),employeeList.get(i).getPhoneNumber());
                        }
                        System.out.println("----------------------------------------------\n0)Tilbage\nVælg: ");
                        checkInput(userInput);
                        int employeeChoice = userInput.nextInt()-1;
                        if(employeeChoice == -1){
                            break;
                        }
                        updateEmployee(userInput,employeeList.get(employeeChoice));

                    } else {
                        //Gå tilbage til mainMenu
                    }
                    break;
                case 2:
                    //Kasserer
                    System.out.println("\nKASSERER\n1) Registrer betaling\n2) Vis medlemskaber i restance\n0) Tilbage");
                    System.out.print("Vælg: ");
                    boolean showArrear = false;
                    checkInput(userInput);
                    option = userInput.nextInt();
                    if (option == 1) {
                        //Vis medlemmer i restance og Registrer betaling
                        showArrearMembers(userInput, showArrear);
                        break;
                    } else if (option == 2) {
                        //Vis medlemmer i restance
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
                    checkInput(userInput);
                    option = userInput.nextInt();
                    boolean competitionRecord = false;
                    if (option == 1) {
                        //Kør showTopFive metoden med en Scanner som parameter
                        showTopFive(userInput);
                    } else if (option == 2 || option == 3) {
                        if (option == 2) {
                            System.out.println("\nREGISTRER REKORD");
                        } else {
                            System.out.println("\nREGISTRER STÆVNETID");
                        }
                        System.out.printf("%-5s %-10s %-15s %s\n%s\n","ID","FORNAVN","EFTERNAVN","TELEFONNUMMER","----------------------------------------------");
                        boolean found = false;                  
                        for (int k = 0; k < memberList.size(); k++) { //Kør medlemslisten igennem
                            //Hvis medlemmet er konkurrencesvømmer så printer vi den til terminalen
                            if (memberList.get(k).getMembershipType()) {
                                //Print fornavn, efternavn og telefonnummer
                                found = true;
                                System.out.printf("%-5s %-10s %-15s %d\n",(k+1+")"),memberList.get(k).getFirstName(),memberList.get(k).getLastName(),memberList.get(k).getPhoneNumber());
                            }
                            
                        }
                        if(!found){
                            System.out.print("Ingen konkurrencesvømmere tilgængelige.");
                        }
                        //Vælg medlem
                        System.out.print("----------------------------------------------\n0)Tilbage\nVælg: ");
                        checkInput(userInput);
                        int choice = userInput.nextInt() - 1;
                        if (choice == -1){
                            break;
                        }
                        if (option == 2) {
                            //Registrer rekord
                            registerRecord(userInput, choice, competitionRecord);
                        } else {
                            //Registrer stævne rekord
                            competitionRecord = true;
                            registerRecord(userInput, choice, competitionRecord);
                        }
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
        while (employeeData.hasNextLine()) { //Kører alle de ansate igennem
            String line = employeeData.nextLine();
            String[] arr = line.split(";"); //Der laves en split på linjen med ";" som seperator
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
        while (memberData.hasNextLine()) { //Læser alle medlemmer i member.txt
            String line = memberData.nextLine();
            String[] arr = line.split(";"); //Splitter linjen op i et array inddelt efter ";"
            Member member = new Member(arr[0], arr[1], Integer.parseInt(arr[2]), Boolean.parseBoolean(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6]), Boolean.parseBoolean(arr[7]), Boolean.parseBoolean(arr[8]));
            if (arr.length > 12) {//Hvis medlemmet er konkurrencesvømmer
                member.setMembershipType(true);

                //Indlæs og tilknyt træner
                for (int i = 0; i < employeeList.size(); i++) {
                    if (employeeList.get(i).getPhoneNumber() == Integer.parseInt(arr[13])) {
                        member.setCoach(employeeList.get(i));
                    }
                }
                //Indlæs restance
                 member.setArrear(Boolean.parseBoolean(arr[14]));
                //Indlæs og tilføj discipliner
                for (int i = 0; i < Integer.parseInt(arr[15]); i++) {
                    Discipline discipline = new Discipline(arr[i + 16]);
                    member.updateDiciplineList(discipline);
                }
                //Indlæs og tilføj rekorder
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
            System.out.println("\nHvad vil du ændre:\n1) Fornavn\n2) Efternavn\n3) Alder\n4) Adresse\n5) Email\n6) Telefonnummer\n7) Træner");
        }
        String input;
        System.out.print("Vælg: ");
        checkInput(userInput);
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
                System.out.print("Indtast ny alder: ");
                checkInput(userInput);
                option = userInput.nextInt();
                member.setAge(option);
                System.out.println("Alder ændret.");
                break;
            case 4:
                //Adresse
                System.out.println("Indtastede adresse: " + member.getAddress());
                System.out.print("Indtast ny adresse: ");
                input = userInput.next();
                member.setAddress(input);
                System.out.println("Adresse ændret.");
                break;
            case 5:
                //Email
                System.out.println("Indtastede email: " + member.getEmail());
                System.out.print("Indtast ny email: ");
                input = userInput.next();
                member.setEmail(input);
                System.out.println("Email ændret.");
                break;
            case 6:
                //Tlf.nr
                System.out.println("Indtastede telefonnummer: " + member.getPhoneNumber());
                System.out.print("Indtast nyt telefonnummer: ");
                checkInput(userInput);
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
                    checkInput(userInput);
                    option = userInput.nextInt();
                    member.setCoach(employeeList.get(option - 1));
                } else {
                    System.out.println("Medlemmet er ikke konkurrencesvømmer");
                }
                break;
        }
        confirmDetails(userInput, member);

    }
    public static void updateEmployee(Scanner userInput, Employee employee) {
        System.out.println("\nHvad vil du ændre?\n1) Titel\n2) Fornavn(e)\n3) Efternavn\n4) Adresse\n5) Telefonnummer");
        String input;
        System.out.print("Vælg: ");
        checkInput(userInput);
        int option = userInput.nextInt();
        switch (option) {
            case 1:
                //Titel
                System.out.println("\nIndtastede titel: " + employee.getTitle());
                System.out.println("Indtast ny titel (Manager/Cashier/Coach)");
                String title = userInput.next();
                employee.setTitle(title);
                System.out.println("Titel ændret.");
                break;
            case 2:
                //Fornavn
                System.out.println("Indtastede fornavn: " + employee.getFirstName());
                System.out.print("Indtast nyt fornavn: ");
                input = userInput.next();
                employee.setFirstName(input);
                System.out.println("Fornavn ændret.");
                break;
            case 3:
                //Efternavn
                System.out.println("Indtastede efternavn: " + employee.getLastName());
                System.out.print("Indtast nyt efternavn: ");
                input = userInput.next();
                employee.setLastName(input);
                System.out.println("Efternavn ændret.");
                break;
            case 4:
                //Adresse
                System.out.println("Indtastede adresse: " + employee.getAddress());
                System.out.println("Indtast ny adresse");
                input = userInput.next();
                employee.setAddress(input);
                System.out.println("Adresse ændret.");
                break;
            case 5:
                //Telefonnummer
                System.out.println("Indtastede telefonnummer: " + employee.getPhoneNumber());
                System.out.println("Indtast nyt telefonnummer");
                checkInput(userInput);
                option = userInput.nextInt();
                employee.setPhoneNumber(option);
                System.out.println("Telefonnummer ændret.");
                break;
        }
        System.out.println("Oplysninger opdateret.");

    }

    //SHOW LIST METHODS
    public static void showTopFive(Scanner userInput) throws FileNotFoundException {
        System.out.println("\nInden for hvilken disciplin vil du se top 5?");
        System.out.println("1) Butterfly\n2) Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning\n0) Tilbage");
        checkInput(userInput);
        System.out.print("Vælg: ");
        int option = userInput.nextInt();
        if(option == 0){
            System.out.println("");
            mainMenu();
        }
        Discipline discipline = new Discipline(option);//Opretter midlertidig disciplin
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
        System.out.printf("%-7s %-10s %-15s %-15s %s\n%s\n","PLADS","FORNAVN","EFTERNAVN","TELEFONNUMMER","TID","-----------------------------------------------------------");
        for (int i = 0; i < 5; i++) {
            if(recordMembers.get(i).getBestRecord() > 60){
                int minutes = recordMembers.get(i).getBestRecord()/60;
                int seconds = recordMembers.get(i).getBestRecord()-(minutes*60);
                System.out.printf("%-7s %-10s %-15s %-15d %d%-3s %d%s\n",(i+1+")"),memberList.get(i).getFirstName(),memberList.get(i).getLastName(),memberList.get(i).getPhoneNumber(),minutes,"min",seconds,"sek");
            }else{
                System.out.printf("%-7s %-10s %-15s %-15d %d%s\n",(i+1+")"),memberList.get(i).getFirstName(),memberList.get(i).getLastName(),memberList.get(i).getPhoneNumber(),recordMembers.get(i).getBestRecord(),"sek");
            }
        }
        //Mulighed for at gå tilbage
        System.out.print("-----------------------------------------------------------\n0) Tilbage\nVælg: ");
        checkInput(userInput);
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
        System.out.printf("%-5s %-10s %-15s %s\n%s\n","ID","FORNAVN","EFTERNAVN","TELEFONNUMMER","----------------------------------------------");
        for (int i = 0; i < memberList.size(); i++) {                                             
            if (memberList.get(i).getArrear()) {
                System.out.printf("%-5s %-10s %-15s %d\n",(i+1+")"),memberList.get(i).getFirstName(),memberList.get(i).getLastName(),memberList.get(i).getPhoneNumber());
            }
        }
        System.out.print("----------------------------------------------\n0)Tilbage\nVælg: ");
        checkInput(userInput);
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
        checkInput(userInput);
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
        checkInput(userInput);
        int age = userInput.nextInt();
        System.out.print("Køn: \n1) Mand\n2) Kvinde\n"); //True = Kvinde, False = Mand
        checkInput(userInput);
        int genderhoice = userInput.nextInt();
        Boolean gender = false;
        if(genderhoice == 1){
            gender = false;
        }else if (genderhoice == 2){
            gender = true;
        }
        System.out.print("Indtast adresse: ");
        userInput.nextLine(); //Empty read line to finish reading current line
        String address = userInput.nextLine();
        System.out.print("Indtast email: ");
        String email = userInput.nextLine();
        System.out.print("Indtast telefonnummber: ");
        checkInput(userInput);
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
        /*
        * CreateCompetitor metoden tager en Scanner til user input og et medlem som parameter
        * Metoden konverterer et medlem til en konkurrencesvømmer
        * og tilknytter en træner til medlemmet samt tilknytter de discipliner som
        * medlemmet vil deltage i
        * */
        System.out.println("\nTilknyt en træner: ");
        showCoaches(); //Viser en liste over trænere i klubben
        System.out.print("Vælg: ");
        checkInput(userInput);
        int option = userInput.nextInt();
        member.setCoach(employeeList.get(option - 1));
        System.out.println("\nVælg disciplin");
        newDiscipline(userInput,member); //Tilføjer discipliner til medlem

        //Record record = new Record(discipline, "LOKAL", "DATO", 99999);
        //member.updateRecordList(record);
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
        checkInput(userInput);
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

    public static void newDiscipline(Scanner userInput,Member member){
        /* newDiscipline metoden tager en Scanner og et medlem som parametre
        * Metoden tilføjer nye disciplner til et medlem, men tjekker først om
        * medlemmet allerede er tilknyttet til den valgte disciplin
        * Hvis medlemmet allerede er tilknyttet til disciplinen tilføjes den ikke,
        * men spørger derimod om at vælge en anden disciplin.
        */

        System.out.println("1) Butterfly\n2) Crawl\n3) Rygcrawl\n4) Brystsvømning\n5) Hundesvømning");
        System.out.print("Vælg: ");
        checkInput(userInput);
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
            checkInput(userInput);
            option = userInput.nextInt();
            if(option == 1){
                System.out.println("Vælg næste disciplin: ");
                newDiscipline(userInput, member);
            }

        }



    }

    public static void confirmDetails(Scanner userInput, Member member) {
        System.out.println("\nINDTASTEDE OPLYSNINGER\n----------------------");
        System.out.printf("Fornavn: %s\nEfternavn: %s\nAlder: %d\nKøn: %s\nAdresse: %s\nE-mail: %s\nTlf.: %d\nMedlemsstatus: %s\nMedlemstype: %s\n", member.getFirstName(), member.getLastName(), member.getAge(), member.getStrGender(), member.getAddress(), member.getEmail(), member.getPhoneNumber(), member.getStrMembershipStatus(), member.getStrMembershipType());


        if (member.getMembershipType()) {
            System.out.println("Tilknyttet træner: " + member.strCoach());
            System.out.println("Disciplin(er):" + member.printDiscipline());
        }
        System.out.println("\nEr oplysningerne korrekte?\n1) Ja\n2) Nej");
        System.out.print("Vælg: ");
        checkInput(userInput);
        int option = userInput.nextInt();
        if (option == 2) {
            updateMember(userInput, member);
            System.out.println("Oplysninger opdateret.");
        }
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
        checkInput(userInput);
        int minutes = userInput.nextInt();
        System.out.print("Indtast antal hele sekunder: ");
        checkInput(userInput);
        int seconds = userInput.nextInt();
        int time = (minutes * 60) + seconds;
        for (int i = 0; i < memberList.get(choice).getDisciplineList().size(); i++) {
            System.out.println((i + 1) + ")" + memberList.get(choice).getDiscipline(i));
        }
        System.out.println("");
        System.out.print("Vælg disciplin: ");
        checkInput(userInput);
        int option = userInput.nextInt();
        Record record = new Record(memberList.get(choice).getDiscipline(option - 1), competition, date, time);
        memberList.get(choice).updateRecordList(record);
        if (competitionTime) {
            System.out.print("Stævnetid registreret.");
        } else {
            System.out.print("Rekord registreret.");
        }
    }

    //Tjek input

    public static void checkInput(Scanner x){
        String number = "";
        while (!x.hasNextInt()){
            number = x.next();
            System.out.println("");
            System.out.print("Indtast venligst et tal: ");
        }
    }
}