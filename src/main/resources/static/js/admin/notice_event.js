document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("notice-link")){
        noticeService.getAllNotice(noticeLayout.showNoticeList);
    }
})


//  서버와의 연동
const noticeSearchInput = document.querySelector("input.notice-search-input")
const noticePageWrap = document.querySelector(".notice-pagination")
const noticeWrapper = document.querySelector(".notice-management")
// 페이지버튼으로 이동
// 기존 필터링 유지 후, 페이지만
noticePageWrap.addEventListener('click',(e) =>{
   if(e.target.classList.contains("page-btn")){
       const noticeKeyword = noticeSearchInput.value;
       const param = {page:e.target.id, search : {keyword : noticeKeyword}};
       noticeService.getAllNotice(noticeLayout.showNoticeList, param)
   }
})

// 키워드 검색
// 기존 필터링 모두 초기화
noticeSearchInput.addEventListener("keyup",(e) =>{
    if(e.key === 'Enter'){
        const noticeKeyword = e.target.value;

        if(noticeKeyword){
            const param = {search : {keyword : noticeKeyword}}
            noticeService.getAllNotice(noticeLayout.showNoticeList, param)
        }
    }
})

noticeWrapper.addEventListener('click',(e) => {
    if(e.target.classList.contains("modal-detail-btn")){
        const noticeId = e.target.value
        noticeService.getNoticeDetail(noticeLayout.showNoticeDetail,noticeId)
    }
})

document.addEventListener('click',(e) =>{
    if(e.target.classList.contains("save-btn") && e.target.parentElement.classList.contains("notice-footer")){
        const noticeId = e.target.value;
        const noticeTitle = document.querySelector("#notice-title").value;
        const noticeText = document.querySelector("#notice-text").value;
        noticeService.updateNotice(noticeId,noticeTitle,noticeText);
    }
})

const addNoticeButton = document.querySelector(".notice-add-btn")
const confirmNoticeButton = document.querySelector(".notice-confirm-btn")
const addNoticeModal = document.querySelector(".notice-modal")
addNoticeButton.addEventListener("click", () =>{
    openModal(addNoticeModal)
})

confirmNoticeButton.addEventListener('click',() =>{
    const noticeTitle = document.querySelector("#new-notice-title").value;
    const noticeText = document.querySelector("#new-notice-text").value;
    noticeService.addNotice(noticeTitle, noticeText)
    closeModal(addNoticeModal);
})

