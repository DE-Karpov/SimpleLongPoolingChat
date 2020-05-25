<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Login</title>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <script>
        function sendLoginForm() {
            const loginForm = {
                login: $('#login').val(),
                password: $('#password').val()
            };
            $.ajax({
                url: "/login",
                method: "POST",
                data: JSON.stringify(loginForm),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    localStorage.setItem("AUTH", data.value.toString());
                    localStorage.setItem("login", loginForm.login);
                    document.cookie = "AUTH=" + localStorage.getItem("AUTH");
                    window.location.href = "/chat";
                }
            });
        }
    </script>
</head>
<body>
<div class="form-style-3">

    <fieldset>
        <legend>Sign In</legend>
        <label for="login"><span>login</span><input id="login" type="email"
                                                    class="input-field"
                                                    name="login"
                                                    value=""/></label>
        <label for="password"><span>Password</span><input id="password" type="password"
                                                          class="input-field"
                                                          name="password"
                                                          value=""/></label>
    </fieldset>
    <fieldset>
        <label><span> </span>
            <button onclick="sendLoginForm()" type="submit">Submit</button>
        </label>
    </fieldset>

</div>
</body>
</html>