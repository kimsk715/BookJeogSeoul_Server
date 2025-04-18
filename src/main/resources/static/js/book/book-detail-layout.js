const bookDetailLayout = (() => {
    const showScrapCount = async () => {
        const count = await bookDetailService.getScrapCount();
        document.querySelector(".scrapCount").innerText = count + "+";
    }
    return {showScrapCount : showScrapCount};
})();