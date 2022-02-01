package phoenixit.education.components;

import org.springframework.stereotype.Component;
import phoenixit.education.models.*;
import phoenixit.education.models.Class;

@Component
public class Converter {

    public Class requestToClass(ClassRequest classRequest) {
        Class classItem = new Class();
        classItem.setId(classRequest.getId());
        classItem.setName(classRequest.getName());
        classItem.setComment(classRequest.getComment());
        classItem.setKey(DocClassKey.convertToEnum(classRequest.getKey()));
        return classItem;
    }

    public ClassResponse classToResponse(Class classItem) {
        ClassResponse responce = new ClassResponse();
        responce.setId(classItem.getId());
        responce.setName(classItem.getName());
        responce.setComment(classItem.getComment());
        responce.setKey(classItem.getKey().name());
        responce.setNodeId(classItem.getNodeId());
        responce.setCreateAt(classItem.getCreateAt());
        responce.setUpdateAt(classItem.getUpdateAt());
        responce.setCreator(classItem.getCreator());
        responce.setUpdater(classItem.getUpdater());
        return  responce;
    }
}
