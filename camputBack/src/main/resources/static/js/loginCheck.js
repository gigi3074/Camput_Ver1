$(document).ready(function (){
    if(loginCheck!=null){
        console.log("login");
        $('.nav.notLogin').hide();
        $('.menu.Login').show();
    }else{
        console.log("notLogin");
        $('.nav.notLogin').show();
        $('.menu.Login').hide();
    }
});
