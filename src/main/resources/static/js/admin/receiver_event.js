const receiverPageWrap = document.querySelector(".receiver-pagination")

document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("receiver-link")){
        receiverService.getAllReceiverPost(receiverLayout.showReceiverList);
        console.log("독후감 조회 버튼 클릭")
    }
})

receiverPageWrap.addEventListener(('click'),(e) =>{
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id}
        receiverService.getAllReceiverPost(receiverLayout.showReceiverList, param)
    }
})
