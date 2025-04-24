document.addEventListener("DOMContentLoaded", () => {
    // 검색 input, clear 버튼, 최근 검색어 영역 관련 요소들을 모두 가져옴
    const searchInput = document.querySelector("#input-search");        // 검색어 입력창(input)
    const clearBtn = document.querySelector(".clear-button");           // 검색창 우측의 x(삭제)버튼
    const recentList = document.querySelector("ul.recent-word");        // 최근 검색어가 있는 ul
    const recentNull = document.querySelector(".recent-null");          // 최근 검색어가 없을 때 뜨는 창
    const recentHeader = document.querySelector(".recent-header");      // '최근검색어' 제목, 전체삭제 버튼
    const deleteAllBtn = document.querySelector(".search-deleteall");   // 최근 검색어 전체 삭제 버튼
    const prevButton = document.querySelector(".prev-button");          // 이전 페이지 이동 버튼
    const searchButton = document.querySelector(".icon-searchbar");     // 검색하기 버튼(돋보기)

    // 뒤로가기 버튼 클릭 시 이전 페이지가 있으면 이동, 없으면 메인으로 이동
    if (prevButton) {
        prevButton.addEventListener("click", () => {
            if (document.referrer && document.referrer !== location.href) {
                // 이전 페이지가 있고, 자기 자신이 아니라면
                window.history.back();
            } else {
                // 이전 페이지가 없거나 같은 페이지면 메인 페이지로 이동
                location.href = "/main/main";
            }
        });
    }

    // 검색어를 입력하면 X 버튼이 나타나고, 없으면 사라짐
    if (searchInput && clearBtn) {
        searchInput.addEventListener("input", () => {
            clearBtn.style.display = searchInput.value.trim() ? "block" : "none";
        });

        // X 버튼 클릭 시 입력창 내용 비우고 버튼 숨김
        clearBtn.addEventListener("click", () => {
            searchInput.value = "";
            clearBtn.style.display = "none";
        });
    }

    // 검색 실행 함수 (Enter 키 또는 검색 버튼에 공용 사용)
    const performSearch = () => {
        const keyword = searchInput.value.trim(); // 입력된 검색어를 공백 제거 후 가져옴

        // 입력값이 없으면 알림창 출력 후 종료
        if (!keyword) {
            alert("검색어를 입력하세요");
            return;
        }

        // 너무 긴 검색어 제한 (30자 이상은 허용하지 않음)
        if (keyword.length > 30) {
            alert("검색어는 30자 이내로 입력해주세요.");
            return;
        }

        // 쿠키에 검색어 저장 (중복 제거 및 최신순 정렬 포함)
        saveKeywordToCookie(keyword);

        // 검색 결과 페이지로 이동 (통합검색으로)
        location.href = `/search/result?keyword=${encodeURIComponent(keyword)}`;
    };

    // 검색창에서 Enter 키 입력 시 검색 실행
    if (searchInput) {
        searchInput.addEventListener("keydown", (e) => {
            if (e.key === "Enter") {
                performSearch();
            }
        });
    }

    // 검색 버튼 클릭 시 검색 실행
    if (searchButton) {
        searchButton.addEventListener("click", () => {
            performSearch();
        });
    }

    // 페이지 로딩 시 쿠키에서 최근 검색어 목록 가져와 출력
    renderRecentKeywords();

    // 최근 검색어 목록을 출력하는 기능
    function renderRecentKeywords() {
        const raw = getCookie("searchKeywords"); // 쿠키에서 문자열 추출
        const keywords = raw ? raw.split(",") : []; // 콤마로 나눈 배열 [ 검색어1, 검색어2, ... ]

        recentList.innerHTML = ""; // 기존 목록 초기화

        // 검색어가 없으면 안내 메시지를 표시하고 영역 숨김
        if (keywords.length === 0) {
            recentHeader.style.display = "none";
            recentList.style.display = "none";
            recentNull.style.display = "block";
            return;
        }

        // 검색어가 있으면 최근 검색어 영역을 표시하고 목록 생성
        recentHeader.style.display = "flex";
        recentList.style.display = "block";
        recentNull.style.display = "none";

        // 각각의 검색어를 li 태그로 생성하여 추가
        keywords.forEach((word) => {
            const li = document.createElement("li");
            li.className = "search-recent-record";

            li.innerHTML = `
                <a href="/search/result?keyword=${encodeURIComponent(word)}">${word}</a>
                <button type="button" class="icon-recent-x" data-keyword="${word}"></button>
            `;
            recentList.appendChild(li);
        });

        // 각 X 버튼에 클릭 이벤트 등록
        document.querySelectorAll(".icon-recent-x").forEach((btn) => {
            btn.addEventListener("click", (e) => {
                const keywordToRemove = e.currentTarget.dataset.keyword; // 삭제할 키워드
                removeKeywordFromCookie(keywordToRemove); // 쿠키에서 제거
                renderRecentKeywords(); // 목록 다시 출력
            });
        });
    }

    // 전체 삭제 버튼 클릭 시 쿠키 삭제 후 출력
    if (deleteAllBtn) {
        deleteAllBtn.addEventListener("click", () => {
            // searchKeywords= : 쿠키 이름과 값
            // path=/ : 이 쿠키는 전체 사이트에서 유효
            // max-age: 유효기간(초 단위)
            document.cookie = "searchKeywords=; max-age=0; path=/";
            renderRecentKeywords();
        });
    }
});

// 검색어를 쿠키에 저장하는 함수
function saveKeywordToCookie(keyword) {
    const cookieName = "searchKeywords";
    const maxCount = 5; // 최대 5개 유지

    const raw = getCookie(cookieName); // 쿠키값 문자열
    let keywords = raw ? raw.split(",") : []; // ["검색어1", "검색어2"]

    // 중복 제거하고 맨 앞에 새 검색어 추가
    keywords = [keyword, ...keywords.filter(k => k !== keyword)]; // [keyword, ...others]: 새로운 검색어를 맨 앞에 추가

    // 최대 개수만큼 자르기
    keywords = keywords.slice(0, maxCount); // (0, 5): 최근 검색어 maxCount(5)개까지만 자르기

    // 다시 쿠키에 저장
    document.cookie = `${cookieName}=${keywords.join(",")}; path=/; max-age=${60 * 60 * 24 * 30}`; // 30일
}

// 특정 이름의 쿠키 값을 반환하는 함수
function getCookie(name) {
    // document.cookie: "searchKeywords=검색어; sessionId=xyz123" 같이 생긴 문자열
    // "(^| )": 쿠키 맨 앞이거나 공백 뒤에서 시작하는 이름
    // "name=": 우리가 찾는 쿠키 이름
    // "([^;]+)": 세미콜론 전까지의 값 (즉, 실제 쿠키 값)
    const match = document.cookie.match(new RegExp("(^| )" + name + "=([^;]+)"));

    // match에 성공하면 match[2]에 실제 값이 들어있다
    return match ? decodeURIComponent(match[2]) : null;
}

// 특정 검색어를 쿠키에서 제거하는 함수
function removeKeywordFromCookie(keyword) {
    const raw = getCookie("searchKeywords");
    if (!raw) return;   // 쿠키 값이 없다면 함수 종료

    // 있다면 split(",") 쉼표 기준으로 나눠서 배열로 만든 뒤, filter()를 통해 받은 키워드랑 일치하지 않는것만 남김
    const keywords = raw.split(",").filter(k => k !== keyword); // 해당 키워드를 제외한 나머지
    document.cookie = `searchKeywords=${keywords.join(",")}; path=/; max-age=${60 * 60 * 24 * 30}`; // 30일
}
