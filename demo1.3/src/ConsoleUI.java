/**
 * @Author:hongjianbo
 * @Decription:
 * @Date:Created on 2023/11/1 10:18
 * @Email:2832766479@qq.com
 */
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private ContactManager contactManager;
    private Scanner scanner;

    public ConsoleUI(ContactManager contactManager) {
        this.contactManager = contactManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Personal Contact Manager");
            System.out.println("1. View Contacts");
            System.out.println("2. Add Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewContacts() {
        List<Contact> contacts = contactManager.getAllContacts();
        System.out.println("Contacts:");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getName());
        }
    }

    public void addContact() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        contactManager.addContact(name, phone, email);
        System.out.println("Contact added successfully.");
    }

    public void updateContact() {
        viewContacts();
        System.out.print("Select the contact to update (enter the number): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        contactManager.updateContact(choice - 1, name, phone, email);
        System.out.println("Contact updated successfully.");
    }

    public void deleteContact() {
        viewContacts();
        System.out.print("Select the contact to delete (enter the number): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        contactManager.deleteContact(choice - 1);
        System.out.println("Contact deleted successfully.");
    }
}

