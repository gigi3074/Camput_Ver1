// 메뉴
$(function(){
    // 각각 목록 내려오기
    $(".menuBtn>li").mouseover(function(){
        $(this).children(".miniMenu").stop().slideDown();
    });
    $(".menuBtn>li").mouseout(function(){
        $(this).children(".miniMenu").stop().slideUp();
    });
}) // last

let menuEvent =$(".campSideInfo"); 

menuEvent.hover(function(){
   var content= menuEvent.children("ul");
   content.show();
},function(){
    var content= menuEvent.children("ul");
    content.hide();
})

$(document).ready(function(){
    var nowNum= 0;
    var slide=$('.campSideInfo')
    var maxLength = slide.length;
    slide.eq(nowNum).fadeIn();

    $('.next').bind('click',function(){
        rollingFn('right');
    });
    $('.pre').bind('click',function(){
        rollingFn('left');
    });

    function rollingFn(direction){
        if(direction=="right"){
            nowNum = nowNum+1;
            if(nowNum>maxLength-1){
                nowNum = 0;
            }
        }else{
            nowNum =nowNum-1;
            if(nowNum<0){
                nowNum = maxLength-1;
            }
        }
        slide.hide().eq(nowNum).fadeIn();
    }
});

function checkVisible( element, check = 'above' ) {
    let viewportHeight = $(window).height();
    let scrolltop = $(window).scrollTop();
    let y = $(element).offset().top;
    let elementHeight = $(element).height();   
    
    document.querySelector('.campIcon').classList.remove('animate__animated','animate__slideInUp');
    document.querySelector('.campTopSeven').classList.remove('animate__animated','animate__slideInUp')

    if (check == "visible") 
      return ((y < (viewportHeight + scrolltop)) && (y > (scrolltop - elementHeight)));

      if (check == "above") 
    return ((y < (viewportHeight + scrolltop)));
     }
     let func1 = function () {
        if (checkVisible('.campIcon') ) {
            document.querySelector('.campIcon').classList.add('animate__animated','animate__slideInUp');
            document.querySelector('.campIcon').style.setProperty('--animate-duration', '2s');
            document.querySelector('.campTopSeven').classList.add('animate__animated','animate__slideInUp');
            document.querySelector('.campTopSeven').style.setProperty('--animate-duration', '2s');
        }
    }
    window.addEventListener('scroll', func1);
    function checkVisible2( element, check = 'above' ) {
        let viewportHeight = $(window).height();
        let scrolltop = $(window).scrollTop();
        let y = $(element).offset().top;
        let elementHeight = $(element).height();   
    
        if (check == "visible") {   
             if (check == "above") 
        return ((y < (viewportHeight + scrolltop)));
         }
        return ((y < (viewportHeight + scrolltop)) && (y > (scrolltop - elementHeight)));
        }
        
    
     
         let newF = function () {
            if (checkVisible2('.map') ) {
                document.querySelector('.top').classList.add('animate__animated','animate__fadeOutUp');
                document.querySelector('.top').classList.remove('.fixed');
            }else{
                document.querySelector('.top').classList.remove('animate__animated','animate__fadeOutUp');
                document.querySelector('.top').classList.add('animate__animated','animate__fadeInDown');
                document.querySelector('.top').classList.add('.fixed');
            }
        }

    window.addEventListener('scroll', newF);



let check = true;
function filter(){
    if(check){
       $('.filterBox').show(); 
       check=false;
    }else{
        $('.filterBox').hide(); 
        check=true;
    }
    
}
