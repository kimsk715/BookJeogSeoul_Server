const receiverService= (() => {
    // 전체 독후감
    const getAllReceiverPost = async(callback, param ={}) =>{
        let page = param.page || 1;
        let search = param.search;
        let receiverKeyword = "";
        let type = "";
        let path =`/admin/receiver-posts?page=${page}`;
        if(search){
            receiverKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(receiverKeyword){
            path += `&keyword=${receiverKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const receiverList = await response.json();
        if(callback){
            callback(receiverList)
        }
    }

    const chooseTopReceiver = async () => {
        await fetch(`/admin/top-receiver`);
    }

    return {getAllReceiverPost : getAllReceiverPost, chooseTopReceiver:chooseTopReceiver};
})();
