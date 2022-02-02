package phoenixit.education.jsonRPC;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import phoenixit.education.exceptions.JsonRpcException;
import phoenixit.education.exceptions.ClassNotFoundException;
import phoenixit.education.models.ClassRequest;
import phoenixit.education.models.ClassResponse;

import java.util.List;

@JsonRpcService("/api/v1/class.json")
public interface API {

    ClassResponse create(@JsonRpcParam(value = "classRequest") ClassRequest classRequest) throws Throwable;
    ClassResponse update(@JsonRpcParam(value = "classRequest") ClassRequest classRequest) throws Throwable;
    ClassResponse delete(@JsonRpcParam(value = "id") String id) throws Throwable;
    List<ClassResponse> fetchAll(@JsonRpcParam(value = "field") String field, @JsonRpcParam(value = "direction") Sort.Direction direction) throws ClassNotFoundException;
    Page<ClassResponse> fetchAllWithPagination(@JsonRpcParam(value = "field") String field, @JsonRpcParam(value = "direction") Sort.Direction direction, @JsonRpcParam(value = "pages") int pages, @JsonRpcParam(value = "size") int size) throws ClassNotFoundException;
    ClassResponse fetchById(@JsonRpcParam(value = "id") String id) throws ClassNotFoundException, java.lang.ClassNotFoundException;
}
