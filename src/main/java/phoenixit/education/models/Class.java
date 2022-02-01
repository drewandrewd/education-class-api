package phoenixit.education.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "classes")
public class Class {

    @Id
    private String id;

    private String name;
    private String comment;
    private Long nodeId;
    private Date createAt;
    private Date updateAt;
    private String creator;
    private String updater;
    private DocClassKey key;

}
