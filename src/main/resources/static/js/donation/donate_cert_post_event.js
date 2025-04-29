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


const addComment = document.querySelector(".post-btn");
const commentArea = document.querySelector(".register-box-inner textarea");
const commentContainer = document.querySelector(".register-box");
addComment.addEventListener("click",(e) => {
    const postId = addComment.value;
    if(document.querySelector(".mention-button")) {
        var mentionId = document.querySelector(".mention-button").value;
    }
    const commentText = commentArea.value;

    if(mentionId != null) {
        commentService.addComment(postId, commentText, commentLayout.addedLayout, mentionId);
        alert("댓글이 등록되었습니다.")
    }
    else{
        commentService.addComment(postId, commentText, commentLayout.addedLayout);
        alert("댓글이 등록되었습니다.")
    }
    document.querySelector(".mention-wrapper").innerHTML = ``;
    commentArea.value = "";
})
const commentWrapper = document.querySelector(".comment-list");

const commentService = (() =>{
    const addComment = async(postId, commentText, callback, mentionId) => {
        let path=`/post-comment?id=${postId}&text=${commentText}`;
        if(mentionId != null){
            path += `&mention-id=${mentionId}`
        }
        await fetch(path);
        if(callback){
            callback(commentText)
        }
    }
    return {addComment : addComment}
})();

const commentLayout =(() =>{
    const addedLayout = async(commentText) =>{
        const addedComment = document.createElement('li');
        addedComment.classList.add("comment-item");
        addedComment.innerHTML = `
                                <div class="comment-item-inner flex-container">
                                    <div class="user-img" style="
                                                background-image: url('https://d3uz7kn1zskbbz.cloudfront.net/profile/05ce9d4466834b948dc0c43e49f001a5.jpeg');
                                            ">
                                        <a href="#"></a>
                                    </div>
                                    <div class="com-contents">
                                        <div class="com-contents-head">
                                            <div class="com-contents-top flex-container">
                                                <p class="nickname">
                                                    <a href="#">${member.memberName}</a>
                                                </p>
                                            </div>
                                            <span class="com-contents-date">2025-04-28 11:35:49</span>
                                        </div>
                                        <div class="com-contents-body">
                                            <p class="comment-text show">${commentText}</p>
                                            <div class="comment-text">
                                                <p contenteditable="plaintext-only">${commentText}</p>
                                                <button type="button" class="mds-button mds-button--secondary mds-button--flex mds-button--h56 mds-button--r4">
                                                    <span>등록</span>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="reply-setting">
                                            <button type="button" class="more-btn">
                                                <img src="https://d3udu241ivsax2.cloudfront.net/v3/images/bookDetail/more-icon.df2e02aec2252c2847fbe9e490cd4354.png" alt="더보기">
                                            </button>
                                            <div class="more-area" style="display: none;">
                                                <button type="button">답글달기</button>
                                                <button type="button">신고하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>                   
        `;
        commentWrapper.prepend(addedComment);
    }
    return { addedLayout : addedLayout}
})();


const mentionMenu = document.querySelector("#mention-menu");
const mentionWrapper = mentionMenu.querySelector("#mention-inner")
document.addEventListener("DOMContentLoaded",(e)=>{

    const memberCount =  mentionWrapper.querySelectorAll('li').length;
    if(memberCount < 5) {
        mentionMenu.style.overflowY = "none";
        console.log("DOM 감지")
    }
})


commentContainer.addEventListener("click",(e)=>{
    if(e.target.classList.contains("mention-button")){
        e.target.closest("div").remove();

    }
})

mentionWrapper.addEventListener("click",(e)=>{
    const mentionButton = document.createElement("div")
    mentionButton.classList.add("mention-wrapper")
    mentionButton.innerHTML = `<button type="button" class="mention-button" value="${e.target.value}">${e.target.innerText}</button>`
    commentArea.before(mentionButton);
    const start = commentArea.selectionStart;
    const end = commentArea.selectionEnd;
    if (start === end && start > 0) {
        // 커서 바로 앞 한 글자 삭제
        commentArea.value = commentArea.value.slice(0, start - 1) + commentArea.value.slice(end);
        commentArea.selectionStart = commentArea.selectionEnd = start - 1;
    }
    hideMentionMenu();
})


commentArea.addEventListener("input", (e) => {
    if(!document.querySelector(".mention-button")) {
        if (e.target.value.trim() !== "") {
            addComment.disabled = false;
        } else {
            addComment.disabled = true;
        }
        const cursorPosition = commentArea.selectionStart;
        const text = commentArea.value.substring(0, cursorPosition)
        const lastWord = text.split(/\s/).pop();
        console.log(lastWord)
        if (lastWord.startsWith(`@`)) {
            showMentionMenu(cursorPosition);
        } else {
            hideMentionMenu();
        }
    }
});

const showMentionMenu = (cursorPosition) => {
    mentionMenu.removeAttribute("style");
    mentionMenu.style.left = `${cursorPosition*5+90}px`
    mentionMenu.style.top = `-80px`;
}

const hideMentionMenu = () => {
    mentionMenu.style.display = "none";
}
