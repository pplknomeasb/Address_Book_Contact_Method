import java.util.*;
import java.util.function.Predicate;

public class AddressBook {


    private List<Contact> contactList;

    public AddressBook() {

    }

    public AddressBook(Boolean seed) {
        seedData();
    }

    public void viewContacts() {

    }

    public void addNewContact() {

    }

    public void deleteContact() {

    }

    /**
     * This function perform the search on the address book based on the criteria selected by the user.
     * Criteria for search : based on first name, based on mobile phone number.
     *
     * @return Contact
     */
    public Contact searchForContact() {

        int menuOption = 0;
        Scanner input = new Scanner(System.in);
        Contact contact = null;

        //Display the search menu
        do {
            System.out.println("Please select below search criteria : ");
            System.out.println("1) Search by Name");
            System.out.println("2) Search by Phone Number");
            System.out.println("3) Return to Main Menu");
            try {
                menuOption = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option Selected.\nPlease select valid menu option.\n");
                input.nextLine();
            }
        } while (menuOption < 1 || menuOption > 3);

        //Perform search based on the criteria selected by user and return the contact if exists.
        switch (menuOption) {
            case 1:
                System.out.println("Please enter the first name of the contact you want search in address book : ");
                String name = input.next();
                //Searching based on criteria first name
                contact = search(contactList, c -> c.getFirstName().equals(name));
                break;
            case 2:
                System.out.println("Please enter the phone number of the contact you want search in address book : ");
                String phone = input.next();
                //Searching based on criteria mobile phone number
                contact = search(contactList, c -> c.getPhoneMobile().equals(phone));
                break;
            default:
                return contact;
        }

        //Display contact if exists else display appropriate message
        printContact(contact);
        return contact;

    }

    /**
     * Displays the contact details if contact is not null else displays appropriate message.
     *
     * @param contact
     */
    private void printContact(Contact contact) {
        if (contact == null)
            System.out.println("No contact found in the address book with the selected criteria");
        else {
            System.out.println("Found the contact! Below are the details : ");
            StringJoiner joiner = new StringJoiner(",");
            System.out.println(joiner.add(contact.getFirstName())
                    .add(contact.getLastName())
                    .add(contact.getPhoneHome())
                    .add(contact.getPhoneMobile())
                    .add(contact.getEmail())
                    .add(contact.getStreet())
                    .add(contact.getNumber())
                    .add(contact.getCity())
                    .add(contact.getZip())
            );
        }
    }

    /**
     * Iterates through the list and returns if the contact found else return null.
     *
     * @param contactList
     * @param predicate
     * @return Contact if found else null
     */
    private Contact search(List<Contact> contactList, Predicate<Contact> predicate) {
        for (Contact contact : contactList) {
            if (predicate.test(contact))
                return contact;
        }
        return null;
    }


    public void editContact() {

    }


    private void seedData() {
        contactList = new ArrayList<Contact>();

        contactList.add(new Contact("Brennan", "Thompson", "1234567890", "1675309544", "thompson.brennan@gmail.com",
                "Main St", "112", "Pittsburgh", "15909"));
        contactList.add(new Contact("Trevor", "Benyack", "4125551111", "4125552222", "trb@gmail.com", "Maryland", "1000", "Pittsburgh"
                , "15232"));
        contactList.add(new Contact("Greg", "Conti", "7245552468", "4125553888", "gdc@gmail.com", "Palameno", "234", "Gibsonia", "15044"));
        contactList.add(new Contact("Kedar", "Dhital", "4219992222", "7249990000", "kdd@gmail.com", "Sunny Dr", "142", "Pittsburgh", "15227"));
        contactList.add(new Contact("Ashwini", "Neelgund", "9876554321", "8795462130", "ad@hotmail.com", "per scholas", "413", "Green Tree", "78945"));
    }


    public void readContactFile() {

    }


    public void writeContactFile() {

    }


}
