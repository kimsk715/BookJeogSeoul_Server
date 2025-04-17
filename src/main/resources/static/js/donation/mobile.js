// 피드 단위 리스트
const cardList = `<div class ="card-wrapper">
                                <div class="card-item">
                                    <div class="user post-theme"><a href=""
                                            class="profile-image gtm-feed-recommend-post-tem-library"
                                            style="background-image: url(&quot;https://d3uz7kn1zskbbz.cloudfront.net/profile/00ecbef493114ef5a7eaccaa8ae46142.jpeg&quot;);"></a>
                                        <div class="metadata"><a href=""
                                                class="nickname-link gtm-feed-recommend-post-tem-library"><span
                                                    class="nickname">허세웅</span> <!----></a>
                                            <div><span class="type">포스트</span>
                                                <span class="time">2일 전</span>
                                            </div>
                                        </div> <button type="button"
                                            class="button gtm-feed-recommend-post-tem-follow">팔로우</button>
                                        <div class="more"><button type="button" tabindex="0"><i
                                                    class="mds-icon--more-24-1"></i></button> <!----></div> <!---->
                                    </div>
                                    <div class="post"><a href=""
                                            class="box post-image gtm-feed-recommend-post-tem-detail">
                                            <div class="img-wrap"><img
                                                    src="https://cover.millie.co.kr/service/post/1146862/67efb024a2734.png?f=webp&amp;w=600&amp;q=80">
                                            </div>
                                        </a>
                                        <div class="metadata"><a href=""
                                                class="inner gtm-feed-recommend-post-tem-detail">
                                                <p class="title">한 번도 촛불을 불어본 적 없는 성빈이</p>
                                                <p class="sub-title">성빈이는 16번째 생일을 맞이합니다.
                                                    성빈이에게 생일은 항상 외롭고 고요한 날이었습니다. 그런 성빈이를 위해 생일파티를 열고 싶지만, 그룹홈에 있는 아이들의
                                                    생일파티를 개별적으로 진행하는 것은 쉽지 않습니다. 그룹홈은 빠듯한 정부 보조금과 아동 1인 월 62만원 수준의 수급비로
                                                    운영되기에, 생일파티를 위한 추가 비용을 마련하기는 어려운 상황입니다. 성빈이는 몇 주 전부터 생일 날짜를 동그라미로
                                                    표시해 놓고 기다리고 있지만, 그 마음을 아는 선생님들의 마음은 미어집니다.</p>
                                            </a></div>
                                    </div>
                                    <div class="bottom"><button type="button" class="btn comment"><i
                                                class="mds-icon--comment"></i> <span class="label">댓글</span> <span
                                                class="value">1</span></button> <button type="button"
                                            class="btn like"><i class="mds-icon--clip"></i> <span
                                                class="label">책갈피</span> <span class="value">1</span></button></div>
                                </div>
                            </div>`;

const cardListWrapper = document.querySelector(".card-list");

window.addEventListener('DOMContentLoaded',()=>{
    console.log("DOM 실행")
    const initwidth = window.innerWidth;
    let initCount = 0;
    let initCardInner = "";
    if(initwidth<700){
        initCount = 1;
    }
    else if(initwidth<1000){
        initCount = 2;
    }
    else if(initwidth<1440){
        initCount = 3;
    }
    else{
        initCount = 4;
    }
    for(i=0; i<initCount; i++){
        initCardInner += cardList
    }
    cardListWrapper.innerHTML = initCardInner
})


window.addEventListener("resize",() =>{
    var screenwidth = window.innerWidth;
    var count=0;
    var cardInner="";
    if(screenwidth<700){
        count = 1;
    }
    else if(screenwidth<1000){
        count = 2;
    }
    else if(screenwidth<1440){
        count = 3;
    }
    else{
        count = 4;
    }
    for(i=0; i<count; i++){
        cardInner += cardList
    }
    cardListWrapper.innerHTML = cardInner
    console.log(i)
})

