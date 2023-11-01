/**
 * @Author:hongjianbo
 * @Decription:
 * @Date:Created on 2023/11/1 10:13
 * @Email:2832766479@qq.com
 */
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        ContactManager contactManager = new ContactManager(contacts);
        ConsoleUI consoleUI = new ConsoleUI(contactManager);

        consoleUI.displayMenu();
    }
}
