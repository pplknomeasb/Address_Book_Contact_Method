import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class AddressBook {

	private List<Contact> contactList;

	public AddressBook() {

		readContactFile();

	}

	public AddressBook(Boolean seed) {
		seedData();
	}

	public void viewContacts() {

		// Print all contacts header and establishing parameters for view all contacts
		System.out.printf(
				"%-25s  ||  %-25s  ||  %-16s  ||  %-16s  ||  %-29s  ||  %-13s   ||  %-15s  ||  %-19s  ||  %-10s %n",
				"First Name", "Last Name", "Home Phone", "Mobile Phone", "Email", "Street Number", "Street", "City",
				"Zip");

		// Separates the header from the contents of the contactList
		System.out.println("==========================================================================================="
				+ "========================================================================================================================");

		// Using for loop to loop through the contactList established in Contacts from
		// starting from 0
		for (Contact contact : contactList)

			// Print formatted for beauty
			System.out.printf(
					"%-25s  ||  %-25s  ||  %-16s  ||  %-16s  ||  %-29s  ||  %-13s   ||  %-15s  ||  %-19s  ||  %-10s %n",
					contact.getFirstName(), contact.getLastName(), contact.getPhoneHome(), contact.getPhoneMobile(),
					contact.getEmail(), contact.getNumber(), contact.getStreet(), contact.getCity(), contact.getZip());
	}

	public void addNewContact() {

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
		if (lastName.equals("")) {
			System.out.println("Enter the last name again");
			lastName = input.nextLine();
		} else {
			contact.setLastName(lastName);
		}
		System.out.println("Enter mobile number");
		String phoneMobile = input.nextLine();
		if (phoneMobile.equals("")) {
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
		System.out.println(firstName + "," + lastName + "," + phoneMobile + "," + email + "," + street + "," + number
				+ "," + city + "," + zip);
		System.out.println("Contact information has been saved");
	}

	public void deleteContact() {

		Contact contactToDelete;
		String isIncorrectContact = "";
		Scanner input = new Scanner(System.in);

		do {

			contactToDelete = searchForContact();

			if (contactToDelete != null) {

				System.out.print(
						"\nIs this the correct contact to delete? (y/n) or type \"exit\" to return to main menu: => ");
				isIncorrectContact = input.nextLine();

				if (!isIncorrectContact.equalsIgnoreCase("y") && !isIncorrectContact.equalsIgnoreCase("exit")) {
					System.out.println("\nPlease try your search again...\n");
				}

			}

		} while (!isIncorrectContact.equalsIgnoreCase("y") && !isIncorrectContact.equalsIgnoreCase("exit")
				&& contactToDelete != null);

		if (isIncorrectContact.equalsIgnoreCase("exit")) {
			System.out.println("\nReturning to main menu...\n");
		}

		if (isIncorrectContact.equalsIgnoreCase("y")) {
			contactList.remove(contactToDelete);
			System.out.println("Your contact has been deleted");
			System.out.println("Press enter to return to main menu");
			input.nextLine();
		}

	}

	/**
	 * This function perform the search on the address book based on the criteria
	 * selected by the user. Criteria for search : based on first name, based on
	 * mobile phone number.
	 *
	 * @return Contact
	 */
	public Contact searchForContact() {

		int menuOption = 0;
		Scanner input = new Scanner(System.in);
		Contact contact = null;

		// Display the search menu
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

		// Perform search based on the criteria selected by user and return the contact
		// if exists.
		switch (menuOption) {
		case 1:
			System.out.println("Please enter the first name of the contact you want search in address book : ");
			String name = input.next();
			// Searching based on criteria first name
			contact = search(contactList, c -> c.getFirstName().equals(name));
			break;
		case 2:
			System.out.println("Please enter the phone number of the contact you want search in address book : ");
			String phone = input.next();
			// Searching based on criteria mobile phone number
			contact = search(contactList, c -> c.getPhoneMobile().equals(phone));
			break;
		default:
			return contact;
		}

		// Display contact if exists else display appropriate message
		printContact(contact);
		return contact;

	}

	/**
	 * Displays the contact details if contact is not null else displays appropriate
	 * message.
	 *
	 * @param contact
	 */
	private void printContact(Contact contact) {
		if (contact == null)
			System.out.println("No contact found in the address book with the selected criteria");
		else {
			System.out.println("Found the contact! Below are the details : ");
			StringJoiner joiner = new StringJoiner(",");
			System.out.println(joiner.add(contact.getFirstName()).add(contact.getLastName()).add(contact.getPhoneHome())
					.add(contact.getPhoneMobile()).add(contact.getEmail()).add(contact.getStreet())
					.add(contact.getNumber()).add(contact.getCity()).add(contact.getZip()));
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
	
	public void editContact(){
	 //searchForContact method to find the contact you want to edit 
	int editMenu = 0;
	Scanner input = new Scanner(System.in);
	Contact contact = null; 
	do {
	 System.out.println("Please select search criteria : ");
	 System.out.println("1) Search by First Name"); 
	 System.out.println("2) Search by Last Name");
	 System.out.println("3) Search by Home Number");
	 System.out.println("4) Search by Mobile Number");
	 System.out.println("5) Search by email"); 
	 System.out.println("6) Search by Street"); 
	 System.out.println("7) Search by Street Number"); 
	 System.out.println("8) Search by City"); 
	 System.out.println("9) Search by Zip");
	 System.out.println("10) return to main menu");
	 try {
			editMenu = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid Option Selected.\nPlease select valid menu option.\n");
			input.nextLine();
		}
	} while (editMenu < 1 || editMenu > 11);
	
	//switch menu allowing to edit all parts of the contact constructor
	 switch(editMenu) {
	
	 case 1 : 
		System.out.println("Please enter the First name you want search in address book : ");
	 	String firstName = input.next();
	 	
	 	//used our search function  equalsignorecase for the search to eliminate capitalization mistakes by 
	 
	 	contact = search(contactList, c -> c.getFirstName().equalsIgnoreCase(firstName));
	 	
	 	//Checks if the first name is in our contact list, if not user will go back to main menu
	 	
	 	if(!contactList.contains(contact)) {
	 		System.out.println("Contact Does Not Exists! Return to main");
	 		return;
	 	}
	 	//print contact so user can make sure they are updating the correct contact
	 	
	 	printContact(contact);
	 	System.out.println("Edit First Name or Type exit");
	 	String updateFirstName = input.next();
	 	
	 	//If user does not want to edit that contact, they exit to return to main menu
	 	
	 	if(updateFirstName.equalsIgnoreCase("exit")) {
	 		System.out.println("You are heading back to main");
	 		return;
	 		
	 	};
	 	
	 	//updates/sets the first name to users input
	 	
	 	contact.setFirstName(updateFirstName);
	 	System.out.println("Contact First Name has been updated");
	 	printContact(contact);
	 break;
	 
	 //I used the same style for the rest of the cases.
	 
	 case 2 :
		System.out.println("Enter Last Name");
	 	String lastName = input.next();
	 	contact = search(contactList, c -> c.getLastName().equalsIgnoreCase(lastName));
	 	if(!contactList.contains(contact)) {
	 		System.out.println("Contact Does Not Exists! Return to main");
	 		return;
	 	}
	 	printContact(contact);
	 	System.out.println("Edit Last Name or type exit to return to main menu");
		String updateLastName = input.next();
		if(updateLastName.equalsIgnoreCase("exit")) {
	 		System.out.println("You are heading back to main");
	 		return;
		}
	 	contact.setLastName(updateLastName);
	 	System.out.println("Contact Last Name has been updated");
	 	printContact(contact);
	 	break;
	 case 3 :
		 System.out.println("Enter Home Phone");
		 String phoneHome = input.next();
		 contact = search(contactList, c -> c.getPhoneHome().equalsIgnoreCase(phoneHome));
		 if(!contactList.contains(contact)) {
		 		System.out.println("Contact Does Not Exists! Return to main");
		 		return;
		 	}
		 printContact(contact);
		 System.out.println("Edit Home Phone or type exit to return to main menu");
		 String updatePhoneHome = input.next();
		 if(updatePhoneHome.equalsIgnoreCase("exit")) {
		 		System.out.println("You are heading back to main");
		 		return;
			}
		 contact.setPhoneHome(updatePhoneHome);
		 System.out.println("Contact Home Phone has been updated");
		 printContact(contact);
		 break;
	 case 4 :
		 System.out.println("Enter Mobile Phone");
		 String phoneMobile = input.next();
		 contact = search(contactList, c -> c.getPhoneMobile().equalsIgnoreCase(phoneMobile));
		 if(!contactList.contains(contact)) {
		 		System.out.println("Contact Does Not Exists! Return to main");
		 		return;
		 	}
		 printContact(contact);
		 System.out.println("Edit Mobile Phone or type exit to return to main menu");
		 String updatePhoneMobile = input.next();
		 if(updatePhoneMobile.equalsIgnoreCase("exit")) {
		 		System.out.println("You are heading back to main");
		 		return;
			}
		 contact.setPhoneMobile(updatePhoneMobile);
		 System.out.println("Contact Mobile Phone has been updated");
		 printContact(contact);
		 break;
	 case 5 :
		 System.out.println("Enter Email");
		 String email = input.next();
		 contact = search(contactList, c -> c.getEmail().equalsIgnoreCase(email));
		 if(!contactList.contains(contact)) {
		 		System.out.println("Contact Does Not Exists! Return to main");
		 		return;
		 	}
		 printContact(contact);
		 System.out.println("Edit Email or type exit to return to main menu");
		 String updateEmail = input.next();
		 if(updateEmail.equalsIgnoreCase("exit")) {
		 		System.out.println("You are heading back to main");
		 		return;
			}
		 contact.setEmail(updateEmail);
		 System.out.println("Contact Email has been updated");
		 printContact(contact);
		 break;
	 case 6 :
		 System.out.println("Enter Street");
		 String street = input.next();
		 contact = search(contactList, c -> c.getStreet().equalsIgnoreCase(street));
		 if(!contactList.contains(contact)) {
		 		System.out.println("Contact Does Not Exists! Return to main");
		 		return;
		 	}
		 printContact(contact);
		 System.out.println("Edit Street or type exit to return to main menu");
		 String updateStreet = input.next();
		 if(updateStreet.equalsIgnoreCase("exit")) {
		 		System.out.println("You are heading back to main");
		 		return;
			}
		 contact.setStreet(updateStreet);
		 System.out.println("Contact Street has been updated");
		 printContact(contact);
		 break;
	 case 7 :
		 System.out.println("Enter Street Number");
		 String number = input.next();
		 contact = search(contactList, c -> c.getNumber().equalsIgnoreCase(number));
		 if(!contactList.contains(contact)) {
		 		System.out.println("Contact Does Not Exists! Return to main");
		 		return;
		 	}
		 printContact(contact);
		 System.out.println("Edit Street Number or type exit to return to main menu");
		 String updateNumber = input.next();
		 if(updateNumber.equalsIgnoreCase("exit")) {
		 		System.out.println("You are heading back to main");
		 		return;
			}
		 contact.setNumber(updateNumber);
		 System.out.println("Contact First Name has been updated");
		 printContact(contact);
		 break;
	 case 8 :
		 System.out.println("Enter City");
		 String city = input.next();
		 contact = search(contactList, c -> c.getCity().equalsIgnoreCase(city));
		 if(!contactList.contains(contact)) {
		 		System.out.println("Contact Does Not Exists! Return to main");
		 		return;
		 	}
		 printContact(contact);
		 System.out.println("Edit City or type exit to head back to main menu");
		 String updateCity = input.next();
		 if(updateCity.equalsIgnoreCase("exit")) {
		 		System.out.println("You are heading back to main");
		 		return;
			}
		 contact.setCity(updateCity);
		 System.out.println("Contact First Name has been updated");
		 printContact(contact);
		 break;
	 case 9 :
		 System.out.println("Enter Zip");
		 String zip = input.next();
		 contact = search(contactList, c -> c.getZip().equalsIgnoreCase(zip));
		 if(!contactList.contains(contact)) {
		 		System.out.println("Contact Does Not Exists! Return to main");
		 		return;
		 	}
		 printContact(contact);
		 System.out.println("Edit First Name or type exit to head back to main menu");
		 String updateZip = input.next();
		 if(updateZip.equalsIgnoreCase("exit")) {
		 		System.out.println("You are heading back to main");
		 		return;
			}
		 contact.setZip(updateZip);
		 System.out.println("Contact First Name has been updated");
		 printContact(contact);
		 break;
	 default:
		 return;
	  }
	 }
		
	   private void seedData() {
		contactList = new ArrayList<>();

		contactList.add(new Contact("Brennan", "Thompson", "1234567890", "1675309544", "thompson.brennan@gmail.com",
				"Main St", "112", "Pittsburgh", "15909"));
		contactList.add(new Contact("Trevor", "Benyack", "4125551111", "4125552222", "trb@gmail.com", "Maryland",
				"1000", "Pittsburgh", "15232"));
		contactList.add(new Contact("Greg", "Conti", "7245552468", "4125553888", "gdc@gmail.com", "Palameno", "234",
				"Gibsonia", "15044"));
		contactList.add(new Contact("Kedar", "Dhital", "4219992222", "7249990000", "kdd@gmail.com", "Sunny Dr", "142",
				"Pittsburgh", "15227"));
		contactList.add(new Contact("Ashwini", "Neelgund", "9876554321", "8795462130", "ad@hotmail.com", "per scholas",
				"413", "Green Tree", "78945"));
	}

	//reads the contacts file into memory
	public void readContactFile() {

		File contactsFile = new File("src/main/java/contacts.csv");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(contactsFile));

			// saves each line in the file to an index of the array
			Object[] array = reader.lines().toArray();

			contactList = new ArrayList<>();

			// splits each array object into String pieces and adds a new contact to the
			// contactList for each line
			for (int i = 1; i < array.length; i++) {

				String tempString = (String) array[i];
				String[] tempContactArray = tempString.split(",");

				contactList.add(new Contact(tempContactArray[0], tempContactArray[1], tempContactArray[2],
						tempContactArray[3], tempContactArray[4], tempContactArray[5], tempContactArray[6],
						tempContactArray[7], tempContactArray[8]

				));

			}

			reader.close();

		} catch (IOException exc) {
			System.out.println("There was an error reading from the file.");
		}

	}

	// writes the contacts in memory to file
	public void writeContactFile() {
		File contactsFile = new File("src/main/java/contacts.csv");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(contactsFile));
			writer.write("Name,Surname,Phone,Mobile,E-mail,Street,Number,Town,Zip");
			writer.newLine();
			for (Contact c : contactList) {
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
