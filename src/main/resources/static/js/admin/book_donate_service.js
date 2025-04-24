const donateService= (() => {
    const getAllDonation = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let donationKeyword = "";
        let type = "";
        let path =`/admin/book-donations?page=${page}`;
        if(search){
            donationKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(donationKeyword){
            path += `&keyword=${donationKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const donationList = await response.json();
        if(callback){
            callback(donationList)
        }
    }

    const getDonationDetail = async(callback, donateId) =>{
        let path = `/admin/book-donation?id=${donateId}`;
        console.log(path)
        const response = await fetch(path);
        const donation = await response.json();
        if(callback){
            callback(donation);
        }
    }

    const updateStatus = async(idList) => {
        let path = `/admin/donate-ok?idList=${idList}`
        await fetch(path);
    }

    const cancelStatus = async(idList) => {
        let path = `/admin/donate-fail?idList=${idList}`
        await fetch(path);
    }
    return {getAllDonation : getAllDonation, getDonationDetail:getDonationDetail,
        updateStatus:updateStatus, cancelStatus:cancelStatus};
})();
