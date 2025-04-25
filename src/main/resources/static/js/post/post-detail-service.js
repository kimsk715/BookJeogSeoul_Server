const postDetailService = (() => {
    // isbn으로 알라딘에서 도서 정보 받아오기
    const getBookInfo = async () => {
        // hidden input에서 isbn을 받아옴
        const isbn = document.querySelector("#book-isbn").value;
        const response = await fetch(`/book/api/${isbn}`);

        const book = await response.json();
        return book;
    }


    // 로그인한 회원의 해당 독후감 좋아요 여부 조회
    const checkPostLike = async () => {
        const bookPostId = post.bookPostId;
        const response = await fetch(`/post/like-check?bookPostId=${bookPostId}`);

        // 조회는 예외 throw 하지 않음
        return (await response.text()) === "true";
    };

    // 독후감 좋아요 누르기
    const addPostLike = async () => {
        const bookPostId = post.bookPostId;
        const response = await fetch(`/post/like-add?bookPostId=${bookPostId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset-utf-8"
            }
        });

        if (!response.ok) throw response;
    };

    // 독후감 좋아요 삭제
    const deletePostLike = async () => {
        const bookPostId = post.bookPostId;
        const response = await fetch(`/post/like-delete?bookPostId=${bookPostId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset-utf-8"
            }
        });

        if (!response.ok) throw response;
    };

    // 작성자 팔로우 여부 확인
    const checkMemberFollow = async () => {
        const receiverId = post.writerId;
        const response = await fetch(`/member/follow-check?memberId=${receiverId}`);

        return (await response.text()) === "true";
    };

    // 작성자 팔로우하기
    const addMemberFollow = async () => {
        const receiverId = post.writerId;
        const response = await fetch(`/member/follow-add?memberId=${receiverId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset-utf-8"
            }
        });

        if (!response.ok) throw response;
    };

    // 작성자 팔로우 취소하기
    const deleteMemberFollow = async () => {
        const receiverId = post.writerId;
        const response = await fetch(`/member/follow-delete?memberId=${receiverId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset-utf-8"
            }
        });

        if (!response.ok) throw response;
    };

    return { getBookInfo : getBookInfo, checkPostLike : checkPostLike, addPostLike : addPostLike,
        deletePostLike : deletePostLike, checkMemberFollow : checkMemberFollow, addMemberFollow : addMemberFollow,
        deleteMemberFollow : deleteMemberFollow }
})();