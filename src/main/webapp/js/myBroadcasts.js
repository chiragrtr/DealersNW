/**
 * Created by malir on 9/1/2016.
 */


function showMyOpenBroadcasts() {
    var xmlHttp = new XMLHttpRequest();
    var dealerId = document.getElementById("myPara").innerHTML;
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var records = eval(xmlHttp.responseText);
            var htmlText = "<div class='form'><table border='1' id='table'><tr><th>Make</th><th>Model</th><th>Color</th><th>Broadcast Date</th><th>Bids</th><th>Latest Bids</th></tr>";

            for (i = 0; i < records.length; ++i) {
                htmlText += "<tr><td>" + records[i].make + "</td><td>" + records[i].model + "</td><td>" + records[i].color + "</td><td>" + records[i].broadcastDate + "</td><td>" + records[i].totalBids + "</td><td>" + records[i].latestBid + "</td></tr>"
            }
            htmlText += "</table></div>";
            //$("#myBroadcasts").html(htmlText);
            document.getElementById("myBroadcast").innerHTML = htmlText;
        }
    };
    xmlHttp.open("post", "showMyOpenBroadcasts.do", true);
    xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlHttp.send("id=" + dealerId);


}

function showMyBroadcastsJquery() {
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
}
    

