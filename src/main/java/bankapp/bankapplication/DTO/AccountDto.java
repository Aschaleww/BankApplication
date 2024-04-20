package bankapp.bankapplication.DTO;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String account;
    private double balance;
}
