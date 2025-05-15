let cardList = "";
donateCerts.forEach( post => {

        cardList += `
                        <div class ="card-wrapper">
                             
                                <div class="card-item">
                                    <div class="user post-theme"><a href="/post/donate/post/${post.id}"
                                            class="profile-image gtm-feed-recommend-post-tem-library"
                                            style="background-image: url(&quot;https://d3uz7kn1zskbbz.cloudfront.net/profile/00ecbef493114ef5a7eaccaa8ae46142.jpeg&quot;);"></a>
                                        <div class="metadata"><a href=""
                                                class="nickname-link gtm-feed-recommend-post-tem-library"><span
                                                    class="nickname">${post.sponsorName}</span> <!----></a>
                                            <div><span class="type">포스트</span>
                                                <span class="time">${post.createdDate}일 전</span>
                                            </div>
                                        </div> <button type="button" style="display: none;"
                                            class="button gtm-feed-recommend-post-tem-follow">팔로우</button>
                                        <div class="more"><button type="button" tabindex="0"><i
                                                    class="mds-icon--more-24-1"></i></button> <!----></div> <!---->
                                    </div>
                                    <div class="post"><a href="/post/donate/post/${post.id}"
                                            class="box post-image gtm-feed-recommend-post-tem-detail">
                                            <div class="img-wrap"><img
                                                    src="/image/${post.donateCertFilePath}/${post.donateCertFileName}" alt="이미지를 찾을 수 없습니다.">
                                            </div>
                                        </a>
                                        <div class="metadata"><a href="/post/donate/post/${post.id}"
                                                class="inner gtm-feed-recommend-post-tem-detail">
                                                <p class="title">${post.donateCertTitle}</p>
                                                <p class="sub-title">${post.donateCertText}</p>
                                            </a></div>
                                    </div>
                                    <div class="bottom"><button type="button" class="btn comment"><i
                                                class="mds-icon--comment"></i> <span class="label">댓글</span> <span
                                                class="value">${post.commentCount}</span></button></div>
                                </div>
                              
                            </div>`;
}

)


const cardListWrapper = document.querySelector(".card-list");
cardListWrapper.innerHTML = cardList
// window.addEventListener('DOMContentLoaded',()=>{
//     console.log("DOM 실행")
//     const initwidth = window.innerWidth;
//     let initCount = 0;
//     let initCardInner = "";
//     if(initwidth<700){
//         initCount = 1;
//     }
//     else if(initwidth<1000){
//         initCount = 2;
//     }
//     else if(initwidth<1440){
//         initCount = 3;
//     }
//     else{
//         initCount = 4;
//     }
//     for(i=0; i<initCount; i++){
//         initCardInner += cardList
//     }
//     cardListWrapper.innerHTML = initCardInner
// })
//
//
// window.addEventListener("resize",() =>{
//     var screenwidth = window.innerWidth;
//     var count=0;
//     var cardInner="";
//     if(screenwidth<700){
//         count = 1;
//     }
//     else if(screenwidth<1000){
//         count = 2;
//     }
//     else if(screenwidth<1440){
//         count = 3;
//     }
//     else{
//         count = 4;
//     }
//     for(i=0; i<count; i++){
//         cardInner += cardList
//     }
//     cardListWrapper.innerHTML = cardInner
//     console.log(i)
// })
//
