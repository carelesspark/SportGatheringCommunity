$(function () {
  // 업로드 제한
  let regex = new RegExp("(.*?).(exe|sh|zip|alz|tiff)$");
  // 10 MB
  const maxSize = 10485760;

  // 파일 크기 및 확장자 검사
  function checkFile(fileName, fileSize) {
    if (fileSize >= maxSize) {
      alert("파일 사이즈 초과");
      return false;
    }
    if (regex.test(fileName)) {
      alert("해당 종류의 파일은 업로드 불가");
      return false;
    }
    return true;
  }

  // uploadResult class div에 업로드한 이미지 보여주기
  function showResult(uploadResultArr) {
    var uploadUL = $(".uploadResult ul");

    var str = "";
    $(uploadResultArr).each(function (idx, object) {
      str += "<li data-path='" + object.folderPath + "' ";
      str += "data-uuid='" + object.uuid + "' ";
      str += "data-name='" + object.fileName + "'>";

      str += "<div>";

      str +=
        "<img src='/image/display?fileName=" + object.getThumbnailURL() + "'>";

      str += "<button type='button' ";
      str += "data-file='" + object.getImageURL() + "' ";
      str += "class ='btn-warning btn-sm'>";
      str += "삭제";
      str += "</button>";

      str += "</div>";

      str += "</li>";
    });

    uploadUL.append(str);
  }

  // 변화가 있을 때마다
  $(".custom-file-input").on("change", function () {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);

    var formData = new FormData();
    var inputFile = $(this);
    var files = inputFile[0].files;
    var appended = false;

    for (var i = 0; i < files.length; i++) {
      if (!checkFile(files[i].name, files[i].size)) {
        return false;
      }

      formData.append("uploadFiles", files[i]);
      appended = true;
    }

    if (!appended) {
      return;
    }

    for (var value of formData.values()) {
      console.log(value);
    }

    $.ajax({
      url: "/image/upload/club/main",
      processData: false,
      contentType: false,
      data: formData,
      type: "POST",
      dataType: "JSON",
      success: function (result) {
        console.log(result);
        showResult(result);
      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus);
      },
    });
  });

  // 이미지 삭제 함수
  $(".uploadResult").on("click", "ul li button", function () {
    // 물리적인 폴더에서 이미지 삭제
    var targetFile = $(this).data("file");
    // 화면에서 이미지 삭제
    var targetLi = $(this).closest("li");

    $.ajax({
      url: "/image/delete",
      data: { fileName: targetFile },
      type: "POST",
      success: function (data, textStatus) {
        alert(textStatus);
        targetLi.remove();
      },
    });
  });

  $(".submitBtn").on("click", function (event) {
    // <a>, submit 등에서 click 이벤트를 직접 적용할 때 사용
    event.preventDefault();

    var str = "";
    $(".uploadResult li").each(function (idx, obj) {
      var target = $(obj);

      str += "<input type='hidden' ";
      str += "name='imageDTOList[" + idx + "].path' ";
      str += "value='" + target.data("path") + "'>";

      str += "<input type='hidden' ";
      str += "name='imageDTOList[" + idx + "].uuid' ";
      str += "value='" + target.data("uuid") + "'>";

      str += "<input type='hidden' ";
      str += "name='imageDTOList[" + idx + "].name' ";
      str += "value='" + target.data("name") + "'>";
    });
    $(".box").html(str);

    $("form").submit();
    $("form").reset();
  });
});
