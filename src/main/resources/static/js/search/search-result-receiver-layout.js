const searchResultReceiverLayout = (() => {
    // 기부글 전체 목록 출력 (무한스크롤용)
    const showReceiverList = async (totalCount, receivers) => {
        const receiverListContainer = document.querySelector("ul.donate");
        const resultCount = document.querySelector("span.result-count");

        // 총 검색 결과 수 표시
        resultCount.innerText = totalCount.toLocaleString();

        // 페이지가 1일 때만 초기화
        if (currentPage === 1) {
            receiverListContainer.innerHTML = "";
        }

        receivers.forEach((receiver) => {
            const li = document.createElement("li");
            li.className = "slide-item";

            // 프로필 이미지 경로
            const profileUrl = (receiver.profileFilePath && receiver.profileFileName)
                ? `/member/profile?path=${receiver.profileFilePath.replace("C:\\upload\\", "").replace(/\\/g, "/")}&name=${receiver.profileFileName}`
                : "/images/common/user-profile-example.png";

            // 본문 썸네일 이미지 경로
            const receiverImageUrl = (receiver.receiverFilePath && receiver.receiverFileName)
                ? `/post/thumbnail?path=${receiver.receiverFilePath.replace("C:\\upload\\", "").replace(/\\/g, "/")}&name=${receiver.receiverFileName}`
                : "/images/common/default-donate-image.png";

            // 날짜 포맷
            const formattedDate = receiver.createdDate?.split(" ")[0] || "";

            li.innerHTML = `
                <div class="search-donate-list">
                    <a href="#" class="donate-inner">
                        <div class="donate-data">
                            <div class="donate-image" style="--background-image: url('${receiverImageUrl}');"></div>
                            <div class="donate-text">
                                <span class="title">${receiver.receiverTitle}</span>
                                <p class="content donateType">${receiver.receiverText.replace(/\n/g, "<br/>")}</p>
                            </div>
                        </div>
                    </a>
                    <a href="#" class="author">
                        <div class="image" style="background-image: url('${profileUrl}')"></div>
                        <div class="info">
                            <span class="name">${receiver.sponsorName}</span>
                            <p class="regdate">${formattedDate}</p>
                        </div>
                    </a>
                </div>
            `;

            receiverListContainer.appendChild(li);
        });

        // 결과가 3개 이하이면 더보기 링크 비활성화
        if (totalCount <= 3) {
            const receiverLink = document.querySelector(".search-result-donate-more > .link.icon-arrow-right");
            if (receiverLink) {
                receiverLink.classList.add("disabled");
                receiverLink.removeAttribute("href");
            }
        }
    };

    return { showReceiverList : showReceiverList };
})();
