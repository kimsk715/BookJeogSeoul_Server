document.addEventListener("DOMContentLoaded", async () => {
// 제목/내용 글자 수
    const titleInput = document.querySelector("input#textarea-title");
    const titleCount = document.querySelector(".post-title .message-wrap>span");
    titleInput.addEventListener("input", (e) => {
        titleCount.innerText = `${e.target.value.length} / 40`;
    });

    const contentInput = document.querySelector("textarea#textarea-content");
    const contentCount = document.querySelector(".post-content .message-wrap>span");
    contentInput.addEventListener("input", (e) => {
        contentCount.innerText = `${e.target.value.length} / 2000`;
    });

    // 도서 정보 표시 (ISBN으로 fetch)
    const book = await postUpdateService.getBookInfo();

    const titleElementTop = document.querySelector("p.title.book-name .book-title");
    const titleElementBottom = document.querySelector(".book-container p.book-name");
    const produceElement = document.querySelector(".book-container .book-produce");
    const infoElement = document.querySelector(".book-container .book-info");
    const coverImgElement = document.querySelector(".book-container img");
    const bookContainer = document.querySelector(".book-container");

    titleElementTop.innerText = book.title;
    titleElementBottom.innerText = book.title;
    produceElement.innerText = `${book.author} 지음 · ${book.publisher}`;
    infoElement.innerText = `${book.pubDate}` || `출간일 정보없음`;
    coverImgElement.src = book.cover;
    bookContainer.style.setProperty("--background-image", `url('${book.cover}')`);

    // 출품 상태 표시
    if (document.getElementById("hiddenSelectedInput")?.value === "PUBLIC") {
        document.querySelector("li.selectedCheck").style.display = "flex";
    }

    // 이미지가 존재하면 이미지 영역 표시
    const fileListLength = document.querySelectorAll("#file-preview-list li").length;
    if (fileListLength > 0) {
        document.querySelector(".sentence .add-box").style.display = "none";
        document.querySelector(".sentence .images").style.display = "block";
        document.querySelector(".sentence .count > strong").innerText = fileListLength;
    }

    // 삭제된 이미지 ID 저장용 hidden input
    const deletedIds = [];
    const deleteInput = document.createElement("input");
    deleteInput.type = "hidden";
    deleteInput.name = "deleteFileIds";
    deleteInput.id = "deletedFileIds";
    document.querySelector("form").appendChild(deleteInput);

    // 기존 이미지 수정, 삭제 버튼
    document.querySelectorAll("#file-preview-list li.item").forEach((li, index) => {
        const fileId = li.dataset.fileId;
        const deleteButton = li.querySelector("button.delete");
        const memoInput = li.querySelector(".mds-input-field");
        const clearBtn = li.querySelector(".clear-button");
        const fileInput = li.querySelector("input[type='file']");
        const changeBtn = li.querySelector(".change-image");
        const lengthSpan = li.querySelector(".length");
        const changeInput = li.querySelector(".change-image input");
        const img = li.querySelector("img");

        // 인덱스 기반 name 설정
        memoInput.name = `fileList[${index}].fileText`;
        fileInput.name = `fileList[${index}].multipartFile`;


        // 기존 파일 ID hidden input 추가
        if (fileId) {
            const idInput = document.createElement("input");
            idInput.type = "hidden";
            idInput.name = `fileList[${index}].id`;
            idInput.value = fileId;
            li.appendChild(idInput);
        }

        // 삭제 버튼
        deleteButton.addEventListener("click", () => {
            if (fileId) {
                deletedIds.push(fileId);
                deleteInput.value = deletedIds.join(",");
            }

            // ✅ form 요소 제거 보장
            li.querySelectorAll("input").forEach(input => input.remove());
            li.remove();

            const remaining = document.querySelectorAll("#file-preview-list li.item").length;
            document.querySelector(".sentence .count > strong").innerText = `${remaining}`;
            if (remaining === 0) {
                document.querySelector(".add-box").style.display = "flex";
                document.querySelector(".images").style.display = "none";
            }
        });

        // 이미지 변경 버튼
        changeBtn?.addEventListener("click", () => {
            changeInput?.click();
        });

        // 이미지가 변경되었을 때
        changeInput?.addEventListener("change", () => {
            const newFile = changeInput.files[0];
            if (!newFile) return;

            // ✅ 삭제 목록에 추가하지 않음 (기존 파일 유지)

            // 이미지 미리보기 교체
            const newUrl = URL.createObjectURL(newFile);
            img.src = newUrl;

            // input[type=file] 새로 만들고 기존 input 교체
            const newInput = document.createElement("input");
            newInput.type = "file";
            newInput.accept = ".jpg, .jpeg, .png";
            newInput.classList.add("input-file");
            newInput.name = fileInput.name;

            const dt = new DataTransfer();
            dt.items.add(newFile);
            newInput.files = dt.files;

            fileInput.replaceWith(newInput);
        });

        // 이미지 추가버튼
        document.querySelector(".add-button").addEventListener("click", () => {
            const previewList = document.getElementById("file-preview-list");
            const index = previewList.querySelectorAll("li.item").length;

            const fileInput = document.createElement("input");
            fileInput.type = "file";
            fileInput.accept = ".jpg,.jpeg,.png";
            fileInput.style.display = "none";
            fileInput.classList.add("input-file");

            document.body.appendChild(fileInput); // 임시로 붙여야 click 작동

            fileInput.addEventListener("change", () => {
                const newFile = fileInput.files[0];
                if (!newFile) {
                    fileInput.remove(); // 선택 안 하면 제거
                    return;
                }

                const li = document.createElement("li");
                li.classList.add("item");

                // 이미지
                const img = document.createElement("img");
                img.src = URL.createObjectURL(newFile);
                img.alt = "첨부 이미지";
                li.appendChild(img);

                // 📌 .mds-input.memo 구조
                const memoDiv = document.createElement("div");
                memoDiv.classList.add("mds-input", "memo");

                const label = document.createElement("label");
                const inner = document.createElement("div");
                inner.classList.add("inner");

                const inputDiv = document.createElement("div");
                inputDiv.classList.add("input");

                const memoInput = document.createElement("input");
                memoInput.type = "text";
                memoInput.classList.add("mds-input-field");
                memoInput.name = `fileList[${index}].fileText`;
                memoInput.placeholder = "나만의 메모를 남겨보세요";
                memoInput.maxLength = 50;
                memoInput.autocomplete = "off";

                const idInput = document.createElement("input");
                idInput.type = "hidden";
                idInput.name = `fileList[${index}].id`;
                idInput.value = ""; // 새 파일이므로 없음

                inputDiv.appendChild(memoInput);
                inputDiv.appendChild(idInput);

                const clearBtn = document.createElement("button");
                clearBtn.type = "button";
                clearBtn.classList.add("mds-icon-input-delete", "clear-button");
                clearBtn.style.display = "none";

                inner.appendChild(inputDiv);
                inner.appendChild(clearBtn);
                label.appendChild(inner);
                memoDiv.appendChild(label);

                const messageWrap = document.createElement("div");
                messageWrap.classList.add("message-wrap");
                const lengthSpan = document.createElement("span");
                lengthSpan.classList.add("length");
                lengthSpan.textContent = "0 / 50";
                messageWrap.appendChild(lengthSpan);

                memoDiv.appendChild(messageWrap);
                li.appendChild(memoDiv);

                // 📌 버튼 영역
                const buttonsDiv = document.createElement("div");
                buttonsDiv.classList.add("buttons");

                const changeBtn = document.createElement("button");
                changeBtn.type = "button";
                changeBtn.classList.add("change-image");
                changeBtn.innerHTML = `이미지 변경`;

                fileInput.name = `fileList[${index}].multipartFile`;
                changeBtn.appendChild(fileInput);

                const deleteBtn = document.createElement("button");
                deleteBtn.type = "button";
                deleteBtn.classList.add("delete");
                deleteBtn.innerHTML = `삭제하기`;

                deleteBtn.addEventListener("click", () => {
                    li.remove();
                    const remaining = previewList.querySelectorAll("li.item").length;
                    document.querySelector(".sentence .count > strong").innerText = `${remaining}`;
                    if (remaining === 0) {
                        document.querySelector(".add-box").style.display = "flex";
                        document.querySelector(".images").style.display = "none";
                    }
                });

                buttonsDiv.appendChild(changeBtn);
                buttonsDiv.appendChild(deleteBtn);
                li.appendChild(buttonsDiv);

                // 이벤트 처리
                memoInput.addEventListener("input", () => {
                    lengthSpan.textContent = `${memoInput.value.length} / 50`;
                    clearBtn.style.display = memoInput.value.length > 0 ? "flex" : "none";
                });

                clearBtn.addEventListener("click", () => {
                    memoInput.value = "";
                    lengthSpan.textContent = "0 / 50";
                    clearBtn.style.display = "none";
                });

                // DOM에 추가
                previewList.appendChild(li);
                document.querySelector(".add-box").style.display = "none";
                document.querySelector(".images").style.display = "block";
                document.querySelector(".sentence .count > strong").innerText = previewList.querySelectorAll("li.item").length;
            });

            // 🔥 클릭으로 파일 선택창 띄우기
            fileInput.click();
        });


// 초기 상태 설정
        const postPublicButton = document.querySelector("label.mds-switch.public");
        const postSubmitButton = document.querySelector("label.mds-switch.submit");

        const publicInput = document.getElementById("publicInput");         // 공개 여부 체크박스
        const selectedInput = document.getElementById("selectedInput");     // 출품 여부 체크박스

        const publicHidden = document.getElementById("publicHidden");
        const selectedHidden = document.getElementById("selectedHidden");

        const selectedCheck = document.querySelector(".selectedCheck");

        // 초기 상태 설정
        const hasSelectedStatus = selectedHidden.value?.trim() !== "";

        if (hasSelectedStatus) {
            selectedCheck.style.display = "flex";
        } else {
            selectedCheck.style.display = "none";
        }

        console.log("▶️ 초기 hidden 값:", {
            public: publicHidden.value,
            selected: selectedHidden.value
        });

        const isPublic = publicHidden.value === "PUBLIC";
        publicInput.checked = isPublic;
        postPublicButton.classList.toggle("checked", isPublic);

        const isSelected = selectedHidden.value === "PUBLIC";
        selectedInput.checked = isSelected;
        postSubmitButton.classList.toggle("checked", isSelected);

        // 공개 여부 토글
        postPublicButton?.addEventListener("click", () => {
            setTimeout(() => {
                const isPublic = publicInput.checked;
                publicHidden.value = isPublic ? "PUBLIC" : "PRIVATE";
                postPublicButton.classList.toggle("checked", isPublic);

                if (!isPublic) {
                    selectedInput.checked = false;
                    selectedHidden.value = "PRIVATE";
                    postSubmitButton?.classList.remove("checked");
                }
            }, 0);
        });

        // 출품 여부 토글
        postSubmitButton?.addEventListener("click", () => {
            setTimeout(() => {
                if (publicHidden.value !== "PUBLIC") {
                    selectedInput.checked = false;
                    selectedHidden.value = "PRIVATE";
                    postSubmitButton.classList.remove("checked");
                    showToast("공개 설정을 켜야 출품할 수 있습니다.");
                    return;
                }

                const isSelected = selectedInput.checked;
                selectedHidden.value = isSelected ? "PUBLIC" : "PRIVATE";
                postSubmitButton.classList.toggle("checked", isSelected);
            }, 0);
        });

// 발행 모달 열기
        const publishButton = document.querySelector("button.publish-btn");
        const settingModal = document.querySelector(".post-settings.on");
        const settingModalInner = settingModal.querySelector(".setting-modal");

        publishButton.addEventListener("click", (e) => {
            e.stopPropagation();
            if (settingModal.style.display === "none") {
                settingModal.style.display = "block";
            }
        });

        window.addEventListener("click", (e) => {
            if (!settingModalInner.contains(e.target) && settingModal.style.display !== "none") {
                settingModal.style.display = "none";
            }
        });

        const postQuestion = document.querySelector(".setting-option i.question");
        const tooltip = document.querySelector("#tooltip");
        postQuestion.addEventListener("click", (e) => {
            tooltip.style.display = tooltip.style.display !== "none" ? "none" : "block";
        });

        // 최종 유효성 검사
        const confirmButton = document.querySelector("button.post-btn");
        confirmButton.addEventListener("click", (e) => {
            reorderFileInputNames(); // ✅ 이름 재정렬

            const title = document.querySelector("#textarea-title").value.trim();
            const content = document.querySelector("#textarea-content").value.trim();

            if (!title || !content) {
                e.preventDefault();
                showToast("제목과 내용을 모두 입력해주세요.");
            }
        });

        // 👉 파일 input과 메모의 name 재정렬
        const reorderFileInputNames = () => {
            const items = document.querySelectorAll("#file-preview-list li.item");
            items.forEach((li, index) => {
                const memoInput = li.querySelector(".mds-input-field");
                const fileInput = li.querySelector("input[type='file']");
                const idInput = li.querySelector("input[type='hidden'][name$='.id']");

                if (memoInput) memoInput.name = `fileList[${index}].fileText`;
                if (fileInput) fileInput.name = `fileList[${index}].multipartFile`;
                if (idInput) idInput.name = `fileList[${index}].id`;
            });
        };

        const form = document.querySelector("form");
        form?.addEventListener("submit", (e) => {
            // e.preventDefault(); // 실제 전송 막고
            const formData = new FormData(form);

            console.log("📦 FormData 내용:");
            for (const [key, value] of formData.entries()) {
                console.log(`${key}:`, value);
            }

            const previewList = document.getElementById("file-preview-list");
            console.log("✅ form에 포함됨?:", form.contains(previewList)); // true가 나와야 함

            const fileInputs = document.querySelectorAll("input[type='file']");
            fileInputs.forEach((input, i) => {
                console.log(`🖼️ file input ${i} | name: ${input.name} | files.length: ${input.files.length}`);
            });

        });

        // 토스트창
        function showToast(message) {
            const toast = document.createElement("div");
            toast.className = "toast";
            toast.innerHTML = `<p>${message}</p>`;
            document.querySelector("#wrap").appendChild(toast);
            setTimeout(() => toast.remove(), 2500);
        }
    });
});