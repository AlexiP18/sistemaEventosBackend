package com.codeElevate.ServiceBookingSystem.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;

    private String content;

    private Date createdAt;

    private String postedBy;
}
