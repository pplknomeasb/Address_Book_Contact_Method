import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AddressBook addrBook = new AddressBook();
        Scanner input = new Scanner(System.in);
        int menuOption = 0;

        do {
            //Displaying the Main Menu
            System.out.println("Welcome to the online Address Book");
            System.out.println("Please select one of below actions : ");
            System.out.println("1) View all contacts");
            System.out.println("2) Add new contact");
            System.out.println("3) Search for a contact");
            System.out.println("4) Edit a contact");
            System.out.println("5) Delete a contact");
            System.out.println("6) Exit");



            try {
                menuOption = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option Selected.\nPlease select valid menu option.\n");
                input.nextLine();
            }

            //Select action to be performed based on the option selected by the user
            switch (menuOption) {
                case 1:
                    addrBook.viewContacts();
                    break;
                case 2:
                    addrBook.addNewContact();
                    break;
                case 3:
                    addrBook.searchForContact();
                    break;
                case 4:
                    addrBook.editContact();
                    break;
                case 5:
                    addrBook.deleteContact();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid Option Selected.\nPlease select valid menu option.\n");
                    break;
            }
        } while (menuOption != 6);

        addrBook.writeContactFile();

    }
}
