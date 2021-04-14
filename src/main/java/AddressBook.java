import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {


    private List<Contact> contactList;

    public AddressBook(){
    	seedData();
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
                
                
                contactList.set(0, editedFirstName);
                System.out.println(contactList.toString());
                System.out.println("The First Name of the contact has been edited");
                
                break;
                
            case 2 : 
            	 System.out.println("Please enter the last name you want search in address book : ");
                 String lastname = input.next();
                 contact = search(lastname, "lastname");
                
                 System.out.println("What would you like to change the first name of the contact to be?");
                 String updateLastName = input.next();
                 Contact editedLastName = new Contact(updateLastName);
                 
                 contactList.set(1, editedLastName);
                 System.out.println(contactList);
                 System.out.println("The Last Name of the contact has been edited");
          
                 break;
                 
            case 3 :
                System.out.println("Please enter the phone number you want search in address book : ");
                String phone = input.next();
                contact = search(phone, "phone");
                System.out.println("What would you like to change the phone of the contact to be?");
                String updatePhone = input.next();
                Contact editedPhone = new Contact(updatePhone);
                
                contactList.set(2, editedPhone);
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
                
                contactList.set(3, editedEmail);
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
                
                contactList.set(4, editedStreet);
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
                
                contactList.set(5, editedNumber);
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
                
                contactList.set(6, editedCity);
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
                
                contactList.set(7, editedZip);
                System.out.println(contactList);
                System.out.println("The Zip of the contact has been edited");
            	break;
            	
            	
            default:
                return;
    	 
        }
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
