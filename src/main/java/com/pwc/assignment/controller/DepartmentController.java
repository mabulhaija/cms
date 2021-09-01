package com.pwc.assignment.controller;

import com.pwc.assignment.authentication.JwtTokenProvider;
import com.pwc.assignment.model.Department;
import com.pwc.assignment.service.DepartmentService;
import com.pwc.assignment.service.response.Response;
import com.pwc.assignment.service.response.success.CreatedResponse;
import com.pwc.assignment.service.response.success.NoContentResponse;
import com.pwc.assignment.service.response.success.OkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    private HttpServletRequest request;


    @GetMapping
    public Response getDepartments(@RequestParam(value = "size", required = false, defaultValue = "5") @Min(value = 5) @Max(value = 25) int size,
                                   @RequestParam(value = "offset", required = false, defaultValue = "0") @Min(value = 0) int offset,
                                   @RequestParam(value = "name", required = false) String name) {
        return new OkResponse("Success!", departmentService.getEntities(size, offset, name));
    }

    @GetMapping("/{id}")
    public Response getDepartmentById(@PathVariable("id") Integer id) {
        return new OkResponse("Success!", departmentService.getEntityById(id));
    }
    @GetMapping("/{id}/users")
    public Response getDepartmentUsers(@PathVariable("id") Integer id,
                                       @RequestParam(value = "size", required = false, defaultValue = "5") @Min(value = 5) @Max(value = 25) int size,
                                       @RequestParam(value = "offset", required = false, defaultValue = "0") @Min(value = 0) int offset) {
        return new OkResponse("Success!", departmentService.getDepartmentUsers(id, size, offset));
    }

    @PostMapping
    public Response addNewDepartment(@NotNull(message = "Request body cannot be null") @Valid @RequestBody Department department) {
        Integer userId = JwtTokenProvider.extractUserData(request).getId();
        department.setAddedBy(userId);

        return new CreatedResponse("Department added successfully!", departmentService.insertEntity(department));
    }

    @PutMapping("/{id}")
    public Response updateDepartment(@PathVariable("id") Integer id,
                                     @NotNull(message = "Request body cannot be null") @Valid @RequestBody Department department) {

        departmentService.updateEntity(id, department);
        return new OkResponse("Department updated successfully!", department);
    }

    @DeleteMapping("/{id}")
    public Response deleteDepartment(@PathVariable("id") Integer id) {

        departmentService.deleteEntity(id);
        return new NoContentResponse();
    }
}
