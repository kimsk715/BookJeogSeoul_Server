window.addEventListener("load", async () => {

    // 도서 검색결과를 fetch받아서 layout으로 출력
    const { totalResults, books } = await searchResultService.getSearchedBooks();
    await searchResultLayout.showBookList(totalResults, books);

    const posts = await searchResultService.getSearchedPosts();
    await searchResultLayout.showPostList(posts);
});

