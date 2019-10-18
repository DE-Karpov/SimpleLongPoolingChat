<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chat</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script>
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
                    let tableHtml = "";
                    for (let i = 0; i < response.length; i++) {
                        tableHtml += '<li>' + response[i].login + ": " + response[i].text + '</li>';
                    }
                    $("#messages").html(tableHtml);
                    receiveMessage(token);
                }
            })
        }

        function login(token) {
            let body = {
                token: token,
                text: 'Hi!'
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
        }</script>
</head>
<body onload="login(localStorage.getItem('AUTH'))">
<h1>Current token:</h1>
<div>
    <input id="message" placeholder="Ваше сообщение">
    <button onclick="sendMessage(localStorage.getItem('AUTH').toString(),
            $('#message').val())">Отправить
    </button>
    <a href="/logout">
        <button>Logout</button>
    </a>
</div>
<div>
    <ul id="messages">
    </ul>
</div>
</body>
</html>