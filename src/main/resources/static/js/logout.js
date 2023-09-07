function logout() {
    alert("로그아웃");

    $jLatest.ajax({
        type: "get",
        async: true,
        url: "/logout"
    })
}