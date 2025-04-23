document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("member-link")){
        personalService.getAllPersonal(personalLayout.showPersonalMemberList);
        console.log("토론 조회 버튼 클릭")
    }
})

const memberPageWrap = document.querySelector(".member-pagination")
const memberKeywordWrap = document.querySelector(".member-search-input")

memberPageWrap.addEventListener('click', (e) => {
    if(e.target.classList.contains("page-btn")){
        const param = {page : e.target.id, search : {keyword : memberKeywordWrap.value}};
        personalService.getAllPersonal(personalLayout.showPersonalMemberList, param);
    }
})

memberKeywordWrap.addEventListener('change',(e) => {
    console.log(e.target.value)
        const param = {search : {keyword : e.target.value}}
        personalService.getAllPersonal(personalLayout.showPersonalMemberList, param);
})



