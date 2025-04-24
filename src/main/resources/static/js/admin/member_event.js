document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("member-link")){
        memberService.getAllPersonal(memberLayout.showPersonalMemberList);
    }
})

const memberPageWrap = document.querySelector(".member-pagination")
const memberKeywordWrap = document.querySelector(".member-search-input")

memberPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : memberKeywordWrap.value}};
        memberService.getAllPersonal(memberLayout.showPersonalMemberList, param);
    }
})

memberKeywordWrap.addEventListener('change',(e) => {
    console.log(e.target.value)
        const param = {search : {keyword : e.target.value}}
        memberService.getAllPersonal(memberLayout.showPersonalMemberList, param);
})

const sponsorPageWrap = document.querySelector(".sponsorship-pagination")
const sponsorKeywordWrap = document.querySelector(".sponsorship-search-input")


document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("sponsorship-link")){
        memberService.getAllSponsor(memberLayout.showSponsorMemberList);
    }
})

sponsorPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : sponsorKeywordWrap.value}};
        memberService.getAllSponsor(memberLayout.showSponsorMemberList, param);
    }
})

sponsorKeywordWrap.addEventListener('change',(e) => {
    console.log(e.target.value)
    const param = {search : {keyword : e.target.value}}
    memberService.getAllSponsor(memberLayout.showSponsorMemberList, param);
})

const sponsorRegisterModal = document.querySelector(".sponsorship-modal")
const adminModal = document.querySelector(".admin-modal")
document.addEventListener('click',(e) =>{
    if(e.target.classList.contains("sponsorship-register-btn")){
        openModal(sponsorRegisterModal)
    }
    else if(e.target.classList.contains("admin-register-btn")){
        openModal(adminModal)
    }
})

const sponsorRegisterButton = document.querySelector(".sponsorship-confirm-btn")
sponsorRegisterButton.addEventListener("click",(e)=> {
    const id = document.querySelector("#new-sponsorship-id").value
    const password = document.querySelector("#new-sponsorship-password").value
    const email = document.querySelector("#new-sponsorship-email").value
    const phone = document.querySelector("#new-sponsorship-phone").value
    const name = document.querySelector("#new-sponsorship-name").value
    const mainAddress = document.querySelector("#new-sponsorship-address").value
    const subAddress = document.querySelector("#new-sponsorship-sub-address").value
    let infoArray = [];
    infoArray.push(id);
    infoArray.push(password);
    infoArray.push(email);
    infoArray.push(phone);
    infoArray.push(name);
    infoArray.push(mainAddress);
    infoArray.push(subAddress);
    memberService.insertSponsor(infoArray);
})

const adminPageWrap = document.querySelector(".admin-pagination")
const adminKeywordWrap = document.querySelector(".admin-search-input")



document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("admin-manage-link")){
        memberService.getAllAdmin(memberLayout.showAdminMemberList);
    }
})

adminPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : adminKeywordWrap.value}};
        memberService.getAllAdmin(memberLayout.showAdminMemberList, param);
    }
})

adminKeywordWrap.addEventListener('change',(e) => {
    console.log(e.target.value)
    const param = {search : {keyword : e.target.value}}
    memberService.getAllAdmin(memberLayout.showAdminMemberList, param);
})



const adminRegisterButton = document.querySelector(".admin-confirm-btn");
adminRegisterButton.addEventListener("click",(e)=> {
    const id = document.querySelector("#new-admin-id").value
    const password = document.querySelector("#new-admin-password").value
    const name = document.querySelector("#new-admin-name").value
    const department = document.querySelector("#new-admin-department").value
    const grade = document.querySelector("#new-admin-grade").value

    let infoArray = [];
    infoArray.push(id);
    infoArray.push(password);
    infoArray.push(name);
    infoArray.push(department);
    infoArray.push(grade);

    memberService.insertAdmin(infoArray);
})


