function res(){
    var widthItem = $('.productItem').width()*1.3;
    $('.productItem').css('min-height', widthItem);
    var widthFoto = $('.productItem img').width();
    $('.productItem img').height(widthFoto);
}
res();
$( window ).resize(function() {
    res();
});
