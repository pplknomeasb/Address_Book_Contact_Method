import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {


    private List<Contact> contactList;

    public AddressBook(){

    }

    public AddressBook(Boolean seed){
        seedData();
    }

    public void viewContacts(){

    }

    public void addNewContact(){

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

    public Contact searchForContact(){
        System.out.println("Please select search criteria : ");
        System.out.println("1) Search by Name");
        System.out.println("2) Search by Phone Number");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        Contact contact;
        switch (option){
            case 1 :
                System.out.println("Please enter the First name you want search in address book : ");
                String name = input.next();
                contact = search(name, "name");
                break;
            case 2 :
                System.out.println("Please enter the phone number you want search in address book : ");
                String phone = input.next();
                contact = search(phone, "phone");
                break;
            default:
                return null;
        }

        return contact;

    }

    private Contact search(String value, String criteria) {
        for (Contact contact : contactList) {
            if(criteria.equals("name")) {
                if (contact.getFirstName().equals(value))
                    return contact;
            } else if (criteria.equals("phone")){
                if (contact.getPhoneMobile().equals(value))
                    return contact;
            }
        }
        return null;
    }


    public void editContact(){

    }

    // stubbed data for testing
    private void seedData(){
        contactList = new ArrayList<>();

        contactList.add(new Contact("Brennan", "Thompson", "1234567890", "1675309544", "thompson.brennan@gmail.com",
                "Main St", "112", "Pittsburgh", "15909"));
        contactList.add(new Contact("Trevor", "Benyack", "4125551111", "4125552222", "trb@gmail.com", "Maryland", "1000", "Pittsburgh"
        , "15232"));
        contactList.add(new Contact("Greg", "Conti", "7245552468", "4125553888", "gdc@gmail.com", "Palameno", "234", "Gibsonia","15044"));
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
