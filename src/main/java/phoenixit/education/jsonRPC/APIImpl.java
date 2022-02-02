package phoenixit.education.jsonRPC;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import phoenixit.education.exceptions.JsonRpcException;
import phoenixit.education.exceptions.ClassNotFoundException;
import phoenixit.education.models.ClassRequest;
import phoenixit.education.models.ClassResponse;
import phoenixit.education.services.ClassLinkService;
import phoenixit.education.services.ClassService;

import java.util.List;

@Service
@AutoJsonRpcServiceImpl
public class APIImpl implements API {

 private ClassService classService;

    @Override
    public ClassResponse create(ClassRequest classRequest) throws Throwable {
        return classService.create(classRequest);
    }

    @Override
    public ClassResponse update(ClassRequest classRequest) throws Throwable {
        return classService.update(classRequest);
    }

    @Override
    public ClassResponse delete(String id) throws Throwable {
        return classService.delete(id);
    }

    @Override
    public List<ClassResponse> fetchAll(String field, Sort.Direction direction) throws ClassNotFoundException {
        return classService.fetchAll(field, direction);
    }

    @Override
    public Page<ClassResponse> fetchAllWithPagination(String field, Sort.Direction direction, int pages, int size) throws ClassNotFoundException {
        return classService.fetchAllWithPagination(field, direction, pages, size);
    }

    @Override
    public ClassResponse fetchById(String id) throws ClassNotFoundException, java.lang.ClassNotFoundException {
       return classService.fetchById(id);
    }

    @Autowired
    public void setclassService(ClassService classService) {
        this.classService = classService;
    }
}
