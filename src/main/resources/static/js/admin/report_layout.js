const reportLayout = (() =>{
    const showBookPostReports = (bookPostReportInfoDTOList) => {
        const bookPostReportTBody = document.querySelector(".book-post-report-table tbody");
        let text = ``;
        const pagination = bookPostReportInfoDTOList.pagination;
        const pageWrap = document.querySelector(".book-post-report-pagination")
        let className = "";
        let classText = "";
        bookPostReportInfoDTOListList.forEach((report) => {
            bookPostReportVO
            switch(report.bookPostReportVO.bookPostReportType){
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

            if(report.bookPostReportVO.bookPostReportStatus === "WAITING"){
                classText = "suspended"
            }
            else{
                classText = "active"
            }

            text += `
            <tr>
                <td>${report.bookPostReportVO.id}</td>
                <td>${report.bookPostReportVO.bookPostId}</td>
                <td>${report.bookPostReportVO.bookPostReportTitle}</td>
                <td>${report.bookPostReportVO.title}</td>
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
    // const showMemberDetail = (memberReport) =>{
    //     commonModalContainer.innerHTML = `
    //         <div class="modal-header">
    //                 <h3>문의 상세정보</h3>
    //                 <button type="button" class="close-btn">
    //                     <img
    //                         class="close-button"
    //                         src="/images/admin/cross.png"
    //                         alt="닫기"
    //                     />
    //                 </button>
    //             </div>
    //
    //             <!-- 모달 본문 -->
    //             <div class="modal-body">
    //                 <!-- 문의 기본 정보 -->
    //
    //                 <div class="normal-report-info">
    //                     <h4>INFO</h4>
    //                     <table class="info-table">
    //                         <tbody>
    //                             <tr>
    //                                 <th>문의번호</th>
    //                                 <td id="normal-report-id">${memberReport.id}</td>
    //                                 <th>문의일시</th>
    //                                 <td id="normal-report-date">${memberReport.createdDate}</td>
    //                             </tr>
    //                             <tr>
    //                                 <th>문의자</th>
    //                                 <td id="normal-inquirer">${memberReport.memberName}</td>
    //                                 <th>이메일</th>
    //                                 <td id="normal-report-email">
    //                                     ${memberReport.memberEmail}
    //                                 </td>
    //                             </tr>
    //                             <tr>
    //                                 <th>문의유형</th>
    //                                 <td colspan="3" id="normal-report-type">
    //                                     ${memberReport.memberReportType}
    //                                 </td>
    //                             </tr>
    //                         </tbody>
    //                     </table>
    //                 </div>
    //
    //                 <!-- 문의 상세 내용 -->
    //                 <div class="normal-report-detail">
    //                     <h4>문의 상세내용</h4>
    //                     <div class="detail-content" id="normal-report-content">
    //                         ${memberReport.memberReportText}
    //                     </div>
    //                 </div>
    //
    //                 <!-- 처리 상태 선택 -->
    //                 <div class="status-selection">
    //                     <h4>처리 상태</h4>
    //                     <select class="status-select">
    //                         <option value="처리대기">처리대기</option>
    //                         <option value="처리완료">처리완료</option>
    //                     </select>
    //                 </div>
    //
    //                 <!-- 관리자 답변 영역 -->
    //                 <div class="admin-answer">
    //                     <h4>답변 내역</h4>
    //                     <textarea
    //                         placeholder="답변할 내용을 입력하세요"
    //                         id="normal-report-answer"
    //                     ></textarea>
    //                 </div>
    //             </div>
    //
    //             <!-- 모달 푸터 -->
    //             <div class="modal-footer">
    //                 <button type="button" class="cancel-btn close-button">취소</button>
    //                 <button type="button" class="save-btn close-button" value="${memberReport.id}">저장</button>
    //             </div>
    //     `
    // }
    // const showSponsorReport = (sponsorReportList) =>{
    //     const sponsorReportTBody = document.querySelector(".sponsor-report-table tbody");
    //     let text = ``;
    //     const pagination = sponsorReportList.pagination;
    //     const pageWrap = document.querySelector(".sponsor-report-pagination")
    //     console.log(sponsorReportList)
    //     let className = "";
    //     let classText = "";
    //     sponsorReportList.sponsorReportDTOList.forEach((report) => {
    //
    //         switch(report.sponsorReportType){
    //             case("BOOK"):
    //                 className = "active"
    //                 break;
    //             case("ACCOUNT"):
    //                 className = "suspended"
    //                 break;
    //             case("ETC"):
    //                 className = "dormancy"
    //                 break;
    //         }
    //
    //         if(report.sponsorReportStatus === "WAITING"){
    //             classText = "suspended"
    //         }
    //         else{
    //             classText = "active"
    //         }
    //
    //         text += `
    //         <tr>
    //             <td>${report.id}</td>
    //             <td>${report.sponsorId}</td>
    //             <td>${report.sponsorReportTitle}</td>
    //             <td>${report.createdDate}</td>
    //             <td><span class="status ${className}">${report.sponsorReportType}</span></td>
    //             <td><span class="status ${classText}">${report.sponsorReportStatus}</span></td>
    //             <td>
    //                 <button type="button" class="detail-btn modal-detail-btn" value="${report.id}">
    //                     상세보기
    //                 </button>
    //             </td>
    //         </tr>
    //         `
    //     })
    //     sponsorReportTBody.innerHTML = text;
    //     text=``;
    //
    //     if(pagination.prev) {
    //         text += `<button type="button" class="page-btn" id="${pagination.startPage - 1}">이전</button>`
    //     }
    //     for(let i = pagination.startPage; i<=pagination.endPage; i++){
    //         if(pagination.page === i){
    //             text += `<button type="button" class="page-btn active" id="${i}">${i}</button>`
    //             continue;
    //         }
    //         text += `<button type="button" class="page-btn" id="${i}">${i}</button>`
    //     }
    //     if(pagination.next){
    //         text += `<button type="button" class="page-btn" id="${pagination.endPage + 1}">다음</button>`
    //     }
    //
    //     pageWrap.innerHTML = text;
    //     text=``;
    // }
    // const showSponsorDetail = (sponsorReport) =>{
    //     commonModalContainer.innerHTML = `
    //         <div class="modal-header">
    //                 <h3>문의 상세정보</h3>
    //                 <button type="button" class="close-btn">
    //                     <img
    //                         class="close-button"
    //                         src="/images/admin/cross.png"
    //                         alt="닫기"
    //                     />
    //                 </button>
    //             </div>
    //
    //             <!-- 모달 본문 -->
    //             <div class="modal-body">
    //                 <!-- 문의 기본 정보 -->
    //
    //                 <div class="sponsor-report-info">
    //                     <h4>INFO</h4>
    //                     <table class="info-table">
    //                         <tbody>
    //                             <tr>
    //                                 <th>문의번호</th>
    //                                 <td id="sponsor-report-id">${sponsorReport.id}</td>
    //                                 <th>문의일시</th>
    //                                 <td id="sponsor-report-date">${sponsorReport.createdDate}</td>
    //                             </tr>
    //                             <tr>
    //                                 <th>문의자</th>
    //                                 <td id="sponsor-inquirer">${sponsorReport.sponsorName}</td>
    //                                 <th>이메일</th>
    //                                 <td id="sponsor-report-email">
    //                                     ${sponsorReport.sponsorEmail}
    //                                 </td>
    //                             </tr>
    //                             <tr>
    //                                 <th>문의유형</th>
    //                                 <td colspan="3" id="sponsor-report-type">
    //                                     ${sponsorReport.sponsorReportType}
    //                                 </td>
    //                             </tr>
    //                         </tbody>
    //                     </table>
    //                 </div>
    //
    //                 <!-- 문의 상세 내용 -->
    //                 <div class="sponsor-report-detail">
    //                     <h4>문의 상세내용</h4>
    //                     <div class="detail-content" id="sponsor-report-content">
    //                         ${sponsorReport.sponsorReportText}
    //                     </div>
    //                 </div>
    //
    //                 <!-- 처리 상태 선택 -->
    //                 <div class="status-selection">
    //                     <h4>처리 상태</h4>
    //                     <select class="status-select">
    //                         <option value="처리대기">처리대기</option>
    //                         <option value="처리완료">처리완료</option>
    //                     </select>
    //                 </div>
    //
    //                 <!-- 관리자 답변 영역 -->
    //                 <div class="admin-answer">
    //                     <h4>답변 내역</h4>
    //                     <textarea
    //                         placeholder="답변할 내용을 입력하세요"
    //                         id="sponsor-report-answer"
    //                     ></textarea>
    //                 </div>
    //             </div>
    //
    //             <!-- 모달 푸터 -->
    //             <div class="modal-footer">
    //                 <button type="button" class="cancel-btn close-button">취소</button>
    //                 <button type="button" class="save-btn close-button" value="${sponsorReport.id}">저장</button>
    //             </div>
    //     `
    // }
    return{showBookPostReports : showBookPostReports,
            // showMemberDetail:showMemberDetail,
            // showSponsorReport:showSponsorReport,
            // showSponsorDetail:showSponsorDetail
            };

    })();