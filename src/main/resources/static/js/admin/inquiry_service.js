const inquiryService= (() => {
    const getAllMemberInquiry = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let memberInquiryKeyword = "";
        let type = "";
        let path =`/admin/member-inquiries?page=${page}`;
        if(search){
            memberInquiryKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(memberInquiryKeyword){
            path += `&keyword=${memberInquiryKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const memberInquiryList = await response.json();
        if(callback){
            callback(memberInquiryList)
        }
    }


    return {getAllMemberInquiry : getAllMemberInquiry};
})();
