document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("discussion-link")){
        discussionService.getAllDiscussion(discussionLayout.showDiscussionList);
        console.log("토론 조회 버튼 클릭")
    }
})



