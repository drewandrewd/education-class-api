package phoenixit.education.services;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import phoenixit.education.exceptions.ClassNotFoundException;
import phoenixit.education.models.Class;
import phoenixit.education.models.ClassRequest;
import phoenixit.education.models.ClassResponse;

import java.util.List;

public interface ClassService {

    ClassResponse create(ClassRequest classRequest) throws Throwable;

    ClassResponse update(ClassRequest classRequest) throws Throwable;

    ClassResponse delete(String id) throws Throwable;

    List<ClassResponse> fetchAll(String field, Sort.Direction direction) throws ClassNotFoundException;

    Page<ClassResponse> fetchAllWithPagination(String field, Sort.Direction direction, int pages, int size);

    ClassResponse fetchById(String id) throws ClassNotFoundException, java.lang.ClassNotFoundException;
}
