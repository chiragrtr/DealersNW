/*$(function() {
    sayHi();

    function sayHi() {
        setTimeout(sayHi,30000);
        alert('hi');
    }
});*/

function repeatedCalls(){
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
