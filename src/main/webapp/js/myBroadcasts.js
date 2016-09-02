/**
 * Created by malir on 9/1/2016.
 */


function showMyOpenBroadcasts() {
    var xmlHttp = new XMLHttpRequest();
    var dealerId = document.getElementById("myPara").innerHTML;
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var records = eval(xmlHttp.responseText);
            var htmlText = "";

            for (i = 0; i < records.length; ++i) {
                var totalBids = records[i].totalBids;
                htmlText += "<p>" + records[i].make + " " + records[i].model + " " + records[i].color + " " + records[i].broadcastDate + " " + totalBids + "";
                if(totalBids > 0) {
                    htmlText += "<ul>";
                    for (j = 0; j < totalBids; j++) {
                        i++;
                        htmlText += "<li>" + "Bidder id: " + records[i].dealerId + " Bid Price: " + records[i].price + " Delivery hours: " + records[i].deliveryHours + " Bid date: " + records[i].bidDate;
                    }
                    htmlText += "</ul>";
                }
                htmlText += "</p>";
            }
            document.getElementById("myBroadcast").innerHTML = htmlText;
        }
    };
    xmlHttp.open("post", "showMyOpenBroadcasts.do", true);
    xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
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
    

