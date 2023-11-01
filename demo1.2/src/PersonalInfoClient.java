/**
 * @Author:hongjianbo
 * @Decription:
 * @Date:Created on 2023/11/1 9:41
 * @Email:2832766479@qq.com
 */
import java.util.List;
import java.util.Scanner;

public class PersonalInfoClient {
    public static void main(String[] args) {
        PersonalInfoService service = new PersonalInfoService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Personal Information Management");
            System.out.println("1. View All Personal Info");
            System.out.println("2. Add Personal Info");
            System.out.println("3. Edit Personal Info");
            System.out.println("4. Delete Personal Info");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<PersonalInfo> personalInfos = service.getAllPersonalInfos();
                    for (PersonalInfo personalInfo : personalInfos) {
                        System.out.println("ID: " + personalInfo.getId() + ", Name: " + personalInfo.getName() + ", Email: " + personalInfo.getEmail());
                    }
                    break;
                case 2:
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    PersonalInfo newPersonalInfo = new PersonalInfo();
                    newPersonalInfo.setName(name);
                    newPersonalInfo.setEmail(email);
                    service.addPersonalInfo(newPersonalInfo);
                    break;
                case 3:
                    System.out.print("Enter ID to edit: ");
                    int idToEdit = scanner.nextInt();
                    PersonalInfo personalInfoToEdit = new PersonalInfo();
                    personalInfoToEdit.setId(idToEdit);
                    System.out.print("Enter Name: ");
                    String newName = scanner.next();
                    System.out.print("Enter Email: ");
                    String newEmail = scanner.next();
                    personalInfoToEdit.setName(newName);
                    personalInfoToEdit.setEmail(newEmail);
                    service.editPersonalInfo(personalInfoToEdit);
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    service.deletePersonalInfo(idToDelete);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
