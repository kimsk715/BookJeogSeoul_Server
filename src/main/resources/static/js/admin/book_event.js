document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-link")){
        // console.log("클릭 됨")
        // console.log(document.querySelector(".book-table tbody"))
        // console.log("📎 bookLayout:", bookLayout);
        // console.log("📎 showBookList:", bookLayout.showBookList);
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

    checkedCount = 0;
});