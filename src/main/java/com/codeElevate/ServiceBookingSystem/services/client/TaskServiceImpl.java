package com.codeElevate.ServiceBookingSystem.services.client;

import com.codeElevate.ServiceBookingSystem.dto.CommentDTO;
import com.codeElevate.ServiceBookingSystem.dto.TaskDTO;
import com.codeElevate.ServiceBookingSystem.entity.Comment;
import com.codeElevate.ServiceBookingSystem.entity.Task;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.enums.TaskStatus;
import com.codeElevate.ServiceBookingSystem.repository.CommentRepository;
import com.codeElevate.ServiceBookingSystem.repository.TaskRepository;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository = null;
    private final CommentRepository commentRepository = null;
    private final UserRepository userRepository = null;

    @Override
    public List<TaskDTO> getTasksByUserId(Long id) {
        return taskRepository.findByUserId(id)
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Task updateTask(Long taskId, String status) {
        Optional<Task> optionalRoom = taskRepository.findById(taskId);
        if (optionalRoom.isPresent()) {
            Task existingTask = optionalRoom.get();
            TaskStatus newStatus = mapStringToTaskStatus(status);
            existingTask.setTaskStatus(newStatus);
            return taskRepository.save(existingTask);
        }
        return null;
    }

    private TaskStatus mapStringToTaskStatus(String statusString) {
        return switch (statusString) {
            case "PENDING" -> TaskStatus.PENDING;
            case "INPROGRESS" -> TaskStatus.INPROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;
        };
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(Task::getTaskDTO).orElse(null);
    }

    public Comment createComment(Long taskId, Long postedBy, String content){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Optional<User> optionalUser = userRepository.findById(taskId);
        if(optionalTask.isPresent() && optionalUser.isPresent()){
            Comment comment = new Comment();
            comment.setTask(optionalTask.get());
            comment.setContent(content);
            comment.setPostedBy(optionalUser.get());
            comment.setCreatedAt(new Date());
            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Task not found");
    }

    public List<CommentDTO> getCommentsByTaskId(Long taskId){
        return commentRepository.findByTaskId(taskId).stream().map(Comment::getDto).collect(Collectors.toList());
    }
}
