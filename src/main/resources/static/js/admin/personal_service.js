const personalService= (() => {
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


    return {getAllPersonal : getAllPersonal};
})();
