const reportLayout = (() =>{
    const showBookPostReports = (bookPostReportInfoDTOList) => {
        const bookPostReportTBody = document.querySelector(".book-post-report-table tbody");
        let text = ``;
        const pagination = bookPostReportInfoDTOList.pagination;
        const list = bookPostReportInfoDTOList.bookPostReportInfoDTOList
        const pageWrap = document.querySelector(".book-post-report-pagination")
        let className = "";
        let classText = "";
        list.forEach((report) => {

            switch(report.bookPostReportVO.bookPostReportType){
                case("욕설"):
                    className = "active"
                    break;
                case("음란성"):
                    className = "suspended"
                    break;
                case("광고성"):
                    className = "dormancy"
                    break;
                case("스포일러"):
                    className = "withdrawn"
                    break;
                case("기타"):
                    className = "active"
                    break;
            }

            if(report.bookPostReportVO.bookPostReportStatus === "WAITING"){
                classText = "suspended"
            }
            else{
                classText = "active"
            }

            text += `
            <tr>
                <td>${report.bookPostReportVO.id}</td>
                <td>${report.memberName}</td>
                <td>${report.bookPostTitle}</td>
                <td>${report.bookTitle}</td>
                <td><span class="status ${className}">${report.bookPostReportVO.bookPostReportType}</span></td>
                <td>${report.bookPostReportVO.createdDate}</td>               
                <td><span class="status ${classText}">${report.bookPostReportVO.bookPostReportStatus}</span></td>
                <td>
                    <button type="button" class="detail-btn modal-detail-btn" value="${report.bookPostReportVO.id}">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        bookPostReportTBody.innerHTML = text;
        text=``;

        if(pagination.prev) {
            text += `<button type="button" class="page-btn" id="${pagination.startPage - 1}">이전</button>`
        }
        for(let i = pagination.startPage; i<=pagination.endPage; i++){
            if(pagination.page === i){
                text += `<button type="button" class="page-btn active" id="${i}">${i}</button>`
                continue;
            }
            text += `<button type="button" class="page-btn" id="${i}">${i}</button>`
        }
        if(pagination.next){
            text += `<button type="button" class="page-btn" id="${pagination.endPage + 1}">다음</button>`
        }

        pageWrap.innerHTML = text;
        text=``;
    }
    const showBookPostReport = (bookPostReport) =>{
        console.log(bookPostReport)
        commonModalContainer.innerHTML = `
            <div class="modal-header">
                    <h3>신고 상세정보</h3>
                    <button type="button" class="close-btn">
                        <img
                            class="close-button"
                            src="/images/admin/cross.png"
                            alt="닫기"
                        />
                    </button>
                </div>

                <!-- 모달 본문 -->
                <div class="modal-body">
                    <!-- 문의 기본 정보 -->

                    <div class="normal-report-info">
                        <h4>INFO</h4>
                        <table class="info-table">
                            <tbody>
                                <tr>
                                    <th>문의번호</th>
                                    <td id="normal-report-id">${bookPostReport.bookPostReportVO.id}</td>
                                    <th>문의일시</th>
                                    <td id="normal-report-date">${bookPostReport.bookPostReportVO.createdDate}</td>
                                </tr>
                                <tr>
                                    <th>문의자</th>
                                    <td id="normal-inquirer">${bookPostReport.memberName}</td>
                                    <th>이메일</th>
                                    <td id="normal-report-email">
                                     
                                    </td>
                                </tr>
                                <tr>
                                    <th>문의유형</th>
                                    <td colspan="3" id="book-post-report-type">
                                        ${bookPostReport.bookPostReportVO.bookPostReportType}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 문의 상세 내용 -->
                    <div class="book-post-report-detail">
                        <h4>문의 상세내용</h4>
                        <div class="detail-content" id="book-post-report-content">
                            ${bookPostReport.bookPostReportVO.bookPostReportText}
                        </div>
                    </div>

                    <!-- 처리 상태 선택 -->
                    <div class="status-selection">
                        <h4>회원 상태</h4>
                        <select class="status-select">
                            <option value="처리대기">활동</option>
                            <option value="처리완료">정지</option>
                        </select>
                        <h4>독후감 상태</h4>
                        <select class="status-select">
                            <option value="처리대기">활성화</option>
                            <option value="처리완료">비활성화</option>
                        </select>
                    </div>
                    

                    <!-- 관리자 답변 영역 -->
                    <div class="admin-answer">
                        <h4>답변 내역</h4>
                        <textarea
                            placeholder="답변할 내용을 입력하세요"
                            id="book-post-report-answer"
                        ></textarea>
                    </div>
                </div>

                <!-- 모달 푸터 -->
                <div class="modal-footer book-post-report-footer">
                    <button type="button" class="cancel-btn close-button">취소</button>
                    <button type="button" class="save-btn close-button" value="${bookPostReport.bookPostReportVO.id}">저장</button>
                    <input type="hidden" value="${bookPostReport.bookPostReportVO.bookPostId}">
                </div>
        `
    }
    const showCommentReports = (commentReportList) =>{
        const commentReportTBody = document.querySelector(".comment-report-table tbody");
        let text = ``;
        const pagination = commentReportList.pagination;
        const pageWrap = document.querySelector(".comment-report-pagination")
        let className = "";
        let classText = "";
        commentReportList.commentReportInfoDTOList.forEach((report) => {

            switch(report.commentReportVO.commentReportType){
                case("ABUSE"):
                    className = "active"
                    break;
                case("SEXUAL"):
                    className = "suspended"
                    break;
                case("SPAM"):
                    className = "dormancy"
                    break;
                case("SPOILER"):
                    className = "withdrawn"
                    break;
                case("ETC"):
                    className = "active"
                    break;
            }

            if(report.commentReportVO.commentReportStatus === "WAITING"){
                classText = "suspended"
            }
            else{
                classText = "active"
            }

            text += `
            <tr>
                <td>${report.commentReportVO.id}</td>
                <td>${report.commentReportVO.commentId}</td>
                <td>${report.commentText}</td>
                <td>${report.commentReportVO.createdDate}</td>
                <td><span class="status ${className}">${report.commentReportVO.commentReportType}</span></td>
                <td><span class="status ${classText}">${report.commentReportVO.commentReportStatus}</span></td>
                <td>
                    <button type="button" class="detail-btn modal-detail-btn" value="${report.commentReportVO.id}">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        commentReportTBody.innerHTML = text;
        text=``;

        if(pagination.prev) {
            text += `<button type="button" class="page-btn" id="${pagination.startPage - 1}">이전</button>`
        }
        for(let i = pagination.startPage; i<=pagination.endPage; i++){
            if(pagination.page === i){
                text += `<button type="button" class="page-btn active" id="${i}">${i}</button>`
                continue;
            }
            text += `<button type="button" class="page-btn" id="${i}">${i}</button>`
        }
        if(pagination.next){
            text += `<button type="button" class="page-btn" id="${pagination.endPage + 1}">다음</button>`
        }

        pageWrap.innerHTML = text;
        text=``;
    }
    const showCommentReport = (commentReport) =>{
        commonModalContainer.innerHTML = `
            <div class="modal-header">
                    <h3>문의 상세정보</h3>
                    <button type="button" class="close-btn">
                        <img
                            class="close-button"
                            src="/images/admin/cross.png"
                            alt="닫기"
                        />
                    </button>
                </div>

                <!-- 모달 본문 -->
                <div class="modal-body">
                    <!-- 문의 기본 정보 -->

                    <div class="comment-report-info">
                        <h4>INFO</h4>
                        <table class="info-table">
                            <tbody>
                                <tr>
                                    <th>문의번호</th>
                                    <td id="comment-report-id">${commentReport.commentReportVO.id}</td>
                                    <th>문의일시</th>
                                    <td id="comment-report-date">${commentReport.commentReportVO.createdDate}</td>
                                </tr>
                                <tr>
                                    <th>문의자</th>
                                    <td id="comment-inquirer">${commentReport.memberName}</td>
                                    <th>이메일</th>
                                    <td id="comment-report-email">
                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th>문의유형</th>
                                    <td colspan="3" id="comment-report-type">
                                        ${commentReport.commentReportVO.commentReportType}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 문의 상세 내용 -->
                    <div class="comment-report-detail">
                        <h4>문의 상세내용</h4>
                        <div class="detail-content" id="comment-report-content">
                            ${commentReport.commentReportVO.commentReportText}
                        </div>
                    </div>

                    <!-- 처리 상태 선택 -->
                    <div class="status-selection">
                        <h4>회원 상태</h4>
                        <select class="status-select">
                            <option value="처리대기">활성</option>
                            <option value="처리완료">정지</option>
                        </select>
                        <h4>댓글 상태</h4>
                        <select class="status-select">
                            <option value="처리대기">유지</option>
                            <option value="처리완료">삭제</option>
                        </select>
                    </div>

                    <!-- 관리자 답변 영역 -->
                    <div class="admin-answer">
                        <h4>답변 내역</h4>
                        <textarea
                            placeholder="답변할 내용을 입력하세요"
                            id="comment-report-answer"
                        ></textarea>
                    </div>
                </div>

                <!-- 모달 푸터 -->
                <div class="modal-footer comment-report-footer">
                    <button type="button" class="cancel-btn close-button">취소</button>
                    <button type="button" class="save-btn close-button" value="${commentReport.commentReportVO.id}">저장</button>
                    <input type="hidden" value="${commentReport.commentReportVO.commentId}">
                </div>
        `
    }
    return{showBookPostReports : showBookPostReports,
        showBookPostReport : showBookPostReport,
        showCommentReports : showCommentReports,
        showCommentReport : showCommentReport
            };

    })();