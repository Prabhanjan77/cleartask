package com.cleartask.api.controllers;

import com.cleartask.api.model.Task;
import com.cleartask.api.model.User;
import com.cleartask.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cleartask.api.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestParam String username, @RequestBody Task task) {
        Task createdTask = taskService.createTask(task, username);
        return ResponseEntity.ok(createdTask);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/tasks/{username}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable String username) {
        List<Task> taskList = taskService.getTasks(username);
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.getUserByUserName(username);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return null;
    }

    @PatchMapping("/tasks/{id}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long id, @RequestParam boolean status) {
        boolean updated = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok("Task status updated successfully.");
    }
}
