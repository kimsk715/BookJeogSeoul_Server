const alarmButton = document.querySelector(".alarm-button");

alarmButton.addEventListener("click",() => {
        alarmService.getCommentAlarms(alarmLayout.showCommentAlarm);
})