// ✅ 페이지 로드시 책 정보, 독후감 정보, 파일 목록 렌더링 및 좋아요/팔로우 상태 초기화
document.addEventListener("DOMContentLoaded", async () => {
    const book = await postDetailService.getBookInfo();
    window.bookInfo = book;

    await postDetailLayout.showBookDetail(book);
    postDetailLayout.showPostDetail();
    postDetailLayout.showFileImages();

    try {
        // 좋아요 상태 초기화
        const liked = await postDetailService.checkPostLike();
        if (liked) {
            document.querySelector(".editor-icon-heart").classList.add("filled");
            document.querySelector(".like-inner .like-btn").classList.add("filled");
        }

        // 팔로우 상태 초기화 + 작성자 본인일 경우 버튼 숨김
        const followButton = document.querySelector(".button-follow");
        const myId = window.loggedInMemberId;
        const writerId = post.writerId;

        console.log("내 ID:", window.loggedInMemberId);
        console.log("작성자 ID:", post.writerId);


        if (String(myId) === String(writerId)) {
            followButton.style.display = "none";
            console.log("팔로우 버튼 숨김 완료");
        } else {
            const followed = await postDetailService.checkMemberFollow();
            if (followed) {
                followButton.classList.add("following");
                followButton.innerText = "팔로잉";
            }
        }
    } catch (error) {
        if (error instanceof Response && error.status === 401) {
            alert("로그인이 필요한 서비스입니다.");
            window.location.href = "/personal/login";
        }
    }

    // 공유 모달 초기 숨김
    const shareModal = document.querySelector(".popup-sns-share");
    if (shareModal) {
        shareModal.style.display = "none";
    }

    // 카카오 초기화
    if (window.Kakao && !Kakao.isInitialized()) {
        Kakao.init("16c74ba60369da3eb1ee0b92f425d32e");
        console.log("Kakao 초기화 완료");
    }
});

// 좋아요 버튼 클릭 시 서버에 좋아요 반영 및 UI 토글
const likeButton = document.querySelector(".editor-icon-heart");
const miniLikeBtn = document.querySelector(".like-inner .like-btn");

async function handleLikeToggle() {
    const isLiked = likeButton.classList.contains("filled");
    try {
        if (isLiked) {
            await postDetailService.deletePostLike();
            showToast("좋아요 취소되었습니다.");
        } else {
            await postDetailService.addPostLike();
            showToast("좋아요가 등록되었습니다.");
        }

        likeButton.classList.toggle("filled");
        miniLikeBtn.classList.toggle("filled");
    } catch (error) {
        if (error instanceof Response && error.status === 401) {
            alert("로그인이 필요한 서비스입니다.");
            window.location.href = "/personal/login";
        }
    }
}

likeButton.addEventListener("click", handleLikeToggle);
miniLikeBtn.addEventListener("click", handleLikeToggle);

// 팔로우 버튼 클릭 시 서버에 반영 및 UI 토글
const followButton = document.querySelector(".button-follow");

followButton.addEventListener("click", async () => {
    const isFollowing = followButton.classList.contains("following");
    try {
        if (isFollowing) {
            await postDetailService.deleteMemberFollow();
            showToast("팔로잉 취소되었습니다.");
            followButton.innerText = "팔로우";
        } else {
            await postDetailService.addMemberFollow();
            showToast("팔로우 되었습니다.");
            followButton.innerText = "팔로잉";
        }

        followButton.classList.toggle("following");
    } catch (error) {
        if (error instanceof Response && error.status === 401) {
            alert("로그인이 필요한 서비스입니다.");
            window.location.href = "/personal/login";
        }
    }
});

// 공유하기 기능 - 모달 열기/닫기 + 카카오, 페북, 트위터, 링크 복사
const openShareBtn = document.querySelector(".share-item > button");
const shareModal = document.querySelector(".popup-sns-share");
const closeShareBtn = document.querySelector(".share-close-button");

openShareBtn?.addEventListener("click", () => {
    shareModal.style.display = "flex";
});

closeShareBtn?.addEventListener("click", () => {
    shareModal.style.display = "none";
});

const kakaoBtn = document.getElementById("kakao-share-btn");
kakaoBtn?.addEventListener("click", (e) => {
    e.preventDefault();
    Kakao.Share.sendDefault({
        objectType: "feed",
        content: {
            title: document.querySelector(".diary-title-container > .text").innerText,
            description: post.bookTitle,
            imageUrl: window.bookInfo?.cover || "/images/common/default-book-cover.png",
            link: {
                mobileWebUrl: window.location.href,
                webUrl: window.location.href,
            },
        },
    });
});

document.getElementById("share-facebook-btn")?.addEventListener("click", (e) => {
    e.preventDefault();
    const url = encodeURIComponent(window.location.href);
    const shareUrl = `https://www.facebook.com/sharer/sharer.php?u=${url}`;
    window.open(shareUrl, "_blank", "width=600,height=400");
});

document.getElementById("twitter-share-btn")?.addEventListener("click", (e) => {
    e.preventDefault();
    const pageUrl = encodeURIComponent(window.location.href);
    const text = encodeURIComponent("독서는 북적서울과 함께 😄");
    const twitterUrl = `https://twitter.com/intent/tweet?url=${pageUrl}&text=${text}`;
    window.open(twitterUrl, "_blank", "width=600,height=400");
});

const copyBtn = document.querySelector(".btn-url-copy");
copyBtn?.addEventListener("click", () => {
    const url = window.location.href;
    navigator.clipboard.writeText(url)
        .then(() => showToast("링크가 복사되었습니다!"))
        .catch(() => showToast("복사에 실패했습니다."));
});

// 신고하기 관련 기능 (버튼 클릭, 메뉴 열기, 모달 열기, 등록/취소 처리)
const reportButton = document.querySelector(".more-item > button");
const reportMenu = document.querySelector(".more-ul");
const reportModal = document.querySelector(".police-popup");
const cancelReport = document.querySelector(".policecancl");
const confirmReport = document.querySelector(".review-police");

reportButton?.addEventListener("click", (e) => {
    e.stopPropagation();
    reportMenu.style.display = "block";
});

reportMenu?.addEventListener("click", (e) => {
    reportModal.style.display = "flex";
});

window.addEventListener("click", (e) => {
    if (!reportMenu.contains(e.target)) {
        reportMenu.style.display = "none";
    }
});

const radios = document.querySelectorAll(".mds-radio");
radios.forEach((label) => {
    label.addEventListener("click", () => {
        radios.forEach((l) => l.classList.remove("checked"));
        label.classList.add("checked");
        const input = label.querySelector("input[type='radio']");
        input.checked = true;
        input.dispatchEvent(new Event("change"));
    });
});

cancelReport?.addEventListener("click", () => {
    reportModal.style.display = "none";
    reportMenu.style.display = "none";
});

confirmReport?.addEventListener("click", () => {
    showToast("신고가 완료되었습니다.");
    reportModal.style.display = "none";
    reportMenu.style.display = "none";
});

// 토스트 메시지 출력 함수
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;
    document.querySelector("#wrap").appendChild(toast);
    setTimeout(() => toast.remove(), 2500);
}