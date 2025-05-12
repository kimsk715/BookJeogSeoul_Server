
const voteButton = document.querySelector(".vote-button")

voteButton.addEventListener("click",async(e)=>{
    const voteRadio = document.querySelector("input[type=radio][name=vote]:checked")
    if(!voteRadio){
        alert("투표할 독후감을 선택해주세요!");
        return;
    }
    if(confirm("선택한 독후감에 투표하시겠습니까?")){
        const bookPostId = voteRadio.value;
        await fetch(`/vote/book-post?bookPostId=${bookPostId}`)
    }
})