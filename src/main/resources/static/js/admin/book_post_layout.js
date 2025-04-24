const bookPostLayout = (() =>{
    const showBookPostList = (bookPostList) => {
        const bookPostTBody = document.querySelector(".book-post-table tbody");
        let text = ``;
        const pagination = bookPostList.pagination;
        const pageWrap = document.querySelector(".book-post-pagination")
        console.log(bookPostList)
        bookPostList.bookPostDTOList.forEach((post) => {
            let className = "";
            let classText = "";
            if(post.bookId != null){
                    className = "active";
                    classText = "추천도서"
            }
            else{
                className = "suspended";
                classText = "일반도서"
            }
            let statusName = "";
            if(post.bookPostIsPublic === "PUBLIC"){
                statusName = "active";
            }
            else{
                statusName = "suspended";
            }

            text += `
            <tr>
                <td>${post.id}</td>
                <td>${post.bookPostTitle}</td>
                <td>${post.bookPostText}</td>
                <td>${post.createdDate}</td>
                <td>${post.updatedDate}</td>
                <td>${post.bookPostLikeCount}</td>
                <td>${post.bookPostVoteCount}</td>
                <td><span class="status ${className}">${classText}</span></td>
                <td>
                    <span class="status ${statusName}">${post.bookPostIsPublic}</span>
                </td>
                <td>
                    <button type="button" class="detail-btn book-detail-btn">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        bookPostTBody.innerHTML = text;

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

    const topBookPostList = (topPostList) =>{
        const topPostTBody = document.querySelector(".top-post-table tbody");
        let text = ``;
        const pagination = topPostList.pagination;
        const pageWrap = document.querySelector(".top-post-pagination")

        topPostList.monthlyBookPostDTOList.forEach((post) => {
            let className = "";
            let classText = "";
            if(post.bookId != null){
                className = "active";
                classText = "추천도서"
            }
            else{
                className = "suspended";
                classText = "일반도서"
            }
            let statusName = "";
            if(post.bookPostIsPublic === "PUBLIC"){
                statusName = "active";
            }
            else{
                statusName = "suspended";
            }

            text += `
            <tr>
                <td>${post.bookPostId}</td>
                <td>${post.bookPostTitle}</td>
                <td>${post.bookPostText}</td>
                <td>${post.createdDate}</td>
                <td>${post.updatedDate}</td>
                <td>${post.bookPostLikeCount}</td>
                <td>${post.bookPostVoteCount}</td>
                
                <td>
                    <button type="button" class="detail-btn book-detail-btn">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        topPostTBody.innerHTML = text;
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
    return{showBookPostList : showBookPostList, topBookPostList:topBookPostList};

    })();