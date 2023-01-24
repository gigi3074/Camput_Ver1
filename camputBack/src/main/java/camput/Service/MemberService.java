package camput.Service;

import camput.Dto.FindIdDto;
import camput.Dto.FindPwDto;
import camput.Dto.LoginDto;
import camput.Dto.MemberInfoDto;
import camput.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberService {
    MemberInfoDto findMemberInfo(Long Id);
    String findMemberNickName(Long memberId);
    String findMemberByPw(String memberPw);
    //List<MemberBooked> findBookedList()

    // 로그인 및 세션
    // 로그인 아이디 있는지? 없는지?
    boolean loginIsValid(String memberLoginId, String memberPassword);
    final String LOGIN_MEMBER = "loginMember";
    List<Member> findAll();
    static Map<Long, Member> store = new HashMap<>();

    // 아이디 찾기
    FindIdDto findByNickNameAndPhoneNumber(FindIdDto findIdDto);
    // 아이디 인증
    boolean checkMemberLoginId(String memberLoginId);
<<<<<<< Updated upstream
=======
<<<<<<< HEAD

    MemberPointDto memberPoint(String asd123,String price);
=======
>>>>>>> parent of cb9ccbe (0124)
>>>>>>> Stashed changes
}
