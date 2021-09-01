package com.pwc.assignment.controller;

import com.pwc.assignment.authentication.JwtTokenProvider;
import com.pwc.assignment.model.SystemUser;
import com.pwc.assignment.service.UserService;
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
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping()
    public Response getUsers(@RequestParam(value = "size", required = false, defaultValue = "5") @Min(value = 5) @Max(value = 25) int size,
                             @RequestParam(value = "offset", required = false, defaultValue = "0") @Min(value = 0) int offset,
                             @RequestParam(value = "name", required = false) String name) {
        return new OkResponse("Success!", userService.getEntities(size, offset, name));
    }

    @GetMapping("/{id}")
    public Response getUserById(@PathVariable("id") Integer id) {
        return new OkResponse("Success!", userService.getEntityById(id));
    }

    @PostMapping()
    public Response addNewUser(@NotNull(message = "Request body cannot be null") @Valid @RequestBody SystemUser systemUser) {
        Integer userId = JwtTokenProvider.extractUserData(request).getId();
        systemUser.setAddedBy(userId);
        return new CreatedResponse("User added successfully!", userService.insertEntity(systemUser));
    }

    @PutMapping("/{id}")
    public Response updateUser(@PathVariable("id") Integer id,
                               @NotNull(message = "Request body cannot be null") @Valid @RequestBody SystemUser systemUser) {

        userService.updateEntity(id, systemUser);
        return new OkResponse("User updated successfully!", systemUser);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable("id") Integer id) {
        userService.deleteEntity(id);
        return new NoContentResponse();
    }
}
