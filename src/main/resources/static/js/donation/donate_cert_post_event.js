const reportButton = document.querySelector(".more-item");
const reportModal = document.querySelector(".more-ul");

reportButton.addEventListener('click',() => {
    if(sponsorMember !== null && (sponsorMember.sponsorName === document.querySelector("#sponsorName").value)){
        console.log("실행됨")
        document.querySelector(".post-edit-button").removeAttribute("style");
    }

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


let commentId = "";
const commentReportButton = document.querySelectorAll(".comment-report")
const reportPopup = document.querySelector(".popup.alert")
commentReportButton.forEach((button) => {
    button.addEventListener('click',(e) => {
        commentId = button.value;
        reportPopup.removeAttribute("style")
        console.log(commentId)
    })
})

const reportConfirmButton = document.querySelector(".btn-review-police")
document.addEventListener("change",()=>{
    const reportType = document.querySelector(".report-list input[type=radio]:checked")
    if(reportType){
        reportConfirmButton.removeAttribute("disabled")
    }
    else{
        reportConfirmButton.disabled = "disabled"
    }
})

const reportCancelButton = document.querySelector(".police-cancel")
reportCancelButton.addEventListener('click',() =>{
    reportPopup.style.display = "none"
})

reportConfirmButton.addEventListener("click",(e) => {
    let reportNum = parseInt(document.querySelector("input[type=radio]:checked").value);
    let reportType = "";
    switch (reportNum){
        case 1:
            reportType = "ABUSE";
            break;
        case 2:
            reportType = "SEXUAL";
            break;
        case 3:
            reportType = "SPAM";
            break;
        case 4:
            reportType = "SPOILER";
            break;
        case 0:
            reportType = "ETC";
            break;
    }
    console.log(reportType)
    reportPopup.style.display = "none"
    reportService.reportComment(commentId, reportType)
})

const reportService = (() => {
    const reportComment = async(commentId, reportType) => {
        let path = `/report-comment?commentId=${commentId}&reportType=${reportType}`;
        await fetch(path);
    }
    return {reportComment: reportComment}
})();

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
    if(document.querySelector(".mention-button")){
        document.querySelector(".mention-button").remove();
    }
    commentArea.value = "";
    mentionWrapper.style.display = "none";
})
const commentWrapper = document.querySelector(".comment-list");

const commentService = (() =>{
    const addComment = async(postId,commentText, callback, mentionId) => {
        let path=`/post-comment?id=${postId}&text=${commentText}`;
        if(mentionId != null){
            path += `&mention-id=${mentionId}`
            var mentionedName = document.querySelector(".mention-button").innerText;
        }

        await fetch(path);
        if(callback && mentionedName != null){
            callback(commentText, mentionedName)
        }
        else{
            callback(commentText)
        }


    }
    const commentCheck = async (commentText) => {
        let path = `http://localhost:8000/api/reply-check?text=${commentText}`;
        const response =  await fetch(path);
        console.log(response);
    }
    return {addComment : addComment, commentCheck:commentCheck};
})();



const commentLayout =(() =>{
    const addedLayout = async(commentText, mentionedName) =>{
        const addedComment = document.createElement('li');
        addedComment.classList.add("comment-item");
        let name = (member !== null) ? member.memberName : sponsorMember.sponsorName;
        if(mentionedName != null){
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
                                                    <a href="#">${name}</a>
                                                </p>
                                            </div>
                                            <span class="com-contents-date">2025-04-28 11:35:49</span>
                                        </div>
                                        <div class="com-contents-body">
                                        <button type="button" class="tag-button" title="${mentionedName}">${mentionedName}</button>
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
        }
        else{
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
                                                    <a href="#">${name}</a>
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
        }
        commentWrapper.prepend(addedComment);
    }
    return { addedLayout : addedLayout}
})();


const mentionMenu = document.querySelector("#mention-menu");
const mentionInner = mentionMenu.querySelector("#mention-inner")
document.addEventListener("DOMContentLoaded",(e)=>{
    const memberCount =  mentionInner.querySelectorAll('li').length;
    if(memberCount < 4) {
        mentionMenu.style.overflowY = "none";
        mentionMenu.style.height = `${30*memberCount}` + "px";
        console.log("DOM 감지")
    }
})

const mentionWrapper = document.querySelector(".mention-wrapper")
commentContainer.addEventListener("click",(e)=>{
    if(e.target.classList.contains("mention-button")){
        e.target.remove();
        mentionWrapper.style.display = "none";
    }
})

mentionInner.addEventListener("click",(e)=>{
    const mentionButton = document.createElement("button")
    mentionButton.classList.add("mention-button")
    mentionButton.setAttribute("title", `${e.target.innerText}`)
    mentionButton.value = `${e.target.value}`;
    mentionButton.innerText = `${e.target.innerText}`;

    // `<button type="button" class="mention-button" title="${e.target.innerText}" value="${e.target.value}"></button>`
    mentionWrapper.removeAttribute("style")
    mentionWrapper.append(mentionButton);
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
        if(e.target.value.trim() !== "") {
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
// 댓글 작성 부분 높이 조절
commentArea.addEventListener('input',function() {
    this.style.height = 'auto';
    this.style.height = this.scrollHeight + 'px';
})