// 독후감 제목 input에 입력하면 글자수가 갱신됨
const titleInput = document.querySelector("input#textarea-title");
const titleCount = document.querySelector(".post-title .message-wrap>span");

titleInput.addEventListener("input", (e) => {
    titleCount.innerText = `${e.target.value.length}` + " / 40";
});

// 독후감 내용 input에 입력하면 글자수 갱신
const contentInput = document.querySelector("textarea#textarea-content");
const contentCount = document.querySelector(".post-content .message-wrap>span");

contentInput.addEventListener("input", (e) => {
    contentCount.innerText = `${e.target.value.length}` + " / 2000";
});

const imageInputButton = document.querySelector(".add-box > button");
const imageInput = document.querySelector(".input-file");
const noImageBox = document.querySelector(".add-box");
const imagesBox = document.querySelector(".sentence > .images");
const previewList = document.querySelector(".images ul"); // 이미지 리스트 영역
const imageCountText = document.querySelector(".image-count"); // 이미지 개수 표시용 요소 (있다면)
const imageAddButton = document.querySelector(".images .add-button");

// 파일 추가 버튼 클릭 시 input[type=file]을 강제로 열어줌
imageInputButton.addEventListener("click", () => {
    imageInput.click();
});

imageAddButton.addEventListener("click", () => {
    imageInput.click();
});

// 최대 5mb의 파일까지만 올라가게 용량 제한
const maxFileSize = 5 * 1024 * 1024; // 5MB (byte 단위)

// 파일 input 값이 변경되었을 때 실행됨
imageInput.addEventListener("change", (e) => {
    const allowedExtensions = ["jpg", "jpeg", "png", "webp"];
    const files = Array.from(imageInput.files);
    const validFiles = [];
    const currentCount = previewList.querySelectorAll("li.item").length;

    for (const file of files) {
        const ext = file.name.split(".").pop().toLowerCase();

        // 지정한 이미지 형식이 아니면
        if (!allowedExtensions.includes(ext)) {
            alert(`.` + ext + `은(는) 지원하지 않는 형식입니다.`);
            continue;
        }

        // 이미지가 최대 용량을 넘어가면
        if (file.size > maxFileSize) {
            alert("이미지 크기는 5MB 이하만 업로드할 수 있습니다.");
            continue;
        }

        // 이미지 개수가 10개를 초과하면
        if (currentCount + validFiles.length >= 10) {
            alert("이미지는 최대 10개까지 등록할 수 있습니다.");
            break;
        }

        validFiles.push(file);
        document.querySelector(".count > strong").innerText = `${
            currentCount + validFiles.length
        }`;
    }

    // 유효한 파일이 없으면 아무것도 하지 않음
    if (validFiles.length === 0) {
        imageInput.value = ""; // 초기화
        return;
    }

    // 이미지가 하나라도 있다면 영역 전환
    noImageBox.style.display = "none";
    imagesBox.style.display = "block";

    // 유효한 파일들을 이미지 리스트로 추가
    validFiles.forEach((file) => {
        const imageUrl = URL.createObjectURL(file);

        const li = document.createElement("li");
        li.className = "item";
        li.innerHTML = `
        <img src="${imageUrl}">
        <div class="mds-input memo">
            <label>
                <div class="inner">
                    <div class="input">
                        <input type="text" autocomplete="off" placeholder="나만의 메모를 남겨보세요" class="mds-input-field" maxlength="50" />
                    </div>
                    <button type="button" class="mds-icon-input-delete clear-button" style="display: none;"></button>
                </div>
            </label>
            <div class="message-wrap">
                <span class="length">0 / 50</span>
            </div>
        </div>
        <div class="buttons">
            <button type="button" class="change-image">
                <input type="file" accept=".jpg, .jpeg, .png" class="input-file" style="display: none;" />
                <svg data-v-a8c352e6="" width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg"><g data-v-a8c352e6="" clip-path="url(#clip0_5831_19518)"><rect data-v-a8c352e6="" x="2.5" y="3.5" width="11" height="9" rx="1.5" stroke="var(--text-01)"></rect> <path data-v-a8c352e6="" d="M4.7002 13.0003L8.81203 6.60709C9.16249 6.06219 9.93066 5.98928 10.3774 6.45852L13.7002 9.94887" stroke="var(--text-01)"></path> <rect data-v-a8c352e6="" x="3.7998" y="5.35254" width="2.4" height="2.35294" rx="1.17647" stroke="var(--text-01)"></rect></g> <defs data-v-a8c352e6=""><clipPath data-v-a8c352e6="" id="clip0_5831_19518"><rect data-v-a8c352e6="" width="12" height="10" fill="white" transform="translate(2 3)"></rect></clipPath></defs></svg>
                이미지 변경
            </button>
            <button type="button" class="delete">
                <svg data-v-a8c352e6="" width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg"><path data-v-a8c352e6="" fill-rule="evenodd" clip-rule="evenodd" d="M6.5 1C5.67157 1 5 1.67157 5 2.5V3H3.5H2.5C2.22386 3 2 3.22386 2 3.5C2 3.77614 2.22386 4 2.5 4H3V12.5C3 13.8807 4.11929 15 5.5 15H10.5C11.8807 15 13 13.8807 13 12.5V4H13.5C13.7761 4 14 3.77614 14 3.5C14 3.22386 13.7761 3 13.5 3H12.5H11V2.5C11 1.67157 10.3284 1 9.5 1H6.5ZM10 3H6V2.5C6 2.22386 6.22386 2 6.5 2H9.5C9.77614 2 10 2.22386 10 2.5V3ZM4 12.5V4H5.5H10.5H12V12.5C12 13.3284 11.3284 14 10.5 14H5.5C4.67157 14 4 13.3284 4 12.5ZM6.5 6C6.5 5.72386 6.27614 5.5 6 5.5C5.72386 5.5 5.5 5.72386 5.5 6V12C5.5 12.2761 5.72386 12.5 6 12.5C6.27614 12.5 6.5 12.2761 6.5 12V6ZM8 5.5C8.27614 5.5 8.5 5.72386 8.5 6V12C8.5 12.2761 8.27614 12.5 8 12.5C7.72386 12.5 7.5 12.2761 7.5 12V6C7.5 5.72386 7.72386 5.5 8 5.5ZM10.5 6C10.5 5.72386 10.2761 5.5 10 5.5C9.72386 5.5 9.5 5.72386 9.5 6V12C9.5 12.2761 9.72386 12.5 10 12.5C10.2761 12.5 10.5 12.2761 10.5 12V6Z" fill="var(--danger)"></path></svg>
                삭제하기
            </button>
        </div>
    `;

        // 이미지 안의 요소들
        const img = li.querySelector("img");
        const inputFile = li.querySelector(".input-file");
        const changeBtn = li.querySelector(".change-image");
        const deleteBtn = li.querySelector(".delete");
        const memoInput = li.querySelector(".mds-input-field");
        const clearBtn = li.querySelector(".clear-button");
        const lengthSpan = li.querySelector(".length");

        // 이미지 변경
        changeBtn.addEventListener("click", () => inputFile.click());
        inputFile.addEventListener("change", () => {
            const file = inputFile.files[0];
            if (!file) return;

            const ext = file.name.split(".").pop().toLowerCase(); // 확장자명
            const allowed = ["jpg", "jpeg", "png"]; // 허용된 파일형식
            if (!allowed.includes(ext)) {
                alert("지원하지 않는 이미지 형식입니다.");
                return;
            }

            const newUrl = URL.createObjectURL(file);
            img.src = newUrl;
        });

        // 삭제 버튼
        deleteBtn.addEventListener("click", () => {
            li.remove();

            // 이미지가 모두 삭제되었는지 확인
            const remainingItems = previewList.querySelectorAll(".item");

            // 개수 다시 업데이트
            document.querySelector(
                ".count > strong"
            ).innerText = `${remainingItems.length}`;

            if (remainingItems.length === 0) {
                // 이미지 0개일 때 창 바꾸기
                noImageBox.style.display = "flex";
                imagesBox.style.display = "none";
            }
        });

        // 메모 입력 시 길이 표시 & 클리어 버튼
        memoInput.addEventListener("input", () => {
            lengthSpan.textContent = `${memoInput.value.length} / 50`;
            clearBtn.style.display =
                memoInput.value.length > 0 ? "flex" : "none";
        });

        clearBtn.addEventListener("click", () => {
            memoInput.value = "";
            lengthSpan.textContent = "0 / 50";
            clearBtn.style.display = "none";
        });

        // 리스트에 추가
        previewList.appendChild(li);
    });

    // 이미지 개수 텍스트가 있다면 갱신
    if (imageCountText) {
        imageCountText.innerText =
            previewList.querySelectorAll("li.item").length;
    }

    // input 초기화 (동일 파일 다시 선택 가능)
    imageInput.value = "";
});

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

