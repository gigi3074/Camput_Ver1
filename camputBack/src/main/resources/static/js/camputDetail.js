
var checkStartYear="";
var checkStartMonth="";
var checkStartDay="";
var checkEndYear="";
var checkEndMonth="";
var checkEndDay="";
var startColor="";
var endColor="";
    var startDayCheck=true;
    var endDayCheck=true;

$(document).ready(function(){
    initCalendar1();
    initCalendar2();
});

function initCalendar1() {
    // 날짜 정보 가져오기
    var date = new Date();
    var utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000);
    var kstGap = 9 * 60 * 60 * 1000; 
    var today = new Date(utc + kstGap); 
   
    var thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    const fixedCurrentMonth=thisMonth.getMonth()+1;
    const fixedCurrentYear=thisMonth.getFullYear();;

    var currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
    var currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
    var currentDate = thisMonth.getDate(); // 달력에서 표기하는 일



    // 캘린더 렌더링
    renderCalender(thisMonth);
    function renderCalender(thisMonth) {
        // 렌더링을 위한 데이터 정리
        currentYear = thisMonth.getFullYear();
        currentMonth = thisMonth.getMonth();
        currentDate = thisMonth.getDate();
        // 이전 달의 마지막 날 날짜와 요일 구하기
        var startDay = new Date(currentYear, currentMonth, 0);
        var prevDate = startDay.getDate();
        var prevDay = startDay.getDay();
        // 이번 달의 마지막날 날짜와 요일 구하기
        var endDay = new Date(currentYear, currentMonth + 1, 0);
        var nextDate = endDay.getDate();
        var nextDay = endDay.getDay();
        // 현재 월 표기
        $('.year-month').text(currentYear + '.' + (currentMonth + 1));
        calendar = document.querySelector('.one')
        calendar.innerHTML = '';
        // 지난달
        for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
            var prevDays= document.createElement('div');
            prevDays.className="day prev disable";
            prevDays.setAttribute('id',i);
            prevDays.innerHTML=i;
            calendar.appendChild(prevDays);
        }
        if(fixedCurrentMonth===currentMonth+1&&fixedCurrentYear===currentYear){
            $("#prev1").hide(); 
        }else{
            $("#prev1").show();
        }
        // 이번달
        
        for (var idx = 1; idx <= nextDate; idx++) {
            var currentDay= document.createElement('div');
            currentDay.className='day current';
            currentDay.setAttribute('id',"start"+(currentMonth + 1)+"month"+idx);
            currentDay.innerHTML=idx;
            var currentDayButton =document.createElement('div');
            
            if(today.getDate()<idx|| fixedCurrentMonth<(currentMonth+1)){
             
            currentDayButton= document.createElement('button');
            currentDayButton.className='startDayButton';
            currentDayButton.innerHTML=idx;
            currentDayButton.dataset.idx = idx;
            }
                currentDay.appendChild(currentDayButton);
                calendar.appendChild(currentDay);
            } 
          
        $(".startDayButton").click(function(){
           var idx= $(this).data("idx");
           var warring= $('.startWarring')
           if(checkStartYear===""&&endDayCheck&&startDayCheck&&checkEndYear===""){
            startReservationDay(this);
            startDayCheck=false;
            console.log("첫입력");
           }else if(endDayCheck&&!startDayCheck){
            var start=document.querySelector(".startReservationDay");
            start.innerHTML=clickedYMD;
            var deldeteColor= document.getElementById(startColor);
            deldeteColor.setAttribute('style',"background-color: white")
            startReservationDay(this);
            startDayCheck=false;
            console.log("엔드안입력 시작일 수정");
           }else if(!endDayCheck&&!startDayCheck&&
            (checkEndYear>today.getFullYear()||
             checkEndMonth>(currentMonth+1)||
             checkEndDay>idx)
            ){
           console.log("엔드입력 시작일 수정 이번달");
           var start=document.querySelector(".startReservationDay");
           start.innerHTML=clickedYMD;
           var deldeteColor= document.getElementById(startColor);
           if(startColor!=""&&deldeteColor!=null){
            deldeteColor.setAttribute('style',"background-color: white")
           startReservationDay(this);
           startDayCheck=false;
           }
           }else if(checkEndYear!=""&&(checkEndYear>currentYear || checkEndMonth>(currentMonth+1))){
            console.log("다른 달 마지막날 입력하고 시작날 바꾸기");
            var start=document.querySelector(".startReservationDay");
            start.innerHTML=clickedYMD;
            var deldeteColor= document.getElementById(startColor);
            if(startColor!=""&&deldeteColor!=null){
             deldeteColor.setAttribute('style',"background-color: white")
            startReservationDay(this);
            startDayCheck=false;
           }
           else{
            console.log("예외");

            warring.text="마지막날 보다 늦은 날자로 입력하세요"
            warring.show();
           }
        }  
        })

        function startReservationDay(_this){
            clickedYear = today.getFullYear();
            clickedMonth = (1+currentMonth);
            clickedDate = $(_this).data("idx");
            checkStartYear=clickedYear;
            checkStartMonth=clickedMonth;
            checkStartDay=clickedDate;
            clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;    
            clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
            clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;
            var start=document.querySelector(".startReservationDay");
            start.innerHTML=clickedYMD;
            var dayId="start"+(currentMonth + 1)+"month"+$(_this).data("idx")
            startColor=dayId;
            var clickEndDay= document.getElementById(dayId);
            clickEndDay.setAttribute('style',"background-color: rgba(72, 137, 73, 0.562)")
            return startDay;
        }; 
        // 다음달
        for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
         var nextDays= document.createElement('div');   
         nextDays.className="day next disable";
         nextDays.setAttribute("id",i);
         nextDays.innerHTML=i;
         calendar.appendChild(nextDays);
        }
        // 오늘 날짜 표기
        if (today.getMonth() == currentMonth) {
            todayDate = today.getDate();
            var currentMonthDate = document.querySelectorAll('.dates .current');
            currentMonthDate[todayDate -1].classList.add('today');
        }
    }
    // 이전달로 이동
    $('.go-prev').on('click', function() {
        thisMonth = new Date(currentYear, currentMonth - 1, 1);
        renderCalender(thisMonth);
    });
    // 다음달로 이동
    $('.go-next').on('click', function() {
        thisMonth = new Date(currentYear, currentMonth + 1, 1);
        renderCalender(thisMonth); 
    });
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function initCalendar2() {
    // 날짜 정보 가져오기
    var date = new Date();
    var utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000);
    var kstGap = 9 * 60 * 60 * 1000; 
    var today = new Date(utc + kstGap); 
   
    var thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    const fixedCurrentMonth=thisMonth.getMonth()+1;
    const fixedCurrentYear=thisMonth.getFullYear();;

    var currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
    var currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
    var currentDate = thisMonth.getDate(); // 달력에서 표기하는 일

    // 캘린더 렌더링
    renderCalender(thisMonth);

    function renderCalender(thisMonth) {
        // 렌더링을 위한 데이터 정리
        currentYear = thisMonth.getFullYear();
        currentMonth = thisMonth.getMonth();
        currentDate = thisMonth.getDate();
        // 이전 달의 마지막 날 날짜와 요일 구하기
        var startDay = new Date(currentYear, currentMonth, 0);
        var prevDate = startDay.getDate();
        var prevDay = startDay.getDay();
        // 이번 달의 마지막날 날짜와 요일 구하기
        var endDay = new Date(currentYear, currentMonth + 1, 0);
        var nextDate = endDay.getDate();
        var nextDay = endDay.getDay();
        // 현재 월 표기


        $('.year-month-end').text(currentYear + '.' + (currentMonth + 1));
        calendar = document.querySelector('.two')


        calendar.innerHTML = '';
        // 지난달
        for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
            var prevDays= document.createElement('div');
            prevDays.className="day prev disable";
            prevDays.setAttribute('id',i);
            prevDays.innerHTML=i;
            calendar.appendChild(prevDays);
        }
        if(fixedCurrentMonth===currentMonth+1&&fixedCurrentYear===currentYear){
            $("#prev2").hide(); 
        }else{
            $("#prev2").show();
        }
        // 이번달
        for (var idx = 1; idx <= nextDate; idx++) {
            var currentDay= document.createElement('div');
            currentDay.className='day current';
            currentDay.setAttribute('id',"end"+(currentMonth + 1)+"month"+idx);
            currentDay.innerHTML=idx;
            var currentDayButton =document.createElement('div');
            
            if(today.getDate()<idx|| fixedCurrentMonth<(currentMonth+1)){
          
            currentDayButton= document.createElement('button');
            currentDayButton.className='endDayButton';

            currentDayButton.innerHTML=idx;
            currentDayButton.dataset.idx = idx;
        }
            currentDay.appendChild(currentDayButton);
            calendar.appendChild(currentDay);
        } 
        console.log(startColor);
        console.log(endColor);

        $(".endDayButton").click(function(){
           var warring= $('.endWarring')
           var idx= $(this).data("idx");
            if(checkStartYear!=""&&checkEndYear===""&&idx>checkStartDay){
                console.log("end first")
                warring.hide();
                endReservationDay(this);
                endDayCheck=false;
            }else if(checkStartYear!=""&&
            (checkStartYear<today.getFullYear|| checkStartMonth<(currentMonth+1)||checkStartDay<idx)
            ){
               console.log("시작정하고 같은 달 바꿈")
               console.log(checkStartYear);
               console.log(":"+today.getFullYear());
               console.log(checkStartMonth);
               console.log(":"+(currentMonth+1));   
               console.log(checkStartDay);
               console.log(":"+idx);
               var end=document.querySelector(".endReservationDay");
               end.innerHTML=clickedYMD;
               var deldeteColor= document.getElementById(endColor);
               if(endColor!=""&&deldeteColor!=null){
               deldeteColor.setAttribute('style',"background-color: white")}
               endReservationDay(this);
               startDayCheck=false;
            }else if(checkStartYear!=""&&(checkStartYear<currentYear || checkStartMonth<(currentMonth+1))){
                console.log("시작정하고 마지막날 달 바뀜")
                console.log(checkStartYear);
                console.log(":"+today.getFullYear());
                console.log(checkStartMonth);
                console.log(":"+(currentMonth+1));   
                console.log(checkStartDay);
                console.log(":"+idx);
                var end=document.querySelector(".endReservationDay");
                end.innerHTML=clickedYMD;
                var deldeteColor= document.getElementById(endColor);
                if(endColor!=""&&deldeteColor!=null){
                    deldeteColor.setAttribute('style',"background-color: white")}
                endReservationDay(this);
                startDayCheck=false;
            }
            else{
                console.log("시작정하고 마지막날 달 바뀜")
                console.log(checkStartYear);
                console.log(":"+today.getFullYear());
                console.log(checkStartMonth);
                console.log(":"+(currentMonth+1));   
                console.log(checkStartDay);
                console.log(":"+idx);
                console.log("end error")
                warring.text="올바른 시작일이 필요합니다"
                warring.show();

            }
           
        })

        function endReservationDay(_this){
            clickedYear = today.getFullYear();
            clickedMonth = (1+currentMonth);
            clickedDate = $(_this).data("idx");

            checkEndYear = clickedYear;
            checkEndMonth = clickedMonth;
            checkEndDay = clickedDate;
            clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;    
            clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
            clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;
            var v=document.querySelector(".endReservationDay");
            v.innerHTML=clickedYMD;
            var dayId="end"+(currentMonth + 1)+"month"+$(_this).data("idx")
            endColor=dayId;
            document.getElementById(dayId).setAttribute('style',"background-color: rgba(6, 120, 6, 0.562)")
            return startDay;
        }; 
        // 다음달
        for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
         var nextDays= document.createElement('div');   
         nextDays.className="day next disable";
         nextDays.setAttribute("id",i);
         nextDays.innerHTML=i;
         calendar.appendChild(nextDays);
        }
        // 오늘 날짜 표기
        if (today.getMonth() == currentMonth) {
            todayDate = today.getDate();
            var currentMonthDate = document.querySelectorAll('.two .current');
            currentMonthDate[todayDate -1].classList.add('today');
        } 
    }
    // 이전달로 이동
    $('.go-prevend').on('click', function() {
        thisMonth = new Date(currentYear, currentMonth - 1, 1);
        renderCalender(thisMonth);
    });
    // 다음달로 이동
    $('.go-nextend').on('click', function() {
        thisMonth = new Date(currentYear, currentMonth + 1, 1);
        renderCalender(thisMonth); 
    });

}