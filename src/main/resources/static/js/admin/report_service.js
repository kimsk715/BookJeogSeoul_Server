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
    const getMemberReport = async(callback, reportId) =>{
        let path = `/admin/member-report?id=${reportId}`
        console.log(path)
        const response = await fetch(path);
        const memberReport = await response.json();
        if(callback){
            callback(memberReport)
        }
    }

    const personalReportAnswer = async(reportId, reportAnswer) => {
        let path = `/admin/answer-personal?id=${reportId}&answer=${reportAnswer}`
        console.log(path)
        await fetch(path);
    }

    const getAllSponsorReport = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let sponsorReportKeyword = "";
        let type = "";
        let path =`/admin/sponsor-inquiries?page=${page}`;
        if(search){
            sponsorReportKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(sponsorReportKeyword){
            path += `&keyword=${sponsorReportKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const sponsorReportList = await response.json();
        if(callback){
            callback(sponsorReportList)
        }
    }

    const getSponsorReport = async(callback, reportId) =>{
        let path = `/admin/sponsor-report?id=${reportId}`
        console.log(path)
        const response = await fetch(path);
        const sponsorReport = await response.json();
        if(callback){
            callback(sponsorReport)
        }
    }

    const sponsorReportAnswer = async(reportId, reportAnswer) => {
        let path = `/admin/answer-sponsor?id=${reportId}&answer=${reportAnswer}`
        console.log(path)
        await fetch(path);
    }




    return {getAllBookPostReports : getAllBookPostReports,
            // getMemberReport:getMemberReport,
            // personalReportAnswer:personalReportAnswer,
            // getAllSponsorReport:getAllSponsorReport,
            // getSponsorReport:getSponsorReport,
            // sponsorReportAnswer:sponsorReportAnswer
            };
})();
