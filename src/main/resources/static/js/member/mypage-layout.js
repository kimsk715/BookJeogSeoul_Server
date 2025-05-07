const mypageLayout = (() => {
    const initProgressBars = () => {
        const bars = document.querySelectorAll(".progress-state.mine");
        bars.forEach(bar => {
            const monthly = parseInt(bar.dataset.monthlyPosts, 10) || 0;
            const average = parseInt(bar.dataset.averagePosts, 10) || 1;
            const percent = Math.min(100, Math.round((monthly * 100) / average));
            bar.style.width = `${percent}%`;
        });
    }
    return { initProgressBars : initProgressBars};
})();