const bookService= (() => {


    const getAllBook = async(callback, param ={}) =>{
        // let page = param.page || 1;
        // let search = param.search;
        // let companyInquiryKeyword = "";
        // let status = "";
        // let date = 0;
        // if(search){
        //     companyInquiryKeyword = search.companyInquiryKeyword;
        //     status = search.status;
        //     date = search.date;
        // }
        let path =`/admin/books`;
        // if(status){
        //     path += `&status=${status}`
        // }
        // if(date){
        //     path += `&date=${date}`
        // }
        // if(companyInquiryKeyword){
        //     path += `&companyInquiryKeyword=${companyInquiryKeyword}`
        // }
        const response = await fetch(path)
        const bookList = await response.json();
        if(callback){
            callback(bookList)
        }
    }


    return {getAllBook: getAllBook};
})();
