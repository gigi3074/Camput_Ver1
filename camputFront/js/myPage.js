
async function checkMember(){
    var memberPw=$('.pwCheck').val();
    var result=await fetch("/camput/myPage/information/checkMember?memberPw="+memberPw).then(re=>re.text()).catch(err=>console.log("error"));
    if(result===""){
        $('.warring').show();
    }else{
        window.location.href='/camput/myPage/information/change';
    }
}
function openWindow(){
    $('.modal').show();
}
function hideWarring(){
    console.log('hide')
    $('.warring').hide();
}
function hideWindow(){
    console.log('windowhide')
    $('.modal').hide();
}

function showCheckWindow(){
    $('.modal').show();
    }

    
$(function(){
    // 각각 목록 내려오기
    $(".menuBtn>li").mouseover(function(){
        $(this).children(".miniMenu").stop().slideDown();
    });
    $(".menuBtn>li").mouseout(function(){
        $(this).children(".miniMenu").stop().slideUp();
    });
}) // last
