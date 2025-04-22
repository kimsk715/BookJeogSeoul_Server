document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-post-report-link")){
        reportService.getAllBookPostReports(reportLayout.showBookPostReports);
    }
})

const bookPostReportPageWrap = document.querySelector(".book-post-report-pagination")
const bookPostReportKeywordWrap = document.querySelector(".book-post-report-search-input")
const bookPostReportWrapper = document.querySelector(".book-post-report-management")
bookPostReportPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : bookPostReportKeywordWrap.value}};
        reportService.getAllBookPostReports(reportLayout.showBookPostReports, param);
    }
})

bookPostReportWrapper.addEventListener("click",(e) =>{
    if(e.target.classList.contains("modal-detail-btn")){
        reportService.getBookPostReport(reportLayout.showBookPostReport, e.target.value);
    }
})

bookPostReportKeywordWrap.addEventListener('change',(e)=>{
    const param = {search : {keyword : e.target.value}}
    reportService.getAllBookPostReports(reportLayout.showBookPostReports, param);
})

document.addEventListener("click",(e) =>{
    if(e.target.classList.contains("save-btn")){
        const reportId = e.target.value;
        const postId = e.target.nextElementSibling.value;
        console.log(postId)
        reportService.bookReportProcess(reportId, postId);
    }
})

/* 댓글 신고 이벤트 */
const commentReportPageWrap = document.querySelector(".comment-report-pagination")
const commentReportKeywordWrap = document.querySelector(".comment-report-search-input")
const commentReportWrapper = document.querySelector(".comment-report-management")
document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("comment-report-link")){
        reportService.getAllCommentReport(reportLayout.showCommentReport);
    }
})

commentReportPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : commentReportKeywordWrap.value}};
        reportService.getAllCommentReport(reportLayout.showCommentReport, param);
    }
})

commentReportKeywordWrap.addEventListener('change',(e) => {
    console.log(e.target.value)
    const param = {search : {keyword : e.target.value}}
    reportService.getAllCommentReport(reportLayout.showCommentReport, param);
})

commentReportWrapper.addEventListener("click",(e) =>{
    if(e.target.classList.contains("modal-detail-btn")){
        reportService.getCommentReport(reportLayout.showCommentDetail, e.target.value);
    }
})

document.addEventListener("click",(e) =>{
    console.log("이벤트 확인")
    const reportAnswerArea = commonModalContainer.querySelector("#comment-report-answer")
    if(e.target.classList.contains("save-btn")){
        console.log("이벤트 확인")
        const reportAnswer = reportAnswerArea.value;
        const reportId = e.target.value;
        reportService.commentReportAnswer(reportId, reportAnswer)
    }
})

