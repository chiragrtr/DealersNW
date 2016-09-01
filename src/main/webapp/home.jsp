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
<nav class="navbar navbar-fixed-top navbar-inverse">
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

<div class="container-fluid text-center navbar-default">
    <div class="row content">
        <div class="col-sm-2 sidenav ">
            <button class="well">Edit Profile</button><br>
            <button class="well"><a href="#createNewBroadcast">New BroadCast</a></button><br>
            <button class="well">My Broadcasts</button>
        </div>
        <div class="col-sm-6 text-left" id="display">
            <div id="createNewBroadcast">
                <form name="createBroadcast">
                    <div class='form-group'><label>Select Make</label>
                        <select class='form-control' id='make' name='make'>
                            <option><label>Select Your Make</label></option>
                            <option>Mercedez</option>
                            <option>BMW</option>
                            <option>Tesla</option>
                        </select>
                        <label>Select Model</label><br><select class='form-control' id='model' name = 'model'> </select>
                        <label>Select Color</label><br><select class='form-control' id = 'color' name='color'>
                            <option>Red</option>
                            <option>Black</option>
                            <option>White</option>
                            <option>SIlver</option>
                        </select>
                        <input type="button" name="newBroadcast_Btn" value="newBroadcast_Btn" onclick="createBroadcast2()">
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
    function createBroadcast(){
        var data = {
            "make" : $("#make").val(),
            "model" :$("#model").val(),
            "color" : $("#color").val()
        };
        $.ajax({
            url:"createBroadcast.do",
            data : data,
            dataType: "json",
            type : "post",
            success: function (result) {
                alert("success");
            },
            error: function (result) {
                alert("error");
            }

        });
        alert($("#make").val());
        alert($("#model").val());
        alert($("#color").val());

    }

    function createBroadcast2() {
        var make=document.createBroadcast.make.value;
        var model = document.createBroadcast.model.value;
        var color=document.createBroadcast.color.value;
        alert(make + model + color);
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                //if(xmlHttp.responseText.localeCompare("success")){
                console.log(xmlHttp.responseText);
                alert("success");

            } else {
                console.log(xmlHttp.responseText);
                alert("failure");
            }
        }

        xmlHttp.open("post", "createBroadcast.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("make="+make+"&model="+model+"&color="+color);

    }
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
