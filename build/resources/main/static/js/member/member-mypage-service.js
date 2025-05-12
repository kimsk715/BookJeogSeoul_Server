const memberMypageService = (() => {

    // 팔로우 여부 조회
    const checkFollowStatus = async (memberId) => {
        try {
            const response = await fetch(`/member/follow-check?memberId=${memberId}`);
            if (!response.ok) throw new Error("팔로우 상태 확인 실패");
            return await response.json();
        } catch (error) {
            console.error("팔로우 상태 확인 에러:", error);
            return false;
        }
    }

    // 팔로우 추가
    const addFollow = async (memberId) => {
        try {
            const response = await fetch("/member/follow-add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: new URLSearchParams({ memberId }),
            });

            if (!response.ok) throw new Error("팔로우 요청 실패");
        } catch (error) {
            console.error("팔로우 추가 에러:", error);
        }
    }

    // 팔로우 취소
    const deleteFollow = async (memberId) => {
        try {
            const response = await fetch("/member/follow-delete", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: new URLSearchParams({ memberId }),
            });

            if (!response.ok) throw new Error("언팔로우 요청 실패");
        } catch (error) {
            console.error("팔로우 취소 에러:", error);
        }
    }

    return { checkFollowStatus : checkFollowStatus, addFollow : addFollow, deleteFollow : deleteFollow}
})();