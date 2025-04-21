window.addEventListener("load", async () => {

    // 도서의 독후감 목록 받아오기
    const posts = await bookDetailPostService.getThisBookAllPosts();

    // 도서의 독후감 개수 받아오기
    const count = await  bookDetailPostService.getThisBookPostsCount();

    // 받아온 데이터를 layout에서 출력
    await bookDetailPostLayout.showThisBookAllPosts(posts);
    await bookDetailPostLayout.showPostCount(count);
});

// 스크롤 끝까지 내리면 6개씩 독후감 갱신(무한스크롤)
window.addEventListener("scroll", () => {

    // 현재 스크롤 위치 + 브라우저 높이
    const scrollPosition = window.scrollY + window.innerHeight;
    // 전체 문서 높이
    const documentHeight = document.documentElement.scrollHeight;

    if (scrollPosition >= documentHeight - 10) { // 오차 범위 10px 설정
        console.log("✅ 페이지 끝까지 스크롤 됨!");
    }
});
