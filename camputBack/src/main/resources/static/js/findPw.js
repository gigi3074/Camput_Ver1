
// 데이터 주고 받기
function findPw_check(){

    var memberLoginId=$('#memberLoginId').val()
    console.log(memberLoginId);

    $.ajax({
        url:"/camput/findPw/id", // 어디서
        type:"POST",    // 어떤 메소드 타입의 애가
        async:false,
        data: {"memberLoginId":memberLoginId} ,
        success:function(response){
            console.log(response);
            if(response === true){
                $('#idCheckResult').text("아이디 인증 완료");
                $( document ).ready(function() {
                    $('#checkId').click(function() {
                        $('#pwUpdate').show();
                        $('#checkBox').hide();
                    });
                });
            } else {
                $('.checkError').text("아이디가 없습니다.");
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
};
