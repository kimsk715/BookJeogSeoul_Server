const memberLayout = (() =>{
    const showPersonalMemberList = (personalMemberList) => {
        const personalMemberTBody = document.querySelector(".member-table tbody");
        let text = ``;
        const pagination = personalMemberList.pagination;
        const pageWrap = document.querySelector(".member-pagination")

        let className = "";
        let classText = "";
        personalMemberList.personalMemberDTOList.forEach((member) => {
            switch (member.memberStatus){
                case("ACTIVE") :
                    className = "active";
                    classText = "활성"
                    break;
                case("DORMANCY"):
                    className = "dormant"
                    classText = "휴면"
                    break;
                case("SUSPENDED"):
                    className = "suspended"
                    classText = "정지"
                    break;
                case("DELETE"):
                    className = "withdrawn"
                    classText = "탈퇴"
                    break;
            }
            text += `
            <tr>
                <td>${member.id}</td>
                <td>${member.memberName}</td>
                <td>${member.memberEmail}</td>
                <td>${member.createdDate}</td>
                <td>${member.updatedDate}</td>
                <td>
                    <span class="status ${className}">${classText}</span>
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

    const showSponsorMemberList = (sponsorMemberList) => {
        const sponsorMemberTBody = document.querySelector(".sponsorship-table tbody");
        let text = ``;
        const pagination = sponsorMemberList.pagination;
        const pageWrap = document.querySelector(".sponsorship-pagination")

        let className = "";
        let classText = "";
        sponsorMemberList.sponsorMemberDTOList.forEach((member) => {
            switch (member.sponsorMemberStatus){
                case("ACTIVE") :
                    className = "active";
                    classText = "활성"
                    break;
                case("DORMANCY"):
                    className = "dormant"
                    classText = "휴면"
                    break;
                case("SUSPENDED"):
                    className = "suspended"
                    classText = "정지"
                    break;
                case("DELETE"):
                    className = "withdrawn"
                    classText = "탈퇴"
                    break;
            }
            text += `
            <tr>
                <td>${member.id}</td>
                <td>${member.sponsorName}</td>
                <td>${member.sponsorEmail}</td>
                <td>${member.sponsorPhoneNumber}</td>
                <td><a href="https://m.map.naver.com/search2/search.nhn?query=${member.sponsorMainAddress}#map"
                                                target="_blank">${member.sponsorMainAddress}</a></td>
                <td>${member.createdDate}</td>
                <td>${member.updatedDate}</td>
                <td>
                    <span class="status ${className}">${classText}</span>
                </td>
            </tr>
            `
        })
        sponsorMemberTBody.innerHTML = text;
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

    const showAdminMemberList = (adminMemberList) => {
        const adminMemberTBody = document.querySelector(".admin-table tbody");
        let text = ``;
        const pagination = adminMemberList.pagination;
        const pageWrap = document.querySelector(".admin-pagination")

        let className = "";
        let classText = "";
        adminMemberList.adminDTOList.forEach((member) => {
            switch (member.adminMemberStatus){
                case("재직") :
                    className = "active";
                    // classText = "재직"
                    break;
                case("휴직"):
                    className = "dormant"
                    // classText = "휴직"
                    break;
                case("퇴직"):
                    className = "withdrawn"
                    // classText = "퇴직"
                    break;
            }
            text += `
            <tr>
                <td>${member.id}</td>
                <td>${member.adminName}</td>
                <td>${member.adminDepartment}</td>
                <td>${member.adminGrade}</td>
                <td>${member.updatedDate}</td>
                <td>
                    <span class="status ${className}">${member.adminMemberStatus}</span>
                </td>
                <td>
                    <button type="button" class="detail-btn admin-detail-btn">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        adminMemberTBody.innerHTML = text;
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

    return{showPersonalMemberList : showPersonalMemberList,
        showSponsorMemberList:showSponsorMemberList,
        showAdminMemberList:showAdminMemberList};

    })();