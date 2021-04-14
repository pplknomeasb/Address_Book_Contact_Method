import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class AddressBook {


    private List<Contact> contactList;

    public AddressBook(){

        readContactFile();

    }

    public AddressBook(Boolean seed){
        seedData();
    }

    public void viewContacts(){

        //Print all contacts header and establishing parameters for view all contacts
        System.out.printf("%-25s  ||  %-25s  ||  %-16s  ||  %-16s  ||  %-29s  ||  %-13s   ||  %-15s  ||  %-19s  ||  %-10s %n",
        "First Name", "Last Name", "Home Phone", "Mobile Phone", "Email", "Street Number", "Street", "City", "Zip");

        //Separates the header from the contents of the contactList
        System.out.println("===========================================================================================" +
                "========================================================================================================================");

        //Using for loop to loop through the contactList established in Contacts from starting from 0
        for(Contact contact:contactList)

            //Print formatted for beauty
            System.out.printf("%-25s  ||  %-25s  ||  %-16s  ||  %-16s  ||  %-29s  ||  %-13s   ||  %-15s  ||  %-19s  ||  %-10s %n",
                    contact.getFirstName(), contact.getLastName(),
                    contact.getPhoneHome(), contact.getPhoneMobile(),
            contact.getEmail(), contact.getNumber(), contact.getStreet(), contact.getCity(), contact.getZip());
    }

    public void addNewContact(){

        // Prompts user to enter their contacts details
        System.out.println("Add a new contact");

        // created new Contact and Scanner object
        Contact contact = new Contact();
        Scanner input = new Scanner(System.in);

        // prompts user to enter first name and stores in firstName variable
        System.out.println("Enter First name");
        String firstName = input.nextLine();
            if (firstName.equals("")) {
                System.out.println("Enter the first name again");
                firstName = input.nextLine();
            } else {
                contact.setFirstName(firstName);
            }
        // prompts user to enter last name and stores in lastName variable
        System.out.println("Enter Last name");
        String lastName = input.nextLine();
            if( lastName.equals("")){
                System.out.println("Enter the last name again");
                lastName = input.nextLine();
            } else {
                contact.setLastName(lastName);
            }
        System.out.println("Enter mobile number");
            String phoneMobile = input.nextLine();
            if(phoneMobile.equals("")){
                System.out.println("Enter mobile number again");
                phoneMobile = input.nextLine();

            } else {

                contact.setPhoneMobile(phoneMobile);
            }
        System.out.println("Enter email");
        String email = input.nextLine();
        contact.setEmail(email);

        System.out.println("Enter street, home number city and zip");
        String street = input.nextLine();
        contact.setStreet(street);

        String number = input.nextLine();
        contact.setNumber(number);

        String city = input.nextLine();
        contact.setCity(city);

        String zip = input.nextLine();
        contact.setZip(zip);

        // stored the contact information
        contactList.add(contact);
        System.out.println(firstName + "," + lastName+ "," + phoneMobile+ "," +email+ "," +street+ "," +number + "," +city+ "," +zip );
        System.out.println("Contact information has been saved");
    }

    public void deleteContact(){

        Contact contactToDelete;
        String isIncorrectContact = "";
        Scanner input = new Scanner(System.in);

        do {

            contactToDelete = searchForContact();

            if (contactToDelete != null) {

                System.out.print("\nIs this the correct contact to delete? (y/n) or type \"exit\" to return to main menu: => ");
                isIncorrectContact = input.nextLine();

                if(!isIncorrectContact.equalsIgnoreCase("y") && !isIncorrectContact.equalsIgnoreCase("exit")) {
                    System.out.println("\nPlease try your search again...\n");
                }

            }

        } while (!isIncorrectContact.equalsIgnoreCase("y") && !isIncorrectContact.equalsIgnoreCase("exit") && contactToDelete != null);

        if(isIncorrectContact.equalsIgnoreCase("exit")) {
            System.out.println("\nReturning to main menu...\n");
        }

        if(isIncorrectContact.equalsIgnoreCase("y")) {
            contactList.remove(contactToDelete);
            System.out.println("Your contact has been deleted");
            System.out.println("Press enter to return to main menu");
            input.nextLine();
        }



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


<<<<<<< Updated upstream
    public void editContact() {

=======
<<<<<<< HEAD
    public void editContact(){
    	//calling the searchForContact method to find the contact you want to edit
    	 
    	System.out.println("Please select search criteria : ");
        System.out.println("1) Search by First Name");
        System.out.println("2 Search by Last Name");
        System.out.println("3) Search by Phone Number");
        System.out.println("4) Search by email");
        System.out.println("5) Search by Street");
        System.out.println("6) Search by Street Number");
        System.out.println("7) Search by City");
        System.out.println("8) Search by Zip");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        Contact contact;
        switch (option){
            case 1 :
                System.out.println("Please enter the First name you want search in address book : ");
                String firstname = input.next();
                contact = search(firstname, "firstname");
                System.out.println("What would you like to change the first name of the contact to be?");
                String updateFirstName = input.next();
                Contact editedFirstName = new Contact(updateFirstName);
                int index = contactList.indexOf(firstname);
                contactList.set(index, editedFirstName);
                System.out.println(contactList);
                System.out.println("The First Name of the contact has been edited");
                
                break;
                
            case 2 : 
            	 System.out.println("Please enter the First name you want search in address book : ");
                 String lastname = input.next();
                 contact = search(lastname, "lastname");
                
                 System.out.println("What would you like to change the first name of the contact to be?");
                 String updateLastName = input.next();
                 Contact editedLastName = new Contact(updateLastName);
                 int index1 = contactList.indexOf(lastname);
                 contactList.set(index1, editedLastName);
                 System.out.println(contactList);
                 System.out.println("The First Name of the contact has been edited");
          
                 break;
                 
            case 3 :
                System.out.println("Please enter the phone number you want search in address book : ");
                String phone = input.next();
                contact = search(phone, "phone");
                System.out.println("What would you like to change the phone of the contact to be?");
                String updatePhone = input.next();
                Contact editedPhone = new Contact(updatePhone);
                int index2 = contactList.indexOf(phone);
                contactList.set(index2, editedPhone);
                System.out.println(contactList);
                System.out.println("The Phone of the contact has been edited");
                break;
            case 4 : 
            	System.out.println("Please enter the email you want search in address book : ");
            	String email = input.next();
            	contact = search(email, "email");
            	System.out.println("What would you like to change the first name of the contact to be?");
                String updateEmail = input.next();
                Contact editedEmail = new Contact(updateEmail);
                int index3 = contactList.indexOf(email);
                contactList.set(index3, editedEmail);
                System.out.println(contactList);
                System.out.println("The Email of the contact has been edited");
            	break;
            case 5 :
            	System.out.println("Please enter the street you want search in address book : ");
            	String street = input.next();
            	contact = search(street, "street");
            	System.out.println("What would you like to change the street of the contact to be?");
                String updateStreet = input.next();
                Contact editedStreet = new Contact(updateStreet);
                int index4 = contactList.indexOf(street);
                contactList.set(index4, editedStreet);
                System.out.println(contactList);
                System.out.println("The First Name of the contact has been edited");
            	break;
            case 6 :
            	System.out.println("Please enter the Street Number  you want search in address book : ");
            	String number = input.next();
            	contact = search(number, "number");
            	System.out.println("What would you like to change the first name of the contact to be?");
                String updateNumber = input.next();
                Contact editedNumber = new Contact(updateNumber);
                int index5 = contactList.indexOf(number);
                contactList.set(index5, editedNumber);
                System.out.println(contactList);
                System.out.println("The Street Number of the contact has been edited");
            	break;
            case 7 :
            	System.out.println("Please enter the city you want search in address book : ");
            	String city = input.next();
            	contact = search(city, "city");
            	System.out.println("What would you like to change the first name of the contact to be?");
                String updateCity = input.next();
                Contact editedCity = new Contact(updateCity);
                int index6 = contactList.indexOf(city);
                contactList.set(index6, editedCity);
                System.out.println(contactList);
                System.out.println("The First Name of the contact has been edited");
            	break;
            case 8 :
            	System.out.println("Please enter the zip you want search in address book : ");
            	String zip = input.next();
            	contact = search(zip, "zip");
            	System.out.println("What would you like to change the first name of the contact to be?");
                String updateZip = input.next();
                Contact editedZip = new Contact(updateZip);
                int index7 = contactList.indexOf(zip);
                contactList.set(index7, editedZip);
                System.out.println(contactList);
                System.out.println("The Zip of the contact has been edited");
            	break;
            	
            	
            default:
                return;
    	 
        }
=======
    public void editContact() {

>>>>>>> origin/Preproduction
>>>>>>> Stashed changes
    }

    // stubbed data for testing
    private void seedData(){
        contactList = new ArrayList<>();

        contactList.add(new Contact("Brennan", "Thompson", "1234567890", "1675309544", "thompson.brennan@gmail.com",
                "Main St", "112", "Pittsburgh", "15909"));
        contactList.add(new Contact("Trevor", "Benyack", "4125551111", "4125552222", "trb@gmail.com", "Maryland", "1000", "Pittsburgh"
                , "15232"));
        contactList.add(new Contact("Greg", "Conti", "7245552468", "4125553888", "gdc@gmail.com", "Palameno", "234", "Gibsonia", "15044"));
        contactList.add(new Contact("Kedar", "Dhital", "4219992222", "7249990000", "kdd@gmail.com", "Sunny Dr", "142", "Pittsburgh", "15227"));
        contactList.add(new Contact("Ashwini", "Neelgund", "9876554321", "8795462130", "ad@hotmail.com", "per scholas", "413", "Green Tree", "78945"));
    }

    // reads the contacts file into memory
    public void readContactFile(){

        File contactsFile = new File("src/main/java/contacts.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(contactsFile));

            // saves each line in the file to an index of the array
            Object[] array = reader.lines().toArray();

            contactList = new ArrayList<>();

            // splits each array object into String pieces and adds a new contact to the contactList for each line
            for (int i = 1; i < array.length; i++) {

                String tempString = (String)array[i];
                String[] tempContactArray = tempString.split(",");

                contactList.add(new Contact(
                        tempContactArray[0],
                        tempContactArray[1],
                        tempContactArray[2],
                        tempContactArray[3],
                        tempContactArray[4],
                        tempContactArray[5],
                        tempContactArray[6],
                        tempContactArray[7],
                        tempContactArray[8]

                ));

            }

            reader.close();

        } catch (IOException exc) {
            System.out.println("There was an error reading from the file.");
        }

    }

    // writes the contacts in memory to file
    public void writeContactFile(){
        File contactsFile = new File("src/main/java/contacts.csv");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(contactsFile));
            writer.write("Name,Surname,Phone,Mobile,E-mail,Street,Number,Town,Zip");
            writer.newLine();
            for(Contact c : contactList) {
                writer.write(c.getFirstName() + ",");
                writer.write(c.getLastName() + ",");
                writer.write(c.getPhoneHome() + ",");
                writer.write(c.getPhoneMobile() + ",");
                writer.write(c.getEmail() + ",");
                writer.write(c.getStreet() + ",");
                writer.write(c.getNumber() + ",");
                writer.write(c.getCity() + ",");
                writer.write(c.getZip());
                writer.newLine();
            }
            writer.flush();
            writer.close();


        } catch (IOException exc) {
            System.out.println("There was an error writing to the file.");
        }




    }

    public List<Contact> getContactList() {
        return contactList;
    }
}
