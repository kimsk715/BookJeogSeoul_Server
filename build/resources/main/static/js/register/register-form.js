const registerBtn = document.querySelector("#next-btn")
const form = document.querySelector("#register-form")

registerBtn.addEventListener('click', (e) => {
    if(nextBtn.style.opacity === "1") {
        form.submit();
    }else{
        e.preventDefault()
    }

})