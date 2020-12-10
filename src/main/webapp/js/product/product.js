function res(){
    var widthFoto = $('.slide img').width();
    var deleteFoto= $('.deleteFot').width();
    $('.slide img').height(widthFoto);
    $('.deleteFot').height(deleteFoto);
    console.log(deleteFoto)
    console.log(widthFoto)
}
$(document).ready(function () {
    res();
});
$( window ).resize(function() {
    res();
});

$('#minus').click(function() {
    var input = $('#prodForm input[name=amount]');
    var count = parseInt(input.val()) - 1;
    count = count < 1 ? 1 : count;
    input.val(count);
    input.change();
    return false;
});
$('#plus').click(function() {
    var input = $('#prodForm input[name=amount]');
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


$('.slider').slick({
    autoplay: false,
    dots: true,
    customPaging: function(slider, i) {
        var thumb = $(slider.$slides[i]).data('thumb');
        return '<a class="dotImg" ><img src="' + thumb + '"></a>';
    }
});


/* окно удаления фотографий*/
if ($('#deleteImgBtn').length){
    $('#deleteImgBtn').on('click', function () {
        $('#deleteWindow').css('display', 'block');
        res();
    });
}
if($('#closeDelWindowBtn').length){
    $('#closeDelWindowBtn').on('click',function () {
        $('#deleteWindow').css('display', 'none');
    });
}
$('#deleteFotos').on('click',function () {
    var path = document.getElementById('basketHidden').value;
    var URL= path+"/app/product/deleteFoto";
    $.ajax({
        url: URL,
        type: "POST",
        dataType : "application/x-www-form-urlencoded",
        success: function (data, textStatus) {

        }
    });
});

