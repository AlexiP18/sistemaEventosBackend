package com.codeElevate.ServiceBookingSystem.services.client;

import com.codeElevate.ServiceBookingSystem.dto.CommentDTO;
import com.codeElevate.ServiceBookingSystem.dto.TaskDTO;
import com.codeElevate.ServiceBookingSystem.entity.Comment;
import com.codeElevate.ServiceBookingSystem.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasksByUserId(Long id);

    Task updateTask(Long taskId, String status);

    TaskDTO getTaskById(Long id);

    Comment createComment(Long taskId, Long postedBy, String content);

    List<CommentDTO> getCommentsByTaskId(Long taskId);
}
