document.addEventListener(('click'),(e) => {
    if(e.target.classList.contains("member-link")){
        personalService.getAllPersonal(personalLayout.showPersonalMemberList);
        console.log("토론 조회 버튼 클릭")
    }
})



