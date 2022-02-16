import java.time.ZonedDateTime;

/**
 * @author zhy
 * @create 2022-02-16 5:42 PM
 * 2022-02-16T17:43:27.502+08:00[Asia/Shanghai]
 * 找出时间，可以给gateway配置
 */
public class T2 {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
