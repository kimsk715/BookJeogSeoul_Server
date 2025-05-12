const voteRadio = document.querySelector("input[type=radio][name=vote]")
const voteButton = document.querySelector(".vote-button")

voteButton.addEventListener("click",async(e)=>{
    if(confirm("선택한 독후감에 투표하시겠습니까?")){
        const bookPostId = voteRadio.value;
        await fetch(`/vote/book-post?bookPostId=${bookPostId}`)
    }
})