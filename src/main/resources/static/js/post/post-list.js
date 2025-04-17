// 상단 버튼을 누를 때마다 추천과 팔로잉 독후감 목록이 뜸
const recommendListButton = document.querySelector(
    ".menu-list>ul>li:nth-child(1)"
);
const followingListButton = document.querySelector(
    ".menu-list>ul>li:nth-child(2)"
);

const recommendList = document.querySelector(".card-list.recommend");
const followingList = document.querySelector(".card-list.following");

// 팔로잉 메뉴를 눌렀을 때
followingListButton.addEventListener("click", (e) => {
    e.preventDefault();

    // 클릭된 요소의 부모 요소인 <li>나 <a>를 찾아서 클래스 수정
    const clickedButton = e.target.closest("li");

    // 추천 목록 및 팔로잉 목록의 활성화 상태 변경
    recommendList.classList.remove("active");
    followingList.classList.add("active");

    // 버튼의 활성화 상태 변경
    recommendListButton.classList.remove("active");
    clickedButton.classList.add("active"); // clickedButton은 <li>가 됨
});

// 추천 메뉴를 눌렀을 때
recommendListButton.addEventListener("click", (e) => {
    e.preventDefault();

    // 클릭된 요소의 부모 요소인 <li>를 찾아서 클래스 수정
    const clickedButton = e.target.closest("li");

    // 팔로잉 목록을 비활성화하고 추천 목록을 활성화
    followingList.classList.remove("active");
    recommendList.classList.add("active");

    // 버튼의 활성화 상태 변경
    followingListButton.classList.remove("active");
    clickedButton.classList.add("active"); // clickedButton은 <li>가 됨
});

// 팔로우 누르면 버튼 스타일이 바뀜
const followButtons = document.querySelectorAll(".user>button");

followButtons.forEach((followButton) => {
    followButton.addEventListener("click", (e) => {
        const notFollowing = e.target.classList.toggle("following");
        if (notFollowing) {
            alert("팔로우했습니다.");
            e.target.innerText = "팔로잉";
        } else {
            alert("팔로우를 해제했습니다.");
            e.target.innerText = "팔로우";
        }
    });
});

// 토스트 기능(잠깐 나타났다가 사라지는 창)
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;

    document.querySelector("#wrap").appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 2500); // 2.5초 후 자동 제거
}

// 선택한 신고 항목에 .checked 추가
document.querySelectorAll(".mds-radio").forEach((label) => {
    label.addEventListener("click", (e) => {
        const input = label.querySelector('input[type="radio"]');

        if (input) {
            // 강제로 선택
            input.checked = true;

            // change 이벤트 트리거 (선택되었음을 알림)
            input.dispatchEvent(new Event("change"));

            // .checked 클래스 초기화
            document.querySelectorAll(".mds-radio").forEach((lbl) => {
                lbl.classList.remove("checked");
            });

            // 현재 선택된 라벨에 .checked 추가
            label.classList.add("checked");
        }
    });
});

// 신고하기 버튼 메뉴
const reportButton = document.querySelector(".mds-icon-more");
const reportMenu = document.querySelector(".more-area");

reportButton.addEventListener("click", (e) => {
    e.stopPropagation();
    reportMenu.style.display = "block";
});

// 신고하기 메뉴 누르면 모달 뜸
const reportModal = document.querySelector(".police-popup");

reportMenu.querySelector(".report").addEventListener("click", (e) => {
    reportModal.style.display = "flex";
});

// 창 바깥 누르면 신고하기 메뉴 꺼짐
window.addEventListener("click", (e) => {
    if (!reportMenu.contains(e.target)) {
        reportMenu.style.display = "none";
    }
});

// 신고하기 취소, 등록버튼
const cancelReport = document.querySelector(".policecancl");
const confirmReport = document.querySelector(".review-police");

cancelReport.addEventListener("click", (e) => {
    reportModal.style.display = "none";
    reportMenu.style.display = "none";
});

confirmReport.addEventListener("click", (e) => {
    showToast("신고가 완료되었습니다.");
    reportModal.style.display = "none";
    reportMenu.style.display = "none";
});
