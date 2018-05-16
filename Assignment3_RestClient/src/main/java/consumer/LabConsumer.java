package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Laboratory;
import consumerContracts.ILabConsumer;
import model.request.LabRequestModel;
import model.request.LoginModel;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LabConsumer implements ILabConsumer {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Laboratory> getAllLaboratories(LoginModel credentials) {
        String url = "/laboratories";
        return getLaboratories(url, credentials);
    }

    @Override
    public List<Laboratory> getFilteredLaboratories(String keyword, LoginModel credentials) {
        String url = "/laboratories?keyword=" + keyword;
        return getLaboratories(url, credentials);
    }

    @Override
    public Laboratory getLaboratoryById(int id, LoginModel credentials) {
        String url = "/laboratories/" + id;
        return getLaboratories(url, credentials).get(0);
    }

    private List<Laboratory> getLaboratories(String url, LoginModel credentials){
        List<Laboratory> labs = new ArrayList<>();
        try{
            String response = HttpClient.getRequest(url, credentials);
            Laboratory[] labArray = mapper.readValue(response, Laboratory[].class);
            labs = Arrays.asList(labArray);
        } catch (IOException e){
            e.printStackTrace();
        }
        return labs;
    }

    @Override
    public boolean addLaboratory(LabRequestModel lab, LoginModel credentials){
        boolean added = false;
        String url = "/laboratories";
        try{
            String body = mapper.writeValueAsString(lab);
            added = HttpClient.postRequest(url, new StringEntity(body), credentials);
        } catch(IOException e){
            e.printStackTrace();
        }
        return added;
    }

    public boolean editLab(LabRequestModel lab, int id, LoginModel credentials){
        boolean edited = false;
        String url = "/laboratories/" + id;
        try{
            String body = mapper.writeValueAsString(lab);
            edited = HttpClient.putRequest(url, new StringEntity(body), credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return edited;
    }

    @Override
    public boolean deleteLab(int id, LoginModel credentials) {
        String url = "/laboratories/" + id;
        boolean deleted = false;
        try{
            deleted = HttpClient.deleteRequest(url, credentials);
        } catch(IOException e){
            e.printStackTrace();
        }
        return deleted;
    }
}
