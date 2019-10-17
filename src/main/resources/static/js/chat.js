function sendMessage(text, token) {
    let body = {
        text: text
    };

    $.ajax({
        url: "/messages?token=" + token,
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
        }
    });
}
function receiveMessage(token) {
    $.ajax({
        url: "/messages?token=" + token,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['login'] + ": "+ response[0]['text'] + '</li>')
            receiveMessage(token);
        }
    })
}

function login(token) {
    let body = {
        text: 'Connect to chat'
    };

    $.ajax({
        url: "/messages?token=" + token,
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            receiveMessage(token);
        }
    });
}
//
// $(document).ready(function () {
//    sendMessage(pageId, 'Hi');
//    receiveMessage(pageId);
// });