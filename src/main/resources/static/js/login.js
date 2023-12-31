const idInputForEvent = document.getElementById("log-id");
const pwdInputForEvent = document.getElementById("log-pwd");

function loginSubmit() {
  showSpinner();
  let idInput = document.getElementById("log-id");
  let pwdInput = document.getElementById("log-pwd");

  let id = idInput.value;
  let pwd = pwdInput.value;

  if (id === "") {
    alert('아이디를 입력해 주세요');
  } else if (pwd === "") {
    alert('패스워드를 입력해 주세요');
  } else {
    $jLatest.ajax({
      type: "post",
      async: true,
      url: "/auth",
      data: {
        "id": id,
        "pwd": pwd
      },
      success: function (data, textStatus, request) {
        if (request.getResponseHeader("X-LOGIN") === null) {
          hideSpinner();
          alert('아이디나 패스워드가 잘못 입력되었습니다.');
        } else {
          window.location.href = "/";
        }
      }
    })
  }
}

idInputForEvent.addEventListener("keydown", function withEnter() {
  if (event.keyCode === 13) {
    const loginButton = document.getElementById("login-button");
    loginButton.click();
  }
});

pwdInputForEvent.addEventListener("keydown", function withEnter() {
  if (event.keyCode === 13) {
    const loginButton = document.getElementById("login-button");
    loginButton.click();
  }
});

function showSpinner() {
  document.getElementsByClassName('layerPopup')[0].style.display = 'block';
}

function hideSpinner() {
  document.getElementsByClassName('layerPopup')[0].style.display = 'none';
}