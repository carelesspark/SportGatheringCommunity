function sendNumber() {
    $("#mail_number").css("display", "block");
    $.ajax({
        url: "/verify-email",
        type: "post",
        dataType: "json",
        data: {
                "email": $("#email").val()
                },
        success: function (data, status, xhr) {
            if (data.status === "success") {
                // 인증 성공 시
                alert("이메일이 전송되었습니다.");
                // 또는 다른 UI 업데이트 로직을 추가할 수 있습니다.
            } else {
                // 인증 실패 시
                alert("이메일 전송에 실패하였습니다.");
                // 또는 다른 UI 업데이트 로직을 추가할 수 있습니다.
            }
        },
        error: function(xhr, status, error){
            console.log("Error during send Email", error);
        }
    });
}

function confirmNumber() {
    let email = $("#email").val();
    let verificationCode = $("#verificationCode").val();

    $.ajax({
        url: "/verification-code",
        type: "post",
        dataType: "json",
        data: {"email": email, "verifyCode": verificationCode},
        success: function (data, status, xhr) {
            if (data.status === "success") {
                // 인증 성공 시
                alert("인증이 완료되었습니다.");
            } else {
                // 인증 실패 시
                alert("인증에 실패하였습니다. 올바른 인증 코드를 입력해주세요.");
            }
        },
        error: function(xhr, status, error){
            console.log("Error during verification", error);
        }
    });
}

function findPwd() {
    console.log("findPwd() 호출");
    console.log("userid : " + $("#userid").val())
    $.ajax({
            url: "/login/findPwd",
            type: "post",
            dataType: "json",
            data: {
               "email": $("#email").val(),
               "userpwd": $("#userpwd").val(),
               "verifyCode": $("#verificationCode").val()
            },
            success: function (data, status, xhr) {
                if (data.status === "success") {
                    alert("회원님의 비밀번호는 " + data.userPwd + "입니다.");
                    window.location.assign('/login/login');
                } else {
                    alert(data.message);
                }
            },
            error: function(xhr, status, error){
                console.log("Error during send Email", error);
            }
        });
}