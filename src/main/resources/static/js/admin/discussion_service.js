const discussionService= (() => {
    const getAllDiscussion = async(callback, param ={}) =>{

        let page = param.page || 1;
        let search = param.search;
        let discussionKeyword = "";
        let type = "";
        let path =`/admin/discussions?page=${page}`;
        if(search){
            discussionKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(discussionKeyword){
            path += `&keyword=${discussionKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const discussionList = await response.json();
        if(callback){
            callback(discussionList)
        }
    }


    return {getAllDiscussion : getAllDiscussion};
})();
