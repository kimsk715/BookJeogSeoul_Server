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
    const getMemberInquiry = async(callback, inquiryId) =>{
        let path = `/admin/member-inquiry?id=${inquiryId}`
        console.log(path)
        const response = await fetch(path);
        const memberInquiry = await response.json();
        if(callback){
            callback(memberInquiry)
        }
    }

    const personalInquiryAnswer = async(inquiryId, inquiryAnswer) => {
        let path = `/admin/answer-personal?id=${inquiryId}&answer=${inquiryAnswer}`
        console.log(path)
        await fetch(path);
    }

    const getAllSponsorInquiry = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let sponsorInquiryKeyword = "";
        let type = "";
        let path =`/admin/sponsor-inquiries?page=${page}`;
        if(search){
            sponsorInquiryKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(sponsorInquiryKeyword){
            path += `&keyword=${sponsorInquiryKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const sponsorInquiryList = await response.json();
        if(callback){
            callback(sponsorInquiryList)
        }
    }

    const getSponsorInquiry = async(callback, inquiryId) =>{
        let path = `/admin/sponsor-inquiry?id=${inquiryId}`
        console.log(path)
        const response = await fetch(path);
        const sponsorInquiry = await response.json();
        if(callback){
            callback(sponsorInquiry)
        }
    }

    const sponsorInquiryAnswer = async(inquiryId, inquiryAnswer) => {
        let path = `/admin/answer-sponsor?id=${inquiryId}&answer=${inquiryAnswer}`
        console.log(path)
        await fetch(path);
    }




    return {getAllMemberInquiry : getAllMemberInquiry,
            getMemberInquiry:getMemberInquiry,
            personalInquiryAnswer:personalInquiryAnswer,
            getAllSponsorInquiry:getAllSponsorInquiry,
            getSponsorInquiry:getSponsorInquiry,
            sponsorInquiryAnswer:sponsorInquiryAnswer
            };
})();
