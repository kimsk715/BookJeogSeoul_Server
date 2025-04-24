document.addEventListener("DOMContentLoaded", () => {
    const keyword = new URLSearchParams(location.search).get("keyword");

    // 검색어 input에 value 설정
    const searchInput = document.querySelector("#input-search");
    if (searchInput && keyword) {
        searchInput.value = keyword;

        // 입력값이 있으므로 삭제 버튼도 보이게
        const clearBtn = document.querySelector(".clear-button");
        if (clearBtn) {
            clearBtn.style.display = "block";
        }
    }

    // 검색 결과 페이지라면 keyword를 쿠키에 저장
    if (keyword && !location.pathname.includes("/search/window")) {
        saveKeywordToCookie(keyword);
    }

    // keyword가 있을 경우 모든 링크에 query string으로 반영하고 aria-selected 설정
    updateDynamicKeywordLinks(keyword);

    // 검색창의 삭제 버튼, 엔터 검색 기능 등 초기화
    setupSearchInput();
});

// 현재 검색어가 있을 경우, 각 카테고리 링크에 ?keyword=... 추가
// 그리고 현재 URL과 일치하는 링크에 aria-selected="true" 부여
function updateDynamicKeywordLinks(keyword) {
    document.querySelectorAll(".dynamic-keyword-link").forEach(link => {
        const url = new URL(link.href, location.origin);

        // keyword가 있을 경우만 링크에 반영
        if (keyword && !url.searchParams.get("keyword")) {
            url.searchParams.set("keyword", keyword);
            link.href = url.toString();
        }

        const currentPath = location.pathname + location.search;
        const targetPath = url.pathname + url.search;

        // 현재 URL과 동일한 링크에 aria-selected 부여
        if (currentPath === targetPath) {
            link.setAttribute("aria-selected", "true");
        } else {
            link.removeAttribute("aria-selected");
        }
    });
}

// 검색창 초기화 및 이벤트 등록
// 삭제 버튼 클릭 시 검색어 초기화 후 검색창으로 이동
// input 값이 있을 때만 삭제 버튼 표시
// 엔터 입력 시 현재 카테고리 유지한 채 검색 수행
function setupSearchInput() {
    const searchInput = document.querySelector("#input-search");
    const clearBtn = document.querySelector(".clear-button");

    if (!searchInput || !clearBtn) return;

    // 삭제 버튼 클릭 시 검색어 초기화 및 검색창 페이지로 이동
    clearBtn.addEventListener("click", () => {
        searchInput.value = "";
        location.replace("/search/window");
    });

    // 입력값 여부에 따라 삭제 버튼 표시 여부 제어
    searchInput.addEventListener("input", () => {
        clearBtn.style.display = searchInput.value.trim() ? "block" : "none";
    });

    // 엔터 키 입력 시 같은 카테고리 경로 + 새로운 검색어로 이동
    searchInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            const keyword = searchInput.value.trim();
            if (!keyword) return;

            const currentPath = location.pathname;
            const newUrl = `${currentPath}?keyword=${encodeURIComponent(keyword)}`;
            window.location.href = newUrl;
        }
    });
}

// 검색어를 쿠키에 저장 (최대 5개, 중복 제거)
function saveKeywordToCookie(keyword) {
    const cookieName = "searchKeywords";
    const maxCount = 5;

    const raw = getCookie(cookieName); // 기존 쿠키 문자열
    let keywords = raw ? raw.split(",").map(decodeURIComponent) : [];

    // 중복 제거 후 앞에 추가
    keywords = [keyword, ...keywords.filter(k => k !== keyword)];

    // 최대 개수 유지
    keywords = keywords.slice(0, maxCount);

    // 저장: encodeURIComponent 처리 후 ,로 join
    const cookieValue = keywords.map(k => encodeURIComponent(k)).join(",");
    document.cookie = `${cookieName}=${cookieValue}; path=/; max-age=${60 * 60 * 24 * 30}`;
}

// 쿠키에서 값 가져오기
function getCookie(name) {
    const match = document.cookie.match(new RegExp("(^| )" + name + "=([^;]+)"));
    return match ? decodeURIComponent(match[2]) : null;
}
