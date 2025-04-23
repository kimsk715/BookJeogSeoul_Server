document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("normal-inquiry-link")){
        inquiryService.getAllMemberInquiry(inquiryLayout.showMemberInquiry);
    }
})

const normalInquiryPageWrap = document.querySelector(".normal-inquiry-pagination")
const normalInquiryKeywordWrap = document.querySelector(".normal-inquiry-search-input")
const normalInquiryWrapper = document.querySelector(".normal-inquiry-management")
normalInquiryPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : normalInquiryKeywordWrap.value}};
        inquiryService.getAllMemberInquiry(inquiryLayout.showMemberInquiry, param);
    }
})

normalInquiryKeywordWrap.addEventListener('change',(e) => {
    console.log(e.target.value)
    const param = {search : {keyword : e.target.value}}
    inquiryService.getAllMemberInquiry(inquiryLayout.showMemberInquiry, param);
})

normalInquiryWrapper.addEventListener("click",(e) =>{
    if(e.target.classList.contains("modal-detail-btn")){
        inquiryService.getMemberInquiry(inquiryLayout.showMemberDetail, e.target.value);
    }
})

document.addEventListener("click",(e) =>{
    console.log("이벤트 확인")
    const inquiryAnswerArea = commonModalContainer.querySelector("#normal-inquiry-answer")
    if(e.target.classList.contains("save-btn")){
        const inquiryAnswer = inquiryAnswerArea.value;
        const inquiryId = e.target.value;
        inquiryService.personalInquiryAnswer(inquiryId, inquiryAnswer)
    }
})

/* 단체 문의 이벤트 */
const sponsorInquiryPageWrap = document.querySelector(".sponsor-inquiry-pagination")
const sponsorInquiryKeywordWrap = document.querySelector(".sponsor-inquiry-search-input")
const sponsorInquiryWrapper = document.querySelector(".sponsor-inquiry-management")
document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("sponsor-inquiry-link")){
        inquiryService.getAllSponsorInquiry(inquiryLayout.showSponsorInquiry);
    }
})

sponsorInquiryPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : sponsorInquiryKeywordWrap.value}};
        inquiryService.getAllSponsorInquiry(inquiryLayout.showSponsorInquiry, param);
    }
})

sponsorInquiryKeywordWrap.addEventListener('change',(e) => {
    console.log(e.target.value)
    const param = {search : {keyword : e.target.value}}
    inquiryService.getAllSponsorInquiry(inquiryLayout.showSponsorInquiry, param);
})

sponsorInquiryWrapper.addEventListener("click",(e) =>{
    if(e.target.classList.contains("modal-detail-btn")){
        inquiryService.getSponsorInquiry(inquiryLayout.showSponsorDetail, e.target.value);
    }
})

document.addEventListener("click",(e) =>{
    const inquiryAnswerArea = commonModalContainer.querySelector("#sponsor-inquiry-answer")
    if(e.target.classList.contains("save-btn")){
        const inquiryAnswer = inquiryAnswerArea.value;
        const inquiryId = e.target.value;
        inquiryService.sponsorInquiryAnswer(inquiryId, inquiryAnswer)
    }
})

