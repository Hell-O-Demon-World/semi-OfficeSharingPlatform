package com.golfzonaca.officesharingplatform.web.auth.form;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.web.auth.form.prefertype.PreferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpSaveForm {
    @NotNull(message = "이메일은 Null일 수 없습니다.")
    @Email(message = "이메일 형식을 지켜주세요.")
    @Size(max = 32,message = "이메일 길이는 최대 32자 이내 입니다.")
    private String email;
    @NotBlank(message = "비밀번호에 빈 문자나 공백 또는 Null 값이 들어갈 수 없습니다.")
    @Size(min = 8, max = 15, message = "비밀번호의 길이는 8 ~ 15 자리여야 합니다.")
    private String password;
    @NotBlank(message = "이름에 빈 문자나 공백 또는 Null 값이 들어갈 수 없습니다.")
    @Size(max = 20,message = "이름의 길이는 최대 20자 이내 입니다.")
    private String name;
    @NotBlank(message = "전화번호에 빈 문자나 공백 또는 Null 값이 들어갈 수 없습니다.")
    @Size(max = 22,message = "전화번호의 길이는 '-' 포함 최대 22자 이내 입니다.")
    private String phoneNumber;
    @NotNull(message = "직업은 Null일 수 없습니다.")
    @Size(max = 20,message = "직업명의 길이는 최대 20자 이내 입니다.")
    private String job;
    @NotNull(message = "선호 유형은 Null일 수 없습니다.")
    private List<PreferType> preferType;

    public User toEntity() {

        String changePreferString = getChangePreferString();
        return new User(name, email, password, phoneNumber, job, changePreferString);
    }

    private String getChangePreferString() {
        String changePreferString = "";
        int cnt = 0;
        for (PreferType preferType1: preferType) {
            if (cnt == 0) {
                changePreferString.concat("desk:");
            }
            if (cnt == 1) {
                changePreferString.concat("meetingroom:");
            }
            if (cnt == 2) {
                changePreferString.concat("office:");
            }
            changePreferString.concat(preferType1.toString()+"&");
            cnt++;
        }
        return changePreferString;
    }
}
