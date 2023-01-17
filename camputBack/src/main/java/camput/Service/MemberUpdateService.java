package camput.Service;

import camput.Dto.MemberJoinDto;

public interface MemberUpdateService {

    MemberJoinDto view(MemberJoinDto memberJoinDto);
    String update(MemberJoinDto memberJoinDto);
}
