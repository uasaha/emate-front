let nickReg = /^[a-zA-Zㄱ-ㅣ가-힣\d]{2,8}$/;
let emptyReg = /\s/g;
let pwdReg = /^[a-zA-Z0-9\d]{4,8}$/;

function viewRegisterBtnAn() {
    const btn = document.getElementById("comment-register-btn-an");
    btn.style.display = "contents";
}

function viewRegisterBtnMe() {
    const btn = document.getElementById("comment-register-btn-me");
    btn.style.display = "contents";
}

function viewReplyAn(reply) {
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
            '                    <div class="comment-head">\n' +
            '                        <label class="width-80">\n' +
            '                            <input id="child-comment-content" type="text" name="content" class="color-white width-80 comment-text-box" placeholder="댓글 추가...">\n' +
            '                            <i id="comment-register-btn" class="bi bi-arrow-up-square color-white font-25" onclick="childCommentSubmit()"></i>\n' +
            '                        </label>\n' +
            '                    </div>\n' +
            '                </form>';

        mom.appendChild(replyDiv);
        mom.style.borderBottom = "0px solid white";
        document.getElementById("momContentsNo").value = document.getElementById("contentsNo-an").value;
        document.getElementById("momCommentNo").value = reply.id;
    } else {
        const replyDiv = document.getElementById('replyDiv');
        replyDiv.remove();
        mom.style.borderBottom = "1px solid #404040";
    }
}

function viewReplyMe(reply) {
    console.log(reply.id);
    const mom = document.getElementById('mom' + reply.id);
    const memberNo = document.getElementById("memberNo").value;
    console.log(memberNo);
    const memberNick = document.getElementById("memNick").value;

    if (document.getElementById('replyDiv') == null) {
        const replyDiv = document.createElement('div');
        replyDiv.classList.add("col-md-12");
        replyDiv.style.paddingTop = "1rem";
        replyDiv.id = 'replyDiv';
        replyDiv.innerHTML = '<form id="child-form" action="/comments/member" method="post">\n' +
            '                    <div>\n' +
            '                        <input class="display-none" name="contentsNo" id="momContentsNo-me">\n' +
            '                        <input class="display-none" name="momNo" id="momNo-me" value="">\n' +
            '                        <input class="display-none" name="memberNo" value="' + memberNo +'">\n' +
            '                        <div style="display: inline-block">' +
            '                            <img src="/static/img/favicon/favicon-32x32.png" class="comment-nick-image">' +
            '                            <span class="color-white comment-nick">' + memberNick + '</span>\n' +
            '                        </div>' +
            '                    </div>\n' +
            '                    <div class="form-check form-switch">\n' +
            '                        <input id="secret-check-child" class="form-check-input" type="checkbox" onclick="setSecretChild(this)">\n' +
            '                        <input class="display-none" id="real-secret-check-child" type="text" name="secret" value="false">\n' +
            '                        <label for="secret-check-child" class="color-white form-check-label">비밀 댓글</label>\n' +
            '                    </div>\n' +
            '                    <div class="comment-head">\n' +
            '                        <label class="width-80">\n' +
            '                            <input id="child-comment-content-me" type="text" name="content" class="color-white width-80 comment-text-box" placeholder="댓글 추가...">\n' +
            '                            <i id="comment-register-btn-me" class="bi bi-arrow-up-square color-white font-25" onclick="memberChildCommentSubmit()"></i>\n' +
            '                        </label>\n' +
            '                    </div>\n' +
            '                </form>'

        mom.appendChild(replyDiv);
        mom.style.borderBottom = "0px solid white";
        document.getElementById("momContentsNo-me").value = document.getElementById("contentsNo-me").value;
        document.getElementById("momNo-me").value = reply.id;
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

function memberChildCommentSubmit() {
    showSpinner();
    const childForm = document.getElementById("child-form");

    if(memberTesting("child-comment")) {
        childForm.submit();
    }
}


function anonymousCommentSubmit() {
    showSpinner();
    const momForm = document.getElementById("mom-form-an");

    if (totalTesting("mom-comment")) {
        momForm.submit();
    }
}

function memberCommentSubmit() {
    showSpinner();
    const momForm = document.getElementById("mom-form-me");

    if (memberTesting("mom-comment")) {
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

function isValidContentForMember(idStr) {
    const content = document.getElementById(idStr + "-content-me").value;
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

function memberTesting(idStr) {
    return isValidContentForMember(idStr);
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

function setSecretChild(secret) {
    const realChild = document.getElementById("real-secret-check-child");

    if (secret.checked) {
        realChild.value = true;
    } else {
        realChild.value = false;
    }
}
