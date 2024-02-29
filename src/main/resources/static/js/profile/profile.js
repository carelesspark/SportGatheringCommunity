function checkUserNickname() {
    var userNickname = $("#newNickname").val(); // 입력된 닉네임 값
    $.ajax({
        url: "/profile/checkUserNickname/" + userNickname,
        type: "GET",
        success: function (response) {
            if (response === "EXIST") {
                // 닉네임이 이미 존재하는 경우에 대한 처리
                alert("이미 존재하는 닉네임입니다.");
            } else {
                // 닉네임이 사용 가능한 경우에 대한 처리
                alert("사용 가능한 닉네임입니다.");
            }
        },
        error: function () {
            // 에러 처리
            alert("서버 오류가 발생했습니다.");
        }
    });
}

function checkUserEmail() {
    var userEmail = $("#newEmail").val(); // 입력된 닉네임 값
    console.log(userEmail);
    $.ajax({
        url: "/profile/checkUserEmail/"+ userEmail,
        type: "GET",
        success: function (response) {
            if (response === "EXIST") {
                // 이메일이 이미 존재하는 경우에 대한 처리
                alert("이미 존재하는 이메일입니다.");
            } else {
                // 이메일이 사용 가능한 경우에 대한 처리
                alert("사용 가능한 이메일입니다.");
            }
        },
        error: function () {
            // 에러 처리
            alert("서버 오류가 발생했습니다.");
        }
    });
}