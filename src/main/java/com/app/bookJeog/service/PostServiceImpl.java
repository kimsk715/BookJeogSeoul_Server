package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.repository.CommentDAO;
import com.app.bookJeog.repository.FavoriteDAO;
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
    private final FavoriteDAO favoriteDAO;
    private final CommentDAO commentDAO;

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
            int voteCount = favoriteDAO.receiverVote(receiver.getId());
            receiverInfoDTO.setReceiverLikeCount(voteCount);

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

    @Override
    public List<BookDonateDTO> getDonateBooks() {
        List<BookDonateVO> tempList = postDAO.findDonatedBooks();
        List<BookDonateDTO> bookDonateDTOList = new ArrayList<>();
        for (BookDonateVO bookDonateVO : tempList) {
            BookDonateDTO bookDonateDTO = new BookDonateDTO();
            bookDonateDTO.setId(bookDonateVO.getId());
            bookDonateDTO.setBookReceivedStatus(bookDonateVO.getBookReceivedStatus());
            bookDonateDTO.setBookIsbn(bookDonateVO.getBookIsbn());
            bookDonateDTO.setBookTitle(bookDonateVO.getBookTitle());
            bookDonateDTO.setMemberId(bookDonateVO.getMemberId());
            bookDonateDTO.setCreatedDate(bookDonateVO.getCreatedDate());
            bookDonateDTO.setUpdatedDate(bookDonateVO.getUpdatedDate());
            bookDonateDTOList.add(bookDonateDTO);
        }
        return bookDonateDTOList;
    }

    @Override
    public List<BookDonateDTO> getAllDonateBooks() {
        List<BookDonateVO> tempList = postDAO.findAllDonatedBooks();
        List<BookDonateDTO> bookDonateDTOList = new ArrayList<>();
        for (BookDonateVO bookDonateVO : tempList) {
            BookDonateDTO bookDonateDTO = new BookDonateDTO();
            bookDonateDTO.setId(bookDonateVO.getId());
            bookDonateDTO.setBookReceivedStatus(bookDonateVO.getBookReceivedStatus());
            bookDonateDTO.setBookIsbn(bookDonateVO.getBookIsbn());
            bookDonateDTO.setBookTitle(bookDonateVO.getBookTitle());
            bookDonateDTO.setMemberId(bookDonateVO.getMemberId());
            bookDonateDTO.setCreatedDate(bookDonateVO.getCreatedDate());
            bookDonateDTO.setUpdatedDate(bookDonateVO.getUpdatedDate());
            bookDonateDTOList.add(bookDonateDTO);
        }
        return bookDonateDTOList;
    }

