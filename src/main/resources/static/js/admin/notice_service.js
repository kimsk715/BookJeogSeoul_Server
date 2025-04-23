const noticeService= (() => {
    const getAllNotice = async(callback, param ={}) =>{

        let page = param.page || 1;
        let search = param.search;
        let noticeKeyword = "";
        let type = "";
        let path =`/admin/notices?page=${page}`;
        if(search){
            noticeKeyword = search.keyword;
            type = search.type;
        }
        if(type){
            path += `&type=${type}`
        }
        if(noticeKeyword){
            path += `&keyword=${noticeKeyword}`
        }
        console.log(path)
        const response = await fetch(path);
        const noticeList = await response.json();
        if(callback){
            callback(noticeList)
        }
    }

    const getNoticeDetail = async(callback, noticeId) => {
        let path = `/admin/notice?id=${noticeId}`;
        const response = await fetch(path);
        const notice = await response.json();
        if(callback){
            callback(notice)
        }
    }

    const updateNotice = async(noticeId, noticeTitle, noticeText) => {
        let path = `/admin/notice-edit?id=${noticeId}&title=${noticeTitle}&text=${noticeText}`
        await fetch(path);
    }

    const addNotice = async(noticeTitle, noticeText) => {
        let path = `/admin/notice-add?title=${noticeTitle}&text=${noticeText}`;
        await fetch(path)
    }
    return {getAllNotice : getAllNotice,
        getNoticeDetail : getNoticeDetail,
        updateNotice:updateNotice,
        addNotice : addNotice
    };
})();
