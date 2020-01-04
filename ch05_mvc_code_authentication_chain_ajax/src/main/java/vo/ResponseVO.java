package vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 挥霍的人生
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseVO {
    private String code;
    private String msg;
    private Object data;
}
