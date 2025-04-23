const reportService= (() => {
    const getAllBookPostReports = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let bookPostReportKeyword = "";
        let type = "";
        let path =`/admin/book-post-reports?page=${page}`;
        if(search){
            bookPostReportKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(bookPostReportKeyword){
            path += `&keyword=${bookPostReportKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const bookPostReportDTOList = await response.json();
        if(callback){
            callback(bookPostReportDTOList)
        }
    }
    const getBookPostReport = async(callback, reportId) =>{
        let path = `/admin/book-post-report?id=${reportId}`
        console.log(path)
        const response = await fetch(path);
        const bookPostReport = await response.json();
        if(callback){
            callback(bookPostReport)
        }
    }

    const bookReportProcess = async(reportId, postId) => {
        let path = `/admin/answer-book-post?id=${reportId}&post-id=${postId}`
        console.log(path)
        await fetch(path);
    }

    const getAllCommentReports = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let commentReportKeyword = "";
        let type = "";
        let path =`/admin/comment-reports?page=${page}`;
        if(search){
            commentReportKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(commentReportKeyword){
            path += `&keyword=${commentReportKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const commentReportList = await response.json();
        if(callback){
            callback(commentReportList)
        }
    }

    const getCommentReport = async(callback, reportId) =>{
        let path = `/admin/comment-report?id=${reportId}`
        console.log(path)
        const response = await fetch(path);
        const commentReport = await response.json();
        if(callback){
            callback(commentReport)
        }
    }

    const commentReportAnswer = async(reportId, commentId) => {
        let path = `/admin/answer-comment?id=${reportId}&comment-id=${commentId}`
        console.log(path)
        await fetch(path);
    }

    return {getAllBookPostReports : getAllBookPostReports,
        getBookPostReport: getBookPostReport,
        bookReportProcess : bookReportProcess,
        getAllCommentReports : getAllCommentReports,
        getCommentReport : getCommentReport,
        commentReportAnswer : commentReportAnswer
            };
})();
