
document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("book-link")){
        bookService.getAllBook(bookLayout.showBookList);
    }
})