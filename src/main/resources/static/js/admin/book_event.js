document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-link")){
        bookService.getAllBook(bookLayout.showBookList);
    }
})

// 추천 도서 임시 저장 및 최종 선정 과정.
const bookChooseButton = document.querySelector(".book-choose-btn");
const bookTable = document.querySelector(".book-table")

let count = 0;
let checkedCount = 0;
let chosenBookList = [];


bookTable.addEventListener('change',()=> {
    chosenBookList = [];
    const chosenBooks = bookTable.querySelectorAll("input.chosenBook");
    chosenBooks.forEach((book) =>{
        if(book.checked){
            chosenBookList.push(book.value)
        }
    })
    console.log(chosenBookList)

})


// 책 배열을 임시로 저장하도록 하는 배열이 필요.
bookChooseButton.addEventListener("click", () => {
    checkedCount = chosenBookList.length;
    console.log(checkedCount)
    if(checkedCount === 0){
        alert("선택된 도서가 없습니다.");
    }
    else{
        const chosenBooks = bookTable.querySelectorAll("input.chosenBook");
        alert("선택한 도서가 목록에 저장되었습니다.");
        chosenBooks.forEach((button) => {
            button.checked = false;
        })
        bookService.tempSelectedBooks(chosenBookList);
    }
    console.log(checkedCount)


});
//  서버와의 연동
const pageButtons = document.querySelectorAll(".page-btn");
const bookSearchInput = document.querySelector("input.book-search-input")
const pageWrap = document.querySelector(".book-pagination")
const keywordOption = document.querySelector(".keyword-filter")
// 페이지버튼으로 이동
// 기존 필터링 유지 후, 페이지만
pageWrap.addEventListener('click',(e) =>{
   if(e.target.classList.contains("page-btn")){
       const searchType = keywordOption.value
       const bookKeyword = bookSearchInput.value;
       const param = {page:e.target.id, search : {keyword : bookKeyword, type : searchType}};
       bookService.getAllBook(bookLayout.showBookList, param)
   }
})

// 키워드 검색
// 기존 필터링 모두 초기화
bookSearchInput.addEventListener("keyup",(e) =>{
    if(e.key === 'Enter'){
        const bookKeyword = e.target.value;
        const searchType = keywordOption.value
        console.log(searchType)
        if(bookKeyword){
            const param = {search : {keyword : bookKeyword, type : searchType}}
            bookService.getAllBook(bookLayout.showBookList, param)
        }

    }
})

// 월간 추천 도서 선정을 위한 목록 모달
const monthlyBookButton = document.querySelector(".book-recommend-btn");
const monthlyBookListModal = document.querySelector(".monthly-book-modal");

monthlyBookButton.addEventListener("click", () => {
    openModal(monthlyBookListModal);
    bookService.tempLists(bookLayout.showTempSelectedList)
});

const openAIButton = document.querySelector(".openAPI-btn");

const openAIModal = document.querySelector(".openAI-modal");
const openAIResult = openAIModal.querySelector(".modal-body");

openAIButton.addEventListener('click',(e) => {
    checkedCount = chosenBookList.length;
    console.log(checkedCount)
    if(checkedCount === 1) {
        let isbn = chosenBookList[0];
        bookService.openAI(bookLayout.showOpenAI, isbn);
        openModal(openAIModal);

    }
    else{
        e.preventDefault();
        alert("한 번에 선택할 수 있는 도서는 1개 입니다.")
    }

})

openAIModal.addEventListener("click",(e) =>{
    const result1 = openAIModal.querySelector(".result1")
    const result2 = openAIModal.querySelector(".result2")
    const result3 = openAIModal.querySelector(".result3")
    if(e.target.classList.contains("result-1")){
        result1.classList.add("selected")
        result2.classList.remove("selected")
        result3.classList.remove("selected")
        result1.removeAttribute("style");
        result2.style.display = "none";
        result3.style.display = "none";
    }
    else if(e.target.classList.contains("result-2")){
        result1.classList.remove("selected")
        result2.classList.add("selected")
        result3.classList.remove("selected")
        result1.style.display = "none";
        result2.removeAttribute("style");
        result3.style.display = "none";
    }
    else if(e.target.classList.contains("result-3")){
        result1.classList.remove("selected")
        result2.classList.remove("selected")
        result3.classList.add("selected")
        result1.style.display = "none";
        result2.style.display = "none";
        result3.removeAttribute("style");
    }
})

const openAIConfirmButton = document.querySelector(".openAI-confirm-btn");

openAIConfirmButton.addEventListener("click",() => {
    const title = document.querySelector("div.selected #apiResult-title").value;
    const text= document.querySelector("div.selected #apiResult-text").value;
    const isbn = document.querySelector("div.selected #apiResult-book-isbn").value;
    const bookTitle = document.querySelector("div.selected #apiResult-book-title").value;
    const param = {title : title, text : text, isbn : isbn, bookTitle : bookTitle};
    console.log(param);
    discussionService.addDiscussionPost(param);
})



