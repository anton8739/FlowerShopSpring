
$('#minus').click(function() {
    var input = $('#editForm input[name=amount]');
    var count = parseInt(input.val()) - 1;
    count = count < 1 ? 1 : count;
    input.val(count);
    input.change();
    return false;
});
$('#plus').click(function() {
    var input = $('#editForm input[name=amount]');
    input.val(parseInt(input.val()) + 1);
    input.change();
    return false;
});


$('#sel1').click(function () {
    $("#size").val("30 см");
    $("#sel1").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel3").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel4").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel2').click(function () {
    $("#size").val("40 см");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel3").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel4").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel3').click(function () {
    $("#size").val("50 см");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel3").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel4").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel4').click(function () {
    $("#size").val("60 см");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel3").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel4").css("background-color", "rgba(196, 55, 211, 0.5)");
});