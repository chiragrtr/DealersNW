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
            <button class="well"><a href="#createNewBroadcast">New BroadCast</a></button><br>
            <button class="well">My Broadcasts</button>
        </div>
        <div class="col-sm-6 text-left" id="display">
            <div id="createNewBroadcast">
                <form action='createBroadcast.do' method='post'>
                    <div class='form-group'>Select Make:</label>
                        <select class='form-control' id='make'>
                            <option><label>Select Your Make</label></option>
                            <option>Mercedez</option>
                            <option>BMW</option>
                            <option>Tesla</option>
                        </select>
                        <select class='form-control' id='model'> <label>Select Model</label></select>
                    </div>
                </form>
            </div>

        <!-- div to add new functionality-->
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
<script type="text/javascript">


    var option = "";
    $("#make").on('change',function () {
        var value = $(this).val();
        if(value == "BMW"){
            options="<option>Select Your Model</option>"
                    +"<option value='BMW1'>BMW 1</option>"
                    +"<option value='BMW2'>BMW 2</option>" +
                    "<option value='BMW3'>BMW 3</option>";
            $("#model").html(options);
        }else if(value=="Mercedez")
        {
            options='<option>Select Your Model</option>'
                    +'<option value="Mercedez 1">Mercedez 1</option>'
                    +'<option value="Mercedez 2">Mercedez 2</option>';
            $("#model").html(options);
        }else if(value=="Tesla")
        {
            options='<option>Select Your Model</option>'
                    +'<option value="Tesla1">Tesla 1</option>'
                    +'<option value="Tesla2">Tesla 2</option>';
            $("#model").html(options);
        }
    });


</script>
</html>
