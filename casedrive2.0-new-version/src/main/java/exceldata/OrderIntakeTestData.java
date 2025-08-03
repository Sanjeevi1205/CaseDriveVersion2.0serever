package exceldata;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class OrderIntakeTestData {
    private String username;
    private String password;


    public String toString() {
        return "OrderIntakeTestData{" +
                "username=" + username +
                ", password=" + password +
                '}';
    }
}
