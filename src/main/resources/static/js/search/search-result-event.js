window.addEventListener("load", async () => {

    // 도서 검색결과를 fetch받아서 layout으로 출력
    const { totalResults, books } = await searchResultService.getSearchedBooks();
    await searchResultLayout.showBookList(totalResults, books);

    // 독후감
    const posts = await searchResultService.getSearchedPosts();
    await searchResultLayout.showPostList(posts);

    // 기부단체
    const { totalCount, sponsors } = await searchResultService.getSearchedSponsors();
    await searchResultLayout.showSponsorList(totalCount, sponsors);
});

