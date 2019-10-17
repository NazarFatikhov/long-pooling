<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <title>Chat</title>
</head>
<body>
<div class="container">
    <div class="py-5 text-left">

        <h2>Sign up</h2>

    </div>

    <div class="row">

        <div class="col-md-8 order-md-1">
            <form class="needs-validation" novalidate method="post" action="/signUp">
                <div class="mb-3">
                    <label for="login">Login <span class="text-muted">(Optional)</span></label>
                    <input class="form-control" id="login" placeholder="you@example.com" name="login">
                </div>
                <div class="mb-3">
                    <label for="password">Password</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">SIGN UP</button>
            </form>
        </div>
    </div>
</body>
</html>