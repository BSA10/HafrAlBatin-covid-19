

$("button.sendEmails").click(function (){
    console.log("inside button function")
    $.post("http://localhost:8080/api/email/sendToEmails" , function (){
        alert("Success");
    });
    console.log("Done with get function")
});


// my idea is to try make restController for get the status of covid19 details and send it timely everyday in post method in jquery
// i make get method and when i have the data i send all the data by post and handle it with restController sendEmails.