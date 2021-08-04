

// console.log("");
var url = "www.google.com";



function test(){
    console.log("test");
    // $.get("/api/status" ,null,function(data){
    // $("#test").html(data)});
    $.getJSON("/api/status", function(data){
        $.each(data,function(k,v) {
            $.each(v , function (i,j){
                $("ul").append("<li>"+i+": "+j+"</li>");
            });
        });
    });

    $("#test").append(" Hello World");

    // console.log($.getJSON("/api/status",null, function(data){
    //     $.each(data,function(k,v) {
    //         $("ul").append("<li>"+v+"</li>");
    //     })
    // }));
}

setTimeout(test,10);