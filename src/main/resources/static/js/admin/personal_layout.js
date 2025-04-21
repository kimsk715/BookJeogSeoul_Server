const personalLayout = (() =>{
    const showPersonalMemberList = (personalMemberList) => {
        const personalMemberTBody = document.querySelector(".member-table tbody");
        let text = ``;
        const pagination = personalMemberList.pagination;
        const pageWrap = document.querySelector(".member-pagination")
        console.log(personalMemberList)
        personalMemberList.personalMemberDTOList.forEach((member) => {

            text += `
            <tr>
                <td>${member.id}</td>
                <td>${member.memberName}</td>
                <td>${member.memberEmail}</td>
                <td>${member.createdDate}</td>
                <td>${member.updatedDate}</td>
                <td>
                    <span class="status">${member.memberStatus}</span>
                </td>
                <td>
                    <button type="button" class="detail-btn book-detail-btn">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        personalMemberTBody.innerHTML = text;
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


    return{showPersonalMemberList : showPersonalMemberList};

    })();