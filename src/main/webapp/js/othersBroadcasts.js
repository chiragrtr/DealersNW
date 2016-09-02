/**
 * Created by malir on 9/1/2016.
 */

function placeThisBid() {
    alert("hi");
    /*var price=document.placeBid.price.value;
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
     xmlHttp.send("broadcastId="+broadcastId+"&price="+price+"&deliveryHours="+deliveryHours);*/
}
function showOthersBroadcasts(value) {
    /*console.log(1);
     //$("#choice").on('change',function () {
     alert("changed");
     //var value = $(this).val();*/

    var dealerId = document.getElementById("myPara").innerHTML;
    if (value == "open") {

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var records = eval(xmlHttp.responseText);
                var htmlText = "";
                for (i = 0; i < records.length; ++i) {
                    htmlText += "<p>";
                    htmlText += "MAKE: " + records[i].make + " MODEL: " + records[i].model + " COLOR: " + records[i].color + "<BR>" + " BROADCAST BY: " + records[i].dealerId + " DATE OF BROADCAST: " + records[i].broadcastDate;
                    htmlText += "<form name='placeBid'><p id='broadcastId' style='visibility:hidden'>" + records[i].broadcastId + "</p>" +
                        "<input type='number' name='price' placeholder='price'><input type='number' name='days' placeholder='days'>" +
                        "<input type='number' name='hours' placeholder='hours'><button type='button' onclick='placeThisBid()'>Place Bid</button> " +
                        "</form></p>";
                }
                document.getElementById("othersBroadcasts").innerHTML = htmlText;
            }
        };

        xmlHttp.open("post", "showOthersOpenBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("id=" + dealerId);

    } else if (value == "closed") {
        console.log(dealerId);
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                console.log(xmlHttp.responseText);
                console.log(2);
                var records = eval(xmlHttp.responseText);
                var htmlText = "";
                for (i = 0; i < records.length; ++i) {
                    htmlText += "<p>" + " MAKE: " + records[i].make + " MODEL: " + records[i].model + " COLOR: " + records[i].color + "<BR>" + " BROADCAST BY: " + records[i].dealerId + " DATE OF BROADCAST: " + records[i].broadcastDate + "</p>";
                }
                document.getElementById("othersBroadcasts").innerHTML = htmlText;
            }
        };
        xmlHttp.open("post", "showOthersBroadcasts.do", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("id=" + dealerId);
    }
}

