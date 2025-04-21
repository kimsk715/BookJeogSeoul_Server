const bookPostService= (() => {
    const getAllBookPost = async(callback, param ={}) =>{

        let page = param.page || 1;
        let search = param.search;
        let bookPostKeyword = "";
        let type = "";
        let path =`/admin/book-posts?page=${page}`;
        if(search){
            bookPostKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(bookPostKeyword){
            path += `&keyword=${bookPostKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const bookList = await response.json();
        if(callback){
            callback(bookList)
        }
    }


    return {getAllBookPost : getAllBookPost};
})();
