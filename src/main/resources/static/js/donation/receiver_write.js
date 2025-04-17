const contentTextarea = document.querySelectorAll(".mds-textarea-field");
// textarea가 focus 될 때, 감싸고 있는 label 태그가 focus와 유사한 효과를 내도록 하는 이벤트
// 위는 focus, 아래는 focus 해제
contentTextarea.forEach((textarea) => {
    textarea.addEventListener("focus", () => {
        textarea.parentElement.parentElement.parentElement.classList.add(
            "textarea-focus"
        );
    });
});

contentTextarea.forEach((textarea) => {
    document.addEventListener("click", (e) => {
        if (!textarea.contains(e.target)) {
            textarea.parentElement.parentElement.parentElement.classList.remove(
                "textarea-focus"
            );
        }
    });
});

const fileInput = document.querySelector(".input-file");
const addBox = document.querySelector(".add-box");
const fileAddButton = document.querySelector(".add-box button");
const newAddButton = document.querySelector(".add-button");
const fileArea = document.querySelector("div.images");
const fileList = fileArea.querySelector("ul");
fileAddButton.addEventListener("click", () => {
    fileInput.click();   
});

newAddButton.addEventListener("click", () => {
    fileInput.click();    
});

fileInput.addEventListener("change", (e) => {
    const [file] = e.target.files;
    const reader = new FileReader();
    reader.readAsDataURL(file);
    var newFile = document.createElement("li");
    console.log(file);
    const imageUrl = URL.createObjectURL(file);
    newFile.innerHTML = `<img src="${imageUrl}">
    <div class="mds-input memo mds-input--default mds-input--left"><label>
            <div class="inner"><!---->
                <div class="input"><!----> <input autocomplete="off" id="input-64" aria-labelledby="input-64-label"
                        aria-errormessage="input-64-message" type="text" placeholder="나만의 메모를 남겨보세요"
                        class="mds-input-field"></div> <!----> <button type="button" aria-label="내용 삭제"
                    class="mds-icon--input-delete clear-button" style="display: none;"></button> <!----> <!---->
            </div>
        </label>
        <div class="message-wrap"><!----> <span class="length">0 / 50</span></div> <!---->
    </div>
    <div class="buttons"><button type="button" class="new-add-button"><input type="file" accept=".jpg, .png, .jpeg, .heic" class="new-input-file">
            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                <g clip-path="url(#clip0_5831_19518)">
                    <rect x="2.5" y="3.5" width="11" height="9" rx="1.5" stroke="var(--text-01)"></rect>
                    <path
                        d="M4.7002 13.0003L8.81203 6.60709C9.16249 6.06219 9.93066 5.98928 10.3774 6.45852L13.7002 9.94887"
                        stroke="var(--text-01)"></path>
                    <rect x="3.7998" y="5.35254" width="2.4" height="2.35294" rx="1.17647" stroke="var(--text-01)">
                    </rect>
                </g>
                <defs>
                    <clipPath id="clip0_5831_19518">
                        <rect width="12" height="10" fill="white" transform="translate(2 3)"></rect>
                    </clipPath>
                </defs>
            </svg>
            이미지 변경
        </button> <button type="button" class="delete"><svg width="16" height="16" viewBox="0 0 16 16" fill="none"
                xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd"
                    d="M6.5 1C5.67157 1 5 1.67157 5 2.5V3H3.5H2.5C2.22386 3 2 3.22386 2 3.5C2 3.77614 2.22386 4 2.5 4H3V12.5C3 13.8807 4.11929 15 5.5 15H10.5C11.8807 15 13 13.8807 13 12.5V4H13.5C13.7761 4 14 3.77614 14 3.5C14 3.22386 13.7761 3 13.5 3H12.5H11V2.5C11 1.67157 10.3284 1 9.5 1H6.5ZM10 3H6V2.5C6 2.22386 6.22386 2 6.5 2H9.5C9.77614 2 10 2.22386 10 2.5V3ZM4 12.5V4H5.5H10.5H12V12.5C12 13.3284 11.3284 14 10.5 14H5.5C4.67157 14 4 13.3284 4 12.5ZM6.5 6C6.5 5.72386 6.27614 5.5 6 5.5C5.72386 5.5 5.5 5.72386 5.5 6V12C5.5 12.2761 5.72386 12.5 6 12.5C6.27614 12.5 6.5 12.2761 6.5 12V6ZM8 5.5C8.27614 5.5 8.5 5.72386 8.5 6V12C8.5 12.2761 8.27614 12.5 8 12.5C7.72386 12.5 7.5 12.2761 7.5 12V6C7.5 5.72386 7.72386 5.5 8 5.5ZM10.5 6C10.5 5.72386 10.2761 5.5 10 5.5C9.72386 5.5 9.5 5.72386 9.5 6V12C9.5 12.2761 9.72386 12.5 10 12.5C10.2761 12.5 10.5 12.2761 10.5 12V6Z"
                    fill="var(--danger)"></path>
            </svg>
            삭제하기
        </button></div>
`;
    newFile.classList.add("item");
    // console.log(newFile)

    reader.addEventListener("load", (e) => {
        const path = e.target.result;
        if (path.includes("image")) {
            fileList.appendChild(newFile);
            if (fileList && fileList.querySelector("li")) {
                fileArea.removeAttribute("style");
                addBox.style.display = "none";
            } else {
                fileArea.style.display = "none";
                addBox.removeAttribute("style");
            }
        }
        imageCount.innerText = fileList.childElementCount
    });
});

