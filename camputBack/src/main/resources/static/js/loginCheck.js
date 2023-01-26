$(document).ready(function (){
    if(loginCheck!=null){
        console.log("login");
        $('.notLogin').hide();
        $('.Login').show();
    }else{
        console.log("notLogin");
        $('.notLogin').show();
        $('.Login').hide();
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