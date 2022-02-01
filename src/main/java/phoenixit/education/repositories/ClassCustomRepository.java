package phoenixit.education.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import phoenixit.education.models.Class;

import java.util.List;


public interface ClassCustomRepository {

    List<Class> fetchAll(String field, Sort.Direction direction);
    Page<Class> fetchAllWithPagination(String field, Sort.Direction direction, int pages, int size);
}