const deleteButton = document.querySelectorAll(".delete");
document.addEventListener("click", (e) => {
    if (e.target && e.target.classList.contains("delete")) {
        const thumbnail = e.target.closest("li");
        if (thumbnail) {
            thumbnail.remove();
            if (!fileList.querySelector("li")) {
                addBox.removeAttribute("style");
                fileArea.style.display = "none";
            }
        }
    }
});

const imageContainer = document.querySelector("div.images"); // 부모를 잡자

imageContainer.addEventListener("click", (e) => {
    // 클릭된게 new-add-button이면
    if (e.target.classList.contains("new-add-button")) {
        const input = e.target.querySelector(".new-input-file"); // 자식 input을 찾고
        if (input) {
            input.click(); // input 클릭 트리거!
        }
    }
});

// 그리고 change 이벤트는 그대로
imageContainer.addEventListener("change", (e) => {
    if (e.target.classList.contains("new-input-file")) {
        const [file] = e.target.files;
        if (!file) return;

        const reader = new FileReader();
        reader.readAsDataURL(file);

        var newFile = document.createElement("li");
        const imageUrl = URL.createObjectURL(file);

        newFile.innerHTML = `<img src="${imageUrl}">
    <div class="mds-input memo mds-input--default mds-input--left"><label>
            <div class="inner"><!---->
                <div class="input"><!----> <input autocomplete="off" id="input-64" aria-labelledby="input-64-label"
                        aria-errormessage="input-64-message" type="text" placeholder="나만의 메모를 남겨보세요"
                        class="mds-input-field"></div> <!----> <button type="button" aria-label="내용 삭제"
                    class="mds-icon--input-delete clear-button" style="display: none;"></button> <!----> <!---->
            </div>
        </label>
        <div class="message-wrap"><!----> <span class="length">0 / 50</span></div> <!---->
    </div>
    <div class="buttons"><button type="button" class="new-add-button"><input type="file" accept=".jpg, .png, .jpeg, .heic" class="new-input-file">
            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                <g clip-path="url(#clip0_5831_19518)">
                    <rect x="2.5" y="3.5" width="11" height="9" rx="1.5" stroke="var(--text-01)"></rect>
                    <path
                        d="M4.7002 13.0003L8.81203 6.60709C9.16249 6.06219 9.93066 5.98928 10.3774 6.45852L13.7002 9.94887"
                        stroke="var(--text-01)"></path>
                    <rect x="3.7998" y="5.35254" width="2.4" height="2.35294" rx="1.17647" stroke="var(--text-01)">
                    </rect>
                </g>
                <defs>
                    <clipPath id="clip0_5831_19518">
                        <rect width="12" height="10" fill="white" transform="translate(2 3)"></rect>
                    </clipPath>
                </defs>
            </svg>
            이미지 변경
        </button> <button type="button" class="delete"><svg width="16" height="16" viewBox="0 0 16 16" fill="none"
                xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd"
                    d="M6.5 1C5.67157 1 5 1.67157 5 2.5V3H3.5H2.5C2.22386 3 2 3.22386 2 3.5C2 3.77614 2.22386 4 2.5 4H3V12.5C3 13.8807 4.11929 15 5.5 15H10.5C11.8807 15 13 13.8807 13 12.5V4H13.5C13.7761 4 14 3.77614 14 3.5C14 3.22386 13.7761 3 13.5 3H12.5H11V2.5C11 1.67157 10.3284 1 9.5 1H6.5ZM10 3H6V2.5C6 2.22386 6.22386 2 6.5 2H9.5C9.77614 2 10 2.22386 10 2.5V3ZM4 12.5V4H5.5H10.5H12V12.5C12 13.3284 11.3284 14 10.5 14H5.5C4.67157 14 4 13.3284 4 12.5ZM6.5 6C6.5 5.72386 6.27614 5.5 6 5.5C5.72386 5.5 5.5 5.72386 5.5 6V12C5.5 12.2761 5.72386 12.5 6 12.5C6.27614 12.5 6.5 12.2761 6.5 12V6ZM8 5.5C8.27614 5.5 8.5 5.72386 8.5 6V12C8.5 12.2761 8.27614 12.5 8 12.5C7.72386 12.5 7.5 12.2761 7.5 12V6C7.5 5.72386 7.72386 5.5 8 5.5ZM10.5 6C10.5 5.72386 10.2761 5.5 10 5.5C9.72386 5.5 9.5 5.72386 9.5 6V12C9.5 12.2761 9.72386 12.5 10 12.5C10.2761 12.5 10.5 12.2761 10.5 12V6Z"
                    fill="var(--danger)"></path>
            </svg>
            삭제하기
        </button></div>
`;

        newFile.classList.add("item");

        reader.addEventListener("load", (e2) => {
            const path = e2.target.result;
            const changedFile = e.target.closest("li");

            if (path.includes("image")) {
                changedFile.replaceWith(newFile);

                if (fileList && fileList.querySelector("li")) {
                    fileArea.removeAttribute("style");
                    addBox.style.display = "none";
                } else {
                    fileArea.style.display = "none";
                    addBox.removeAttribute("style");
                }
            }
            imageCount.innerText = fileList.childElementCount
        });
    }
});

