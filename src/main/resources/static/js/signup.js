let emptyReg = /\s/g;
let idReg = /^[a-z0-9]{5,20}$/;
let pwdReg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+=?<>.,/|~`])[A-Za-z\d!@#$%^&*()_+=?<>.,/|~`]{8,}$/
let nickReg = /^[a-zA-Zㄱ-ㅣ가-힣\d]{2,10}$/;
let emailReg = /^\w+([\\.-]?\w+)*@\w+([\\.-]?\w+)*(\.\w{2,3})+$/;
const nicknameCheck = $("#nickname-check");
const idCheck = $("#id-check");
const passwordCheck = $("#password-check");
const emailCheck = $("#email-check");

window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');

    Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }
            if (check() === false) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });
}, false);


function idPattern() {
    let idVal = document.getElementById('memberId').value;
    if (!idReg.test(idVal) || emptyReg.test(idVal)) {
        alert('아이디는 영어나 숫자로 5글자에서 20글자로 입력해주세요.')
        return false;
    }
    return true;
}

function nicknamePattern() {
    let nickVal = document.getElementById('nickname').value;
    if (!nickReg.test(nickVal) || emptyReg.test(nickVal)) {
        alert('닉네임은 한글, 영어나 숫자로 2글자 이상 8글자 이하로 입력해주세요.')
        return false;
    }
    return true;
}

function emailPattern() {
    let emailVal = document.getElementById('email').value;
    if (!emailReg.test(emailVal) || emptyReg.test(emailVal)) {
        alert('이메일 형식을 갖춰서 생성해주세요');
        return false;
    }
    return true;
}

function pwdCheck() {
    const pwdVal = document.getElementById('pwd').value;
    const pwdOkVal = document.getElementById('pwd-check').value;

    if (!pwdReg.test(pwdVal) || emptyReg.test(pwdVal)) {
        alert('비밀번호는 문자,숫자,특수문자로 구성된 8글자 이상 20글자 이하로 생성하세요.')
    } else {
        if (pwdOkVal) {
            if (pwdVal !== pwdOkVal) {
                alert('비밀번호가 일치하지 않습니다.')
            } else {
                passwordCheck.value = "1";
                document.getElementById("pwd").readOnly = true;
                document.getElementById("pwd-check").readOnly = true;
                alert("확인되었습니다.");
                if (allCheck()) {
                    $("#submitBtn").removeAttr("disabled");
                }
            }
        } else {
            alert('비밀번호확인을 입력하세요.')
        }
    }
}


function idCheckFunc() {
    const id = $("#memberId").val();
    if (idPattern()) {
        $jLatest.ajax({
            type: "post",
            async: true,
            url: "/idcheck",
            data: {"id": id},
            success: function (result) {
                if (result === true) {
                    idCheck.value = "1";
                    document.getElementById("memberId").readOnly = true;
                    alert("사용 가능한 아이디 입니다.");
                    if (allCheck()) {
                        $("#submitBtn").removeAttr("disabled");
                    }
                } else {
                    alert("이미 사용하는 아이디 입니다.");
                    $('#memberId').val('');
                }
            },
        })
    } else {
        $('#id').val('');
    }
}

function nickCheckFunc() {
    const nickname = $("#nickname").val();

    if (nicknamePattern()) {
        $jLatest.ajax({
            type: "post",
            async: true,
            url: "/nickcheck",
            data: {"nickname": nickname},
            success: function (result) {
                if (result === true) {
                    nicknameCheck.value = "1";
                    document.getElementById("nickname").readOnly = true;
                    alert("사용 가능한 닉네임 입니다.");
                    if (allCheck()) {
                        $("#submitBtn").removeAttr("disabled");
                    }
                } else {
                    alert("이미 사용하는 닉네임 입니다.");
                    $('#nickname').val('');
                }
            },
        })
    }
}

function emailCheckFunc() {
    const email = $("#email").val();

    if (emailPattern()) {
        $jLatest.ajax({
            type: "post",
            async: true,
            url: "/emailcheck",
            data: {"email": email},
            success: function (result) {
                if (result === true) {
                    emailCheck.value = "1";
                    document.getElementById("email").readOnly = true;
                    alert("사용 가능한 이메일 입니다.");
                    if (allCheck()) {
                        $("#submitBtn").removeAttr("disabled");
                    }
                } else {
                    alert("이미 사용하는 이메일 입니다.");
                    $('#email').val('');
                }
            },
        })
    }
}

function allCheck() {
    if (idCheck===0) {
        return false;
    }
    if (passwordCheck===0) {
        return false;
    }
    if (nicknameCheck===0) {
        return false;
    }
    if (emailCheck===0) {
        return false;
    }

    return true;
}

function finalCheck() {
    if (allCheck()) {
        let form = document.getElementById("signupForm");

        form.submit()
    }
}