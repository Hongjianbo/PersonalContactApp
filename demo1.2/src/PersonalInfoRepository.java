/**
 * @Author:hongjianbo
 * @Decription:
 * @Date:Created on 2023/11/1 9:40
 * @Email:2832766479@qq.com
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalInfoRepository {
    private static final String DATABASE_URL = "jdbc:sqlite:personal_info.db";

    public List<PersonalInfo> getAllPersonalInfos() {
        List<PersonalInfo> personalInfos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM personal_info");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                PersonalInfo personalInfo = new PersonalInfo();
                personalInfo.setId(resultSet.getInt("id"));
                personalInfo.setName(resultSet.getString("name"));
                personalInfo.setEmail(resultSet.getString("email"));
                personalInfos.add(personalInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personalInfos;
    }

    public void addPersonalInfo(PersonalInfo personalInfo) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO personal_info (name, email) VALUES (?, ?)")) {
            preparedStatement.setString(1, personalInfo.getName());
            preparedStatement.setString(2, personalInfo.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editPersonalInfo(PersonalInfo personalInfo) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE personal_info SET name = ?, email = ? WHERE id = ?")) {
            preparedStatement.setString(1, personalInfo.getName());
            preparedStatement.setString(2, personalInfo.getEmail());
            preparedStatement.setInt(3, personalInfo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePersonalInfo(int id) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM personal_info WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