//  게시판 조회용 후원 대상
    @Override
    public List<ReceiverPostDTO> getReceiverPosts() {
        List<ReceiverVO> tempList = postDAO.findAllReceivers();
        List<ReceiverPostDTO> receiverPostDTOList = new ArrayList<>();
        for (ReceiverVO receiverVO : tempList) {
            Long memberId = postDAO.findPostById(receiverVO.getId()).getMemberId();
            ReceiverPostDTO receiverPostDTO = new ReceiverPostDTO();
            receiverPostDTO.setId(receiverVO.getId());
            receiverPostDTO.setSponsorName(memberDAO.findSponsorMemberById(memberId).getSponsorName());
            receiverPostDTO.setCreatedDate(receiverVO.getCreatedDate());
            receiverPostDTO.setReceiverText(receiverVO.getReceiverText());
            receiverPostDTO.setLikeScore(favoriteDAO.receiverVote(receiverVO.getId()));
            receiverPostDTO.setReceiverText(receiverVO.getReceiverText());
            receiverPostDTO.setReceiverTitle(receiverVO.getReceiverTitle());
            receiverPostDTOList.add(receiverPostDTO);
        }
        log.info(receiverPostDTOList.toString());
        return receiverPostDTOList;
    }

    @Override
    public ReceiverPostDTO getReceiverPostById(Long id) {
        ReceiverPostDTO receiverPostDTO = new ReceiverPostDTO();
        ReceiverVO receiverVO = postDAO.findReceiverPostById(id);
        log.info(receiverVO.toString());
        Long memberId = postDAO.findPostById(receiverVO.getId()).getMemberId();
        receiverPostDTO.setId(id);
        receiverPostDTO.setReceiverText(receiverVO.getReceiverText());
        receiverPostDTO.setReceiverTitle(receiverVO.getReceiverTitle());
        receiverPostDTO.setSponsorName(memberDAO.findSponsorMemberById(memberId).getSponsorName());
        receiverPostDTO.setCreatedDate(receiverVO.getCreatedDate());
        receiverPostDTO.setUpdatedDate(receiverVO.getUpdatedDate());
        receiverPostDTO.setLikeScore(favoriteDAO.receiverVote(receiverVO.getId()));

        return receiverPostDTO;
    }

    @Override
    public List<DonateCertPostDTO> getAllDonateCerts() {
        List<DonateCertVO> tempList = postDAO.findAllDonatedCerts();
        List<DonateCertPostDTO> donateCertPostDTOList = new ArrayList<>();
        for(DonateCertVO donateCertVO : tempList) {
            DonateCertPostDTO donateCertPostDTO = new DonateCertPostDTO();
            donateCertPostDTO.setId(donateCertVO.getId());
            donateCertPostDTO.setMemberId(donateCertVO.getMemberId());
            donateCertPostDTO.setCreatedDate(donateCertVO.getCreatedDate());
            donateCertPostDTO.setUpdatedDate(donateCertVO.getUpdatedDate());
            donateCertPostDTO.setDonateCertTitle(donateCertVO.getDonateCertTitle());
            donateCertPostDTO.setDonateCertText(donateCertVO.getDonateCertText());
            donateCertPostDTOList.add(donateCertPostDTO);
        }
//        log.info(donateCertPostDTOList.toString());
        return donateCertPostDTOList;
    }

    @Override
    public DonateCertPostDTO getDonateCertById(Long id) {
        DonateCertPostDTO donateCertPostDTO = new DonateCertPostDTO();
        DonateCertVO donateCertVO = postDAO.findDonateCertById(id);
        donateCertPostDTO.setId(id);
        donateCertPostDTO.setMemberId(donateCertVO.getMemberId());
        donateCertPostDTO.setCreatedDate(donateCertVO.getCreatedDate());
        donateCertPostDTO.setUpdatedDate(donateCertVO.getUpdatedDate());
        donateCertPostDTO.setDonateCertTitle(donateCertVO.getDonateCertTitle());
        donateCertPostDTO.setDonateCertText(donateCertVO.getDonateCertText());
        donateCertPostDTO.setCommentCount(commentDAO.countAllCommentByPostId(id));
        donateCertPostDTO.setSponsorName(memberDAO.findSponsorMemberById(donateCertVO.getMemberId()).getSponsorName());

        return donateCertPostDTO;
    }

    @Override
    public List<DiscussionPostDTO> getAllDiscussions() {
        List<DiscussionVO> tempList = postDAO.findAllDiscussions();
        List<DiscussionPostDTO> discussionPostDTOList = new ArrayList<>();
        for(DiscussionVO discussionVO : tempList) {
            DiscussionPostDTO discussionPostDTO = new DiscussionPostDTO();
            discussionPostDTO.setId(discussionVO.getId());
            discussionPostDTO.setCreatedDate(discussionVO.getCreatedDate());
            discussionPostDTO.setBookIsbn(discussionVO.getBookIsbn());

            discussionPostDTO.setDiscussionTitle(discussionVO.getDiscussionTitle());
            discussionPostDTO.setDiscussionText(discussionVO.getDiscussionText());
            discussionPostDTO.setCommentCount(commentDAO.countAllCommentByPostId(discussionVO.getId()));
            discussionPostDTOList.add(discussionPostDTO);
        }
        return discussionPostDTOList;
    }

    @Override
    public DiscussionPostDTO getDiscussionById(Long id) {
        DiscussionPostDTO discussionPostDTO = new DiscussionPostDTO();
        DiscussionVO discussionVO = postDAO.findDiscussionById(id);
        discussionPostDTO.setId(id);
        discussionPostDTO.setCreatedDate(discussionVO.getCreatedDate());
        discussionPostDTO.setBookIsbn(discussionVO.getBookIsbn());

        discussionPostDTO.setDiscussionTitle(discussionVO.getDiscussionTitle());
        discussionPostDTO.setDiscussionText(discussionVO.getDiscussionText());
        discussionPostDTO.setCommentCount(commentDAO.countAllCommentByPostId(discussionVO.getId()));
        return discussionPostDTO;
    }

    @Override
    public void insertDiscussion(DiscussionDTO discussionDTO) {
        postDAO.insertDiscussion(discussionDTO.toVO());
    }

    @Override
    public PostVO insertPost(PostVO postVO) {
        postDAO.insertPost(postVO);
        return postVO;
    }

    @Override
    public void setTopReceiver(Long receiverId) {
        postDAO.setTopReceiver(receiverId);
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
        PostVO postVO = fileBookPostDTO.toPostVO();
        postDAO.insertPostBook(postVO);

        // 일반 독후감 세부 정보 작성 (tbl_book_post)
        fileBookPostDTO.setBookPostId(postVO.getId());
        postDAO.insertBookPost(fileBookPostDTO.toBookPostVO());

        // 선정도서 독후감 작성 (tbl_selected_book_post)
        SelectedBookPostVO selectedBookPostVO = fileBookPostDTO.toSelectedBookPostVO();
        if (selectedBookPostVO.getBookId() != null) {
            postDAO.insertSelectedBookPost(selectedBookPostVO);
        }

        // 첨부파일이 있을 경우
        if (files != null && !files.isEmpty()) {
            IntStream.range(0, files.size())
                    .filter(i -> !files.get(i).isEmpty())
                    .forEach(i -> {
                        MultipartFile file = files.get(i);
                        BookPostFileDTO dto = fileBookPostDTO.getFileList().get(i);

                        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

                        dto.setFileName(fileName);
                        dto.setFilePath(todayPath);
                        dto.setBookPostId(fileBookPostDTO.getBookPostId()); // 독후감 ID 설정

                        try {
                            new File(rootPath).mkdirs();
                            file.transferTo(new File(rootPath, fileName));
                        } catch (IOException e) {
                            throw new RuntimeException("파일 저장 실패", e);
                        }

                        // 파일 insert
                        FileVO fileVO = dto.toFileVO();
                        fileDAO.insertFiles(fileVO);
                        dto.setId(fileVO.getId());

                        // 파일-독후감 매핑 insert (각 dto 기반)
                        fileDAO.insertBookPostFiles(dto.toBookPostFileVO());
                    });
        }
        return fileBookPostDTO.getBookPostId();
    }
    // 오늘 날짜로 경로 반환
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public void setDonateCertPost(DonateCertVO donateCertVO) {
        postDAO.insertDonateCertPost(donateCertVO);
    }

    // 독후감 수정을 위한 데이터 조회
    public FileBookPostDTO findWrittenBookPost(Long bookPostId) {
        FileBookPostDTO fileBookPostDTO = postDAO.findWrittenBookPost(bookPostId);
        FileBookPostDTO selected = postDAO.findWrittenSelectedPost(bookPostId);

        // 선정도서 독후감이면 추가정보 조회
        if (selected != null) {
            fileBookPostDTO.setBookPostStatus(selected.getBookPostStatus());
        }

        return fileBookPostDTO;
    }

    // 독후감 수정을 위한 첨부파일 조회
    public List<BookPostFileDTO> findWrittenBookPostFiles(Long bookPostId) {
        return fileDAO.findWrittenBookPostFiles(bookPostId);
    }

    // 독후감 수정
    public void setBookPost(FileBookPostDTO fileBookPostDTO, List<MultipartFile> files, List<Long> deletedFileIds){
        String todayPath = getPath(); // 오늘 날짜 폴더 경로 생성
        String rootPath = "C:/upload/" + todayPath; // 실제 저장할 경로

        // 게시글 내용 수정
        PostVO postVO = fileBookPostDTO.toPostVO();
        postDAO.setPost(postVO);
        fileBookPostDTO.setBookPostId(postVO.getId());

        postDAO.setBookPost(fileBookPostDTO.toBookPostVO());

        if(fileBookPostDTO.getBookId() != null) {
            postDAO.setSelectedBookPost(fileBookPostDTO.toSelectedBookPostVO());
        }

        // 삭제할 파일 처리
        if(deletedFileIds != null && !deletedFileIds.isEmpty()) {
            for(Long fileId : deletedFileIds) {
                fileDAO.deleteBookPostFiles(fileId);
                fileDAO.deleteFiles(fileId);
            }
        }

        // 새로 첨부된 파일 처리
        if (files != null && !files.isEmpty()) {
            IntStream.range(0, files.size())
                    .filter(i -> !files.get(i).isEmpty())
                    .forEach(i -> {
                        MultipartFile file = files.get(i);
                        BookPostFileDTO fileDTO = fileBookPostDTO.getFileList().get(i);

                        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

                        fileDTO.setFileName(fileName);
                        fileDTO.setFilePath(todayPath);
                        fileDTO.setBookPostId(fileBookPostDTO.getBookPostId()); // 독후감 ID

                        try {
                            new File(rootPath).mkdirs();
                            file.transferTo(new File(rootPath, fileName));
                        } catch (IOException e) {
                            throw new RuntimeException("파일 저장 실패", e);
                        }

                        // DB insert
                        FileVO fileVO = fileDTO.toFileVO();
                        fileDAO.insertFiles(fileVO);
                        fileDTO.setId(fileVO.getId());

                        fileDAO.insertBookPostFiles(fileDTO.toBookPostFileVO());
            });
        }
    };
}