const titleArea = document.querySelector("#textarea-title");
const contentArea = document.querySelector("#textarea-content");

titleArea.addEventListener("input", () => {
    let titleLength = titleArea.value.length;
    document.querySelector(".title-length").innerText = `${titleLength} / 100`;
    if (titleLength > 99) {
        titleArea.value = titleArea.value.slice(0, 99);
        document.querySelector(".title-length").innerText = `100 / 100`;
    }
});

contentArea.addEventListener("input", () => {
    let contentLength = contentArea.value.length;
    document.querySelector(
        ".content-length"
    ).innerText = `${contentLength} / 2,000`;
    if (contentLength > 1999) {
        contentArea.value = contentArea.value.slice(0, 1999);
        document.querySelector(".content-length").innerText = `2,000 / 2,000`;
    }
});

const alertModal = document.querySelector(".unload-popup"); // 뒤로 가기 누를 시 나오는 모달
const backModalOpen = document.querySelector(".back-btn"); // 뒤로 가기 버튼
const closeAlertModal = document.querySelector(".close"); // 모달창의 취소 버튼
const applyButton = document.querySelector(".apply") // 모달창의 확인 버튼
backModalOpen.addEventListener("click",() =>{
    alertModal.removeAttribute("style")
})

closeAlertModal.addEventListener("click", () =>{
    alertModal.style.display = "none";
})

// 확인 버튼은 나중에 서버 때 연결. 선언만.