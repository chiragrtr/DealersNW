/**
 * Created by malir on 9/1/2016.
 */

function selectBidAjax(bidId) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var records = xmlHttp.responseText;
            alert(records);
        }
    };
    xmlHttp.open("post", "selectDeal.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("bidId=" + bidId);
}

function selectBid(bidId){
    selectBidAjax(bidId);
    setTimeout(showMyOpenBroadcasts,1000);
}


function createBroadcastList(records) {
    var htmlText = "";
    for (i = 0; i < records.length; ++i) {
        htmlText += htmlText = "<div class='col-xs-12 col-sm-12 sidebar-offcanvas' role='navigation'   style='float:left'>" +
            "<div class='panel-group'><div class ='panel panel-default'>" +
            "<ul>";
        var totalBids = records[i].totalBids;
        htmlText += "<p>" + records[i].make + " " + records[i].model + " " + records[i].color + " " + records[i].broadcastDate + " " + totalBids + "";
        if (totalBids > 0) {
            htmlText += "<ul class='nav'>";
            for (j = 0; j < totalBids; j++) {
                i++;
                htmlText += "<div class='panel-body'><li>" + "Bidder id: " + records[i].dealerId + " Bid Price: " + records[i].price + " Delivery hours: " + records[i].deliveryHours + " Bid date: " + records[i].bidDate + "<button onclick = selectBid(" + records[i].bidId + ")>Select this bid</button>" + "</li></div>";
            }
            htmlText += "</ul>";
        }
        htmlText += "</p>";
        htmlText += "</ul></div></div></div>";
    }

    return htmlText;
}

function showMyOpenBroadcasts() {
    var xmlHttp = new XMLHttpRequest();
    var dealerId = document.getElementById("myPara").innerHTML;
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var htmlText = "";
            if (xmlHttp.responseText != "") {
                var records = eval(xmlHttp.responseText);
                htmlText = createBroadcastList(records);
            }
            document.getElementById("myBroadcast").innerHTML = htmlText;
        }
    };
    xmlHttp.open("post", "showMyOpenBroadcasts.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("id=" + dealerId);
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
    

