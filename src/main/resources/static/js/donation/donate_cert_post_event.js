const reportButton = document.querySelector(".more-item");
const reportModal = document.querySelector(".more-ul");

reportButton.addEventListener('click',() => {
    if(reportModal.style.display == "none"){
        reportModal.removeAttribute("style")
    }
    else{
        reportModal.style.display = "none";
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