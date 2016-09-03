/**
 * Created by malir on 9/1/2016.
 */

function selectBidAjax(bidId) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var records = xmlHttp.responseText;
            console.log(records);
        }
    };
    xmlHttp.open("post", "selectDeal.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("bidId=" + bidId);
}

function selectBid(bidId){
    selectBidAjax(bidId);
    setTimeout(showMyBroadcasts,500);
}


function createOpenBroadcastList(records) {
    var htmlText = "<div class='col-xs-12 col-sm-12' style='float:left'><div class='panel-group'>";

    for (i = 0; i < records.length; i++) {
        htmlText +="<div class ='panel panel-default'>" +
            "<ul class='myUl'>";

        var totalBids = records[i].totalBids;
        htmlText += "<p>" + records[i].make + " " + records[i].model + " " + records[i].color + " " + records[i].broadcastDate + " " + totalBids + "";
        if (totalBids > 0) {
            htmlText += "<div id='myUlDiv'><ul class='nav'>";
            for (j = 0; j < totalBids; j++) {
                i++;
                htmlText += "<div class='panel-body'><li>" + "Bidder id: " + records[i].dealerId + " Bid Price: " + records[i].price + " Delivery hours: " + records[i].deliveryHours + " Bid date: " + records[i].bidDate + "<button onclick = selectBid(" + records[i].bidId + ")>Select this bid</button>" + "</li></div>";
            }
            htmlText += "</ul></div>";
        }
        htmlText += "</p>";
        htmlText += "</ul></div>";
    }
    htmlText += "</div></div>";
    return htmlText;
}

function createClosedBroadcastList(records) {
    var htmlText = "<div class='col-xs-12 col-sm-12' style='float:left'><div class='panel-group'>";
    for (i = 0; i < records.length; i++) {
        htmlText +="<div class ='panel panel-default'>" +
            "<ul class='myUl'>";

        var totalBids = records[i].totalBids;
        htmlText += "<p>" + records[i].make + " " + records[i].model + " " + records[i].color + " " + records[i].broadcastDate + " " + totalBids + "";
        if (totalBids > 0) {
            htmlText += "<div id='myUlDiv'><ul class='nav'>";
            for (j = 0; j < totalBids; j++) {
                i++;
                var status = records[i].status;
                if(status == 1){
                    htmlText += "<div class='panel-body'><font color='green'> <li>" + "Bidder id: " + records[i].dealerId + " Bid Price: " + records[i].price + " Delivery hours: " + records[i].deliveryHours + " Bid date: " + records[i].bidDate + "</li></font></div>";
                } else {
                    htmlText += "<div class='panel-body'><li>" + "Bidder id: " + records[i].dealerId + " Bid Price: " + records[i].price + " Delivery hours: " + records[i].deliveryHours + " Bid date: " + records[i].bidDate + "</li></div>";
                }
            }
            htmlText += "</ul></div>";
        }
        htmlText += "</p>";
        htmlText +=  "</ul></div>";
    }
    htmlText += "</div></div>";
    return htmlText;
}

function showMyBroadcasts() {
    var value = document.getElementById("myOpenOrClosed").value;
    var dealerId = document.getElementById("myPara").innerHTML;
    if(value=="open") {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var htmlText = "";
                if (xmlHttp.responseText != "") {
                    var records = eval(xmlHttp.responseText);
                    htmlText = createOpenBroadcastList(records);
                }
                document.getElementById("myBroadcast").innerHTML = htmlText;
            }
        };
        xmlHttp.open("post", "showMyOpenBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("id=" + dealerId);
    } else if (value == "closed"){

        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var htmlText = "";
                if (xmlHttp.responseText != "") {
                    var records = eval(xmlHttp.responseText);
                    htmlText = createClosedBroadcastList(records);
                }
                document.getElementById("myBroadcast").innerHTML = htmlText;
            }
        };
        xmlHttp.open("post", "showMyClosedBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("id=" + dealerId);

    }
}


/*function showMyBroadcastsJquery() {
 var data = {"id" : $("#myPara").html()};
 console.log(data);
 $.ajax({
 url : "showMyOpenBroadcasts.do",
 type : "post",
 data : data,
 datatype : "text",
 success : function (result) {
 var htmlText = "<table border='1' id='table'><tr><th>Broadcast Id</th><th>Make</th><th>Model</th><th>Color</th><th>Broadcast Date</th><th>Bids</th><th>Latest Bids</th></tr>";
 $.each($.getJSON(result),function (i,e) {
 htmlText += "<tr><td>" + e.make + "</td><td>" + e.model + "</td><td>" + e.color + "</td><td>" + e.broadcastDate + "</td><td>" + e.totalBids + "</td><td>e.latestBid</td></tr>";

 });
 htmlText += "</table>";
 $("#myBroadcast").html(htmlText);
 }
 });
 }*/
    

