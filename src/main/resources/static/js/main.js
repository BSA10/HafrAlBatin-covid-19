
var now = new Date();
var millisTill10 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 12, 0, 0, 0) - now;

if (millisTill10 < 0) {
    millisTill10 += 86400000; // 86400000 it's a day in millisecond so it got repeated everyday.
}
setTimeout(function(){
    $.get( "http://localhost:8080/api/last-status-moh").done(function (data){
        location.reload();
    });

}, millisTill10);



