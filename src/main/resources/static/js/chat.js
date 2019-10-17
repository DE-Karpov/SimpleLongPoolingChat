function sendMessage(token, text) {
    let body = {
        token: token,
        text: text
    };

    $.ajax({
        url: "/messages",
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
            $('#messages').first().after('<li>' +  response[0].sender.login + ": " + response[0]['value'] + '</li>');
            receiveMessage(login);
        }
    })
}

function login(token) {
    console.log(token);
    let body = {
        text: 'Hi!',
        token: token
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            receiveMessage(token);
        }
    });
}