/**
 * Created by malir on 9/1/2016.
 */


function showMyOpenBroadcasts() {
    var xmlHttp = new XMLHttpRequest();
    var dealerId = document.getElementById("hiddenDId").innerHTML;
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var records = eval(xmlHttp.responseText);
            var htmlText = "<table border='1' id='table'><tr><th>Make</th><th>Model</th><th>Color</th><th>Broadcast Date</th><th>Bids</th><th>Latest Bids</th></tr>";

            for (i = 0; i < records.length; ++i) {
                htmlText += "<tr id='" + records[i].make + "'><td>" + records[i].model + "</td><td>" + records[i].color + "</td><td>" + records[i].broadcastDate + "</td><td>" + records[i].totalBids + "</td><td>" + records[i].latestBid + "</td></tr>"
            }
            htmlText += "</table>";
            //$("#myBroadcasts").html(htmlText);
            document.getElementById("myBroadcast").innerHTML = htmlText;
        }
    };
    xmlHttp.open("post", ".do", true);
    xmlHttp.send("dealerId=" + dealerId);


}

function showMyBroadcastsJquery() {
    var data = {name : document.getElementById("hiddenDId").value}
    $.ajax({
        url : ".do",
        type : "post",
        data : data,
        datatype : "json",
        success : function (result) {
            var htmlText = "<table border='1' id='table'><tr><th>MBroadcast Id</th><th>Make</th><th>Model</th><th>Color</th><th>Broadcast Date</th><th>Bids</th><th>Latest Bids</th></tr>";
            $.each(result,function (i,e) {
                htmlText += "<tr><td>" + e.broadcastId+ "</td><td>" + e.make + "</td><td>" + e.model + "</td><td>" + e.color + "</td><td>" + e.broadcastDate + "</td><td>" + e.totalBids + "</td><td>e.latestBid</td></tr>";

            });
            htmlText += "</table>";
            $("#myBroadcast").html(htmlText);
        }
    });
}
    

