package phoenixit.education.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import phoenixit.education.models.Class;

import java.util.List;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {

    List<Class> findByName(String name);

}
