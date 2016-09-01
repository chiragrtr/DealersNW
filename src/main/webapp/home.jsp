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
    <link rel="stylesheet" href="bootstrap/bootstrap.css">
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
    <script src="js/jquery-3.1.0.min.js"></script>
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
       <div class="row">
        <div class="col-sm-12 sidenav ">
            <div id="createNewBroadcast" >
                <form class="form-inline">
                    <div class='form-group'><label>Select Make</label>
                        <select class='form-control' id='make' name='make'>
                            <option><label>Select Your Make</label></option>
                            <option>Mercedez</option>
                            <option>BMW</option>
                            <option>Tesla</option>
                        </select>
                        <label>Select Model</label><select class='form-control' id='model' name = 'model'> </select>
                        <label>Select Color</label><select class='form-control' id = 'color' name='color'>
                            <option>Red</option>
                            <option>Black</option>
                            <option>White</option>
                            <option>Silver</option>
                        </select>
                        <button type="button" name="newBroadcast_Btn" onclick="createBroadcast()">Create Broadcast</button>
                    </div>
                </form>
            </div>

            <%-- <button class="well">Edit Profile</button><br>
            <button class="well" onclick="showBroadcastForm()">New BroadCast</button><br>
            &lt;%&ndash;$('#myLink').click(function(){ MyFunction(); return false; });-- to call function on href onclick()&ndash;%&gt;
            <button class="well" onclick="showMyBroadcastDiv()">My Broadcasts</button>--%>
        </div>
       </div>
        <div class="col-sm-7 text-left" id="display">

            <div id="myBroadcast" hidden>
                <table id = 'myBroadcastTable' border = "1px solid black" class="table table-bordered table-hover">
                    <tr>
                        <thead>Make</thead>
                        <thead>Model</thead>
                        <thead>Color</thead>
                        <thead>Date</thead>
                        <thead>Status</thead>
                    </tr>
                </table>
            </div>
        <!-- div to add new functionality-->
        </div>
        <div class="col-sm-5 sidenav">
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
    function showBroadcastForm() {
        $("#myBroadcast").hide();
        $("#createNewBroadcast").show();
    }
    function showMyBroadcastDiv(){
        $("#createNewBroadcast").hide();
        $("#myBroadcast").show();

    }
    function createBroadcast(){
        var data = {
            "make" : $("#make").val(),
            "model" :$("#model").val(),
            "color" : $("#color").val()
        };
        $.ajax({
            url:"/createBroadcast.do",
            data : data,
            dataType: "text",
            type : "POST",

            success: function (result) {

            },

            error: function (result) {
                alert("error");
                console.log(result);
            },

        });
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
