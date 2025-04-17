// 토스트 기능(잠깐 나타났다가 사라지는 창)
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;

    document.querySelector("#wrap").appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 2500); // 2.5초 후 자동 제거
}

// 카카오회원이면 .kakao 클래스 추가(카카오 아이콘)
const signUpMark = document.querySelector(".sign-up-mark");

// 프사 변경, 삭제
const imageBox = document.querySelector(".image-box");
const fileInput = document.querySelector("#file-input");
const removeButton = document.querySelector(".picture-remove-button");

const defaultImage = "../../static/images/post/user_profile_example.png";

// 프사 변경
fileInput.addEventListener("change", (e) => {
    const file = e.target.files[0];
    const maxSize = 3 * 1024 * 1024; // 3MB

    if (file && file.size > maxSize) {
        showToast("3MB 이하의 이미지만 업로드할 수 있습니다.");
        fileInput.value = ""; // 파일 초기화
        return;
    }

    if (file) {
        const reader = new FileReader();
        reader.onload = function (event) {
            imageBox.style.backgroundImage = `url('${event.target.result}')`;
        };
        reader.readAsDataURL(file);
    }
});

// 프사 초기화 (기본 이미지로)
removeButton.addEventListener("click", () => {
    imageBox.style.backgroundImage = `url('${defaultImage}')`;
    fileInput.value = ""; // input 초기화
});

// 필명 변경 토스뜨
const nameChangeBtn = document.querySelector(".nickname button");
const nameChangeInput = document.querySelector(".nickname .input-text");
const nameInputBox = document.querySelector(".nickname .input-box");

nameChangeBtn.addEventListener("click", (e) => {
    if (nameChangeInput.value.trim() !== "") {
        showToast("변경 되었습니다.");
        nameInputBox.classList.remove("input-error");
    } else {
        showToast("단체명을 입력해주세요.");
        nameInputBox.classList.add("input-error");
    }
});

// 아이디 변경
const emailChangeBtn = document.querySelector(".email button");
const emailChangeInput = document.querySelector(".email .input-text");
const emailInputBox = document.querySelector(".nickname .input-box");

emailChangeBtn.addEventListener("click", (e) => {
    const email = emailChangeInput.value;
    if (emailChangeInput.value.trim() !== "") {
        showToast("변경 되었습니다.");
        emailInputBox.classList.remove("input-error");
    } else if (emailChangeInput.value.trim() === "") {
        showToast("아이디를 입력해주세요.");
        emailInputBox.classList.add("input-error");
    } else {
        showToast("아이디 변경 오류 발생");
        emailInputBox.classList.add("input-error");
    }
});

// 이메일 유효성검사 함수
function validateEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return emailPattern.test(email);
}

// 비밀번호 변경하기 버튼 클릭 시
const changeBtn = document.querySelector(".input-group.password>button");
const currentPasswordWrapper = document
    .querySelector("#input-currentPassword")
    .closest(".input-item");
const currentPasswordInput = document.querySelector("#input-currentPassword");
const newPasswordInput = document.querySelector("#input-password");
const validationWrap = document.querySelector(".validation-wrap");

// 숨겨져있던 현재 비밀번호 입력창 출현
changeBtn.addEventListener("click", () => {
    currentPasswordWrapper.style.display = "block";
    newPasswordInput.disabled = false;
    newPasswordInput.placeholder = "새 비밀번호 입력";
    changeBtn.disabled = true;
    changeBtn.innerText = "저장하기";
    validationWrap.style.display = "block";
});

// 입력값에 따라 비번 숨기기 아이콘 보이기/숨기기
function setupPasswordToggle(inputId) {
    const input = document.querySelector(`#${inputId}`);
    const parentBox = input.closest(".input-box"); // 안전하게 감싸는 div 찾기
    const button = parentBox.querySelector(".pwSecretBtn"); // 정확히 버튼 찾기
    const icon = button.querySelector("i");

    input.addEventListener("input", () => {
        button.style.display =
            input.value.trim() !== "" ? "inline-block" : "none";
    });

    button.addEventListener("click", () => {
        const isHidden = input.type === "password";
        input.type = isHidden ? "text" : "password";
        icon.classList.toggle("icon-eye_off", !isHidden);
        icon.classList.toggle("icon-eye_on", isHidden);
    });
}

// 두 개 input 모두 적용
setupPasswordToggle("input-currentPassword");
setupPasswordToggle("input-password");

// 비번 유효성 검사, 항목별로
const passwordInput = document.querySelector("#input-password");
const checklistItems = document.querySelectorAll(".validation-wrap li");

passwordInput.addEventListener("input", () => {
    const val = passwordInput.value;

    const hasLetter = /[a-zA-Z]/.test(val); // 영문 대/소문자
    const hasNumber = /\d/.test(val); // 숫자
    const hasSpecial = /[!@#$%^&*(),.?":{}|<>]/.test(val); // 특수문자
    const isValidLength = val.length >= 8 && val.length <= 20;

    // 각 항목별 검사
    checklistItems[0].classList.toggle("active", hasLetter);
    checklistItems[1].classList.toggle("active", hasNumber);
    checklistItems[2].classList.toggle("active", hasSpecial);
    checklistItems[3].classList.toggle("active", isValidLength);

    // 비밀번호 검사가 모두 통과했을 때 changeBtn 활성화
    if (hasLetter && hasNumber && hasSpecial && isValidLength) {
        changeBtn.disabled = false; // 활성화
    } else {
        changeBtn.disabled = true; // 비활성화
    }
});

// 통과된 저장하기 버튼을 누르면 비밀번호 변경 안내
changeBtn.addEventListener("click", (e) => {
    if (passwordInput.value.trim() !== "") {
        // 비밀번호 변경 로직 처리
        showToast("비밀번호가 변경되었습니다."); // 예시로 알림창을 표시

        // 여기서 비밀번호 변경 요청을 서버로 보내거나, 필요한 작업을 처리.

        // 비밀번호 변경 후 버튼 비활성화 (원래 상태로 돌아가기)
        changeBtn.disabled = true;
        changeBtn.innerText = "변경 완료";
    }
});
