/*$(function() {
    sayHi();

    function sayHi() {
        setTimeout(sayHi,30000);
        alert('hi');
    }
});*/

function repeatedCalls(){
    setTimeout(repeatedCalls,30000);
    showOthersBroadcasts('open');
}

/*$(function () {
     autoCalls();*/
    function autoCalls() {
        showMyOpenBroadcasts();
        repeatedCalls();
    }
/*
});*/
