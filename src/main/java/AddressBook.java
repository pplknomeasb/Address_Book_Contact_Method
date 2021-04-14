import java.util.ArrayList;
import java.util.List;
@
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
