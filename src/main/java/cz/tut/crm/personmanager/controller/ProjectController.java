package cz.tut.crm.personmanager.controller;

import cz.tut.crm.personmanager.domain.Project;
import cz.tut.crm.personmanager.exception.ProjectIdException;
import cz.tut.crm.personmanager.services.MapValidationErrorService;
import cz.tut.crm.personmanager.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService validationErrorService;

    public ProjectController(ProjectService projectService, MapValidationErrorService validationErrorService) {
        this.projectService = projectService;
        this.validationErrorService = validationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {

        ResponseEntity<?> errorMap = validationErrorService.mapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        Project saveProject = projectService.saveOrUpdate(project);
        return new ResponseEntity<>(saveProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID "+projectId+" not found!");
        }
        return new ResponseEntity<>(project,HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return  projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<>("Project with ID: "+projectId+" was deleted",HttpStatus.OK);
    }
}
