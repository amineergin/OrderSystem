package com.example.qkare.orderSystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer id;
    private String comment;
    private Integer productId;
    private Integer customerId;


}
