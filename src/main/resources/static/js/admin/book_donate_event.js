document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-donate-link")){
        donateService.getAllDonation(donateLayout.showDonationList);
    }
})
const donationWrapper = document.querySelector(".book-donate-management")
const donationPageWrap = document.querySelector(".book-donate-pagination")
const donationKeywordWrap = document.querySelector(".book-donate-search-input")
donationPageWrap.addEventListener("click",(e)=>{
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : donationKeywordWrap.value}};
        donateService.getAllDonation(donateLayout.showDonationList, param);
    }
})

donationKeywordWrap.addEventListener("change",(e) =>{
    const param = {search : {keyword : e.target.value}}
    donateService.getAllDonation(donateLayout.showDonationList, param);
})

donationWrapper.addEventListener("click",(e)=>{
    if(e.target.classList.contains("modal-detail-btn")){
        const donateId = e.target.value;
        donateService.getDonationDetail(donateLayout.showDonationDetail, donateId)
    }
})

donationWrapper.addEventListener('click',(e) => {
    let checkList = document.querySelectorAll(".chosenDonate:checked")
    let valueList = Array.from(checkList).map(node => node.value);
    console.log(valueList);

    if(e.target.classList.contains("donate-ok")){
        alert("수취완료 상태로 변경되었습니다.")
        donateService.updateStatus(valueList)
    }
    else if(e.target.classList.contains("donate-fail")){
        alert("수취대기 상태로 변경되었습니다.")
        donateService.cancelStatus(valueList)
    }
})