/*$(function() {
    sayHi();

    function sayHi() {
        setTimeout(sayHi,30000);
        alert('hi');
    }
});*/

function numOfNewBids() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var newBids = xmlHttp.responseText;
            console.log(newBids);
            document.getElementById("bidsNotification").innerHTML = "You have " + newBids + " new Bids";
        }
    };
    xmlHttp.open("post", "numOfNewBids.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("dealerId=" + document.getElementById("myPara").innerHTML);
}

function repeatedCalls(){
    numOfNewBids();
    if(document.getElementById("r2").checked){
        return;
    }
    setTimeout(repeatedCalls,60000);
    showOthersBroadcasts(document.getElementById("openOrClosed").value);
}

/*$(function () {
     autoCalls();*/
    function autoCalls() {
        showMyBroadcasts();
        repeatedCalls();
    }
/*
});*/
