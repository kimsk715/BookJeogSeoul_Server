const memberService= (() => {
    const getAllPersonal = async(callback, param ={}) =>{

        let page = param.page || 1;
        let search = param.search;
        let memberKeyword = "";
        let type = "";
        let path =`/admin/personal-members?page=${page}`;
        if(search){
            memberKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(memberKeyword){
            path += `&keyword=${memberKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const personalMemberList = await response.json();
        if(callback){
            callback(personalMemberList)
        }
    }

    const getAllSponsor = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let memberKeyword = "";
        let type = "";
        let path =`/admin/sponsor-members?page=${page}`;
        if(search){
            memberKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(memberKeyword){
            path += `&keyword=${memberKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const sponsorMemberList = await response.json();
        if(callback){
            callback(sponsorMemberList)
        }
    }

    const insertSponsor = async (infoArray) => {
        let path = `admin/insert-sponsor?info=${infoArray}`
        await fetch(path);
    }

    const getAllAdmin = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let adminKeyword = "";
        let type = "";
        let path =`/admin/admin-members?page=${page}`;
        if(search){
            adminKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(adminKeyword){
            path += `&keyword=${adminKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const adminMemberList = await response.json();
        if(callback){
            callback(adminMemberList)
        }
    }

    const insertAdmin = async (infoArray) => {
        let path = `admin/insert-admin?info=${infoArray}`
        await fetch(path);
    }
    return {getAllPersonal : getAllPersonal, getAllSponsor:getAllSponsor, insertSponsor:insertSponsor,getAllAdmin:getAllAdmin, insertAdmin:insertAdmin};
})();
