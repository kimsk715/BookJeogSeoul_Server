const sortModalOpen = document.querySelector(".sort-option");
const sortModal = document.querySelector(".sort-modal")
const modalCloseButton = document.querySelector(".modal-close-button")
const modalConfirmButton = document.querySelector(".modal-confirm-button")

sortModalOpen.addEventListener("click",() =>{
    sortModal.removeAttribute("style");
    document.documentElement.style.overflow = "hidden";
    document.body.style.overflow = "hidden";
})

modalCloseButton.addEventListener('click', () =>{
    sortModal.style.display = "none"
    document.documentElement.style.overflow = "";
    document.body.style.overflow = "";
})

// 화면 상에서 정렬 버튼 클릭 시 해당 필터로 텍스트 변경되도록 하는 스크립트
// 서버랑 별개
modalConfirmButton.addEventListener("click",() =>{
    console.log(document.querySelector("input[name=sort]").value);
    console.log(document.querySelector("input[name=sort]:checked").nextElementSibling.innerText)
    const value = document.querySelector("input[name=sort]:checked").nextElementSibling.innerText
    sortModalOpen.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg"
                            width="16" height="16" fill="none" viewBox="0 0 16 16" class="mds-icon"
                            color="var(--text-01)">
                            <path fill-rule="evenodd"
                                d="M7.798 5.576a.5.5 0 0 1-.707 0L5.722 4.207V13a.5.5 0 0 1-1 0V4.207L3.354 5.576a.5.5 0 1 1-.708-.707L4.864 2.65a.5.5 0 0 1 .358-.151h.01a.5.5 0 0 1 .344.146L7.798 4.87a.5.5 0 0 1 0 .707M11.28 3a.5.5 0 0 0-1 0v8.795l-1.37-1.37a.5.5 0 0 0-.708.706l2.222 2.223a.5.5 0 0 0 .355.146h.001a.5.5 0 0 0 .38-.174l2.194-2.195a.5.5 0 0 0-.707-.707l-1.367 1.367z"
                                clip-rule="evenodd" fill="currentColor"></path>
                        </svg> ${value}`
    sortModal.style.display = "none"
    document.documentElement.style.overflow = "";
    document.body.style.overflow = "";
})


