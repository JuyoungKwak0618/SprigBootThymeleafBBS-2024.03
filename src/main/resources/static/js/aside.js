/*
* Aside menu control
*/
$(document).ready(function() {            // 이벤트 등록
  $('#stateMsgBtn').click(function(e) {
    $('#stateMsgInput').attr({'class' : 'mt-2'});    // 입력창이 보이게
    $('#stateInput').val($('#stateMsg').text());  // 입력창에 stateMsg 내용이 보이게
  });
  $('#stateMsgSubmit').click(changestateMsg);   // 이벤트 등록
  $('#getWeatherButton').click(getWeather);
});

function changestateMsg() {
  let stateInputVal = $('#stateInput').val();   // 사용자가 수정한 글 읽기
  $('#stateMsgInput').attr({'class' : 'mt-2 d-none'}); // 입력창 감추기
  $.ajax({      // Asynchronous Javascript and XML, 화면의 일부부만 바꿀 때 주로 사용
    type:'GET',
    url: '/abbs/aside/stateMsg',
    data: {stateMsg: stateInputVal},
    success: function(result) {
      console.log('state message:', stateInputVal, result);
      $('#stateMsg').html(stateInputVal);
    }
  });
}

function getWeather() {
  $.ajax({
    type: 'GET',
    url: '/abbs/aside/weather',
    success: function(result) {
      $('#weather').html(result);
    }
  });
}

function uploadProfileImage() {
  var fileInput = document.getElementById('profileImageInput');
  var file = fileInput.files[0];
  if (file) {
    var formData = new FormData();
    formData.append('profile', profile);

    $.ajax({
        type: 'POST',
        url: '/abbs/aside/uploadProfileImage', // 이미지 업로드를 처리하는 컨트롤러의 URL로 변경해야 합니다.
        data: formData,
        dataType:'json',
        processData: false,
        contentType: false,
        success: function (data) {
            // 성공 시 처리
            console.log('이미지 업로드 성공:', data);
            // 필요한 경우 페이지를 새로고침하거나 이미지를 변경하는 등의 작업을 수행할 수 있습니다.
        },
        error: function (error) {
            // 실패 시 처리
            console.error('이미지 업로드 실패:', error);
        }
    });
} else {
    console.warn('파일을 선택하세요.');
}
}
