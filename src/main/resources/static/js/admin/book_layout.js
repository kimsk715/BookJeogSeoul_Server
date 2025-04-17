const bookLayout = (() =>{
    const showBookList = async (bookList) => {
        const bookTBody = document.querySelector(".book-table tbody");
        let text = ``;
        bookList.forEach((book) => {
            text += `
            <tr>
                <td><input type="checkbox" class="chosenBook"></td>
                <td>${book.isbn}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.publisher}</td>
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
    }


    return{showBookList : showBookList};

    })