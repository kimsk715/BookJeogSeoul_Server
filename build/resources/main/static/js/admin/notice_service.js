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

    const addNotice = async(noticeTitle, noticeText, files) => {
        const formData = new FormData();
        formData.append("title", noticeTitle);
        formData.append("text", noticeText);

        for (const file of files) {
            formData.append("files", file); // name=files인 파라미터로 여러 파일
        }

        fetch("/admin/notice-add", {
            method: "POST",
            body: formData,
        })
            .then(response => response.json());
    }
    return {getAllNotice : getAllNotice,
        getNoticeDetail : getNoticeDetail,
        updateNotice:updateNotice,
        addNotice : addNotice
    };
})();
