/**
 * 관리자 페이지의 메인 컨텐츠 영역 전환 담당 페이지
 *
 * 주요 기능:
 * 1. 페이지 간 전환 (홈 <-> 각 관리 페이지)
 * 2. 초기 상태 설정 및 관리
 * 3. 화면 표시/숨김 처리
 */

// DOM이 완전히 로드된 후 실행
document.addEventListener("DOMContentLoaded", function () {
  /**
   * --------------------
   * 1. DOM 요소 선택 및 초기화
   * --------------------
   */

  // 홈 페이지 컨텐츠 요소
  // project-container는 홈 페이지의 가장 바깥 컨테이너
  const homeContainer = document.querySelector(".project-container");

  /**
   * 관리 페이지 요소들을 담는 객체
   * key: 각 페이지를 식별하는 고유 키
   * value: 해당 페이지의 DOM 요소
   *
   * querySelector로 각 관리 페이지의 최상위 컨테이너를 선택
   * 예: .announce-management는 공고관리 페이지의 루트 컨테이너
   */
  const pages = {
    member : document.querySelectorAll(".member-management"),
    bookPost : document.querySelectorAll(".book-post-management"),
    sponsorShip : document.querySelectorAll(".sponsorship-management"),
    pesronalSponsor : document.querySelectorAll(".personal-sponsor-management"),
    receiver : document.querySelectorAll(".receiver-management"),
    notice : document.querySelectorAll(".notice-management"),
    normalInquiry : document.querySelectorAll(".normal-inquiry-management"),
    sonpsorInquiry : document.querySelectorAll(".sponsor-inquiry-management"),
    commentReport : document.querySelectorAll(".comment-report-management"),
    bookPostReport : document.querySelectorAll(".book-post-report-management"),
    topPost : document.querySelectorAll(".top-post-management"),
    discussion : document.querySelectorAll(".discussion-management"),
    book : document.querySelectorAll(".book-management"),
    admin : document.querySelectorAll(".admin-management")
  };

  // 로고 버튼 요소 - 홈으로 돌아가는 기능을 담당
  const logoBtn = document.querySelector(".logo-btn");

  /**
   * 페이지 이동 링크들을 담는 객체
   * key: 페이지 식별자 (pages 객체의 키와 매칭)
   * value: 해당 페이지로 이동하는 모든 링크 요소들의 NodeList
   *
   * querySelectorAll로 각 페이지에 대한 모든 링크를 선택
   * - 1. 홈 화면의 바로가기 버튼
   * - 2. 사이드바의 메뉴 항목
   * 둘 다 포함하여 선택됨
   */
  const pageLinks = {
    member : document.querySelectorAll(".member-link"),
    bookPost : document.querySelectorAll(".book-post-link"),
    sponsorShip : document.querySelectorAll(".sponsorship-link"),
    pesronalSponsor : document.querySelectorAll(".personal-sponsor-link"),
    receiver : document.querySelectorAll(".receiver-link"),
    notice : document.querySelectorAll(".notice-link"),
    normalInquiry : document.querySelectorAll(".normal-inquiry-link"),
    sonpsorInquiry : document.querySelectorAll(".sponsor-inquiry-link"),
    commentReport : document.querySelectorAll(".comment-report-link"),
    bookPostReport : document.querySelectorAll(".book-post-report-link"),
    topPost : document.querySelectorAll(".top-post-link"),
    discussion : document.querySelectorAll(".discussion-link"),
    book : document.querySelectorAll(".book-link"),
    admin : document.querySelectorAll(".admin-manage-link")
  };

  /**
   * --------------------
   * 2. 기능 만들기
   * --------------------
   */

  /**
   * 페이지 초기 상태 설정 함수
   * - 모든 관리 페이지를 숨기고
   * - 홈 페이지만 표시하는 기능
   *
   * Object.values()를 사용하여 pages 객체의 모든 값(DOM 요소)을 순회
   */
  function initializePages() {
    // 모든 관리 페이지를 숨김 처리
    Object.values(pages).forEach((nodeList) => {
      nodeList.forEach((page) => {
        if (page) page.style.display = "none";
      });
    });
    // 홈 페이지를 표시
    homeContainer.style.display = "block";
  }

  /**
   * 특정 페이지 표시 함수
   *  pageKey - 표시할 페이지의 식별자 (pages 객체의 키)
   * 1. 현재 표시된 모든 페이지를 숨김
   * 2. 지정된 페이지만 표시
   */
  function showPage(pageKey) {
    homeContainer.style.display = "none"; // 홈 페이지 숨김
  
    Object.values(pages).forEach((nodeList) => {
      nodeList.forEach((page) => {
        if (page) page.style.display = "none";
      });
    });
  
    if (pages[pageKey]) {
      pages[pageKey].forEach((page) => {
        page.style.display = "block";
      });
    }
  }
  /**
   * 홈으로 돌아가는 함수
   * 로고 클릭 시 실행되며, 초기 상태로 되돌림
   */
  function returnToHome(e) {
    e.preventDefault(); // 기본 이벤트 동작 방지
    initializePages(); // 초기 상태로 페이지 설정
  }

  /**
   * 모든 이벤트 리스너 초기화 및 등록 함수
   *
   * 1. 로고 버튼 이벤트
   * 2. 각 페이지 링크들의 이벤트
   * pageValue - pageLinks 객체의 각 값(NodeList)을 의미
   *  => 특정 페이지로 이동하는 링크 요소들의 집합
   */
  function initializeEventListeners() {
    // 1. 로고 버튼에 클릭 이벤트 연결
    logoBtn.addEventListener("click", returnToHome);

    // 2. 각 페이지 링크들에 이벤트 연결
    Object.entries(pageLinks).forEach(([pageKey, pageValue]) => {
      // pageValue는 querySelectorAll()에 의해 반환된 NodeList이므로 forEach로 각 요소에 접근
      pageValue.forEach((pageValue) => {
        pageValue.addEventListener("click", (e) => {
          e.preventDefault(); // 기본 링크 동작 방지
          showPage(pageKey); // 해당 페이지 표시
        });
      });
    });
  }

  /**
   * --------------------
   * 3. 초기화 실행
   * --------------------
   */
  initializePages(); // 초기 페이지 상태 설정
  initializeEventListeners(); // 모든 이벤트 리스너 등록
});
