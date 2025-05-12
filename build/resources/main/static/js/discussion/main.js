// const openModal = (modal) => {
//     if(modal){
//         modal.style.display = "block";
//         document.body.style.overflow = "hidden"; 
//     }
// }

// const closeModal = (modal) => {
//     if(modal){
//         modal.style.display = "none";
//         document.body.style.overflow = "";
//     }
// }

const filterModal = document.querySelector(".range-popup");
const modalOpenButton = document.querySelector(".write");
const modalConfirmButton = filterModal.querySelector(".mds-button--primary");
const modalCloseButton = filterModal.querySelector(".mds-button--cancel");
modalOpenButton.addEventListener('click',(e) => {
    if(filterModal.style.display === "none"){
        filterModal.removeAttribute("style");
    }
    else{
        filterModal.style.display = "none";
    }
})

modalCloseButton.addEventListener("click",() => {
    filterModal.style.display = "none";
})

modalConfirmButton.addEventListener('click', () => {
    filterModal.style.display = "none";
})

// 신고 모달창 임시 이벤트

const reportTapButton = document.querySelectorAll(".more");
const reportModalButton = document.querySelectorAll(".more-area");
const reportModal = document.querySelector(".report-popup")
const reportConfirmButton = reportModal.querySelector(".apply")
const reportCloseButton = reportModal.querySelector(".close")

reportTapButton.forEach((button) =>{
    button.addEventListener('click',() =>{
        button.lastElementChild.classList.toggle("more-active");
    })
})

reportModalButton.forEach((button) =>{
    button.addEventListener('click',() =>{
        document.querySelector(".report-popup").removeAttribute("style")
    })
})

reportConfirmButton.addEventListener('click',() =>{
    reportModal.style.display = "none"
})

reportCloseButton.addEventListener('click',() =>{
    reportModal.style.display = "none"
})

//  여기까지