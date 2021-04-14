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

    }

    public void searchForContact(){
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
                return;
        }
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


    private void seedData(){
        contactList = new ArrayList<Contact>();

        contactList.add(new Contact("Brennan", "Thompson", "1234567890", "1675309544", "thompson.brennan@gmail.com",
                "Main St", "112", "Pittsburgh", "15909"));
        contactList.add(new Contact("Trevor", "Benyack", "4125551111", "4125552222", "trb@gmail.com", "Maryland", "1000", "Pittsburgh"
        , "15232"));
        contactList.add(new Contact("Greg", "Conti", "7245552468", "4125553888", "gdc@gmail.com", "Palameno", "234", "Gibsonia","15044"));
        contactList.add(new Contact("Kedar", "Dhital", "4219992222", "7249990000", "kdd@gmail.com", "Sunny Dr", "142", "Pittsburgh", "15227"));
        contactList.add(new Contact("Ashwini", "Neelgund", "9876554321", "8795462130", "ad@hotmail.com", "per scholas", "413", "Green Tree", "78945"));
    }




    public void readContactFile(){

    }


    public void writeContactFile(){

    }



}
