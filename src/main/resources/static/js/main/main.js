// 드래그 할 대상 전체 영역 (.slider-container)
const container = document.querySelector(".swiper-container");

// 실제 스크롤이 일어나는 대상 내부 카드들이 감싸진 영역 (.swiper-wrapper)
const wrapper = document.querySelector(".swiper-wrapper");

// 드래그 상태를 저장할 변수들
let isDown = false; // 마우스를 누르고 있는지 여부
let startX; // 드래그 시작할 때 마우스의 X좌표
let scrollLeft; // 드래그 시작할 때 wrapper의 기존 스크롤 위치

// 사용자가 마우스를 누르기 시작했을 때
container.addEventListener("mousedown", (e) => {
    isDown = true; // 드래그 시작
    container.classList.add("active"); // 커서 스타일 등을 위한 클래스 (선택사항)
    startX = e.pageX; // 현재 마우스 X 위치 저장
    scrollLeft = wrapper.scrollLeft; // 현재 wrapper의 scroll 위치 저장
});

// 사용자가 마우스를 컨테이너 밖으로 빼면 드래그 종료
container.addEventListener("mouseleave", () => {
    isDown = false; // 드래그 종료
    container.classList.remove("active"); // 커서 스타일 초기화
});

// 마우스 버튼을 떼면 드래그 종료
container.addEventListener("mouseup", () => {
    isDown = false; // 드래그 종료
    container.classList.remove("active"); // 커서 스타일 초기화
});

// 마우스를 움직일 때 (드래그 중일 경우에만 작동)
container.addEventListener("mousemove", (e) => {
    if (!isDown) return; // 마우스를 누르고 있지 않다면 무시
    e.preventDefault(); // 기본 동작(텍스트 선택 등) 방지
    const x = e.pageX; // 현재 마우스 X 좌표
    const walk = (x - startX) * 1.5; // 얼마나 움직였는지 계산 (1.5는 스피드 조절)
    wrapper.scrollLeft = scrollLeft - walk; // 원래 스크롤 위치에서 이동 거리만큼 더함
});

//  ************슬라이드 배너 영역 ******************

const slideWrapper = document.querySelector(".hero-swiper-wrapper"); // 슬라이드 전체 감싸는 ul
const slides = document.querySelectorAll(".hero-swiper-slide"); // 실제 슬라이드 li들
const totalSlides = slides.length; // 슬라이드 개수
const heroBanner = document.querySelector(".hero-swiper-banner"); // 전체 배너

const currentPage = document.querySelector(".current-page"); // 현재 페이지 표시
const totalPage = document.querySelector(".total-page"); // 총 페이지 수 표시

totalPage.innerText = totalSlides; // 복제 전 기준

const prevButton = document.querySelector(".prev-button");
const nextButton = document.querySelector(".next-button");

let isCooldown = false; // 쿨타임 여부
const cooldownTime = 2000; // 쿨타임 시간 (2초)

const slideWidth = 1392; // 슬라이드 너비

// 복제 슬라이드 추가
const firstClone = slides[0].cloneNode(true);
const lastClone = slides[totalSlides - 1].cloneNode(true);
firstClone.classList.add("clone");
lastClone.classList.add("clone");

slideWrapper.appendChild(firstClone);
slideWrapper.insertBefore(lastClone, slides[0]);

const allSlides = document.querySelectorAll(".hero-swiper-slide");
const newTotal = allSlides.length;

let slideIndex = 1; // 진짜 첫 슬라이드부터 시작
let autoSlide = null; // 자동 슬라이드 타이머

// 슬라이드 이동
const updateSlidePosition = () => {
    const offset = -slideIndex * slideWidth;
    slideWrapper.style.transform = `translateX(${offset}px)`;
};
// 자동 슬라이드
const moveSlidePosition = () => {
    const offset = -slideIndex * slideWidth;
    slideWrapper.style.transform = `translateX(${offset}px)`;
    slideWrapper.style.transition = "transform 0.5s ease";
};

// 자동 슬라이드
const startAutoSlide = () => {
    autoSlide = setInterval(() => {
        nextSlide();
        updatePageIndicator(); // 페이지 인디케이터 업데이트
    }, 5000);
};

// 자동 슬라이드 멈춤
const stopAutoSlide = () => {
    clearInterval(autoSlide);
};

// 다음 슬라이드
const nextSlide = () => {
    slideIndex++;
    moveSlidePosition();
    updatePageIndicator(); // 페이지 인디케이터 업데이트

    if (slideIndex === newTotal - 1) {
        setTimeout(() => {
            slideWrapper.style.transition = "none";
            slideIndex = 1;
            updateSlidePosition();
        }, 500);
    }
};

// 이전 슬라이드
const prevSlide = () => {
    slideIndex--;
    moveSlidePosition();
    updatePageIndicator(); // 페이지 인디케이터 업데이트

    if (slideIndex === 0) {
        setTimeout(() => {
            slideWrapper.style.transition = "none";
            slideIndex = newTotal - 2;
            updateSlidePosition();
        }, 500);
    }
};

// 쿨타임 적용 함수
const withCooldown = (action) => {
    if (isCooldown) return; // 쿨타임 중이면 무시
    isCooldown = true;
    stopAutoSlide(); // 버튼 누르면 자동 슬라이드 멈춤
    action(); // 실제 동작 실행
    setTimeout(() => {
        isCooldown = false;
        startAutoSlide(); // 쿨타임 끝나면 자동 슬라이드 재시작
    }, cooldownTime);
};

// 현재 페이지 업데이트 함수
const updatePageIndicator = () => {
    // 복제 슬라이드 때문에 slideIndex 1 ~ totalSlides 까지가 실제 슬라이드임
    let displayIndex = slideIndex;
    if (slideIndex === 0) displayIndex = totalSlides;
    else if (slideIndex === newTotal - 1) displayIndex = 1;

    currentPage.textContent = displayIndex;
};

// 버튼 이벤트
nextButton.addEventListener("click", () => withCooldown(nextSlide));
prevButton.addEventListener("click", () => withCooldown(prevSlide));

// 초기 위치 및 자동 슬라이드 시작
updateSlidePosition();
startAutoSlide();
