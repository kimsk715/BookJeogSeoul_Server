const noticeLayout = (() =>{
    const showNoticeList = (noticeList) => {
        const noticeTBody = document.querySelector(".notice-table tbody");
        let text = ``;
        const pagination = noticeList.pagination;
        const pageWrap = document.querySelector(".notice-pagination")

        noticeList.noticeDTOList.forEach((notice) => {
            text += `
            <tr>
                <td>${notice.id}</td>
                <td>${notice.noticeTitle}</td>
                <td>${notice.noticeText}</td>
                <td>${notice.createdDate}</td>
                <td>${notice.updatedDate}</td>
                <td>
                    <button type="button" class="detail-btn modal-detail-btn" value="${notice.id}">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        noticeTBody.innerHTML = text;
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
        if(pagination.check === 0){
            text = "";
        }
        pageWrap.innerHTML = text;
        text=``;
    }

    const showNoticeDetail = (notice) => {
        commonModalContainer.innerHTML = `
        <div class="modal-header">
                    <h3>공지 상세정보</h3>
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

                    <div class="notice-info">
                        <h4>INFO</h4>
                        <table class="info-table">
                            <tbody>
                                <tr>
                                    <th>공지번호</th>
                                    <td id="notice-id">${notice.id}</td>
                                    <th>작성일시</th>
                                    <td id="notice-date">${notice.createdDate}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 공지 상세 내용 -->
                    <div class="notice-detail">
                        <h4>공지 제목</h4>
                        <textarea class="detail-content" id="notice-title">${notice.noticeTitle}</textarea>
                    </div>
                    <div class="notice-detail">
                        <h4>공지 내용</h4>
                        <textarea class="detail-content" id="notice-text">${notice.noticeText}</textarea>
                    </div>
                    <div class="notice-detail">
                        <h4>첨부 파일</h4>
                        <img src="/images/common/favicon.png" alt="임시 이미지">
                        <img src="/images/common/favicon.png" alt="임시 이미지">
                        <img src="/images/common/favicon.png" alt="임시 이미지">
                        <img src="/images/common/favicon.png" alt="임시 이미지">
                        
                    </div>
                    
                </div>

                <!-- 모달 푸터 -->
                <div class="modal-footer notice-footer">
                    <button type="button" class="cancel-btn close-button">취소</button>
                    <button type="button" class="save-btn close-button" value="${notice.id}">저장</button>
                </div>
        `
    }



    return{showNoticeList : showNoticeList, showNoticeDetail : showNoticeDetail};
    const showAddedFile = (imageUrl, file, index) => {
        const fileLists = [];
        const fileArea = document.querySelector(".file-preview")
        const newFile = document.createElement("li");
        newFile.dataset.index = index; // data-index 속성 부여
        newFile.innerHTML = `<img src="${imageUrl}" style="width : 100px" alt="임시 이미지">`
        fileArea.append(newFile);
    }

    return{showNoticeList : showNoticeList, showNoticeDetail : showNoticeDetail, showAddedFile:showAddedFile};

    })();