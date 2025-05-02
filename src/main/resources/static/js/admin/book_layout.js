const bookLayout = (() =>{
    const showBookList = (bookList) => {
        const bookTBody = document.querySelector(".book-table tbody");
        let text = ``;
        const pagination = bookList.pagination;
        const pageWrap = document.querySelector(".book-pagination")

        bookList.bookInfoList.forEach((book) => {
            text += `
            <tr>
                <td><input type="checkbox" class="chosenBook" value="${book.ISBN}"></td>
                <td>${book.ISBN}</td>
                <td>${book.TITLE}</td>
                <td>${book.AUTHOR}</td>
                <td>${book.PUBLER}</td>
                <td><span class="status active">선정</span></td>
                <td>
                    <span class="status active">대출중</span>
                </td>
                <td>
                    <button type="button" class="detail-btn book-detail-btn">
                        상세보기
                    </button>
                </td>
            </tr>
            `
        })
        bookTBody.innerHTML = text;
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

    const showTempSelectedList = (tempList) => {
        const listBody = document.querySelector(".monthly-book-modal .monthly-book-table tbody")
        let text= ``;
        tempList.forEach((book) => {
            text += `
                            <tr>
                                <td>${book.id}</td>
                                <td>${book.bookIsbn}</td>
                                <td>${book.title}</td>
                                <td>${book.classNo}</td>
                            </tr>
            `
        })
        listBody.innerHTML = text;
        text = ``;
    }

    const showOpenAI = (topics) => {


        topics.forEach((topic) => {
            openAIResult.innerHTML += `
            <div class="result${topic.index}">
            <div class="discussion-detail">
                    <h4>토론 제목</h4>
                    <textarea class="detail-content" id="apiResult-title" value="${topic.topic}">${topic.topic}</textarea>
                </div>
                <div class="discussion-detail">
                    <h4>토론 내용</h4>
                    <textarea class="detail-content" id="apiResult-text" value="${topic.description}">${topic.description}</textarea>
                </div>
                <div class="discussion-detail">
                    <h4>ISBN</h4>
                    <textarea class="detail-content" id="apiResult-book-isbn" value="${topic.isbn}">${topic.isbn}</textarea>
                </div>
                <div class="discussion-detail">
                    <h4>도서명</h4>
                    <textarea class="detail-content" id="apiResult-book-title" value="${topic.bookTitle}">${topic.bookTitle}</textarea>
                </div>
                </div>
            `

        })
        const result1 = openAIModal.querySelector(".result1")
        result1.classList.add("selected")
        const result2 = openAIModal.querySelector(".result2")
        result2.style.display = "none";
        const result3 = openAIModal.querySelector(".result3")
        result3.style.display = "none";
    }
    return{showBookList : showBookList, showTempSelectedList:showTempSelectedList, showOpenAI:showOpenAI};

    })();