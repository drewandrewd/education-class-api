package phoenixit.education.services;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import phoenixit.education.exceptions.JsonRpcException;
import phoenixit.education.models.ClassLinkMessage;

@Service
public class ClassLinkServiceImpl implements ClassLinkService {

    private JsonRpcHttpClient jsonRpcHttpClient;

    @Override
    public Long create(String name) throws Throwable {
        ClassLinkMessage classItem = new ClassLinkMessage();
        classItem.setClassNodeTitle(name);
        try {
            Long classNodeId = jsonRpcHttpClient.invoke("classCreate", new Object[]{classItem}, Long.class);
            return classNodeId;
        } catch (Exception e) {
            throw new JsonRpcException();
        }
    }

    @Override
    public void update(String name, Long classNodeId) throws Throwable {
        ClassLinkMessage classItem = new ClassLinkMessage();
        classItem.setClassNodeTitle(name);
        classItem.setClassNodeId(classNodeId);
        try {
            jsonRpcHttpClient.invoke("classUpdate", new Object[]{classItem}, ClassLinkMessage.class);
        } catch (Exception e) {
            throw new JsonRpcException();
        }
    }

    @Override
    public void delete(Long classNodeId) throws Throwable {
        try {
            jsonRpcHttpClient.invoke("classDelete", new Object[]{classNodeId}, Long.class);
        } catch (Exception e) {
            throw new JsonRpcException();
        }
    }

    @Autowired
    @Qualifier("jsonRpcHttpClient")
    public void setJsonRpcHttpClient(JsonRpcHttpClient jsonRpcHttpClient) {
        this.jsonRpcHttpClient = jsonRpcHttpClient;
    }
}
