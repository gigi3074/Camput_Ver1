package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageCampDto {
    private String myPageCampName;
    private String myPageCampAddress;
    private String myPageCampDay;
    private String myPageCampStartDay;
    private String myPageCampEndDay;
    private String myPageCampImageUrl;

    @Builder
    public MyPageCampDto(String myPageCampName, String myPageCampAddress, String myPageCampDay, String myPageCampStartDay, String myPageCampEndDay, String myPageCampImageUrl) {
        this.myPageCampName = myPageCampName;
        this.myPageCampAddress = myPageCampAddress;
        this.myPageCampDay = myPageCampDay;
        this.myPageCampStartDay = myPageCampStartDay;
        this.myPageCampEndDay = myPageCampEndDay;
        this.myPageCampImageUrl = myPageCampImageUrl;
    }
}