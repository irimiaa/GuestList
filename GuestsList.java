package P1;

import java.util.ArrayList;

public class GuestsList {

    private int availableSpots;
    private int waitingListNo;
    private ArrayList<Guest> guestList;
    private ArrayList<Guest> waitingList;

    public ArrayList<Guest> getGuestList() {
        return this.guestList;
    }

    public ArrayList<Guest> getWaitingList() {
        return this.waitingList;
    }

    public GuestsList(int availableSpots) {
        this.availableSpots = availableSpots;
        guestList = new ArrayList<>(availableSpots);
        waitingList = new ArrayList<>();
    }

    public int add(Guest newGuest) {
        if (isInList(newGuest) || isInWaitingList(newGuest)) {
            return -1;
        }

        if (this.availableSpots > 0) {
            guestList.add(newGuest);
            System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            this.availableSpots--;
            return 0;

        } else {

            this.waitingList.add(newGuest);
            waitingListNo++;
            System.out.printf("%s, te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine %d." +
                    " Te vom notifica daca un loc devine disponibil%n",this.getGuestName(newGuest), this.waitingListNo);
        }
        return waitingListNo;
    }

    public boolean checkByEmail(String email) {
        return getGuestByEmail(email, this.guestList) != null ||
                getGuestByEmail(email, this.waitingList) != null;
    }

    public boolean checkByPhone(String phoneNumber) {
        return getGuestByNumber(phoneNumber, this.guestList) != null ||
                getGuestByNumber(phoneNumber, this.waitingList) != null;
    }

    public boolean checkByName(String fName, String lName) {
        return getGuestByName(fName, lName, this.guestList) != null ||
                getGuestByName(fName, lName, this.waitingList) != null;
    }

    private boolean isInList(Guest g) {
        for (Guest guest: this.guestList) {
            if (guest.isSameGuest(g)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInWaitingList(Guest g) {
        for (Guest guest: this.waitingList) {
            if (guest.isSameGuest(g)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Guest guest) {
        if (!isInList(guest) && !isInWaitingList(guest)) {
            return false;
        }

        if (isInList(guest)) {
            this.guestList.remove(guest);

            if (this.availableSpots == 0 && this.waitingList.size() > 0) {

                this.guestList.add(this.waitingList.get(0));
                System.out.printf("%s se afla acum in lista de participanti!%n",
                        getGuestName(this.waitingList.get(0)));

                this.waitingList.remove(0);
                this.waitingListNo--;
                return true;

            } else if (this.waitingList.size() == 0) {
                this.availableSpots++;
                return true;
            }
            this.availableSpots++;
        }

        if (isInWaitingList(guest)) {
            this.waitingList.remove(guest);
            this.waitingListNo--;
            return true;
        }
        return false;
    }

    public String getGuestName(Guest guest) {
        return String.format("%s %s", guest.getFirstName(), guest.getLastName());
    }


    public void guests() {
        System.out.println(this.guestList);
    }

    public void waitList() {
        System.out.println(this.waitingList);
    }

    public int available() {
        return this.availableSpots;
    }

    public int guestNumber() {
        return this.guestList.size();
    }

    public int waitListNumber() {
        return this.waitingListNo;
    }

    public int subscribeNumber() {
        return this.guestList.size() + this.waitingListNo;
    }

    public String[] search(String key) {
        ArrayList<String> arrayList = findSubstrings(key, guestList);
        arrayList.addAll(findSubstrings(key, waitingList));
        String[] sArray = new String[arrayList.size()];

        return arrayList.toArray(sArray);
    }

    private ArrayList<String> findSubstrings(String key, ArrayList<Guest> guestArrayList) {
        ArrayList<String> arr = new ArrayList<>();

        for (Guest guest: guestArrayList) {
            if (guest.getEmail().toLowerCase().contains(key)) {
                arr.add(guest.getEmail());
            }
            if (guest.getPhoneNumber().toLowerCase().contains(key)) {
                arr.add(guest.getPhoneNumber());
            }
            if (guest.getFirstName().toLowerCase().contains(key)) {
                arr.add(guest.getFirstName());
            }
            if (guest.getLastName().toLowerCase().contains(key)) {
                arr.add(guest.getLastName());
            }
        }
        return arr;
    }


    public Guest getGuestByName(String fName, String lName, ArrayList<Guest> arrayList) {
        for (Guest guest: arrayList) {
            if (fName.equals(guest.getFirstName()) && lName.equals(guest.getLastName())) {
                return guest;
            }
        }
        return null;
    }

    public Guest getGuestByEmail(String email, ArrayList<Guest> arrayList) {
        for (Guest guest: arrayList) {
            if (email.equals(guest.getEmail())) {
                return guest;
            }
        }
        return null;
    }

    public Guest getGuestByNumber(String phoneNumber, ArrayList<Guest> arrayList) {
        for (Guest guest: arrayList) {
            if (phoneNumber.equals(guest.getPhoneNumber())) {
                return guest;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("GuestsList: %s%nWaitingList:" +
                " %s%nAvailableSpots: %d", this.guestList, this.waitingList, this.availableSpots);
    }

}
