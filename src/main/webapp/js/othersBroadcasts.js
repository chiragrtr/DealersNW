/**
 * Created by malir on 9/1/2016.
 */

function placeThisBid() {
    alert("hi");
    var price=document.placeBid.price.value;
     var days = document.placeBid.days.value;
     var hours=document.placeBid.hours.value;
     var broadcastId = document.getElementById("broadcastId").innerHTML;

     var deliveryHours = parseInt(days) * 24 + parseInt(hours);
     alert(broadcastId + price + deliveryHours);

     var xmlHttp = new XMLHttpRequest();
     xmlHttp.onreadystatechange = function () {
     if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
     alert("success");
     } else {
     alert("Failure");
     }
     };

     xmlHttp.open("post", "placeBid.do", true);
     xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
     xmlHttp.send("broadcastId="+broadcastId+"&price="+price+"&deliveryHours="+deliveryHours);
}
function showOthersBroadcasts(value) {
    console.log(1);
    //$("#choice").on('change',function () {
    alert("changed");
    //var value = $(this).val();

    var dealerId = document.getElementById("myPara").innerHTML;
    if (value == "Open") {

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var records = eval(xmlHttp.responseText);
                var htmlText = "<table border='1' id='table'><tr><th>Make</th><th>Model</th><th>Color</th>" +
                 "<th>Broadcast Date</th><th>Price</th><th colspan='2'>Delivery Duration</th><th>Place Bid</th> </tr>";
                for (i = 0; i < records.length; ++i) {
                    htmlText += "<tr><td>" + records[i].make + "</td><td>" + records[i].model + "</td>" +
                     "<td>" + records[i].color + "</td><td>" + records[i].broadcastDate + "</td>" +
                     "<form name='placeBid'><p id='broadcastId' style='visibility:hidden'>"+ records[i].broadcastId+"</p>" +
                     "<td><input type='number' name='price' placeholder='price'></td><td><input type='number' name='days' placeholder='days'></td>" +
                     "<td><input type='number' name='hours' placeholder='hours'></td><td><button type='button' onclick='placeThisBid()'>Place Bid</button> " +
                     "</td></form> </tr>";
                }
                htmlText += "</table>";
                 document.getElementById("othersBroadcast").innerHTML = htmlText;
            }
        };

        xmlHttp.open("post", "showOthersOpenBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("id=" + dealerId);

    } else if (value == "All") {
       /* var htmlText = "<table border='1' id='table'><tr><th>Make</th><th>Model</th><th>Color</th>" +
            "<th>Broadcast Date</th><th>Status</th></tr>";*/
       console.log(dealerId);
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                console.log(xmlHttp.responseText);
                console.log(2);
                var records = eval(xmlHttp.responseText);
                var htmlText = "<table border='1' id='table'><tr><th>Make</th><th>Model</th><th>Color</th>" +
                 "<th>Broadcast Date</th><th>Status</th></tr>";
                for (i = 0; i < records.length; ++i) {
                    htmlText += "<tr><td>" + records[i].make + "</td><td>" + records[i].model + "</td><td>" + records[i].color + "</td><td>" + records[i].broadcastDate + "</td><td>" + records[i].status + "</td></tr>"
                }
                htmlText += "</table>";
                document.getElementById("othersBroadcast").innerHTML = htmlText;
            }
        };
        xmlHttp.open("post", "showOthersBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("id=" + dealerId);

    }
    //});

}

