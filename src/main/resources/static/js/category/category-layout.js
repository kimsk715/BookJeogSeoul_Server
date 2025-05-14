const categoryLayout = (() => {

    const kdcNameMap = {
        "000": "총류",
        "100": "철학",
        "200": "종교",
        "300": "사회과학",
        "400": "자연과학",
        "500": "기술과학",
        "600": "예술",
        "700": "언어",
        "800": "문학",
        "900": "역사"
    };

    const books = async (bookList, kdc) => {
        const categoryName = document.querySelector(".category-title");
        const listView = document.querySelector(".list-view");

        // KDC 값으로 카테고리 이름 설정
        categoryName.textContent = kdcNameMap[kdc] || "카테고리 없음";


        let text = ``

        bookList.forEach((book) => {
            text += `
                <li class="book-item">
                                            <div class="search-book-list">
                                                <div class="bjs-book">
                                                    <a
                                                        href=""
                                                        class="book-image"
                                                    >
                                                        <div class="thumbnail">
                                                            <div
                                                                class="thumbnail-inner"
                                                            >
                                                                <div
                                                                    class="book-picture-wrap"
                                                                >
                                                                    <picture
                                                                        class="book-picture"
                                                                    >
                                                                        <img
                                                                            src="${book.image}"
                                                                            width="145"
                                                                    /></picture>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                    <a href="" class="book-data"
                                                        ><div
                                                            data-v-55d598cc=""
                                                            class="metadata"
                                                        >
                                                            <p
                                                                data-v-55d598cc=""
                                                                class="title"
                                                            >
                                                                ${book.bookName}
                                                            </p>
                                                            <p
                                                                data-v-55d598cc=""
                                                                class="author"
                                                            >
                                                                ${book.author}
                                                                ${book.publisher}
                                                            </p>
                                                        </div></a
                                                    >
                                                </div>
                                            </div>
                                            <!-- 찜 버튼 만들거면만드셈 -->
                                        </li>           
            `

            listView.innerHTML = text;
        })
    }
    return {books:books}
})();
