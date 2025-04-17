
document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-link")){
        console.log("클릭 됨")
        console.log(document.querySelector(".book-table tbody"))
        console.log("📎 bookLayout:", bookLayout);
        console.log("📎 showBookList:", bookLayout.showBookList);
        bookService.getAllBook(bookLayout.showBookList);
    }
})