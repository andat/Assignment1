package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Laboratory;
import consumerContracts.ILabConsumer;
import model.request.LabRequestModel;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LabConsumer implements ILabConsumer {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Laboratory> getAllLaboratories() {
        String url = "/laboratories";
        return getLaboratories(url);
    }

    @Override
    public List<Laboratory> getFilteredLaboratories(String keyword) {
        String url = "/laboratories?keyword=" + keyword;
        return getLaboratories(url);
    }

    private List<Laboratory> getLaboratories(String url){
        List<Laboratory> labs = new ArrayList<>();
        try{
            String response = HttpClient.getRequest(url);
            Laboratory[] labArray = mapper.readValue(response, Laboratory[].class);
            labs = Arrays.asList(labArray);
        } catch (IOException e){
            e.printStackTrace();
        }
        return labs;
    }

    @Override
    public boolean addLaboratory(LabRequestModel lab){
        boolean added = false;
        String url = "/laboratories";
        try{
            String body = mapper.writeValueAsString(lab);
            added = HttpClient.postRequest(url, new StringEntity(body));
        } catch(IOException e){
            e.printStackTrace();
        }
        return added;
    }

    public boolean editLab(LabRequestModel lab, int id){
        boolean edited = false;
        String url = "/laboratories/" + id;
        try{
            String body = mapper.writeValueAsString(lab);
            edited = HttpClient.putRequest(url, new StringEntity(body));
        } catch (IOException e){
            e.printStackTrace();
        }
        return edited;
    }

    @Override
    public boolean deleteLab(int id) {
        String url = "/laboratories/" + id;
        boolean deleted = false;
        try{
            deleted = HttpClient.deleteRequest(url);
        } catch(IOException e){
            e.printStackTrace();
        }
        return deleted;
    }
}
