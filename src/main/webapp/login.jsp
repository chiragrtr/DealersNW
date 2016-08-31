<%--
  Created by IntelliJ IDEA.
  User: malir
  Date: 8/31/2016
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        /*function login() {

            var email=document.forms[0].email.value;
            var password = document.forms[0].password.value;

            var xmlHttp = new XMLHttpRequest();
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var id = xmlHttp.responseText;
                    if(id == 0){
                        alert("Incorrect Email ID or password ");
                    } else {


                    }
                }
            }

            xmlHttp.open("post", "login.do", true);
            xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send("email="+email+"&password="+password);

        }*/


    </script>
</head>
<body onload="document.getElementById('login').style.display='block'">


<div id="login" class="modal">

    <form class="modal-content animate" action="login.do">

        <div class="container">
            <label><b>Email ID</b></label>
            <input type="email" placeholder="Enter Email ID" name="email" required>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <input type="submit">Login</input>
            <input type="button" >Register</input>
        </div>

    </form>
</div>


</body>
</html>
