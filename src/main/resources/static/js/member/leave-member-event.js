document.addEventListener("DOMContentLoaded", function () {
    const checkBox = document.querySelector("#check1");
    const continueButton = document.querySelector(".yellow");
    const cancelButton = document.querySelector(".gray");

    // 초기 상태: 비활성화
    continueButton.disabled = true;
    continueButton.classList.add("disabled");

    // 체크박스 상태 변경 시 버튼 활성화/비활성화
    checkBox.addEventListener("change", function () {
        if (this.checked) {
            continueButton.disabled = false;
            continueButton.classList.remove("disabled");
        } else {
            continueButton.disabled = true;
            continueButton.classList.add("disabled");
        }
    });

    // 나중에 하기 버튼 클릭 시 메인 페이지로 이동
    cancelButton.addEventListener("click", function () {
        window.location.href = "/main/main";
    });

    // 계속 진행하기 버튼 클릭 시 폼 제출
    continueButton.addEventListener("click", function () {
        if (checkBox.checked) {
            document.querySelector("form").submit();
        }
    });
});