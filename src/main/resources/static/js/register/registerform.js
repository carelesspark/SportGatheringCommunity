window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');

    Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });
}, false);

document.addEventListener('DOMContentLoaded', function () {
    // 에러 메시지를 표시할 요소를 가져옵니다.
    const errorMessage = document.getElementById('errorMessage');

    if (errorMessage && errorMessage.textContent.trim() !== '') {
        // 에러 메시지가 존재하고 내용이 비어있지 않다면, 알림창으로 메시지를 표시합니다.
        const modifiedErrorMessage = errorMessage.textContent.trim().replace(/Flags\s*=/, '').replace(/'/g, '').replace(/\//g, '');
        alert(modifiedErrorMessage);
    }

    // 필요에 따라 다른 클라이언트 측 유효성 검사 또는 기능을 추가할 수 있습니다.
    // 예를 들어, 이 스크립트를 사용하여 폼 제출을 처리하는 등의 작업을 수행할 수 있습니다.
});

function checkUserId() {
    var userId = $("#id").val(); // 입력된 아이디 값
    $.ajax({
        url: "/register/checkUserId/" + userId,
        type: "GET",
        success: function (response) {
            if (response === "EXIST") {
                // 아이디가 이미 존재하는 경우에 대한 처리
                alert("이미 존재하는 아이디입니다.");
            } else {
                // 아이디가 사용 가능한 경우에 대한 처리
                alert("사용 가능한 아이디입니다.");
            }
        },
        error: function () {
            // 에러 처리
            alert("서버 오류가 발생했습니다.");
        }
    });
}

function checkUserNickname() {
    var userNickname = $("#nickname").val(); // 입력된 닉네임 값
    $.ajax({
        url: "/register/checkUserNickname/" + userNickname,
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

document.addEventListener("DOMContentLoaded", function() {
    // 에러 메시지가 있을 경우 알림을 표시
    var errorMessage = /*[[${errorMessage}]]*/ '';
    if (errorMessage.trim() !== '') {
        alert(errorMessage);
    }
});

function sendNumber() {
    $("#mail_number").css("display", "block");
    $.ajax({
        url: "/verify-email",
        type: "post",
        dataType: "json",
        data: {"email": $("#email").val()},
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
                // 또는 다른 UI 업데이트 로직을 추가할 수 있습니다.
            } else {
                // 인증 실패 시
                alert("인증에 실패하였습니다. 올바른 인증 코드를 입력해주세요.");
                // 또는 다른 UI 업데이트 로직을 추가할 수 있습니다.
            }
        },
        error: function(xhr, status, error){
            console.log("Error during verification", error);
        }
    });
}