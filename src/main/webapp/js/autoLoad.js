/*$(function() {
 sayHi();

 function sayHi() {
 setTimeout(sayHi,30000);
 alert('hi');
 }
 });*/
function allBidsViewed() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var newBids = xmlHttp.responseText;
            if (newBids == "0") {
                document.getElementById("bidsNotification").innerHTML = "No new Bids";
            }
            else {
                document.getElementById("bidsNotification").innerHTML = "<b>Notification: You have " + newBids + " new Bids </b><button onclick=allBidsViewed()>Hide</button>";
            }
        }
    };
    xmlHttp.open("post", "allBidsViewed.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("dealerId=" + document.getElementById("myPara").innerHTML);
}


function numOfNewBids() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var newBids = xmlHttp.responseText;
            if (newBids == "0") {
                document.getElementById("bidsNotification").innerHTML = "No new Bids";
            }
            else {
                document.getElementById("bidsNotification").innerHTML = "<b>You have " + newBids + " new Bids </b><button onclick=allBidsViewed()>Hide</button>";
            }
        }
    };
    xmlHttp.open("post", "numOfNewBids.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("dealerId=" + document.getElementById("myPara").innerHTML);
}

function allMyBidsViewed() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var records = eval(xmlHttp.responseText);
            var selectedBids = records[0].selectedBids;
            var rejectedBids = records[0].rejectedBids;
            console.log(records[0]);
            console.log(records[0].selectedBids);
            if (selectedBids == "0" && rejectedBids == "0") {
                document.getElementById("myBidsNotification").innerHTML = "No new notification";
            }
            else {
                document.getElementById("myBidsNotification").innerHTML = "<b>Notification: Your " + selectedBids + " bids were selected and " + rejectedBids + " bids were rejected </b><button onclick=allMyBidsViewed()>Hide</button>";
            }
        }
    };
    xmlHttp.open("post", "allMyBidsViewed.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("dealerId=" + document.getElementById("myPara").innerHTML);
}
function numOfSelectedBids() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var records = eval(xmlHttp.responseText);
            var selectedBids = records[0].selectedBids;
            var rejectedBids = records[0].rejectedBids;
            console.log(records[0]);
            console.log(records[0].selectedBids);
            if (selectedBids == "0" && rejectedBids == "0") {
                document.getElementById("myBidsNotification").innerHTML = "No new notification";
            }
            else {
                document.getElementById("myBidsNotification").innerHTML = "<b>Notification: Your " + selectedBids + " bids were selected and " + rejectedBids + " bids were rejected </b><button onclick=allMyBidsViewed()>Hide</button>";
            }
        }
    };
    xmlHttp.open("post", "numOfSelectedBids.do", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("dealerId=" + document.getElementById("myPara").innerHTML);
}

function alwaysRepeat(){
    numOfNewBids();
    numOfSelectedBids();
    showMyBroadcasts();
    setTimeout(alwaysRepeat, 10000);
}

function repeatOnCondition(){
    if (document.getElementById("r2").checked) {
        return;
    }
    setTimeout(repeatOnCondition, 60000);
    showOthersBroadcasts(document.getElementById("openOrClosed").value);
}
function repeatedCalls() {
    alwaysRepeat();
    repeatOnCondition();
}

/*$(function () {
 autoCalls();*/
function autoCalls() {
    showMyBroadcasts();
    repeatedCalls();
}
/*
 });*/
