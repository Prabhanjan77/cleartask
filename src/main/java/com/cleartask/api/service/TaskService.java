package com.cleartask.api.service;

import com.cleartask.api.model.Task;
import com.cleartask.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cleartask.api.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks(String userName){
        Optional<User> user = userService.getUserByUserName(userName);
        if(user.isPresent()){
            return user.get().getTasks();
        }
        return null;
    }

    public Task createTask(Task task, String userName){
        User user = userService.getUserByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with name: " + userName));

        task.setUser(user);
        taskRepository.save(task);
        return task;
    }

    public boolean updateTaskStatus(Long taskId, boolean status){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));
        task.setCompleted(status);
        taskRepository.save(task);
        return true;
    }
}
