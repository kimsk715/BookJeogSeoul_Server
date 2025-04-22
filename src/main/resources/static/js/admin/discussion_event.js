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
