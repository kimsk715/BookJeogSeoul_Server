// 페이지 최초 로딩 시 데이터 출력
window.addEventListener("load", async () => {
    // 도서의 독후감 목록 받아오기
    const posts = await bookDetailPostService.getThisBookAllPosts();

    // 도서의 독후감 개수 받아오기
    const count = await bookDetailPostService.getThisBookPostsCount();

    // 받아온 데이터를 layout에서 출력
    await bookDetailPostLayout.showThisBookAllPosts(posts);
    await bookDetailPostLayout.showPostCount(count);
});

// 스크롤 끝까지 내리면 6개씩 독후감 갱신(무한스크롤)
let isLoading = false; // 중복 갱신을 방지하기 위한 플래그
let lastScrollTop = 0; // 위로 올리는 동작을 무시하기 위한 스크롤 위치 변수

window.addEventListener("scroll", async () => {
    const scrollTop = window.scrollY; // 현재 스크롤 위치
    const scrollPosition = scrollTop + window.innerHeight; // 현재 스크롤 위치 + 화면의 브라우저 높이
    const documentHeight = document.documentElement.scrollHeight; // 전체 문서의 높이

    // 아래로 스크롤 중 & 페이지 맨 아래 도달 & 로딩 중 아닐 때
    if (scrollTop > lastScrollTop && scrollPosition >= documentHeight - 10 && !isLoading) {
        isLoading = true; // 데이터 로딩 중 상태로 변경

        // 새 독후감 데이터 요청
        const newPosts = await bookDetailPostService.getThisBookAllPosts();

        // 응답받은 데이터가 존재하면 화면에 추가
        if (newPosts && newPosts.length > 0) {
            await bookDetailPostLayout.showThisBookAllPosts(newPosts);
        }

        isLoading = false; // 로딩 끝
    }

    lastScrollTop = scrollTop;
});
