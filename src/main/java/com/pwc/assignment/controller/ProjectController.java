package com.pwc.assignment.controller;

import com.pwc.assignment.model.Project;
import com.pwc.assignment.service.ProjectService;
import com.pwc.assignment.service.response.Response;
import com.pwc.assignment.service.response.success.CreatedResponse;
import com.pwc.assignment.service.response.success.NoContentResponse;
import com.pwc.assignment.service.response.success.OkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;


    @GetMapping()
    public Response getProjects(@RequestParam(value = "size", required = false, defaultValue = "5") @Min(value = 5) @Max(value = 25) int size,
                                   @RequestParam(value = "offset", required = false, defaultValue = "0") @Min(value = 0) int offset,
                                   @RequestParam(value = "name", required = false) String name) {
        return new OkResponse("Success!", projectService.getEntities(size, offset, name));
    }

    @GetMapping("/{id}")
    public Response getProjectById(@PathVariable("id") Integer id) {
        return new OkResponse("Success!", projectService.getEntityById(id));
    }

    @PostMapping()
    public Response addNewProject(@NotNull(message = "Request body cannot be null") @Valid @RequestBody Project project) {
        return new CreatedResponse("Project added successfully!", projectService.insertEntity(project));
    }

    @PutMapping("/{id}")
    public Response updateDepartment(@PathVariable("id") Integer id,
                                     @NotNull(message = "Request body cannot be null") @Valid @RequestBody Project project) {

        projectService.updateEntity(id, project);
        return new OkResponse("Project updated successfully!", project);
    }

    @DeleteMapping("/{id}")
    public Response deleteProject(@PathVariable("id") Integer id) {

        projectService.deleteEntity(id);
        return new NoContentResponse();
    }
}
