const myProfileService = (() => {
    // 프사 생성 또는 수정
    const setProfileImage = async (file) => {
        const formData = new FormData();
        formData.append("file", file);

        const response = await fetch(`/personal/upload-profile`, {
            method: "POST",
            body: formData
        });

        if (!response.ok) throw response;
        return await response.text();
    };

    // 프사 삭제
    const deleteProfileImage = async () => {
        const response = await fetch(`/personal/delete-profile`, {
            method : "DELETE",
            headers: {
                "Content-Type": "application/json;charset-utf-8"
            }
        });

        if (!response.ok) throw response;
    }

    // 닉네임 변경
    const setNickname = async (nickname) => {
        const response = await fetch(`/personal/nickname`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `nickname=${encodeURIComponent(nickname)}`
        });
        if (!response.ok) throw response;
        return await response.text();
    };

    // 비밀번호 변경
    const setPassword = async (password) => {
        const response = await fetch(`/personal/password`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `password=${encodeURIComponent(password)}`
        });
        if (!response.ok) throw response;
        return await response.text();
    };

    return { setProfileImage : setProfileImage, deleteProfileImage : deleteProfileImage,
        setNickname : setNickname, setPassword : setPassword
    }
})();