package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.repository.FileDAO;
import com.app.bookJeog.repository.MemberDAO;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;
    private final MemberDAO memberDAO;
    private final FileDAO fileDAO;
    private final BookPostFileDTO bookPostFileDTO;

    @Override
    public List<BookPostVO> getAllBookPost(Pagination pagination) {
        return postDAO.findAllBookPost(pagination);
    }

    @Override
    public List<DiscussionVO> getAllDiscussionPost(Pagination pagination) {

        return postDAO.findAllDiscussionPost(pagination);
    }

    @Override
    public int countAllDiscussionPost(Pagination pagination) {
        return postDAO.countAllDiscussionPost(pagination);
    }


    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostMemberDTO> selectThisBookPosts(Long isbn){
        return postDAO.findThisBookPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostMemberDTO> selectThisBookAllPosts(Long isbn, int offset){
        return postDAO.findThisBookAllPosts(isbn, offset);
    };

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn) {
        return postDAO.findBookAllPostsCount(isbn);
    }

    //   특정 독후감 상세정보 조회(회원 프로필, 첨부파일 포함)
    public FileBookPostDTO getPostWithFiles(Long id) {
        FileBookPostDTO fileBookPostDTO = postDAO.findPostDetail(id);

        if (fileBookPostDTO != null) {
            // 첨부파일이 없어도 null이 아닌 빈 리스트를 넣어주는 게 안전
            List<BookPostFileDTO> fileList = postDAO.findPostFiles(id);
            fileBookPostDTO.setFileList(fileList != null ? fileList : new ArrayList<>());
        }

        return fileBookPostDTO;
    }

    // top20 독후감 선정
    @Override
    public List<BookPostVO> getTopPosts() {
        return postDAO.findTopPosts();
    }

    // top20 독후감 테이블에 저장
    @Override
    public void insertTopBookPosts(MonthlyBookPostVO monthlyBookPostVO) {
        postDAO.insertTopBookPosts(monthlyBookPostVO);
    }

    // top20
    @Override
    public List<MonthlyBookPostVO> getMonthlyBookPosts(Pagination pagination) {
        return postDAO.findMonthlyBookPosts(pagination);
    }

    @Override
    public Optional<MonthlyBookPostVO> getBestPost() {
        return postDAO.findBestPost();
    }

    @Override
    public void insertBestPost(MonthlyBookPostVO monthlyBookPostVO) {
        postDAO.insertBestPost(monthlyBookPostVO);
    }

    @Override
    public int countAllBookPost() {
        return postDAO.countAllBookPost();
    }

    @Override
    public int countTopPosts() {
        return postDAO.countTopPosts();
    }

    @Override
    public BookPostVO getBookPostById(Long id) {
        return postDAO.findBookPostById(id);
    }

    @Override
    public void updateBookPostStatus(Long postId) {
        postDAO.updateBookPostStatus(postId);
    }

    @Override
    public List<ReceiverInfoDTO> getAllReceiverPost(Pagination pagination) {
        List<ReceiverVO> tempList = postDAO.findAllReceiverPost(pagination);
        List<ReceiverInfoDTO> receiverInfoDTOList = new ArrayList<>();
        for (ReceiverVO receiver : tempList) {
            ReceiverInfoDTO receiverInfoDTO = new ReceiverInfoDTO();
            ReceiverDTO receiverDTO = new ReceiverDTO();
            receiverDTO.setId(receiver.getId());
            receiverDTO.setReceiverText(receiver.getReceiverText());
            receiverDTO.setReceiverTitle(receiver.getReceiverTitle());
            receiverDTO.setReceiverStatus(receiver.getReceiverStatus());
            receiverDTO.setCreatedDate(receiver.getCreatedDate());
            receiverDTO.setUpdatedDate(receiver.getUpdatedDate());
            receiverInfoDTO.setReceiverDTO(receiverDTO);
            Long sponsorId = postDAO.findPostById(receiver.getId()).getMemberId();
            String sponsorName = memberDAO.findSponsorMemberById(sponsorId).getSponsorName();
            receiverInfoDTO.setSponsorName(sponsorName);
            receiverInfoDTO.setReceiverLikeCount(1); // 임시값;
            receiverInfoDTOList.add(receiverInfoDTO);
        }

        return receiverInfoDTOList;
    }

    @Override
    public int countAllReceiverPost(Pagination pagination) {
        return postDAO.countAllReceiverPost(pagination);
    }

    @Override
    public ReceiverDTO getReceiverById(Long id) {
        ReceiverVO receiverVO = postDAO.findReceiverById(id);
        ReceiverDTO receiverDTO = new ReceiverDTO();
        receiverDTO.setId(receiverVO.getId());
        receiverDTO.setReceiverText(receiverVO.getReceiverText());
        receiverDTO.setReceiverTitle(receiverVO.getReceiverTitle());
        receiverDTO.setReceiverStatus(receiverVO.getReceiverStatus());
        receiverDTO.setCreatedDate(receiverVO.getCreatedDate());
        receiverDTO.setUpdatedDate(receiverVO.getUpdatedDate());

        return receiverDTO;
    }

    // 독후감 피드 전체조회(무한스크롤)
    @Override
    public List<FileBookPostDTO> findAllBookPostFeed(int offset){
        return postDAO.findAllBookPostFeed(offset);
    };

    // 독후감 팔로잉 전체조회(무한스크롤)
    @Override
    public List<FileBookPostDTO> findFollowBookPostFeed(Long loginMemberId, int offset){
        return postDAO.findFollowBookPostFeed(loginMemberId, offset);
    };

    // 독후감 작성(첨부파일 포함)
    public Long write(FileBookPostDTO fileBookPostDTO, List<MultipartFile> files) {
        String todayPath = getPath(); // 오늘 날짜 폴더 경로 생성
        String rootPath = "C:/upload/" + todayPath; // 실제 저장할 경로

        // 일반 독후감 글 작성 (tbl_post)
        postDAO.insertPost(fileBookPostDTO.toPostVO());

        // 일반 독후감 세부 정보 작성 (tbl_book_post)
        postDAO.insertBookPost(fileBookPostDTO.toBookPostVO());

        // 선정도서 독후감 작성 (tbl_selected_book_post)
        // 일반 독후감은 없으니까 optional 사용
        Optional.ofNullable(fileBookPostDTO.toSelectedBookPostVO())
                .ifPresent(postDAO::insertSelectedBookPost);

        // 첨부파일이 있을 경우
        if (files != null && !files.isEmpty()) {
            // stream을 쓰면 file 객체 하나만 가져오기 때문에 여러개의 files를 가져오려면 IntStream
            IntStream.range(0, files.size())
                    .filter(i -> !files.get(i).isEmpty()) // 파일이 비어있지 않은 것만
                    .forEach(i -> { // 반복
                        MultipartFile file = files.get(i);
                        BookPostFileDTO dto = fileBookPostDTO.getFileList().get(i);

                        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

                        dto.setFileName(fileName);
                        dto.setFilePath(todayPath);

                        try {
                            new File(rootPath).mkdirs();
                            file.transferTo(new File(rootPath, fileName));
                        } catch (IOException e) {
                            throw new RuntimeException("파일 저장 실패", e);
                        }

                        // 파일 insert
                        FileVO fileVO = dto.toFileVO();
                        fileDAO.insertFiles(fileVO);

                        // 파일-독후감 매핑 insert
                        fileDAO.insertBookPostFiles(bookPostFileDTO.toBookPostFileVO());
                    });
        }
        return fileBookPostDTO.getBookPostId(); // 이동을 위해 독후감 id 리턴
    }
    // 오늘 날짜로 경로 반환
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
