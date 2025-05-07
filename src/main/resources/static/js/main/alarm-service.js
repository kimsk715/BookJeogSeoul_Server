const alarmService = (() => {
    const selectAlarm = async (callback) => {
        const response = await fetch('/alarm-info');
        const alarmList = await response.json();

        if(callback) {
            callback(alarmList)
        }
    };

    const getCommentAlarms = async(callback) => {
        const response = await fetch('/post-alarm')
        const commentAlarms = await response.json();

        if(callback){
            callback(commentAlarms)
        }
    }

    return { selectAlarm: selectAlarm, getCommentAlarms:getCommentAlarms };
})();