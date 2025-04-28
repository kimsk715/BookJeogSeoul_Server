document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("discussion-link")){
        discussionService.getAllDiscussion(discussionLayout.showDiscussionList);
        console.log("토론 조회 버튼 클릭")
    }
})

const discussionPageWrap = document.querySelector(".discussion-pagination")
const discussionKeywordWrap = document.querySelector(".discussion-search-input")
discussionPageWrap.addEventListener("click",(e)=>{
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : discussionKeywordWrap.value}};
        discussionService.getAllDiscussion(discussionLayout.showDiscussionList, param);
    }
})

discussionKeywordWrap.addEventListener("change",(e) =>{
    const param = {search : {keyword : e.target.value}}
    discussionService.getAllDiscussion(discussionLayout.showDiscussionList, param);
})

const discussionModal = document.querySelector(".discussion-modal")
const newDiscussionButton = document.querySelector(".open-new-discussion-btn")
newDiscussionButton.addEventListener('click',() =>{
    openModal(discussionModal);
})

const discussionConfirmButton = document.querySelector(".discussion-confirm-btn")
discussionConfirmButton.addEventListener("click",() => {
    const title = document.querySelector("#new-discussion-title").value;
    const text= document.querySelector("#new-discussion-text").value;
    const isbn = document.querySelector("#new-discussion-book-isbn").value;
    const bookTitle = document.querySelector("#new-discussion-book-title").value;
    const param = {title : title, text : text, isbn : isbn, bookTitle : bookTitle};
    discussionService.addDiscussionPost(param);
})


