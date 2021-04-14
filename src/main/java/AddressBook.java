import java.util.*;

public class AddressBook {

    private List<Contact> contactList = new List<Contact>(){

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Contact> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Contact contact) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Contact> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Contact> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Contact get(int index) {
            return null;
        }

        @Override
        public Contact set(int index, Contact element) {
            return null;
        }

        @Override
        public void add(int index, Contact element) {

        }

        @Override
        public Contact remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Contact> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Contact> listIterator(int index) {
            return null;
        }

        @Override
        public List<Contact> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    public AddressBook(){

    }

    public AddressBook(Boolean seed){
        seedData();
    }
    public void viewContacts(){
        System.out.printf("%-8s  ||  %-8s  ||  %-5s  ||  %-5s  ||  %-29s  ||  %-5s   ||  %-15s  ||  %-19s  ||  %-10s %n",
        "First Name", "Last Name", "Home Phone", "Mobile Phone", "Email", "Street Number", "Street", "City", "Zip");
        System.out.println("===========================================================================================" +
                "=================================================================================");
        for(Contact contact:contactList)
             System.out.printf("%-10s  ||  %-9s  ||  %-5s  ||  %-12s  ||  %-29s  ||  %-13s   ||  %-15s  ||  %-19s  ||  %-10s %n",
                    contact.getFirstName(), contact.getLastName(),
                    contact.getPhoneHome(), contact.getPhoneMobile(),
            contact.getEmail(), contact.getNumber(), contact.getStreet(), contact.getCity(), contact.getZip());
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
