/*$(function() {
    sayHi();

    function sayHi() {
        setTimeout(sayHi,30000);
        alert('hi');
    }
});*/

function repeatedCalls(){
    setTimeout(repeatedCalls,30000);
    showOthersBroadcasts(document.getElementById("openOrClosed").value);
}

/*$(function () {
     autoCalls();*/
    function autoCalls() {
        showMyBroadcasts(document.getElementById("myOpenOrClosed").value);
        repeatedCalls();
    }
/*
});*/
