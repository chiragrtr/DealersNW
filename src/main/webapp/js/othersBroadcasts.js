/**
 * Created by malir on 9/1/2016.
 */

function showOthersBroadcasts() {
    $("#choice").on('change',function () {
        var value = $(this).val();
        var xmlHttp = new XMLHttpRequest();
        var dealerId = document.getElementById("myPara").innerHTML;
        if(value == "Open"){
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var records = eval(xmlHttp.responseText);
                    var htmlText = "<table border='1' id='table'><tr><th>Make</th><th>Model</th><th>Color</th><th>Broadcast Date</th><th>Bids</th><th>Latest Bids</th></tr>";

                    for (i = 0; i < records.length; ++i) {
                        htmlText += "<tr><td>" + records[i].make + "</td><td>" + records[i].model + "</td><td>" + records[i].color + "</td><td>" + records[i].broadcastDate + "</td></tr>"
                    }
                    htmlText += "</table>";
                    //$("#myBroadcasts").html(htmlText);
                    document.getElementById("othersBroadcast").innerHTML = htmlText;
                }
            };
            xmlHttp.open("post", "showOthersOpenBroadcasts.do", true);
            xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlHttp.send("id=" + dealerId);

        }else if(value=="All")
        {
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var records = eval(xmlHttp.responseText);
                    var htmlText = "<table border='1' id='table'><tr><th>Make</th><th>Model</th><th>Color</th><th>Broadcast Date</th><th>Bids</th><th>Latest Bids</th></tr>";

                    for (i = 0; i < records.length; ++i) {
                        htmlText += "<tr><td>" + records[i].make + "</td><td>" + records[i].model + "</td><td>" + records[i].color + "</td><td>" + records[i].broadcastDate + "</td><td>" + records[i].status + "</td></tr>"
                    }
                    htmlText += "</table>";
                    //$("#myBroadcasts").html(htmlText);
                    document.getElementById("othersBroadcast").innerHTML = htmlText;
                }
            };
            xmlHttp.open("post", "showOthersBroadcasts.do", true);
            xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlHttp.send("id=" + dealerId);

        }
    });

}
