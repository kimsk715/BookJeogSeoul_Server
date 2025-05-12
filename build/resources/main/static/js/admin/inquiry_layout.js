const inquiryLayout = (() =>{
    const showMemberInquiry = (memberInquiryList) => {
        const inquiryTBody = document.querySelector(".normal-inquiry-table tbody");
        let text = ``;
        const pagination = memberInquiryList.pagination;
        const pageWrap = document.querySelector(".normal-inquiry-pagination")
        console.log(memberInquiryList)
        let className = "";
        let classText = "";
        memberInquiryList.memberInquiryDTOList.forEach((inquiry) => {

            switch(inquiry.memberInquiryType){
                case("BOOK"):
                    className = "active"
                    break;
                case("ACCOUNT"):
                    className = "suspended"
                    break;
                case("ETC"):
                    className = "dormancy"
                    break;
            }

            if(inquiry.memberInquiryStatus === "WAITING"){
                classText = "suspended"
            }
            else{
                classText = "active"
            }

            text += `
            <tr>
                <td>${inquiry.id}</td>
                <td>${inquiry.memberId}</td>
                <td>${inquiry.memberInquiryTitle}</td>
                <td>${inquiry.createdDate}</td>
                <td><span class="status ${className}">${inquiry.memberInquiryType}</span></td>
                <td><span class="status ${classText}">${inquiry.memberInquiryStatus}</span></td>
                <td>
                    <button type="button" class="detail-btn modal-detail-btn" value="${inquiry.id}">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        inquiryTBody.innerHTML = text;
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
    const showMemberDetail = (memberInquiry) =>{
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

                    <div class="normal-inquiry-info">
                        <h4>INFO</h4>
                        <table class="info-table">
                            <tbody>
                                <tr>
                                    <th>문의번호</th>
                                    <td id="normal-inquiry-id">${memberInquiry.id}</td>
                                    <th>문의일시</th>
                                    <td id="normal-inquiry-date">${memberInquiry.createdDate}</td>
                                </tr>
                                <tr>
                                    <th>문의자</th>
                                    <td id="normal-inquirer">${memberInquiry.memberName}</td>
                                    <th>이메일</th>
                                    <td id="normal-inquiry-email">
                                        ${memberInquiry.memberEmail}
                                    </td>
                                </tr>
                                <tr>
                                    <th>문의유형</th>
                                    <td colspan="3" id="normal-inquiry-type">
                                        ${memberInquiry.memberInquiryType}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 문의 상세 내용 -->
                    <div class="normal-inquiry-detail">
                        <h4>문의 상세내용</h4>
                        <div class="detail-content" id="normal-inquiry-content">
                            ${memberInquiry.memberInquiryText}
                        </div>
                    </div>

                    <!-- 처리 상태 선택 -->
                    <div class="status-selection">
                        <h4>처리 상태</h4>
                        <select class="status-select">
                            <option value="처리대기">처리대기</option>
                            <option value="처리완료">처리완료</option>
                        </select>
                    </div>

                    <!-- 관리자 답변 영역 -->
                    <div class="admin-answer">
                        <h4>답변 내역</h4>
                        <textarea
                            placeholder="답변할 내용을 입력하세요"
                            id="normal-inquiry-answer"
                        ></textarea>
                    </div>
                </div>

                <!-- 모달 푸터 -->
                <div class="modal-footer normal-inquiry-footer">
                    <button type="button" class="cancel-btn close-button">취소</button>
                    <button type="button" class="save-btn close-button" value="${memberInquiry.id}">저장</button>
                </div>
        `
    }
    const showSponsorInquiry = (sponsorInquiryList) =>{
        const sponsorInquiryTBody = document.querySelector(".sponsor-inquiry-table tbody");
        let text = ``;
        const pagination = sponsorInquiryList.pagination;
        const pageWrap = document.querySelector(".sponsor-inquiry-pagination")
        console.log(sponsorInquiryList)
        let className = "";
        let classText = "";
        sponsorInquiryList.sponsorInquiryDTOList.forEach((inquiry) => {

            switch(inquiry.sponsorInquiryType){
                case("BOOK"):
                    className = "active"
                    break;
                case("ACCOUNT"):
                    className = "suspended"
                    break;
                case("ETC"):
                    className = "dormancy"
                    break;
            }

            if(inquiry.sponsorInquiryStatus === "WAITING"){
                classText = "suspended"
            }
            else{
                classText = "active"
            }

            text += `
            <tr>
                <td>${inquiry.id}</td>
                <td>${inquiry.sponsorId}</td>
                <td>${inquiry.sponsorInquiryTitle}</td>
                <td>${inquiry.createdDate}</td>
                <td><span class="status ${className}">${inquiry.sponsorInquiryType}</span></td>
                <td><span class="status ${classText}">${inquiry.sponsorInquiryStatus}</span></td>
                <td>
                    <button type="button" class="detail-btn modal-detail-btn" value="${inquiry.id}">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        sponsorInquiryTBody.innerHTML = text;
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
    const showSponsorDetail = (sponsorInquiry) =>{
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

                    <div class="sponsor-inquiry-info">
                        <h4>INFO</h4>
                        <table class="info-table">
                            <tbody>
                                <tr>
                                    <th>문의번호</th>
                                    <td id="sponsor-inquiry-id">${sponsorInquiry.id}</td>
                                    <th>문의일시</th>
                                    <td id="sponsor-inquiry-date">${sponsorInquiry.createdDate}</td>
                                </tr>
                                <tr>
                                    <th>문의자</th>
                                    <td id="sponsor-inquirer">${sponsorInquiry.sponsorName}</td>
                                    <th>이메일</th>
                                    <td id="sponsor-inquiry-email">
                                        ${sponsorInquiry.sponsorEmail}
                                    </td>
                                </tr>
                                <tr>
                                    <th>문의유형</th>
                                    <td colspan="3" id="sponsor-inquiry-type">
                                        ${sponsorInquiry.sponsorInquiryType}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 문의 상세 내용 -->
                    <div class="sponsor-inquiry-detail">
                        <h4>문의 상세내용</h4>
                        <div class="detail-content" id="sponsor-inquiry-content">
                            ${sponsorInquiry.sponsorInquiryText}
                        </div>
                    </div>

                    <!-- 처리 상태 선택 -->
                    <div class="status-selection">
                        <h4>처리 상태</h4>
                        <select class="status-select">
                            <option value="처리대기">처리대기</option>
                            <option value="처리완료">처리완료</option>
                        </select>
                    </div>

                    <!-- 관리자 답변 영역 -->
                    <div class="admin-answer">
                        <h4>답변 내역</h4>
                        <textarea
                            placeholder="답변할 내용을 입력하세요"
                            id="sponsor-inquiry-answer"
                        ></textarea>
                    </div>
                </div>

                <!-- 모달 푸터 -->
                <div class="modal-footer sponsor-inquiry-footer">
                    <button type="button" class="cancel-btn close-button">취소</button>
                    <button type="button" class="save-btn close-button" value="${sponsorInquiry.id}">저장</button>
                </div>
        `
    }
    return{showMemberInquiry : showMemberInquiry,
            showMemberDetail:showMemberDetail,
            showSponsorInquiry:showSponsorInquiry,
            showSponsorDetail:showSponsorDetail
            };

    })();