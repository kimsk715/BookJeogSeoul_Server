// 좋아요 누르면 아이콘 변경
const likeButton = document.querySelector(".editor-icon-heart");
const miniLikeBtn = document.querySelector(".like-inner .like-btn");

function toggleLike() {
    likeButton.classList.toggle("filled");
    miniLikeBtn.classList.toggle("filled");
}

likeButton.addEventListener("click", toggleLike);
miniLikeBtn.addEventListener("click", toggleLike);

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

// 공유 누르면 링크 복사

// 카카오톡 공유
// 나중에 공유할 내용 제목이나 책 이름 가지고 와야겠다
document.addEventListener("DOMContentLoaded", () => {
    if (window.Kakao && !Kakao.isInitialized()) {
        Kakao.init("16c74ba60369da3eb1ee0b92f425d32e");
        console.log("Kakao 초기화 완료");
    } else if (!window.Kakao) {
        console.error("❌ Kakao SDK 로딩 실패");
        return;
    }

    document
        .getElementById("kakao-share-btn")
        .addEventListener("click", function (e) {
            e.preventDefault(); // a태그 기본동작 막기
            Kakao.Share.sendDefault({
                objectType: "feed",
                content: {
                    title: document.querySelector(
                        ".diary-title-container>.text"
                    ).innerText,
                    description:
                        document.querySelector(".title-inner>h1").innerText,
                    // 샘플, 나중에 책 이미지로 바꿔야함
                    imageUrl:
                        "https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png",
                    link: {
                        mobileWebUrl: window.location.href,
                        webUrl: window.location.href,
                    },
                },
            });
        });
});

// 페이스북 공유
document
    .getElementById("share-facebook-btn")
    .addEventListener("click", function (e) {
        e.preventDefault();

        const currentUrl = encodeURIComponent(window.location.href);
        const facebookShareUrl = `https://www.facebook.com/sharer/sharer.php?u=${currentUrl}`;

        window.open(facebookShareUrl, "_blank", "width=600,height=400");
    });

// 트위터 공유
document
    .getElementById("twitter-share-btn")
    .addEventListener("click", function (e) {
        e.preventDefault();

        const pageUrl = encodeURIComponent(window.location.href);
        const text = encodeURIComponent("독서는 북적서울과 함께 😄");

        const twitterUrl = `https://twitter.com/intent/tweet?url=${pageUrl}&text=${text}`;

        window.open(twitterUrl, "_blank", "width=600,height=400");
    });

// 링크 복사
const copyBtn = document.querySelector(".btn-url-copy");

copyBtn.addEventListener("click", function () {
    const currentUrl = window.location.href;

    navigator.clipboard
        .writeText(currentUrl)
        .then(() => {
            showToast("링크가 복사되었습니다!");
        })
        .catch(() => {
            showToast("복사에 실패했습니다.");
        });
});

// 링크 모달 열기, 닫기 버튼
const openShareBtn = document.querySelector(".share-item>button");
const shareModal = document.querySelector(".popup-sns-share");
const closeShareBtn = document.querySelector(".share-close-button");

// 초기 모달 안 보임
shareModal.style.display = "none";

openShareBtn.addEventListener("click", (e) => {
    shareModal.style.display = "flex";
});

closeShareBtn.addEventListener("click", (e) => {
    shareModal.style.display = "none";
});

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
const reportButton = document.querySelector(".more-item>button");
const reportMenu = document.querySelector(".more-ul");

reportButton.addEventListener("click", (e) => {
    e.stopPropagation();
    reportMenu.style.display = "block";
});

// 신고하기 메뉴 누르면 모달 뜸
const reportModal = document.querySelector(".police-popup");

reportMenu.addEventListener("click", (e) => {
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

// 팔로우 버튼 이벤트
const followButton = document.querySelector(".button-follow");

followButton.addEventListener("click", (e) => {
    followButton.classList.toggle("following");

    if (followButton.classList.contains("following")) {
        showToast("팔로우 되었습니다.");
        followButton.innerText = "팔로잉";
    } else {
        showToast("팔로잉 취소 되었습니다.");
        followButton.innerText = "팔로우";
    }
});
