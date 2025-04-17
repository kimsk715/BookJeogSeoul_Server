const bookService= (() => {
    const getAllBook = async(callback) =>{
        let path =`/admin/books`;
        const response = await fetch(path);
        const bookList = await response.json();
        if(callback){
            callback(bookList)
        }
    }

    const tempSelectedBooks = async(chosenBookList, callback) => {
        const params = new URLSearchParams();
        chosenBookList.forEach(book => params.append("list", book));

        let path = `admin/temp-list?${params.toString()}`;
        const response = await fetch(path);
        const tempSelectedList = await response.json();
        if(callback){
            callback(tempSelectedList)
        }
    }
    return {getAllBook : getAllBook, tempSelectedBooks:tempSelectedBooks };
})();
