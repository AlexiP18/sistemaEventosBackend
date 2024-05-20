package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.CommentDTO;
import com.codeElevate.ServiceBookingSystem.dto.TaskDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;
import com.codeElevate.ServiceBookingSystem.entity.Comment;
import com.codeElevate.ServiceBookingSystem.entity.Task;

import java.util.List;

public interface TaskService {

    Task postTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    Task updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    List<UserDto> getAllUsers();

    List<TaskDTO> searchTasks(String title);

    Comment createComment(Long taskId, Long postedBy, String content);

    List<CommentDTO> getCommentsByTaskId(Long taskId);
}
