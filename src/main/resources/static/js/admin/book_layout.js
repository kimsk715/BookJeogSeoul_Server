const bookLayout = (() =>{
    const showBookList = (bookList) => {
        const bookTBody = document.querySelector(".book-table tbody");
        let text = ``;
        console.log("실행됨")
        bookList.forEach((book) => {
            text += `
            <tr>
                <td><input type="checkbox" class="chosenBook" value="${book.ISBN}"></td>
                <td>${book.ISBN}</td>
                <td>${book.TITLE}</td>
                <td>${book.AUTHOR}</td>
                <td>${book.PUBLISHER}</td>
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
        // console.log("post text")
        console.log(text)
        bookTBody.innerHTML = text;
        text=``;
    }


    return{showBookList : showBookList};

    })();