const reportButton = document.querySelector(".more-item");
const reportModal = document.querySelector(".more-ul");
const reportModalOpenButton = reportModal.querySelector("a")
const reportPopup = document.querySelector(".police-popup")
reportButton.addEventListener('click',() => {
    if(reportModal.style.display == "none"){
        reportModal.removeAttribute("style")
    }
    else{
        reportModal.style.display = "none";
    }
})

reportModalOpenButton.addEventListener("click",() => {
        if(reportPopup.style.display == "none"){
        reportPopup.removeAttribute("style")
    }
    else{
        reportPopup.style.display = "none";
    }
})

const commentButton = document.querySelector(".comment-item");
const commentDiv = document.querySelector(".comment");
commentButton.addEventListener('click', ( ) => {
        commentDiv.scrollIntoView({
            behavior : 'smooth',
            block : 'start'
        });
})


const giveBookMarkButton = document.querySelector(".like-btn")
const bookMarkModal = document.querySelector(".unload-popup")
const closeBookMarkModal = document.querySelector(".close")

giveBookMarkButton.addEventListener('click',() => {
    document.body.style.overflow = "hidden";
    document.documentElement.style.overflow = "hidden";
    bookMarkModal.removeAttribute("style")
})

closeBookMarkModal.addEventListener('click',() =>{
    document.body.style.overflow = "";
    document.documentElement.style.overflow = "";
    bookMarkModal.style.display = "none"
})

const bookMarkInput = document.querySelector(".dialog-content");

bookMarkInput.addEventListener('input',() =>{
    let numberLength = bookMarkInput.value.length;
    let numberValue = bookMarkInput.value;
    console.log(numberLength)
    console.log(numberValue)
    if(numberLength > 12){
        bookMarkInput.value = bookMarkInput.value.slice(0,12);
    }
    if(numberValue > 6000){
        alert("최대 6000개까지만 줄 수 있습니다.")
        bookMarkInput.value = 6000
    }
})


const imageModal = document.querySelector(".full-image")
const thumbnailWrappers = document.querySelectorAll(".list li")
const imageCloseButton = document.querySelector(".img-close")

thumbnailWrappers.forEach((image) =>{
    image.addEventListener('click',(e) => {
        document.body.style.overflow = "hidden";
        document.documentElement.style.overflow = "hidden";
        let imageSource = image.querySelector("img").getAttribute("src");
        // console.log(imageSource)
        imageModal.removeAttribute("style")
        imageModal.querySelector("img").setAttribute("src",imageSource)
    })
})

// 클릭 또는 ESC키로 Modal 닫기.
imageCloseButton.addEventListener('click',() =>{
    document.body.style.overflow = "";
    document.documentElement.style.overflow = "";
    imageModal.style.display = "none";
})

document.addEventListener("keyup",(e) =>{
    if(e.key === "Escape"){
        document.body.style.overflow = "";
        document.documentElement.style.overflow = "";
        imageModal.style.display = "none";
    }
})

const moreButton = document.querySelectorAll(".more-btn");

moreButton.forEach((button) => {
    button.addEventListener("click",() =>{
        console.log("클릭 확인")
        button.nextElementSibling.classList.toggle("more-active");
    })
})

//  신고 모달


const reportConfirmButton = document.querySelector(".btn-review-police")
document.addEventListener("change",()=>{
    const reportType = document.querySelector(".report-list input[type=radio]:checked")
    if(reportType){
        reportConfirmButton.removeAttribute("disabled")
    }
    else{
        reportConfirmButton.disabled = "disabled"
    }
})

const reportCancelButton = document.querySelector(".police-cancel")
reportCancelButton.addEventListener('click',() =>{
    reportPopup.style.display = "none"
})