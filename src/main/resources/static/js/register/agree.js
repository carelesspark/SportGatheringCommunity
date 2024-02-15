document.addEventListener("DOMContentLoaded", function () {
    var checkAll = document.getElementById("checkAll");
    var checkboxes = document.getElementsByName("agreement");
    var privacyPolicyCheckbox = document.getElementById("privacyPolicy");
    var termsOfServiceCheckbox = document.getElementById("termsOfService");
    var allowPromotionsCheckbox = document.getElementById("allowPromotions");
    var submitButton = document.querySelector(".next-button");

    // 체크박스 전체 선택/해제 기능
    checkAll.addEventListener("change", function () {
        checkboxes.forEach(function (checkbox) {
            checkbox.checked = checkAll.checked;
        });

        updateSubmitButton();
    });

    // 개별 체크박스가 모두 체크되었을 때 확인 버튼 활성화
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener("change", updateSubmitButton);
    });

    // privacyPolicy, termsOfService 체크박스가 모두 체크되었을 때 확인 버튼 활성화
    privacyPolicyCheckbox.addEventListener("change", updateSubmitButton);
    termsOfServiceCheckbox.addEventListener("change", updateSubmitButton);

    // allowPromotions 체크박스의 체크 상태가 해제되면 checkAll 체크박스도 해제
    allowPromotionsCheckbox.addEventListener("change", function () {
        if (!allowPromotionsCheckbox.checked) {
            checkAll.checked = false;
        }
        updateSubmitButton();
    });

    // 다른 체크박스의 체크 상태가 해제되면 checkAll 체크박스도 해제
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener("change", function () {
            if (!checkbox.checked) {
                checkAll.checked = false;
            }
            updateSubmitButton();
        });
    });

    // 확인 버튼 활성/비활성 기능
    function updateSubmitButton() {
        var privacyPolicyChecked = privacyPolicyCheckbox.checked;
        var termsOfServiceChecked = termsOfServiceCheckbox.checked;
        submitButton.disabled = !privacyPolicyChecked || !termsOfServiceChecked;

        // checkAll 체크박스의 상태 업데이트
        checkAll.checked = checkboxes.every(function (checkbox) {
            return checkbox.checked;
        });
    }

    updateSubmitButton(); // 페이지 로드 시 초기 상태 설정
});