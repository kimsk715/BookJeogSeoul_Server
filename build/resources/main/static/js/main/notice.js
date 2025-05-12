const openButtons = document.querySelectorAll(".notice-item")

openButtons.forEach((button) => {
    button.addEventListener('click',()=>{
        button.closest('li').classList.toggle("open")
    })
})

const noticeButton = document.querySelector(".notice-btn")
const eventButton = document.querySelector(".event-btn")

const noticeArea = document.querySelector(".notice-content")
const eventArea = document.querySelector(".event-content")
noticeButton.addEventListener('click',()=>{
    noticeButton.classList.add("active")
    noticeButton.classList.add("selected")
    eventButton.classList.remove("active")
    eventButton.classList.remove("selected")
    noticeArea.removeAttribute("style")
    eventArea.style.display = "none"
})

eventButton.addEventListener('click',()=>{
    eventButton.classList.add("active")
    eventButton.classList.add("selected")
    noticeButton.classList.remove("active")
    noticeButton.classList.remove("selected")
    eventArea.removeAttribute("style")
    noticeArea.style.display = "none"
})