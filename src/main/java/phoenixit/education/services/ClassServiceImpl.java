package phoenixit.education.services;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import phoenixit.education.components.Converter;
import phoenixit.education.models.Class;
import phoenixit.education.models.ClassRequest;
import phoenixit.education.models.ClassResponse;
import phoenixit.education.models.DocClassKey;
import phoenixit.education.repositories.ClassCustomRepository;
import phoenixit.education.repositories.ClassRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClassServiceImpl implements ClassService {

    private ClassRepository classRepository;
    private Converter converter;
    private ClassLinkService classLinkService;
    private ClassCustomRepository classCustomRepository;

    @Override
    public ClassResponse create(ClassRequest classRequest) throws Throwable {
        Class classItem = converter.requestToClass(classRequest);
        classItem.setCreateAt(new Date());
        classItem.setCreator("admin");
        Long classNodeId = classLinkService.create(classRequest.getName());
        classItem.setNodeId(classNodeId);
        return converter.classToResponse(classRepository.save(classItem));
    }

    @Override
    public ClassResponse update(ClassRequest classRequest) throws Throwable {
        Class updating = converter.requestToClass(classRequest);
        log.info("updating: " + updating);
        String updatingName = updating.getName();
        String updatingComment = updating.getComment();
        DocClassKey updatingType = updating.getKey();
        Optional<Class> current = classRepository.findById(updating.getId());
        log.info("current: " + current);
        if (current.isPresent()) {
            Class newClass = current.get();
            if (!newClass.equals(updating)) {
                if(!newClass.getName().equals(updatingName)) {
                    newClass.setName(updatingName);
                }
                if(!newClass.getComment().equals(updatingComment)) {
                    newClass.setComment(updatingComment);
                }
                if(!newClass.getKey().equals(updatingType)) {
                    newClass.setKey(updatingType);
                }
                newClass.setUpdateAt(new Date());
                newClass.setUpdater("admin");
            }
            classLinkService.update(classRequest.getName(), newClass.getNodeId());
            return converter.classToResponse(classRepository.save(newClass));
        } else {
            throw new ClassNotFoundException();
        }
    }

    @Override
    public ClassResponse delete(String id) throws Throwable {
        Optional<Class> current = classRepository.findById(id);
        if (!current.isPresent()) {
            throw new ClassNotFoundException();
        }
        Class newClass = current.get();
        classRepository.delete(newClass);
        classLinkService.delete(newClass.getNodeId());
        return converter.classToResponse(newClass);
    }

    @Override
    public List<ClassResponse> fetchAll(String field, Sort.Direction direction) {
        List<Class> classList = classCustomRepository.fetchAll(field, direction);
        List<ClassResponse> responseList = classList.stream().map(x -> converter.classToResponse(x)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public Page<ClassResponse> fetchAllWithPagination(String field, Sort.Direction direction, int pages, int size) {
        Page<Class> classList = classCustomRepository.fetchAllWithPagination(field, direction, pages, size);
        Page<ClassResponse> page = new PageImpl<>(classList.stream().map(x -> converter.classToResponse(x)).collect(Collectors.toList()));
        return page;
    }

    @Override
    public ClassResponse fetchById(String id) throws ClassNotFoundException {
        Optional<Class> classItem = classRepository.findById(id);
        if (classItem.isPresent()) {
            return converter.classToResponse(classItem.get());
        } else {
            throw new ClassNotFoundException();
        }
    }


    @Autowired
    public void setClassRepository(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setClassLinkService(ClassLinkServiceImpl classLinkService) {
        this.classLinkService = classLinkService;
    }

    @Autowired
    public void setClassCustomRepository(ClassCustomRepository classCustomRepository) {
        this.classCustomRepository = classCustomRepository;
    }
}
