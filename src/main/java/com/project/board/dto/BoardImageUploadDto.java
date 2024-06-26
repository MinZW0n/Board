package com.project.board.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardImageUploadDto {
    private List<MultipartFile> files;

}
