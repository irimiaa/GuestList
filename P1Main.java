package P1;

import java.util.Arrays;
import java.util.Scanner;

public class P1Main {

    static GuestsList guestsList = new GuestsList(4);
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String choice;

        do {
            commandList();
            System.out.print("Alegeti comanda: ");
            choice = sc.next();

            switch (choice) {
                case "help":
                    commandList();
                    break;

                case "check":
                    criteriaCheck(question());
                    break;

                case "add":
                    add();
                    break;

                case "remove":
                    remove();
                    break;

                case "update":
                    update();
                    break;

                case "guests":
                    guests();
                    break;

                case "waitlist":
                    waitingList();
                    break;

                case "available":
                    available();
                    break;

                case "guests_no":
                    guestsNo();
                    break;

                case "waitlist_no":
                    waitListNo();
                    break;

                case "subscribe_no":
                    subscribeNo();
                    break;

                case "search":
                    search(sc.next());
                    break;

            }

        } while (!choice.equals("quit"));

    }

    // TEST CASE
    public static void add() {
        String fName, lName, email, phoneNumber;
        fName = sc.next();
        lName = sc.next();
        email = sc.next();
        phoneNumber = sc.next();

        if (!guestsList.checkByEmail(email)) {
            guestsList.add(new Guest(fName, lName, email, phoneNumber));
        }
    }

    public static void remove() {
        switch (question()) {
            case "nume":
                removeByName(sc.next(), sc.next());
                break;

            case "number":
                removeByNumber(sc.next());
                break;

            case "email":
                removeByEmail(sc.next());
                break;
        }
    }

    public static void update() {
        switch (question()) {
            case "email":
                updateGuestEmail(sc.next(), sc.next());
                break;

            case "nume":
                updateGuestName(sc.next(), sc.next(), sc.next(), sc.next());
                break;

            case "number":
                updateGuestNumber(sc.next(), sc.next());
                break;
        }
    }

    public static void updateGuestEmail(String oldEmail, String newEmail) {
        if (guestsList.getGuestByEmail(oldEmail, guestsList.getGuestList()) == null) {
            guestsList.getGuestByEmail(oldEmail, guestsList.getWaitingList()).setEmail(newEmail);
            return;
        }
        guestsList.getGuestByEmail(oldEmail, guestsList.getGuestList()).setEmail(newEmail);
    }


    public static void updateGuestName(String oldfName, String oldlName, String newfName, String newlName) {
        if (guestsList.getGuestByName(oldfName, oldlName, guestsList.getGuestList()) == null) {
            guestsList.getGuestByName(oldfName, oldlName, guestsList.getWaitingList()).setFirstName(newfName);
            guestsList.getGuestByName(oldfName, oldlName, guestsList.getWaitingList()).setLastName(newlName);
            return;
        }
        guestsList.getGuestByName(oldfName, oldlName, guestsList.getGuestList()).setFirstName(newfName);
        guestsList.getGuestByName(oldfName, oldlName, guestsList.getGuestList()).setLastName(newlName);
    }

    public static void updateGuestNumber(String oldNumber, String newNumber) {
        if (guestsList.getGuestByNumber(oldNumber, guestsList.getGuestList()) == null) {
            guestsList.getGuestByNumber(oldNumber, guestsList.getWaitingList()).setPhoneNumber(newNumber);
            return;
        }
        guestsList.getGuestByNumber(oldNumber, guestsList.getGuestList()).setPhoneNumber(newNumber);
    }


    public static void removeByEmail(String email) {
        if (guestsList.getGuestByEmail(email, guestsList.getGuestList()) == null) {
            guestsList.remove(guestsList.getGuestByEmail(email, guestsList.getWaitingList()));
            return;
        }
        guestsList.remove(guestsList.getGuestByEmail(email, guestsList.getGuestList()));
    }

    public static void removeByNumber(String phone) {
        if (guestsList.getGuestByNumber(phone, guestsList.getGuestList()) == null) {
            guestsList.remove(guestsList.getGuestByNumber(phone, guestsList.getWaitingList()));
            return;
        }
        guestsList.remove(guestsList.getGuestByNumber(phone, guestsList.getGuestList()));
    }

    public static void removeByName(String fname, String lname) {
        if (guestsList.getGuestByName(fname, lname, guestsList.getGuestList()) == null) {
            guestsList.remove(guestsList.getGuestByName(fname, lname, guestsList.getWaitingList()));
            return;
        }
        guestsList.remove(guestsList.getGuestByName(fname, lname, guestsList.getGuestList()));
    }


    public static String question() {
        System.out.print("Alegeti criteriul (nume/email/number): ");
        return sc.next();
    }


    public static void criteriaCheck(String choice) {
        switch (choice) {
            case "nume":
                System.out.println(checkName());
                break;

            case "number":
                System.out.println(checkPhoneNumber());
                break;

            case "email":
                System.out.println(checkEmail());
                break;
        }
    }

    public static boolean checkName() {
        String fname,lname;
        fname = sc.next();
        lname = sc.next();
        return guestsList.checkByName(fname, lname);
    }

    public static boolean checkPhoneNumber() {
        System.out.print("Scrieti numarul de telefon: ");
        String phNumber = sc.next();
        return guestsList.checkByPhone(phNumber);
    }

    public static boolean checkEmail() {
        System.out.print("Scrieti adresa de email: ");
        String email = sc.next();
        return guestsList.checkByEmail(email);
    }

    public static void guests() {
        guestsList.guests();
    }

    public static void waitingList() {
        guestsList.waitList();
    }

    public static void available() {
        System.out.println(guestsList.available());
    }

    public static void guestsNo() {
        System.out.println(guestsList.guestNumber());
    }

    public static void waitListNo() {
        System.out.println(guestsList.waitListNumber());
    }

    public static void subscribeNo() {
        System.out.println(guestsList.subscribeNumber());
    }

    public static void search(String s) {
        System.out.println(Arrays.toString(guestsList.search(s)));
    }

    public static void commandList() {
        System.out.println(
                "help         - Afiseaza aceasta lista de comenzi\n" +
                "add          - Adauga o noua persoana (inscriere)\n" +
                "check        - Verifica daca o persoana este inscrisa la eveniment\n" +
                "remove       - Sterge o persoana existenta din lista\n" +
                "update       - Actualizeaza detaliile unei persoane\n" +
                "guests       - Lista de persoane care participa la eveniment\n" +
                "waitlist     - Persoanele din lista de asteptare\n" +
                "available    - Numarul de locuri libere\n" +
                "guests_no    - Numarul de persoane care participa la eveniment\n" +
                "waitlist_no  - Numarul de persoane din lista de asteptare\n" +
                "subscribe_no - Numarul total de persoane inscrise\n" +
                "search       - Cauta toti invitatii conform sirului de caractere introdus\n" +
                "quit         - Inchide aplicatia");
    }

}
