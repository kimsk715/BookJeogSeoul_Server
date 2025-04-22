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

// bookPostReportKeywordWrap.addEventListener('change',(e) => {
//     console.log(e.target.value)
//     const param = {search : {keyword : e.target.value}}
//     reportService.getAllMemberReport(reportLayout.showMemberReport, param);
// })
//
// bookPostReportWrapper.addEventListener("click",(e) =>{
//     if(e.target.classList.contains("modal-detail-btn")){
//         reportService.getMemberReport(reportLayout.showMemberDetail, e.target.value);
//     }
// })
//
// document.addEventListener("click",(e) =>{
//     console.log("이벤트 확인")
//     const reportAnswerArea = commonModalContainer.querySelector("#book-post-answer")
//     if(e.target.classList.contains("save-btn")){
//         console.log("이벤트 확인")
//         const reportAnswer = reportAnswerArea.value;
//         const reportId = e.target.value;
//         reportService.personalReportAnswer(reportId, reportAnswer)
//     }
// })

/* 단체 문의 이벤트 */
// const sponsorReportPageWrap = document.querySelector(".sponsor-report-pagination")
// const sponsorReportKeywordWrap = document.querySelector(".sponsor-report-search-input")
// const sponsorReportWrapper = document.querySelector(".sponsor-report-management")
// document.addEventListener(('click'),(e) => {
//     if(e.target.classList.contains("sponsor-report-link")){
//         reportService.getAllSponsorReport(reportLayout.showSponsorReport);
//     }
// })
//
// sponsorReportPageWrap.addEventListener('click', (e) => {
//     if(e.target.classList.contains("page-btn")){
//         const param = {page : e.target.id, search : {keyword : sponsorReportKeywordWrap.value}};
//         reportService.getAllSponsorReport(reportLayout.showSponsorReport, param);
//     }
// })
//
// sponsorReportKeywordWrap.addEventListener('change',(e) => {
//     console.log(e.target.value)
//     const param = {search : {keyword : e.target.value}}
//     reportService.getAllSponsorReport(reportLayout.showSponsorReport, param);
// })
//
// sponsorReportWrapper.addEventListener("click",(e) =>{
//     if(e.target.classList.contains("modal-detail-btn")){
//         reportService.getSponsorReport(reportLayout.showSponsorDetail, e.target.value);
//     }
// })
//
// document.addEventListener("click",(e) =>{
//     console.log("이벤트 확인")
//     const reportAnswerArea = commonModalContainer.querySelector("#sponsor-report-answer")
//     if(e.target.classList.contains("save-btn")){
//         console.log("이벤트 확인")
//         const reportAnswer = reportAnswerArea.value;
//         const reportId = e.target.value;
//         reportService.sponsorReportAnswer(reportId, reportAnswer)
//     }
// })

