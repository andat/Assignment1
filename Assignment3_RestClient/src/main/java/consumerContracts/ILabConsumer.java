package consumerContracts;

import model.Laboratory;
import model.request.LabRequestModel;

import java.util.List;

public interface ILabConsumer {
    public List<Laboratory> getAllLaboratories();
    public List<Laboratory> getFilteredLaboratories(String keyword);
    public boolean addLaboratory(LabRequestModel lab);
    public boolean editLab(LabRequestModel lab, int id);
    public boolean deleteLab(int id);
}
