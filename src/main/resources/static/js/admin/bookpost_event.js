document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-post-link")){
        bookPostService.getAllBookPost(bookPostLayout.showBookPostList);
        console.log("독후감 조회 버튼 클릭")
    }
})

document.addEventListener(('click'),(e) =>{
    if(e.target)
})

document.addEventListener(('click'), (e) => {
    if(e.target.classList.contains("top-post-link")){
        bookPostService.getTopBookPost(bookPostLayout.topBookPostList);
    }
})
