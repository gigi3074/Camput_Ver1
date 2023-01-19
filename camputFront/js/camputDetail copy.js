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

window.scrollTo({ left: 0, top: 0, behavior: "smooth" });

$(function(){
    // 각각 목록 내려오기
    $(".slideBtn").click(function(){
        $(this).children(".openBooking").stop().slideDown();
    });
    $(".slideBtn").click(function(){
        $(this).children(".openBooking").stop().slideUp();
    });
}) // last

// 리뷰

// 별점
const drawStar = (target) => {
    document.querySelector(`.star span`).style.width = `${target.value * 20}%`;
}
// 사진 업로드 미리보기

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}


// 모달 창 나오게 만들기 var.4
$( document ).ready(function() {
    $('#open_modal').click(function() {
        $('#modal').show();
    });
    // 모달 창 외의 다른 곳 클릭하면 다시 없어짐.
    $(window).on('click', function() {
        if (event.target == $('#modal').get(0)) {
            $('#modal').hide();
            }
    });
});