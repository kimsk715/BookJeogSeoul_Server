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


            text += `
            <tr>
                <td>${inquiry.id}</td>
                <td>${inquiry.memberId}</td>
                <td>${inquiry.memberInquiryTitle}</td>
                <td>${inquiry.createdDate}</td>
                <td><span class="status ${className}">${inquiry.memberInquiryType}</span></td>
                <td><span class="status">${inquiry.memberInquiryStatus}</span></td>
                <td>
                    <button type="button" class="detail-btn book-detail-btn">
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


    return{showMemberInquiry : showMemberInquiry};

    })();