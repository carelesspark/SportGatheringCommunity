    function updatePwd() {
    console.log("updatePwd() 호출");
        var newPwd = $("#newpwd").val();
        var newPwdConfirm = $("#newpwdConfirm").val();
        var userid = $("#userid").val();
        console.log(userid);
        console.log(newPwd);
        // 입력한 새로운 비밀번호와 확인 비밀번호를 비교
        if (newPwd !== newPwdConfirm) {
            alert("입력한 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return;
        }

        // 비밀번호 재설정을 위한 AJAX 호출
        $.ajax({
            url: "/login/updatePwd", // 해당 컨트롤러의 엔드포인트에 맞게 수정
            type: "post",
            dataType: "json",
            data: {
                "newPwd": newPwd,
                "userid": userid
            },
            success: function (data, status, xhr) {
                if (data.status === "success") {
                    alert("비밀번호가 성공적으로 재설정되었습니다.");
                    window.location.assign('/login/login'); // 비밀번호 재설정 후 로그인 페이지로 이동
                } else {
                    alert(data.message);
                }
            },
            error: function (xhr, status, error) {
                console.log("Error during password update", error);
            }
        });
    }