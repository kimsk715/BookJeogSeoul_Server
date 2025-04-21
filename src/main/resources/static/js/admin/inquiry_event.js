document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("normal-inquiry-link")){
        inquiryService.getAllMemberInquiry(inquiryLayout.showMemberInquiry);
        console.log("토론 조회 버튼 클릭")
    }
})



