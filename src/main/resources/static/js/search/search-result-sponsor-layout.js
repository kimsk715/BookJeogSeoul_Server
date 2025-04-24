const searchResultSponsorLayout = (() => {

    // 단체 전체 목록 출력 (무한스크롤용)
    const showSponsorList = async (totalCount, sponsors) => {
        const sponsorListContainer = document.querySelector("ul.organization");
        const resultCount = document.querySelector("span.result-count");

        // 총 검색 결과 수 표시 (예: 1,234)
        resultCount.innerText = totalCount.toLocaleString();

        sponsors.forEach((sponsor) => {
            const li = document.createElement("li");
            li.className = "slide-item";

            const profileImg = (sponsor.filePath && sponsor.fileName)
                ? `/member/profile?path=${sponsor.filePath.replace("C:\\upload\\", "").replace(/\\/g, "/")}&name=${sponsor.fileName}`
                : "/images/common/user-profile-example.png";

            li.innerHTML = `
                <div class="search-organization-list">
                    <a href="" class="organization-inner">
                        <div class="organization-data">
                            <div class="organization-image"></div>
                            <div class="organization-text">
                                <span class="title">${sponsor.sponsorName}</span>
                                <div class="line"></div>
                                <p class="content">
                                    ${sponsor.sponsorMainAddress || ''}<br/>
                                    ${sponsor.sponsorSubAddress || ''}<br/>
                                    ${sponsor.sponsorPhoneNumber || ''}
                                </p>
                            </div>
                        </div>
                    </a>
                </div>
            `;

            // 이미지 background 설정
            const imageBox = li.querySelector(".organization-image");
            imageBox.style.setProperty("--background-image", `url(${profileImg})`);

            sponsorListContainer.appendChild(li);
        });
    };

    return {showSponsorList: showSponsorList};
})();