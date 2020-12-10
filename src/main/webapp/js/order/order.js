$('#sel1').click(function () {
    $("#paymentMethod").val("CASH");
    $("#sel1").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel3").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel2').click(function () {
    $("#paymentMethod").val("CARD");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel3").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel3').click(function () {
    $("#paymentMethod").val("CARD_ONLINE");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel3").css("background-color", "rgba(196, 55, 211, 0.5)");
});
