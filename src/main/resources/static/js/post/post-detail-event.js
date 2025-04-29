// 페이지 로드시 도서 및 게시글 정보 초기화
// 책 상세정보, 독후감 상세내용, 첨부 이미지 목록 출력
// 좋아요, 팔로우 상태도 동기화

document.addEventListener("DOMContentLoaded", async () => {
    const book = await postDetailService.getBookInfo();
    window.bookInfo = book;

    await postDetailLayout.showBookDetail(book);
    postDetailLayout.showPostDetail();
    postDetailLayout.showFileImages();

    try {
        const liked = await postDetailService.checkPostLike();
        if (liked) {
            document.querySelector(".editor-icon-heart").classList.add("filled");
            document.querySelector(".like-inner .like-btn").classList.add("filled");
        }

        const followButton = document.querySelector(".button-follow");
        const myId = window.loggedInMemberId;
        const writerId = post.writerId;

        if (String(myId) === String(writerId)) {
            followButton.style.display = "none";
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

    const shareModal = document.querySelector(".popup-sns-share");
    if (shareModal) {
        shareModal.style.display = "none";
    }

    if (window.Kakao && !Kakao.isInitialized()) {
        Kakao.init("16c74ba60369da3eb1ee0b92f425d32e");
    }

    const etcRadio = document.querySelector('input[type="radio"][value="기타"]');
    const etcInput = document.getElementById("etcInput");
    const etcInputWrap = document.querySelector(".mds-input.mds-input-default");
    const clearButton = document.querySelector(".clear-button");
    const lengthDisplay = document.querySelector(".length");
    const confirmReportBtn = document.querySelector(".review-police");

    // 기타 사유 선택 시 텍스트 입력창 보이기 및 제출 제한
    document.querySelectorAll('input[name="reportType"]').forEach(radio => {
        radio.addEventListener("change", () => {
            if (etcRadio.checked) {
                etcInputWrap.style.display = "block";
                confirmReportBtn.disabled = true;
            } else {
                etcInputWrap.style.display = "none";
                confirmReportBtn.disabled = false;
            }
        });
    });

    // 기타 입력창 제어 - 글자수 표시, 입력시 버튼 활성화, X버튼 노출
    etcInput.addEventListener("input", () => {
        const length = etcInput.value.length;
        lengthDisplay.innerText = `${length}/50`;

        if (length > 0) {
            confirmReportBtn.disabled = false;
            clearButton.style.display = "flex";
        } else {
            confirmReportBtn.disabled = true;
            clearButton.style.display = "none";
        }
    });

    // X 버튼 클릭 시 입력값 초기화
    clearButton.addEventListener("click", () => {
        etcInput.value = "";
        lengthDisplay.innerText = "0/50";
        clearButton.style.display = "none";
        confirmReportBtn.disabled = true;
    });
});

// 좋아요 버튼 토글 처리
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

// 팔로우 버튼 토글 처리
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

// 공유 모달 열기/닫기
const openShareBtn = document.querySelector(".share-item > button");
const shareModal = document.querySelector(".popup-sns-share");
const closeShareBtn = document.querySelector(".share-close-button");

openShareBtn?.addEventListener("click", () => {
    shareModal.style.display = "flex";
});

closeShareBtn?.addEventListener("click", () => {
    shareModal.style.display = "none";
});

// 공유: 카카오톡
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

// 공유: 페이스북
const facebookBtn = document.getElementById("share-facebook-btn");
facebookBtn?.addEventListener("click", (e) => {
    e.preventDefault();
    const url = encodeURIComponent(window.location.href);
    const shareUrl = `https://www.facebook.com/sharer/sharer.php?u=${url}`;
    window.open(shareUrl, "_blank", "width=600,height=400");
});

// 공유: 트위터
const twitterBtn = document.getElementById("twitter-share-btn");
twitterBtn?.addEventListener("click", (e) => {
    e.preventDefault();
    const pageUrl = encodeURIComponent(window.location.href);
    const text = encodeURIComponent("독서는 북적서울과 함께 😄");
    const twitterUrl = `https://twitter.com/intent/tweet?url=${pageUrl}&text=${text}`;
    window.open(twitterUrl, "_blank", "width=600,height=400");
});

// 공유: 링크 복사
const copyBtn = document.querySelector(".btn-url-copy");
copyBtn?.addEventListener("click", () => {
    const url = window.location.href;
    navigator.clipboard.writeText(url)
        .then(() => showToast("링크가 복사되었습니다."))
        .catch(() => showToast("복사에 실패했습니다."));
});

// 신고 메뉴 및 모달 제어
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

// 신고 라디오 버튼 선택 표시
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

confirmReport?.addEventListener("click", async () => {
    console.log("확인버튼 눌림");
    const etcInput = document.querySelector(".mds-input.mds-input-default input");

    const reportType = document.querySelector('input[name="reportType"]:checked').value;
    const bookPostId = post.bookPostId;
    const memberId = window.loggedInMemberId;
    const bookPostReportText = etcInput.value;
    console.log("reportType: " + reportType);
    console.log("bookPostId: " + bookPostId);
    console.log("memberId: " + memberId);
    console.log("bookPostReportText: " + bookPostReportText);

    await postDetailService.addBookPostReport(reportType, bookPostId, memberId, bookPostReportText);
    showToast("신고가 완료되었습니다.");
    reportModal.style.display = "none";
    reportMenu.style.display = "none";
});

// 공통 토스트 메시지 출력 함수
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;
    document.querySelector("#wrap").appendChild(toast);
    setTimeout(() => toast.remove(), 2500);
}