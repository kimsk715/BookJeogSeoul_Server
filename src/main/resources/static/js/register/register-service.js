const emailcheckService = (() => {
    const getTrueFalse = async (memberEmail, callback) => {

            const response = await fetch(`/personal/check-email?memberEmail=${memberEmail}`, {
                method: 'POST',
            })
            console.log(response);

            const result = await response.json().catch(() => null)

            const isExist = result !== null;  // result가 null이면 이메일없음

            if (callback)
                callback(result);

    }

    const joinMember = async (personalMember, callback) => {
        const response = await fetch('personal/')
    }
    return {getTrueFalse:getTrueFalse}
})();