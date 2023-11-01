/**
 * @Author:hongjianbo
 * @Decription:
 * @Date:Created on 2023/11/1 10:18
 * @Email:2832766479@qq.com
 */
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void addContact(String name, String phone, String email) {
        Contact contact = new Contact(name, phone, email);
        contacts.add(contact);
    }

    public void updateContact(int index, String name, String phone, String email) {
        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            contact.setName(name);
            contact.setPhone(phone);
            contact.setEmail(email);
        }
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
        }
    }
}
