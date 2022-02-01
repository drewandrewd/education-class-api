package phoenixit.education.services;

public interface ClassLinkService {

    Long create(String name) throws Throwable;

    void update(String name, Long classNodeId) throws Throwable;

    void delete(Long classNodeId) throws Throwable;
}
