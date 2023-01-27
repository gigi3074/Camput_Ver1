$(document).ready(function (){
    if(loginCheck!=null){
        console.log("login");
        $('.nav1.notLogin').hide();
        $('.nav2.Login').show();
    }else{
        console.log("notLogin");
        $('.nav1.notLogin').show();
        $('.nav2.Login').hide();
    }
});
$(function(){
    $(".menuBtn>li").mouseover(function(){
        $(this).children(".miniMenu").stop().slideDown();
    });
    $(".menuBtn>li").mouseout(function(){
        $(this).children(".miniMenu").stop().slideUp();
    });
}) // last