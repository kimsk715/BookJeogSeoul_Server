const bookService= (() => {
    const getAllBook = async(callback, param ={}) =>{

        let page = param.page || 1;
        let search = param.search;
        let bookKeyword = "";
        let type = "";
        let path =`/admin/books?page=${page}`;
        if(search){
            bookKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(bookKeyword){
            path += `&keyword=${bookKeyword}`
        }
        console.log(path)
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

    const tempLists = async(callback) =>{
        let path = `admin/temp-lists`;
        const response = await fetch(path)
        const tempList =  await response.json()
        console.log(tempList)
        if(callback){
            callback(tempList)
        }

    }

    const openAI = async(callback, isbn) => {
        let path = `/bot/chat?isbn=${isbn}`;

        openAIResult.innerHTML = `<div class="loader-wrapper">
        <img src="/images/common/loading.gif" class="loading" alt="loading" />
        </div>`

        const loadingImage = document.querySelector(".loading")
        const response = await fetch(path);
        const topics = await response.json();
        loadingImage.style.display = "none";
        if(callback){
            callback(topics);
        }
    }
    return {getAllBook : getAllBook, tempSelectedBooks:tempSelectedBooks, tempLists:tempLists, openAI:openAI };
})();
