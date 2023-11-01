import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PersonalContactApp {
    private Connection connection;
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> contactList;

    public PersonalContactApp() {
        frame = new JFrame("Personal Contact App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            createTableIfNotExists();
            loadContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter Name:");
                String phoneNumber = JOptionPane.showInputDialog(frame, "Enter Phone Number:");
                addContact(name, phoneNumber);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String name = JOptionPane.showInputDialog(frame, "Enter Name:", listModel.getElementAt(selectedIndex));
                    String phoneNumber = JOptionPane.showInputDialog(frame, "Enter Phone Number:", getPhoneNumber(listModel.getElementAt(selectedIndex)));
                    editContact(listModel.getElementAt(selectedIndex), name, phoneNumber);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    deleteContact(listModel.getElementAt(selectedIndex));
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        frame.add(new JScrollPane(contactList), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void createTableIfNotExists() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phoneNumber TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        listModel.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, phoneNumber FROM contacts");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                listModel.addElement(name + " - " + phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addContact(String name, String phoneNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contacts (name, phoneNumber) VALUES (?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.executeUpdate();
            loadContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editContact(String oldName, String newName, String newPhoneNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contacts SET name = ?, phoneNumber = ? WHERE name = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newPhoneNumber);
            preparedStatement.setString(3, oldName);
            preparedStatement.executeUpdate();
            loadContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteContact(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contacts WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            loadContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getPhoneNumber(String contact) {
        String[] parts = contact.split(" - ");
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonalContactApp();
            }
        });
    }
}
