package phoenixit.education.models;

import lombok.*;

import java.util.Date;

@Data
public class ClassResponse {

    private String id;
    private String name;
    private String comment;
    private Long nodeId;
    private Date createAt;
    private Date updateAt;
    private String creator;
    private String updater;
    private String key;
}
