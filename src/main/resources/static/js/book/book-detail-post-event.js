window.addEventListener("load", async () => {

    // 도서의 독후감 목록 받아오기
    const posts = await bookDetailPostService.getThisBookAllPosts();

    // 도서의 독후감 개수 받아오기
    const count = await  bookDetailPostService.getThisBookPostsCount();

    // 받아온 데이터를 layout에서 출력
    await bookDetailPostLayout.showThisBookAllPosts(posts);
    await bookDetailPostLayout.showPostCount(count);
});
