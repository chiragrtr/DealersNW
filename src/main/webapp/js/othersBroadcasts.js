/**
 * Created by malir on 9/1/2016.
 */

function myFun(){
    document.getElementById("openOrClosed").value = "open";
    showOthersBroadcasts("open");
}

function showMyBids(dealerId){
    document.getElementById("openOrClosed").style.visibility = "hidden";
    console.log(dealerId);
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            console.log(xmlHttp.responseText);
            var htmlText = "<div class='col-xs-12 col-sm-12' style='float:left'><div class='panel-group'>";
            if (xmlHttp.responseText != "") {
                var records = eval(xmlHttp.responseText);
                console.log(records);
                for (i = 0; i <records.length; i++) {
                    htmlText += "<div class ='panel panel-default'><ul class='myUl'>" + "<p>"+ " BROADCAST BY: " + records[i].name + " Contact Number: " + records[i].phone + " Email Address: " + records[i].email;
                    i++;
                    htmlText +=" MAKE: " + records[i].make + "&nbsp MODEL: " + records[i].model + "&nbsp COLOR: " + records[i].color + "&nbsp DATE OF BROADCAST: " + records[i].broadcastDate + "</p>";
                    i++;
                    if(records[i].status == 0){
                        bidStatus = "Broadcast is still open";
                    }
                    else if(records[i].status == 1){
                        bidStatus = "<font color=#20b2aa>Your bid was selected</font>";
                    }
                    else{
                        bidStatus = "<font color=red>Sorry, your bid wasn't selected</font>";
                    }
                    var days = parseInt(records[i].deliveryHours / 24);
                    var hours = records[i].deliveryHours % 24;
                    htmlText += "<mark>YOUR BID:</mark><BR>" + "<p>" + "Price: " + records[i].price + " Delivery Days: " + days + " hours: " + hours + " Bid Date: " + records[i].bidDate + "<br> Status: " + bidStatus + "</p>" + "</ul></div>";
                }
            }
            htmlText += "</div></div>";
            document.getElementById("othersBroadcasts").innerHTML = htmlText;
        }
    };
    xmlHttp.open("post", "showMyBids.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("dealerId=" + dealerId);
}
function placeThisBid(formNum) {
    var errorField = document.getElementById("errorMessage"+formNum);
    errorField.innerHTML = "";
    //alert("hi");
    var price = document.getElementById("price" + formNum).value;
    //var price=document.formNum.price.value;
    //alert(price);
    var days = document.getElementById("days" + formNum).value;
    var hours = document.getElementById("hours" + formNum).value;
    var broadcastId = document.getElementById("broadcastId" + formNum).innerHTML;
    //alert(broadcastId);

    var deliveryHours = parseInt(days) * 24 + parseInt(hours);
    if(price == "" || days == "" || hours == ""){
        errorField.innerHTML = "All fields are compulsory";
        return;
    }
    if(deliveryHours == 0){
        errorField.innerHTML = "Delivery time can not be zero";
        return;
    }
    //alert(broadcastId + price + deliveryHours);

    document.getElementById("myForm" + formNum).innerHTML = "<br><br><b>Bid placed, check it in your bids section</b>";
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            console.log("Bid placed");
        } else {
            //alert("Failure");
        }
    };

    xmlHttp.open("post", "setBid.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("broadcastId=" + broadcastId + "&price=" + price + "&deliveryHours=" + deliveryHours);
}
function showOthersBroadcasts(value) {
    document.getElementById("openOrClosed").style.visibility="visible";
    /*console.log(1);
     //$("#choice").on('change',function () {
     alert("changed");
     //var value = $(this).val();*/

    var dealerId = document.getElementById("myPara").innerHTML;
    //alert(dealerId);
    if (value == "open") {

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            var f = 0;
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var htmlText = "";
                if (xmlHttp.responseText != "") {
                    var records = eval(xmlHttp.responseText);
                    for (i = records.length-1; i >= 0; i--) {
                        /*alert(records[i].dealerName);*/
                        htmlText += "<p><div class ='panel panel-default'><ul class='myUl'>";
                        htmlText +=" BROADCAST BY: " + records[i].name + " Contact Number: " + records[i].phone + " Email Address: " + records[i].email + "<BR>";
                        i=i-1;
                        htmlText += "MAKE: " + records[i].make + " MODEL: " + records[i].model + " COLOR: " + records[i].color + "<BR>" + " DATE OF BROADCAST: " + records[i].broadcastDate;

                        htmlText += "<form id='myForm" + (++f) + "'><p id =broadcastId" + f + " style='visibility:hidden'>" + records[i].broadcastId + "</p>" +
                            "<input type='number' id =price" + f + " name='price' placeholder='price' min='0.1' step='any' oninput='validity.valid||(value=\"\")'>" +
                            "<input type='number' id =days" + f + " name='days' placeholder='days' min='0' oninput='validity.valid||(value=\"\")'>" +
                            "<input type='number' id =hours" + f + " name='hours' placeholder='hours' min='0' max='23' oninput='validity.valid||(value=\"\")'>&nbsp" +
                            "<button type='button' onclick=placeThisBid(" + f + ")>Bid</button></form><span id=errorMessage"+ f + " style='color: red'></span> " +
                            "</ul></div></p>";
                    }
                }
                document.getElementById("othersBroadcasts").innerHTML = htmlText;
            }
        };

        xmlHttp.open("post", "showOthersOpenBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        /*headers : {
         'Content-Type' :'application/json'
         },*/
        xmlHttp.send("id=" + dealerId);

    } else if (value == "closed") {
        console.log(dealerId);
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                console.log(xmlHttp.responseText);
                console.log(2);
                var htmlText = "";
                if (xmlHttp.responseText != "") {
                    var records = eval(xmlHttp.responseText);
                    for (i = records.length-1; i >= 0; i--) {
                        htmlText += "<p><div class ='panel panel-default'><ul class='myUl'>";
                        htmlText +=" BROADCAST BY: " + records[i].name + " Contact Number: " + records[i].phone + " Email Address: " + records[i].email + "<BR>";
                        i=i-1;
                        htmlText += "MAKE: " + records[i].make + " MODEL: " + records[i].model + " COLOR: " + records[i].color + "<BR>" + " DATE OF BROADCAST: " + records[i].broadcastDate + "</ul></div></p>";
                    }
                }
                document.getElementById("othersBroadcasts").innerHTML = htmlText;
            }
        };
        xmlHttp.open("post", "showOthersClosedBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("id=" + dealerId);
    }
}

