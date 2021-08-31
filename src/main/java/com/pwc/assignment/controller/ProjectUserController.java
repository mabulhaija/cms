package com.pwc.assignment.controller;

import com.pwc.assignment.model.ProjectUsers;
import com.pwc.assignment.service.ProjectUserService;
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
import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/project-users/{project_id}")
public class ProjectUserController {
    @Autowired
    ProjectUserService projectUserService;

    @GetMapping()
    public Response getProjectUsers(@PathParam ("{project_id}") Integer projectId,
                                   @RequestParam(value = "size", required = false, defaultValue = "5") @Min(value = 5) @Max(value = 25) int size,
                                   @RequestParam(value = "offset", required = false, defaultValue = "0") @Min(value = 0) int offset) {

        return new OkResponse("Success!", projectUserService.getEntities(projectId, size, offset));
    }

    @PostMapping
    public Response assignUserToProject(@NotNull(message = "Request body cannot be null") @Valid @RequestBody ProjectUsers projectUsers) {
        return new CreatedResponse("user assigned successfully!", projectUserService.insertEntity(projectUsers));
    }
    @DeleteMapping("/{user_id}")
    public Response unAssignUser(@PathParam ("{project_id}") Integer projectId,
                                 @PathParam ("{user_id}") Integer userId){
        projectUserService.deleteEntity(projectId, userId);
        return new NoContentResponse();
    }

}
