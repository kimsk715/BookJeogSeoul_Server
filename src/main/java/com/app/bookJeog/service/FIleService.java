package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.FileDTO;
import com.app.bookJeog.domain.vo.BookPostFileVO;
import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.domain.dto.NoticeFileDTO;
import com.app.bookJeog.domain.vo.BookPostFileVO;
import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.domain.vo.NoticeFileVO;
import com.app.bookJeog.domain.vo.ReceiverFileVO;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public interface FIleService{
    public void uploadDonateCertFiles(Long postId , List<MultipartFile> files);

    public default String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public FileDTO getDonateCertFileByPostId(Long postId);

    public List<FileDTO> getDonateCertFilesByPostId(Long postId);

    public default FileDTO toFileDTO(FileVO fileVO){
        FileDTO fileDTO = new FileDTO();
        if(fileVO != null){
            fileDTO.setId(fileVO.getId());
            fileDTO.setFilePath(fileVO.getFilePath());
            fileDTO.setFileName(fileVO.getFileName());
            fileDTO.setFileText(fileVO.getFileText());
            fileDTO.setCreatedDate(fileVO.getCreatedDate());
            fileDTO.setUpdatedDate(fileVO.getUpdatedDate());
        }

        return fileDTO;
    }

    public void uploadReceiverFiles(Long postId, List<MultipartFile> files);

    public FileDTO getReceiverFileByPostId(Long postId);

    public List<FileDTO> getReceiverFilesByPostId(Long postId);

    public void deleteFile(Long fileId);

    public void deleteReceiverFileByPostId(Long postId);

    public void deleteDonateCertFileByPostId(Long postId);

    public void insertExistingReceiverFile(FileDTO fileDTO, Long postId);

    public void insertExistingDonateCertFile(FileDTO fileDTO, Long postId);

    public void uploadNoticeFiles(Long noticeId, List<MultipartFile> files);

    public List<FileDTO> getNoticeFilesByNoticeId(Long noticeId);
}
