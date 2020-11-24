
$(document).ready(function() {
    $('.admin').click(function() {

        $('#adminMenu').slideToggle(500);
        $('#languegeMenu').hide();
        $('#signinMenu').hide();
        $('#linksMenu').hide();
    });
    $('.languege').click(function() {

        $('#languegeMenu').slideToggle(500);
        $('#signinMenu').hide();
        $('#linksMenu').hide();
        $('#adminMenu').hide();
    });
    $('.signin').click(function() {

        $('#signinMenu').slideToggle(500);
        $('#linksMenu').hide();
        $('#languegeMenu').hide();
        $('#adminMenu').hide();
    });


    $('.links').click(function() {

        $('#linksMenu').slideToggle(500);
        $('#languegeMenu').hide();
        $('#adminMenu').hide();
        $('#signinMenu').hide();
    });
    $('.buttomMenuLeft  i').click(function() {

        $('#linksMenu').slideToggle(500);
        $('#languegeMenu').hide();
        $('#adminMenu').hide();
        $('#signinMenu').hide();
    });

});