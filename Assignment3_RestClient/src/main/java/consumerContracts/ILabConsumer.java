package consumerContracts;

import model.Laboratory;
import model.request.LabRequestModel;
import model.request.LoginModel;

import java.util.List;

public interface ILabConsumer {
    public List<Laboratory> getAllLaboratories(LoginModel credentials);
    public List<Laboratory> getFilteredLaboratories(String keyword, LoginModel credentials);
    public Laboratory getLaboratoryById(int id, LoginModel credentials);
    public int addLaboratory(LabRequestModel lab, LoginModel credentials);
    public boolean editLab(LabRequestModel lab, int id, LoginModel credentials);
    public boolean deleteLab(int id, LoginModel credentials);
}
