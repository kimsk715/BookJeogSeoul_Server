document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-post-link")){
        bookPostService.getAllBookPost(bookPostLayout.showBookPostList);
        console.log("독후감 조회 버튼 클릭")
    }
})

// 추천 도서 임시 저장 및 최종 선정 과정.
// const bookChooseButton = document.querySelector(".book-choose-btn");
// const bookTable = document.querySelector(".book-table")
//
// let count = 0;
// let checkedCount = 0;
// let chosenBookList = [];
//
//
// bookTable.addEventListener('change',()=> {
//     chosenBookList = [];
//     const chosenBooks = bookTable.querySelectorAll("input.chosenBook");
//     chosenBooks.forEach((book) =>{
//         if(book.checked){
//             chosenBookList.push(book.value)
//         }
//     })
//     console.log(chosenBookList)
// })
//
//
// // 책 배열을 임시로 저장하도록 하는 배열이 필요.
// bookChooseButton.addEventListener("click", () => {
//     checkedCount = chosenBookList.length;
//     if(checkedCount === 0){
//         alert("선택된 도서가 없습니다.");
//     }
//     else{
//         const chosenBooks = bookTable.querySelectorAll("input.chosenBook");
//         alert("선택한 도서가 목록에 저장되었습니다.");
//         chosenBooks.forEach((button) => {
//             button.checked = false;
//         })
//         bookService.tempSelectedBooks(chosenBookList);
//     }
//     console.log(checkedCount)
//
//     checkedCount = 0;
// });
// //  서버와의 연동
// const pageButtons = document.querySelectorAll(".page-btn");
// const bookSearchInput = document.querySelector("input.book-search-input")
// const pageWrap = document.querySelector(".book-pagination")
// const keywordOption = document.querySelector(".keyword-filter")
// // 페이지버튼으로 이동
// // 기존 필터링 유지 후, 페이지만
// pageWrap.addEventListener('click',(e) =>{
//    if(e.target.classList.contains("page-btn")){
//        const searchType = keywordOption.value
//        const bookKeyword = bookSearchInput.value;
//        const param = {page:e.target.id, search : {keyword : bookKeyword, type : searchType}};
//        bookService.getAllBook(bookLayout.showBookList, param)
//    }
// })
//
// // 키워드 검색
// // 기존 필터링 모두 초기화
// bookSearchInput.addEventListener("keyup",(e) =>{
//     if(e.key === 'Enter'){
//         const bookKeyword = e.target.value;
//         const searchType = keywordOption.value
//         console.log(searchType)
//         if(bookKeyword){
//             const param = {search : {keyword : bookKeyword, type : searchType}}
//             bookService.getAllBook(bookLayout.showBookList, param)
//         }
//
//     }
// })
//
// // 월간 추천 도서 선정을 위한 목록 모달
// const monthlyBookButton = document.querySelector(".book-recommend-btn");
// const monthlyBookListModal = document.querySelector(".monthly-book-modal");
//
// monthlyBookButton.addEventListener("click", () => {
//     openModal(monthlyBookListModal);
//     bookService.tempLists(bookLayout.showTempSelectedList)
// });
//
// const submitListButton = document.querySelector(".submit-list");

submitListButton.addEventListener("click", () => {
    if (
        confirm(
            "아래 목록에 있는 도서들이 이 달의 독후감 지정 도서로 결정됩니다. 결정하시려면 '확인'을, 다시 확인하시려면 '취소'를 눌러주세요"
        )
    ) {
        closeModal(monthlyBookListModal);
        // 여기에는 나중에 서버와 연동
    }
});