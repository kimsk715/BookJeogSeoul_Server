const registerBtn = document.querySelector("#next-btn")
const form = document.querySelector("#register-form")

registerBtn.addEventListener('click', () => {
    form.submit();
})