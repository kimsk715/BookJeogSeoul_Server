// 버튼을 클릭하면 도서 찜하기
const scrapButton = document.querySelector(".mds-icon-scrap");

scrapButton.addEventListener("click", (e) => {
    const isScrapped = e.target.classList.toggle("scrapped");

    if (isScrapped) {
        alert("내 서재에 도서를 넣었습니다.");
        e.target.style.backgroundImage =
            "url('https://d3udu241ivsax2.cloudfront.net/v3/images/bookDetail/back-heart-on.d6a405d1a7177f4eaeb7ddd3793866c4.png')";
    } else {
        alert("내 서재에 도서를 뺐습니다.");
        e.target.style.backgroundImage =
            "url('https://d3udu241ivsax2.cloudfront.net/v3/images/bookDetail/detail-heart-off.fc064a68f51248a73513a5ef4c5035f5.png')";
    }
});

// 버튼을 클릭하면 도서 찜하기(모바일 버튼)
const mobileScrapButton = document.querySelector(".scrap-button");

mobileScrapButton.addEventListener("click", (e) => {
    // 버튼 안의 찜 이미지
    const img = e.target.querySelector("img");

    const isScrapped = e.target.classList.toggle("scrapped");

    if (isScrapped) {
        alert("내 서재에 도서를 넣었습니다.");
        img.src =
            "https://d3udu241ivsax2.cloudfront.net/v3/images/bookDetail/back-heart-on.d6a405d1a7177f4eaeb7ddd3793866c4.png";
    } else {
        alert("내 서재에 도서를 뺐습니다.");
        img.src =
            "https://d3udu241ivsax2.cloudfront.net/v3/images/bookDetail/back-heart-off.b5cd493b0e38b74654f26e2ebf2a3aaf.png";
    }
});

// 현재 nav에 맞춰 동적으로 이동하는 밑줄 span
const tabItems = document.querySelectorAll(".mds-tab-item");
const tabBar = document.querySelector(".mds-tab-bar");

// 밑줄 위치 및 active 클래스 설정 함수
function updateTabBar(targetItem) {
    const textSpan = targetItem.querySelector("span"); // 현재 탭 내부의 span 요소
    const textRect = textSpan.getBoundingClientRect(); // 현재 탭 내부의 span 요소의 위치와 크기를 가져옴
    const parentRect = targetItem.parentElement.getBoundingClientRect(); // 탭 전체(wrapper)의 위치와 크기를 가져옴

    // span 글자의 너비만큼 밑줄의 길이를 설정
    const width = textRect.width;

    // .mds-tab-wrapper를 기준으로 span의 왼쪽 위치를 계산
    // → 밑줄이 정확히 글자 아래로 오게 하기 위해 필요
    const offsetLeft = textRect.left - parentRect.left;

    // 밑줄의 width와 위치(translateX)를 적용
    tabBar.style.width = `${width}px`;
    tabBar.style.transform = `translate3d(${offsetLeft}px, 0, 0)`;

    // 모든 탭에서 active 제거 후 현재 span에 추가
    tabItems.forEach((item) => {
        item.querySelector("span").classList.remove("active");
    });
    textSpan.classList.add("active");
}

// 클릭 시 처리
tabItems.forEach((item) => {
    item.addEventListener("click", () => {
        updateTabBar(item);
    });
});

// 스크롤 시 섹션에 맞는 탭으로 업데이트
window.addEventListener("scroll", () => {
    tabItems.forEach((item) => {
        const targetId = item.dataset.target;
        const section = document.getElementById(targetId);
        if (!section) return;

        const rect = section.getBoundingClientRect();
        if (
            rect.top < window.innerHeight / 2 &&
            rect.bottom > window.innerHeight / 2
        ) {
            updateTabBar(item);
        }
    });
});

// 로드 시 초기화
window.addEventListener("load", () => {
    updateTabBar(tabItems[0]);
});

// 크기 조정 시에도 위치 재조정
window.addEventListener("resize", () => {
    const activeItem =
        [...tabItems].find((item) =>
            item.querySelector("span").classList.contains("active")
        ) || tabItems[0];
    updateTabBar(activeItem);
});

// 책 소개의 더보기 버튼을 누르면 더보기 버튼이 텍스트가 바뀌고 hidden 클래스가 사라짐
const introText = document.querySelector(".book-info-more-cont-inner");
const introButton = document.querySelector(
    '[data-content-type="intro-more-button"]'
);

introButton.addEventListener("click", (e) => {
    const isHidden = introText.classList.toggle("hidden");
    introButton.innerText = isHidden ? "더보기" : "접어보기";
});
