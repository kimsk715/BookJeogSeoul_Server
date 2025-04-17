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

const filterModal = document.querySelector(".mds-dialog--open");
const modalOpenButton = document.querySelector(".write");
const modalConfirmButton = document.querySelector(".mds-button--primary");
const modalCloseButton = document.querySelector(".mds-button--cancel");
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