// 발행 버튼을 누르면 숨겨진 설정창을 보여줌
const publishButton = document.querySelector("button.publish-btn");
const settingModal = document.querySelector(".post-settings.on");
const settingModalInner = settingModal.querySelector(".setting-modal");

publishButton.addEventListener("click", (e) => {
    e.stopPropagation(); // 버튼을 눌러도 밑의 window 이벤트가 발동 안 되게
    if (settingModal.style.display === "none") {
        settingModal.style.display = "block";
    }
});

// 발행 설정 모달창 밖을 누르면 꺼짐
window.addEventListener("click", (e) => {
    if (
        !settingModalInner.contains(e.target) &&
        settingModal.style.display !== "none"
    ) {
        settingModal.style.display = "none";
    }
});

// 설정의 물음표 아이콘을 누르면 정보가 뜸
const postQuestion = document.querySelector(".setting-option i.question");
const tooltip = document.querySelector("#tooltip");

postQuestion.addEventListener("click", (e) => {
    tooltip.style.display = tooltip.style.display !== "none" ? "none" : "block";
});

// 포스트 공개 버튼을 눌러서 껐다 킴
const postPublicButton = document.querySelector("label.mds-switch");
const checkbox = document.querySelector("input#publicInput");

postPublicButton.addEventListener("click", (e) => {
    const label = e.currentTarget;

    // 토글 상태 반영
    setTimeout(() => {
        if (checkbox.checked) {
            label.classList.add("checked");
            checkbox.value = "true";
        } else {
            label.classList.remove("checked");
            checkbox.value = "false";
        }
    }, 0);
});

// 수정하기 버튼을 누르면 input에 입력이 다 되었는지 검사
const confirmButton = document.querySelector("button.post-btn");

confirmButton.addEventListener("click", (e) => {
    const title = document.querySelector("#textarea-title").value.trim();
    const content = document.querySelector("#textarea-content").value.trim();

    // 하나라도 입력이 덜 되어있으면
    if (!title || !content) {
        console.log("입력 덜 됨");
        showToast("제목, 내용을 모두 입력해주세요.");
        e.preventDefault(); // form 제출 방지
        return;
    }
    // 입력을 다 하고 누르면
    console.log("통과");
});
