/**
 * Created by malir on 9/1/2016.
 */

function placeThisBid(formNum) {
    //alert("hi");
    var price = document.getElementById("price" + formNum).value;
    //var price=document.formNum.price.value;
    //alert(price);
    var days = document.getElementById("days" + formNum).value;
    var hours = document.getElementById("hours" + formNum).value;
    var broadcastId = document.getElementById("broadcastId" + formNum).innerHTML;
    //alert(broadcastId);

    var deliveryHours = parseInt(days) * 24 + parseInt(hours);
    //alert(broadcastId + price + deliveryHours);

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            alert("Bid placed");
        } else {
            //alert("Failure");
        }
    };

    xmlHttp.open("post", "setBid.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("broadcastId=" + broadcastId + "&price=" + price + "&deliveryHours=" + deliveryHours);
}
function showOthersBroadcasts(value) {
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
                        htmlText += "<p>";
                        htmlText += "MAKE: " + records[i].make + " MODEL: " + records[i].model + " COLOR: " + records[i].color + "<BR>" + " BROADCAST BY: " + records[i].dealerId + " DATE OF BROADCAST: " + records[i].broadcastDate;
                        htmlText += "<form name='myForm" + (++f) + "'><p id =broadcastId" + f + " style='visibility:hidden'>" + records[i].broadcastId + "</p>" +
                            "<input type='number' id =price" + f + " name='price' placeholder='price'><input type='number' id =days" + f + " name='days' placeholder='days'>" +
                            "<input type='number' id =hours" + f + " name='hours' placeholder='hours'><button type='button' onclick=placeThisBid(" + f + ")>Place Bid</button></form> " +
                            "</p>";
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
                        htmlText += "<p>" + " MAKE: " + records[i].make + " MODEL: " + records[i].model + " COLOR: " + records[i].color + "<BR>" + " BROADCAST BY: " + records[i].dealerId + " DATE OF BROADCAST: " + records[i].broadcastDate + "</p>";
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

