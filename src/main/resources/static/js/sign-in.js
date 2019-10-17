function login(login, password) {
    let body = {
        login: login,
        password: password
    };

    $.ajax({
        url: "/signIn",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function (response) {
            window.localStorage.setItem("token", response.responseText);
            window.location.replace("/chat?token=" + window.localStorage.getItem("token"));
        }
    });
}