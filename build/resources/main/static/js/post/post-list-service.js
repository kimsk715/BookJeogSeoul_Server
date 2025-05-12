const postListService = (() => {

    // 전체 독후감
    const getAllPostList = async (offset = 0) => {
        const response = await fetch(`/post/all-book-post?offset=${offset}`);
        return await response.json();
    };

    // 팔로우중인 독후감
    const getFollowPostList = async (offset = 0) => {
        const response = await fetch(`/post/following-book-post?offset=${offset}`);
        return await response.json();
    };

    // 독후감 커버 이미지 가져오기
    const getCoverImages = async (isbn) => {
        try {
            const response = await fetch(`/book/cover?isbn=${isbn}`);
            return await response.text();
        } catch (e) {
            return "/images/common/default-book-cover.png";
        }
    };

    // 내 독후감 좋아요 여부 조회
    const checkMyPostLike = async (bookPostId) => {
        const response = await fetch(`/post/like-check?bookPostId=${bookPostId}`);
        return (await response.text()) === "true";
    };

    // 특정 독후감 좋아요 개수 조회
    const checkPostLike = async (bookPostId) => {
        const response = await fetch(`/post/like-count?bookPostId=${bookPostId}`);
        return await response.text();
    };

    // 좋아요 추가
    const addPostLike = async (bookPostId) => {
        const response = await fetch(`/post/like-add?bookPostId=${bookPostId}`, {
            method: "POST",
            headers: { "Content-Type": "application/json;charset-utf-8" }
        });
        if (!response.ok) throw response;
    };

    // 좋아요 삭제
    const deletePostLike = async (bookPostId) => {
        const response = await fetch(`/post/like-delete?bookPostId=${bookPostId}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json;charset-utf-8" }
        });
        if (!response.ok) throw response;
    };

    // 특정 작성자 팔로우 여부 조회
    const checkMemberFollow = async (memberId) => {
        const response = await fetch(`/member/follow-check?memberId=${memberId}`);
        return (await response.text()) === "true";
    };

    // 팔로우 추가
    const addMemberFollow = async (memberId) => {
        const response = await fetch(`/member/follow-add?memberId=${memberId}`, {
            method: "POST",
            headers: { "Content-Type": "application/json;charset-utf-8" }
        });
        if (!response.ok) throw response;
    };

    // 팔로우 취소
    const deleteMemberFollow = async (memberId) => {
        const response = await fetch(`/member/follow-delete?memberId=${memberId}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json;charset-utf-8" }
        });
        if (!response.ok) throw response;
    };

    // ISBN 기준 스크랩 여부 조회
    const checkScrap = async (isbn) => {
        const response = await fetch(`/book/scrap-check?isbn=${isbn}`);
        return (await response.text()) === "true";
    };

    // 스크랩 추가
    const addScrap = async (isbn) => {
        const response = await fetch(`/book/scrap-add?isbn=${isbn}`, {
            method: "POST",
            headers: { "Content-Type": "application/json;charset-utf-8" }
        });
        if (!response.ok) throw response;
    };

    // 스크랩 삭제
    const deleteScrap = async (isbn) => {
        const response = await fetch(`/book/scrap-delete?isbn=${isbn}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json;charset-utf-8" }
        });
        if (!response.ok) throw response;
    };

    return {
        getAllPostList : getAllPostList,
        getFollowPostList : getFollowPostList,
        getCoverImages : getCoverImages,
        checkPostLike : checkPostLike,
        addPostLike : addPostLike,
        deletePostLike : deletePostLike,
        checkMemberFollow : checkMemberFollow,
        addMemberFollow : addMemberFollow,
        deleteMemberFollow: deleteMemberFollow,
        checkScrap : checkScrap,
        addScrap : addScrap,
        deleteScrap : deleteScrap,
        checkMyPostLike : checkMyPostLike
    };
})();
