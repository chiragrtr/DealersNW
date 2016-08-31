<%--
  Created by IntelliJ IDEA.
  User: malir
  Date: 8/31/2016
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/home.css">
        /* Remove the navbar's default margin-bottom and rounded borders */

    <script type="text/javascript">

        function createBroadcast() {
            var htmlText="";
            htmlText = "<form action='createBroadcast.do' method='post'>" +
                    "<div class='form-group'>Select Make:</label>"+
                    "<select class='form-control' id='make'>"+
                    "<option>Mercedez</option> <option>BMW</option> <option>Tesla</option></select>"+
                    "<div class='form-group'>Select Model:</label>";
        }

        function myBroadcasts() {

        }
    </script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Dealer Name</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Log out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <button class="well">Edit Profile</button><br>
            <button class="well" onclick="createBroadcast()">New Broadcast</button><br>
            <button class="well" onclick="myBroadcasts()">My Broadcasts</button>
        </div>
        <div class="col-sm-6 text-left" id="display">

        </div>
        <div class="col-sm-4 sidenav">
            <div>
                <p class="well">Show
                    <select>
                        <option value="open">Open</option>
                        <option value="closed">Closed</option>
                        <option value="all">All</option>
                    </select>
                    Broadcasts</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
