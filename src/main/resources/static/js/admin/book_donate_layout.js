const donateLayout = (() =>{
    const showDonationList = (donationList) => {
        const donationTBody = document.querySelector(".book-donate-table tbody");
        let text = ``;
        const pagination = donationList.pagination;
        const pageWrap = document.querySelector(".book-donate-pagination")
        let className = "";

        donationList.bookDonateMemberDTOList.forEach((donation) => {
            switch(donation.bookDonateDTO.bookReceivedStatus){
                case("수취대기"):
                    className = "suspended"
                    break;
                case("수취완료"):
                    className = "active"
                    break;
            }
            text += `
            <tr>
                <td><input type="checkbox" class="chosenDonate" value="${donation.bookDonateDTO.id}"></td>
                <td>${donation.bookDonateDTO.id}</td>
                <td>${donation.memberName}</td>
                <td>${donation.bookDonateDTO.bookTitle}</td>
                <td>${donation.bookDonateDTO.bookIsbn}</td>
                <td>${donation.bookDonateDTO.createdDate}</td>
                <td>
                    <span class="status ${className}">${donation.bookDonateDTO.bookReceivedStatus}</span>
                </td>
                <td>
                    <button type="button" class="detail-btn modal-detail-btn" value="${donation.bookDonateDTO.id}">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        donationTBody.innerHTML = text;
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

    const showDonationDetail = (donation) =>{
        commonModalContainer.innerHTML = `
            <div class="modal-header">
                    <h3>기부 상세정보</h3>
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

                    <div class="book-donate-info">
                        <h4>INFO</h4>
                        <table class="info-table">
                            <tbody>
                                <tr>
                                    <th>문의번호</th>
                                    <td id="book-donate-id">${donation.bookDonateDTO.id}</td>
                                    <th>접수일시</th>
                                    <td id="book-donate-date">${donation.bookDonateDTO.createdDate}</td>
                                </tr>
                                <tr>
                                    <th>기부자</th>
                                    <td colspan="3">${donation.memberName}</td>   
                                </tr>
                                
                            </tbody>
                        </table>
                    </div>

                    <!-- 문의 상세 내용 -->
                    <div class="book-donate-detail">
                        <h4>문의 상세내용</h4>
                        <div class="detail-content" id="book-donate-content">
                            ${donation.donationText}
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
                            id="book-donate-answer"
                        ></textarea>
                    </div>
                </div>

                <!-- 모달 푸터 -->
                <div class="modal-footer">
                    <button type="button" class="cancel-btn close-button">취소</button>
                    <button type="button" class="save-btn close-button" value="${donation.id}">저장</button>
                </div>
        `
    }

    return{showDonationList : showDonationList, showDonationDetail:showDonationDetail};

    })();