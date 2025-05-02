package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.DonateCertFileDTO;
import com.app.bookJeog.domain.dto.FileDTO;
import com.app.bookJeog.domain.dto.ReceiverFileDTO;
import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.domain.vo.ReceiverFileVO;
import com.app.bookJeog.repository.FileDAO;
import com.app.bookJeog.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FIleService {

    private final FileDAO fileDAO;
    private final PostDAO postDAO;

    @Override
    public void uploadDonateCertFiles(Long postId, List<MultipartFile> files) {
            // 오늘 날짜 기준으로 저장할 경로를 가져옴 ex) 2025/03/25
            String todayPath = getPath();
            log.info(files.size() + " files to be uploaded");
            log.info(files.toString());
            // 저장파일경로 설정 ex) /upload/2025/03/25
            // 서버에서 쓸 경로
//            String rootPath = "/upload/" + todayPath;
            // 로컬용 경로
                String rootPath = "C:/upload/" + todayPath;
            // 업로드할 폴더가 존재하지 않으면 생성
            File directory = new File(rootPath);
            if(!directory.exists()){
                directory.mkdirs(); // 여러개의 폴더 한 번에 생성
            }

            // 파일 리스트를 하나씩 순회
            files.forEach((file) -> {
                try {
                log.info("파일 첨부 확인");
                    log.info("파일 이름: '{}'", file.getOriginalFilename());
                // 파일명이 빈 문자열이면 업로드할 필요 없음 (빈 파일 방지)
                if(file.getOriginalFilename().equals("")){
                    return;
                }
                // 중복 방지를 위해 UUID 생성하여 파일명에 추가
                UUID uuid = UUID.randomUUID();

                // 파일 정보 저장을 위한 DTO 객체 생성
                FileDTO fileDTO = new FileDTO();

                // 파일명과 경로 설정 ex) 파일명 = uuid_원본파일명
                fileDTO.setFileName(uuid.toString() + "_" + file.getOriginalFilename());
                fileDTO.setFilePath(todayPath);
//                fileDTO.setFileText();  파일 설명이 들어가는 자리.
                // DB에 슈퍼키 먼저 insert
                FileVO fileVO = fileDTO.toVO();
                fileDAO.setFile(fileVO);
                log.info("fileVO 실행");
                // 서브키에 첨부파일이 속한 게시물 정보 저장
                DonateCertFileDTO donateCertFileDTO = new DonateCertFileDTO();
                donateCertFileDTO.setId(fileVO.getId());
                donateCertFileDTO.setDonateCertId(postId);
                fileDAO.setDonateCertFile(donateCertFileDTO.toVO());
                log.info("donateFileVO 실행");
                log.info("연결 ID: " + donateCertFileDTO.toVO().getDonateCertId());
                // transferTo 내가전달한 경로에 해당파일을 업로드해줌

                    // 파일을 실제 경로에 저장하는
                    file.transferTo(new File(rootPath, uuid.toString() + "_" + file.getOriginalFilename()));
                    log.info("실제 경로 저장 실행");
                    // 이미지 파일이면 썸네일 생성
                    if(file.getContentType().startsWith("image")){
                        FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuid.toString() + "_" + file.getOriginalFilename()));
                        Thumbnailator.createThumbnail(file.getInputStream(), out, 100, 100);
                        out.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

    }

    @Override
    public FileDTO getDonateCertFileByPostId(Long postId) {
        FileVO fileVO = fileDAO.findDonateCertFileByPostId(postId);
        if(fileVO != null){
            return toFileDTO(fileVO);
        }
        else{
            return null;
        }

    }

    @Override
    public List<FileDTO> getDonateCertFilesByPostId(Long postId) {
        List<FileVO> tempList = fileDAO.findDonateCertFilesByPostId(postId);
        List<FileDTO> fileDTOList = new ArrayList<>();
        for(FileVO fileVO : tempList){
            fileDTOList.add(toFileDTO(fileVO));
        }
        return fileDTOList;
    }

}
