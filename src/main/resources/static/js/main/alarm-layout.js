const alarmLayout = (() => {
    const showAlarms = async (alarmList) => {
    }
    const ul = document.querySelectorAll(".alarm-content");

    // let follow = '';
    // let mention = '';
    // let post = '';
    // let comment = '';
    // await alarmList.alarmFollowAlarmDTOS.forEach((follow) => {
    //     follow += `
    //             <li><a href="#">
    //                                     <div class="img"><img
    //                                             src="${follow.filePath}">
    //                                     </div>
    //                                     <div class="context"><!---->
    //                                         <p class="txt">[내서재 알림]<br>${follow.memberNickName}님이 회원님을 팔로우했습니다
    //                                         </p> <time class="time">${follow.createdDate}</time>
    //                                     </div>
    //                                 </a></li>
    //
    //     `
    // })
    //     await alarmList.alarmCommentAlarmDTOS.forEach((comment) => {
    //         comment += `
    //         <li><a href="">
    //                                     <div class="img"><img
    //                                             src="">
    //                                     </div>
    //                                     <div class="context"><!---->
    //                                         <p class="txt">[댓글 알림]<br>회원님의 게시글에 댓글이 달렷어요.
    //                                             </p> <time class="time">${comment.createdDate}</time>
    //                                     </div>
    //                                 </a></li>
    //
    //         `
    //     }
    //     await alarmList.alarmMentionAlarmDTOS.forEach((mention) => {
    //         mention +=`
    //          <li><a href="">
    //                                     <div class="img"><img
    //                                             src="">
    //                                     </div>
    //                                     <div class="context"><!---->
    //                                         <p class="txt">[댓글 알림]<br>회원님의 게시글에 댓글이 달렷어요.
    //                                             </p> <time class="time">${comment.createdDate}</time>
    //                                     </div>
    //                                 </a></li>`
    //
    //     })
    // }

    const showCommentAlarm = async(commentAlarms) => {
        let comment = '';
        commentAlarms.forEach((path) => {
            comment += `
             <li><a href="${path}">
                                         <div class="img"><img
                                                 src="">
                                         </div>
                                         <div class="context"><!---->
                                             <p class="txt">[댓글 알림]<br>회원님의 게시글에 댓글이 달렷어요.
                                                 </p> <time class="time"></time>
                                         </div>
                                     </a></li>
    
             `
        })
        ul.innerHTML = comment;

        }
        return {showCommentAlarm:showCommentAlarm}
})