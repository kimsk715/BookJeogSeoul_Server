document.addEventListener("DOMContentLoaded", () => {
    const keyword = new URLSearchParams(location.search).get("keyword");

    document.querySelectorAll(".dynamic-keyword-link").forEach(link => {
        const url = new URL(link.href, location.origin);

        // keyword가 없으면 유지 안 함
        if (keyword && !url.searchParams.get("keyword")) {
            url.searchParams.set("keyword", keyword);
            link.href = url.toString();
        }

        // 현재 페이지 경로 + 쿼리와 일치하면 aria-selected="true", 아니면 제거
        const currentPath = location.pathname + location.search;
        const targetPath = url.pathname + url.search;

        if (currentPath === targetPath) {
            link.setAttribute("aria-selected", "true");
        } else {
            link.removeAttribute("aria-selected");
        }
    });
});
