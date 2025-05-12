// 이곳은 임시로 구상한 js를 모아놓은 곳, 적절한 js 파일들에 옮겨쓰자



const openModal = (modal) => {
    if (modal) {
        modal.style.display = "block";
        document.body.style.overflow = "hidden";
    }
};

const closeModal = (modal) => {
    if (modal) {
        modal.style.display = "none";
        document.body.style.overflow = "";
    }
};

// 현재 회원 모달만 구현, 클래스명만 바꿔서 다른 카테고리에도 적용하면 됨.
const commonModal = document.querySelector(".common-modal");
const commonModalContainer = document.querySelector(".common-modal div.modal-container");

document.addEventListener("click",(e) =>{
    if(e.target.classList.contains("modal-detail-btn")){
        openModal(commonModal);
    }
})


document.addEventListener("click", (e) => {
    if (
        e.target.classList.contains("cancel-btn") ||
        e.target.classList.contains("save-btn") ||
        e.target.classList.contains("close-btn") ||
        e.target.classList.contains("modal-backdrop") ||
        e.target.classList.contains("close-button")
    ) {
        closeModal(commonModal);
        closeModal(monthlyBookListModal);
        closeModal(sponsorRegisterModal);
        closeModal(adminModal)
        closeModal(discussionModal)
        closeModal(openAIModal)
        closeModal(addNoticeModal)
    }
});

document.addEventListener("keydown", (e) => {
    if (e.key === "Escape" && commonModal.style.display === "block") {
        closeModal(commonModal);
        closeModal(monthlyBookListModal);
        closeModal(sponsorRegisterModal);
        closeModal(adminModal)
        closeModal(discussionModal)
        closeModal(openAIModal)
        closeModal(addNoticeModal)
    }
});

// 이 달의 독후감 후보 선정 버튼
const bestPostChooseButton = document.querySelector(".best-post-choose-btn");
bestPostChooseButton.addEventListener("click", (e) => {
    if (
        confirm(
            "좋아요 수가 가장 많은 20개의 독후감을 투표 대상으로 선정하시겠습니까?"
        )
    ) {
        return;
    }
});

const topChooseButton = document.querySelector(".top-post-choose-btn");
topChooseButton.addEventListener("click", (e) => {
    if (
        confirm(
            "가장 많이 득표한 독후감을 이 달의 장원급제로 선정합니다. 맞다면 '예' 를 눌러 진행해주세요"
        )
    ) {
        return;
    }
});




