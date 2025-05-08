const receiverLayout = (() =>{
    const showReceiverList = (receiverList) => {
        const receiverTBody = document.querySelector(".receiver-table tbody");
        let text = ``;
        const pagination = receiverList.pagination;
        const pageWrap = document.querySelector(".receiver-pagination")
        console.log(receiverList)
        receiverList.receiverInfoDTOList.forEach((post) => {
            let className = "";
            let classText = "";
            let statusText = post.receiverDTO.receiverStatus;
            console.log(statusText.code);
            console.log(statusText.name);
            console.log(statusText)
            switch(statusText){
                case("WAITING"):
                    className = "suspended"
                    break;
                case("DONE"):
                    className = "active"
                    break;
            }
            text += `
            <tr>
                <td>${post.receiverDTO.id}</td>
                <td>${post.sponsorName}</td>
                <td>${post.receiverDTO.receiverTitle}</td> 
                <td>${post.receiverDTO.createdDate}</td>
                <td>${post.receiverDTO.updatedDate}</td>
                <td>${post.receiverLikeCount}</td>
                <td><span class="status ${className}">${statusText}</span></td>
                <td>
                <a href="/post/receiver/post/${post.receiverDTO.id}">
                    <button type="button" class="detail-btn modal-detail-btn">
                        상세보기
                    </button>
                    </a>
                </td>
            </tr>
            `
        })
        receiverTBody.innerHTML = text;

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


    return{showReceiverList : showReceiverList};

    })();