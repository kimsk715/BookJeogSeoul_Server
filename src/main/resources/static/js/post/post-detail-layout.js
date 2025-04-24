const postDetailLayout = (() => {

    // 책 상세정보 출력
    const showBookDetail = async (book) => {
        const formattedAuthor = (book.author || "").split("(")[0].trim();
        const bookCover = book.cover || "";

        // 텍스트 출력
        document.querySelector(".book-produce").innerText = `${formattedAuthor} · ${book.publisher || ""}`;
        document.querySelector(".author").innerText = formattedAuthor;
        // document.querySelector(".title-inner>h1").innerText = book.title;
        document.querySelector("#connect-title").innerText = book.title;

        // 이미지 출력
        const connectedImg = document.querySelector(".connected-book");
        if (connectedImg) connectedImg.src = bookCover;

        const mainCoverImg = document.querySelector(".book-picture.book img");
        if (mainCoverImg) mainCoverImg.src = bookCover;

        // 배경 이미지 스타일 변수 적용
        const bookContainer = document.querySelector(".book-container");
        if (bookContainer) {
            bookContainer.style.setProperty("--background-image", `url(${bookCover})`);
        }

        window.bookInfo = book;
    };


    // 독후감 내용 출력
    const showPostDetail = () => {
        const formattedDate = (post.createdDate || "").split(" ")[0].trim();
        const startDate = (post.bookPostStartDate || "").split(" ")[0].trim();
        const endDate = (post.bookPostEndDate || "").split(" ")[0].trim();
        console.log(formattedDate, startDate, endDate);

        document.querySelector("p#create-date").innerText = formattedDate;
        document.querySelector("span.start-end").innerText = `${startDate} ~ ${endDate}`;
    };

    // 첨부파일 이미지 출력
    const showFileImages = () => {
        const fileList = post.fileList || [];
        const container = document.querySelector(".images-container .list");

        container.innerHTML = "";

        fileList.forEach(file => {
            const { filePath, fileName, fileText } = file;

            // 🔧 경로 슬래시 정리 + upload 제거
            let relativePath = filePath
                .replaceAll("\\", "/")
                .replace(/^C:\/upload\//i, "");

            const imageUrl = `/post/post-image?path=${encodeURIComponent(relativePath)}&name=${encodeURIComponent(fileName)}`;

            const li = document.createElement("li");
            li.innerHTML = `
            <img src="${imageUrl}" alt="첨부 이미지" />
            <p class="text">${fileText || ""}</p>
            <span class="nickname">ⓒ ${post.memberName || ""}</span>
        `;
            container.appendChild(li);
        });
    };
    return { showBookDetail : showBookDetail, showPostDetail : showPostDetail, showFileImages : showFileImages }
})();