// 새로고침할 때마다 페이지 최상단으로
window.onbeforeunload = function () {
    window.scrollTo(0, 0);
};

// 스크롤을 내리면 둥글고 작아지는 최상단 이미지
const visualSection = document.getElementById("visual");

window.addEventListener("scroll", () => {
    const scrollY = window.scrollY;
    const maxScroll = 300;
    const minScale = 0.56;
    const maxRadius = 57;

    // scale 계산
    let scale = 1 - (scrollY / maxScroll) * (1 - minScale);
    scale = Math.max(minScale, Math.min(scale, 1));

    // border-radius 계산 (0 ~ 57px)
    let radius = (scrollY / maxScroll) * maxRadius;
    radius = Math.max(0, Math.min(radius, maxRadius));

    // 적용
    visualSection.style.transform = `scale(${scale})`;
    visualSection.style.borderRadius = `${radius}px`;
});

// ai소개 스크롤 애니메이션 효과
// IntersectionObserver 사용해서 scroll 감지
// float 버튼도 무임승차..
const fadeEls = document.querySelectorAll(".fade-up");
const floatBtn = document.querySelector(".float-btn");

const observer = new IntersectionObserver(
    (entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                entry.target.classList.add("active");
                floatBtn.classList.add("show");
            }
        });
    },
    {
        threshold: 0.2, // 요소가 20% 보이면 동작
    }
);

fadeEls.forEach((el) => observer.observe(el));
