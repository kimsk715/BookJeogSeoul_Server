const receiverPageWrap = document.querySelector(".receiver-pagination")

document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("receiver-link")){
        receiverService.getAllReceiverPost(receiverLayout.showReceiverList);
        console.log("독후감 조회 버튼 클릭")
    }
})
const receiverKeywordWrap = document.querySelector(".receiver-search-input")
receiverPageWrap.addEventListener(('click'),(e) =>{
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : receiverKeywordWrap.value}}
        receiverService.getAllReceiverPost(receiverLayout.showReceiverList, param)
    }
})



receiverKeywordWrap.addEventListener('change',(e)=>{
    const param = {search : {keyword : e.target.value}}
    receiverService.getAllReceiverPost(receiverLayout.showReceiverList, param);
})


const receiverChooseButton = document.querySelector(".receiver-choose-btn");

receiverChooseButton.addEventListener("click", (e) => {
    if (
        confirm(
            "후원대상을 선정 및 확정하시겠습니까?"
        )
    ) {
        receiverService.chooseTopReceiver();
        alert("해당 단체에 안내해주시기 바랍니다!")
    } else {
        e.preventDefault();
    }
});