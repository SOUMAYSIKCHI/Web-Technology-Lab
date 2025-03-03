<html>

<head>
    <title>Login </title>
</head>

<body>
    <form name="form1" method="post">
        <table align="center" width="80%" border="0" cellpadding="3" cellspacing="1">
            <tr>
                <td colspan=2 align="center">
                    <b>
                        <h3>Member Login</h3>
                    </b>
                </td>
            </tr>
            <br />
            <tr>
                <td>Username : </td>
                <td><input name="uname" type="text"></td>
            </tr>
            <tr>
                <td>Password : </td>
                <td><input name="pwd" type="password"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="Submit" value="Login"></td>
            </tr>
        </table>
    </form>
    <?php
    if (isset($_POST["Submit"])) {
        $uname = $_POST["uname"];
        $pwd = $_POST["pwd"];
        $xml = simplexml_load_file("users.xml") or die("Error: Cannot create object");
        foreach ($xml->children() as $users) {
            if ($users->userid == $uname) {
                if ($users->password == $pwd) {
                    echo "<center><b>Hello.." . $uname . ", ";
                    echo "Login Successful</b></center> ";
                    return;
                } else {
                    echo "<center><b>Invalid Password...</b></center>";
                    return;
                }
            }
        }
        echo "<center><b>Invalid Login..</b></center>";
    }
    ?>
</body>

</html>