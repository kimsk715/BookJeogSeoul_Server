const searchResultSponsorService = (() => {
    // 무한스크롤용 단체 검색결과
    const getSponsorList = async (keyword, offset = 0, sortType = "new") => {
        const response = await fetch(`/search/api/result/sponsors?keyword=${keyword}&offset=${offset}&sortType=${sortType}`)
        const data = await response.json();

        const totalCount = data.totalCount;
        const sponsors = data.sponsors;

        return { totalCount, sponsors };
    }
    return {getSponsorList : getSponsorList}
})();