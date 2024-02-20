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

function joinUser() {
    var userDTO = {
        userid: $("#id").val(),
        userpwd: $("#pwd").val(),
        name: $("#name").val(),
        nickname: $("#nickname").val(),
        email: $("#email").val(),
        birth: $("#birth").val(),
        gender: $("input[name='gender']:checked").val()
    };

    $.ajax({
        url: "/register/registerform",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(userDTO),
        success: function (response) {
            // 회원가입 성공 시
            alert("회원가입이 완료되었습니다.");
            // 원하는 페이지로 이동 (예: 메인 페이지)
            window.location.href = "/main/main";
        },
        error: function (xhr, status, error) {
            if (xhr.status === 400) {
                // 서버에서 반환한 오류 메시지를 알림창으로 표시
                alert(xhr.responseText);
            } else {
                // 그 외의 에러 처리
                alert("서버 오류가 발생했습니다.");
            }
        }
    });
}