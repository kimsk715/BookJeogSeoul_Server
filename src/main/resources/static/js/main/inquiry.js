const inquiryTypeSelect = document.querySelector(".mds-select--valued .inner");
const inquiryTypeOption = document.querySelector(".inquiry-type");

// inquiryTypeSelect.addEventListener('click',() =>{
//    inquiryTypeOption.removeAttribute("style")
// })

document.addEventListener("click", (e) => {
    let clicked = inquiryTypeSelect.contains(e.target);
    // console.log(clicked)
    // console.log(e.target)
    if (clicked) {
        inquiryTypeOption.removeAttribute("style");
    } else {
        inquiryTypeOption.style.display = "none";
    }
});

const inquiryTypeSpan = document.querySelector(".inquiry-type-span");
const optionTexts = document.querySelectorAll(".type-option");

optionTexts.forEach((text) => {
    text.addEventListener("click", (e) => {
        if (text.contains(e.target)) {
            let inquiryText = text.firstElementChild.innerText;
            inquiryTypeSpan.placeholder = inquiryText;
        }
    });
});

const fileRemoveButtons = document.querySelectorAll(".filelist button");
const fileList = document.querySelector(".filelist");

const fileInput = document.querySelector(".input-file input");
fileInput.addEventListener("change", (e) => {
    const [file] = e.target.files;
    const reader = new FileReader();
    reader.readAsDataURL(file);
    var newFile = document.createElement("li");
    console.log(file);
    const imageUrl = URL.createObjectURL(file);
    newFile.innerHTML = `<div ><img 
                                                    src="${imageUrl}">
                                                <button class="delete-img"></button></div>`;

    reader.addEventListener("load", (e) => {
        const path = e.target.result;
        if (path.includes("image")) {
            fileList.appendChild(newFile);
            if (fileList && fileList.querySelector("li")) {
                fileList.removeAttribute("style");
            } else {
                fileList.style.display = "none";
            }
        }
    });
});

document.addEventListener("click", (e) => {
    if (e.target && e.target.classList.contains("delete-img")) {
        e.target.closest("li").remove();
        if (!fileList.querySelector("li")) {
            fileList.style.display = "none";
        }
    }
});

const agreeButton = document.querySelector(".mds-check-box-view");
const agreeLabel = document.querySelector(".mds-check-box");
agreeButton.addEventListener("click", () => {
    agreeLabel.classList.toggle("mds-check-box--checked");
});

const confirmButton = document.querySelector(".confirm");

// 이메일 검증  문자 1 + @ + 문자 1 + . + 문자 2개

// 숫자 검증 숫자 10자리 이상

const titleArea = document.querySelector(".mds-input-field.title-input");
const contentArea = document.querySelector(".mds-textarea-field");
const emailArea = document.querySelector(".mds-input-field.email-input");
const phoneArea = document.querySelector(".mds-input-field.phone-input");
const emailWrapper = document.querySelector(".email-wrapper");
const phoneWrapper = document.querySelector(".phone-wrapper");
const agreeCheckBox = document.querySelector("#input-45");
let emailCert;
let phoneCert;
// 일단 js에서 버튼 활성화 여부를 위해 유효성 검사 해놓음 다만, 서버 쪽이랑 연동할 수도 있음.
//  placeholder에 계정 정보로부터 가져온 데이터?
document.addEventListener("input", () => {
    // console.log(titleArea.value)
    // console.log(contentArea.value)

    const emailregex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const phoneregex = /^\d{10,11}$/;
    emailCert = emailregex.test(emailArea.value);
    phoneCert = phoneregex.test(phoneArea.value);
    // console.log(emailCert)
    console.log(phoneCert);
    if (
        titleArea.value != "" &&
        contentArea.value != "" &&
        emailCert &&
        phoneCert &&
        agreeCheckBox.checked
    ) {
        confirmButton.removeAttribute("disabled");
    } else {
        confirmButton.disabled = "disabled";
    }
});

//
document.addEventListener("input", () => {
    if (emailCert) {
        emailWrapper.classList.remove("mds-input--default");
        emailWrapper.classList.remove("mds-input--error");
        emailWrapper.classList.add("mds-input--success");
    } else if (emailArea.value == "") {
        emailWrapper.classList.add("mds-input--default");
        emailWrapper.classList.remove("mds-input--error");
        emailWrapper.classList.remove("mds-input--success");
        confirmButton.disabled = "disabled";
    } else if (!emailCert && emailArea.value != null) {
        emailWrapper.classList.remove("mds-input--default");
        emailWrapper.classList.add("mds-input--error");
        emailWrapper.classList.remove("mds-input--success");
        confirmButton.disabled = "disabled";
    } else {
        emailWrapper.classList.add("mds-input--default");
        emailWrapper.classList.remove("mds-input--error");
        emailWrapper.classList.remove("mds-input--success");
        confirmButton.disabled = "disabled";
    }
});

// 전화번호 검증 여부에 따른 CSS 변화 이벤트
document.addEventListener("input", () => {
    if (phoneCert) {
        phoneWrapper.classList.remove("mds-input--default");
        phoneWrapper.classList.remove("mds-input--error");
        phoneWrapper.classList.add("mds-input--success");
    } else if (phoneArea.value == "") {
        phoneWrapper.classList.add("mds-input--default");
        phoneWrapper.classList.remove("mds-input--error");
        phoneWrapper.classList.remove("mds-input--success");
        confirmButton.disabled = "disabled";
    } else if (!phoneCert && phoneArea.value != null) {
        phoneWrapper.classList.remove("mds-input--default");
        phoneWrapper.classList.add("mds-input--error");
        phoneWrapper.classList.remove("mds-input--success");
        confirmButton.disabled = "disabled";
    } else {
        phoneWrapper.classList.add("mds-input--default");
        phoneWrapper.classList.remove("mds-input--error");
        phoneWrapper.classList.remove("mds-input--success");
        confirmButton.disabled = "disabled";
    }
});

const questionButton = document.querySelector("li.question");
const questionArea = document.querySelector("section.question");
const answerButton = document.querySelector("li.answer");
const answerArea = document.querySelector("section.answer");

questionButton.addEventListener("click", () => {
    questionArea.removeAttribute("style");
    questionButton.classList.add("active");
    answerArea.style.display = "none";
    answerButton.classList.remove("active");
});

answerButton.addEventListener("click", () => {
    answerArea.removeAttribute("style");
    answerButton.classList.add("active");
    questionArea.style.display = "none";
    questionButton.classList.remove("active");
});

const answerList = document.querySelector(".list-inner");
const defaultArea = document.querySelector(".empty-data");
document.addEventListener("DOMContentLoaded", () => {
    if (answerList.querySelector("li")) {
        answerList.removeAttribute("style");
        defaultArea.style.display = "none";
    }
});

document.addEventListener("DOMContentLoaded", () => {
    if (answerList.querySelector("li")) {
        const questionItems = document.querySelectorAll(".list-inner li");
        questionItems.forEach((question) => {
            question.addEventListener("click", () => {
                question.classList.toggle("active");
            });
        });
    }
});
