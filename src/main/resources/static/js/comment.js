let nickReg = /^[a-zA-Zㄱ-ㅣ가-힣\d]{2,10}$/;
let emptyReg = /\s/g;
let pwdReg = /^[a-zA-Z0-9\d]{4,8}$/;

function viewRegisterBtn() {
    const btn = document.getElementById("comment-register-btn");
    btn.style.display = "contents";
}

function viewReply(reply) {
    const mom = document.getElementById('mom' + reply.id);

    if (document.getElementById('replyDiv') == null) {
        const replyDiv = document.createElement('div');
        replyDiv.classList.add("col-md-12");
        replyDiv.style.paddingTop = "1rem";
        replyDiv.id = 'replyDiv';
        replyDiv.innerHTML = '<form id="child-form" action="/comments/anonymous" method="post">\n' +
            '                    <div>\n' +
            '                        <input class="display-none" name="contentsNo" id="momContentsNo">\n' +
            '                        <input class="display-none" name="momNo" id="momCommentNo">\n' +
            '                        <label class="color-white">닉네임\n' +
            '                            <input id="child-comment-nick" type="text" name="nickName" class="color-white max-width-10 comment-text-box" placeholder="2~8자">\n' +
            '                        </label>\n' +
            '                        <label class="color-white">비밀번호\n' +
            '                            <input id="child-comment-password" type="password" name="password" class="color-white max-width-10 comment-text-box" placeholder="4~8자">\n' +
            '                        </label>\n' +
            '                    </div>\n' +
            '                    <div class="form-check form-switch">\n' +
            '                        <input id="secret-check" class="form-check-input" type="checkbox" name="secret">\n' +
            '                        <input type="hidden" name="_secret" value="on">\n' +
            '                        <label for="secret-check" class="color-white form-check-label">비밀 댓글</label>\n' +
            '                    </div>\n' +
            '                    <div class="comment-head">\n' +
            '                        <label class="width-80">\n' +
            '                            <input id="child-comment-content" type="text" name="content" class="color-white width-80 comment-text-box" placeholder="댓글 추가...">\n' +
            '                            <i id="comment-register-btn" class="bi bi-arrow-up-square color-white font-25" onclick="childCommentSubmit()"></i>\n' +
            '                        </label>\n' +
            '                    </div>\n' +
            '                </form>';

        mom.appendChild(replyDiv);
        mom.style.borderBottom = "0px solid white";
        document.getElementById("momContentsNo").value = document.getElementById("contentsNo").value;
        document.getElementById("momCommentNo").value = reply.id;
    } else {
        const replyDiv = document.getElementById('replyDiv');
        replyDiv.remove();
        mom.style.borderBottom = "1px solid #404040";
    }
}

function childCommentSubmit() {
    showSpinner();
    const childForm = document.getElementById("child-form");

    if (totalTesting("child-comment")) {
        childForm.submit();
    }
}


function commentSubmit() {
    showSpinner();
    const momForm = document.getElementById("mom-form");

    if (totalTesting("mom-comment")) {
        momForm.submit();
    }
}

function isValidNickname(idStr) {
    const nickname = document.getElementById(idStr + "-nick").value;
    if (!nickReg.test(nickname) || emptyReg.test(nickname)) {
        alert('닉네임은 한글, 영어나 숫자로 2글자 이상 10글자 이하로 입력해주세요.');
        hideSpinner()
        return false;
    }

    return true;
}

function isValidPassword(idStr) {
    const password = document.getElementById(idStr + "-password").value;
    if (!pwdReg.test(password) || emptyReg.test(password)) {
        alert('비밀번호는 영어나 숫자로 4글자 이상 8글자 이하로 입력해주세요.');
        hideSpinner()
        return false;
    }
    return true;
}

function isValidContent(idStr) {
    const content = document.getElementById(idStr + "-content").value;
    if (content == null || content === "") {
        alert('내용을 입력해주세요.');
        hideSpinner();
        return false;
    }
    return true;
}

function totalTesting(idStr) {
    return isValidNickname(idStr) && isValidPassword(idStr) && isValidContent(idStr);
}

function showSpinner() {
    document.getElementsByClassName('layerPopup')[0].style.display='block';
}

function hideSpinner() {
    document.getElementsByClassName('layerPopup')[0].style.display='none';
}

function setSecret(secret) {
    const real = document.getElementById("real-secret-check");

    if (secret.checked) {
        real.value = true;
    } else {
        real.value = false;
    }
}