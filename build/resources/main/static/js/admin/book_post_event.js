const bookPostPageWrap = document.querySelector(".book-post-pagination")
const topPostPageWrap = document.querySelector(".top-post-pagination")
document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-post-link")){
        bookPostService.getAllBookPost(bookPostLayout.showBookPostList);
        console.log("독후감 조회 버튼 클릭")
    }
})

bookPostPageWrap.addEventListener(('click'),(e) =>{
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id}
        bookPostService.getAllBookPost(bookPostLayout.showBookPostList, param)
    }
})

document.addEventListener(('click'), (e) => {
    if(e.target.classList.contains("top-post-link")){
        bookPostService.getTopBookPost(bookPostLayout.topBookPostList);
    }
})

topPostPageWrap.addEventListener(('click'),(e) =>{
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id}
        bookPostService.getTopBookPost(bookPostLayout.topBookPostList, param)
    }
})