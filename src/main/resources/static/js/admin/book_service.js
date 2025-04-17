const bookService= (() => {


    const getAllBook = async(callback) =>{
        let path =`/admin/books`;
        const response = await fetch(path);
        const bookList = await response.json();
        console.log("📦 bookList 받음:", bookList);
        if(callback){
            console.log("콜백 실행 확인")
            callback(bookList)
        }
    }


    return {getAllBook : getAllBook};
})();
