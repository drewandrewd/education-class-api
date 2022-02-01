package phoenixit.education;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import phoenixit.education.components.Converter;
import phoenixit.education.exceptions.JsonRpcException;
import phoenixit.education.exceptions.ClassNotFoundException;
import phoenixit.education.models.ClassResponse;
import phoenixit.education.repositories.ClassRepository;
import phoenixit.education.services.ClassLinkService;
import phoenixit.education.services.ClassService;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@SpringBootTest
class EducationModelApiApplicationTests {

    private ClassService classService;
    private ClassRepository repository;
    private ClassLinkService modelLinkService;
    private Converter converter;

    @Test
    void contextLoads() {
    }
//
//    @Test
//    void createTest() {
//        ModelRequest modelRequest = new ModelRequest();
//        modelRequest.setName("Andrew0000");
//        modelRequest.setComment("comment0");
//        modelRequest.setType("Paper");
//        String id = modelService.create(modelRequest).getId();
//        log.info("Response: " + id);
//        Optional<Model> model = repository.findById(id);
//        log.info("model " + model);
//    }
//
//    @Test
//    void updateTest() throws ModelNotFoundException {
//        ModelRequest modelRequest = new ModelRequest();
//        modelRequest.setId("61bc6a2b5b61ce233dfc8611");
//        modelRequest.setName("Andrew8");
//        modelRequest.setComment("commentOOP");
//        modelRequest.setType("Electronic");
//        log.info("Response: " + modelService.update(modelRequest));
//
//    }
//
//    @Test
//    void deleteTest() throws ModelNotFoundException {
//        ModelRequest modelRequest = new ModelRequest();
//        modelRequest.setId("61bc6a2b5b61ce233dfc8611");
//        modelService.delete(modelRequest);
//        //log.info("Delete: " + );
//    }
//
    @Test
    void fetchAllTest() throws ClassNotFoundException {
        List<ClassResponse> list = classService.fetchAll("name", DESC);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }

    @Test
    void fetchAllWithPaginationTest() {
        Page<ClassResponse> listOfModels = classService.fetchAllWithPagination("name", DESC, 1, 5);
        System.out.println(listOfModels.getContent());
    }

    @Test void fetchByIdTest() throws ClassNotFoundException, java.lang.ClassNotFoundException {
        ClassResponse classItem = classService.fetchById("61f7c035143e6972d8324229");
        System.out.println(classItem.getName());
    }


    @Autowired
    public void setModelService(ClassService classService) {
        this.classService = classService;
    }

    @Autowired
    public void setRepository(ClassRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setModelLinkService(ClassLinkService modelLinkService) {
        this.modelLinkService = modelLinkService;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }
}
