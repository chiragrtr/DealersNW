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
    <script src="js/myBroadcasts.js"></script>
    <script src="js/othersBroadcasts.js"></script>
    <script src="js/autoLoad.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
<body onload="autoCalls()">
<%
    if(session.getAttribute("dealerId") == null){
        response.sendRedirect("index.html");
    }
%>
<div id="myNavbarDiv">
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Welcome <%=session.getAttribute("dealerName")%></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class=" navbar-right">
                <li><div id="myLogoutDiv"><a href="logout.do">Log out</a></div></li>
            </ul>
        </div>
    </div>
</nav>
</div>

<div class = "container-fluid">

    <div class="row">
        <div id="newBroadcastDiv">
        <div class="col-lg-6">
            <div id="createNewBroadcast" >
                <form class="form-inline">
                    <div class='form-group'><%--Select Make--%>
                        <b>Your requirement:</b>
                        <select class='form-control' id='make' name='make'>
                            <option><label>Select Your Make</label></option>
                            <option>Mercedez</option>
                            <option>BMW</option>
                            <option>Tesla</option>
                        </select>
                        <%--Select Model--%><select class='form-control' id='model' name = 'model'><option>Select Your Model</option> </select>
                        <%--Select Color--%><select class='form-control' id = 'color' name='color'>
                            <option>Select color</option>
                            <option>Red</option>
                            <option>Black</option>
                            <option>White</option>
                            <option>Silver</option>
                        </select>
                        <button type="button" name="newBroadcast_Btn" onclick="createBroadcast()">Broadcast</button>
                    </div>
                </form>
                <%-- <button class="well">Edit Profile</button><br>
                <button class="well" onclick="showBroadcastForm()">New BroadCast</button><br>
                &lt;%&ndash;$('#myLink').click(function(){ MyFunction(); return false; });-- to call function on href onclick()&ndash;%&gt;
                <button class="well" onclick="showMyBroadcastDiv()">My Broadcasts</button>--%>
            </div>
        </div>
        </div>
        <div id="showBroadcastDiv">
        <div class="col-lg-6">
            <div>
                <p class="myPanel">OTHERS' BROADCASTS<span style="float: left"><input type="radio" name="chiragRadio" id="r1" value="broadcasts" onclick=myFun() checked>All</span> <span style="float: right;"><input type="radio" name="chiragRadio" id="r2" value="bids" onclick=showMyBids(document.getElementById("myPara").innerHTML)>Your Bids</span> </p>
                <%--<p class="myPanel">Show Broadcasts</p>--%>
                <select id="openOrClosed" onchange="showOthersBroadcasts(this.value)">
                    <option value="open" selected>Open</option>
                    <option value="closed">Closed</option>
                    <%--<option value="all">All</option>--%>
                </select>

            </div>
            <div id="othersBroadcasts"></div>
        </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <p id="myPara" style="visibility: hidden"><%=session.getAttribute("dealerId")%></p>
            <%--<button type = button onclick="showMyOpenBroadcasts()">Show my broadcasts</button>--%>
            <%--<br><br>--%>
            <div>
                <p class="well"><b>Your Broadcasts</b><br><br><br>
                <span id ="bidsNotification"></span></p>
                <select id="myOpenOrClosed" onchange="showMyBroadcasts()">
                    <option value="open" selected>Open</option>
                    <option value="closed">Closed</option>
                    <%--<option value="all">All</option>--%>
                </select>

            </div>
            <div id="myBroadcast">

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
        createBroadcastAjax();
        //setTimeout(showMyOpenBroadcasts,500);
        setTimeout(showMyBroadcasts,500);
    }

    function createBroadcastAjax(){
        var data = {
            "make" : $("#make").val(),
            "model" :$("#model").val(),
            "color" : $("#color").val()
        };
        if(data.make == "Select Your Make" || data.model == "Select Your Model" || data.color == "Select color"){
            alert("Please select options");
            return;
        }
        $.ajax({
            url:"/createBroadcast.do",
            data : data,
            dataType: "text",
            type : "POST",

            success: function (result) {
                console.log("Broadcast added with id = " + result);
            },

            error: function (result) {
                console.log("error");
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
