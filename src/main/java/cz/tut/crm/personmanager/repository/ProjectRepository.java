package cz.tut.crm.personmanager.repository;

import cz.tut.crm.personmanager.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String projectId);
    @Override
    Iterable<Project> findAll();
}
