/**
 * @Author:hongjianbo
 * @Decription:
 * @Date:Created on 2023/11/1 9:43
 * @Email:2832766479@qq.com
 */
import java.util.List;

public class PersonalInfoService {
    private PersonalInfoRepository repository;

    public PersonalInfoService() {
        repository = new PersonalInfoRepository();
    }

    public List<PersonalInfo> getAllPersonalInfos() {
        return repository.getAllPersonalInfos();
    }

    public void addPersonalInfo(PersonalInfo personalInfo) {
        repository.addPersonalInfo(personalInfo);
    }

    public void editPersonalInfo(PersonalInfo personalInfo) {
        repository.editPersonalInfo(personalInfo);
    }

    public void deletePersonalInfo(int id) {
        repository.deletePersonalInfo(id);
    }
}
