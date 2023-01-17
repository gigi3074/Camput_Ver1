package camput.Impl;

import camput.Dto.MyPageCampDto;
import camput.Service.MyPageLikeService;
import camput.domain.MemberLiked;
import camput.repository.MemberLikedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageLikeImpl implements MyPageLikeService {
    private final MemberLikedRepository memberLikedRepository;
    @Override
    public Page<MyPageCampDto> likeCamps(Long memberId, Pageable pageable) {
        int page =(pageable.getPageNumber()==0)?0:(pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 3);
        Page<MemberLiked> likeCamp = memberLikedRepository.findByMember_Id(memberId, pageable);
        Page<MyPageCampDto> likeCampDto= likeCamp.map(camp->
            MyPageCampDto.builder()
                    .myPageCampName(camp.getLikedCampName())
                    .myPageCampAddress(camp.getLikedCampAddress())
                    .myPageCampImageUrl(camp.getLikedCampImageUrl())
                    .build());
        return likeCampDto;
    }
}
